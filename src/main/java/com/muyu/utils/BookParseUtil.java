package com.muyu.utils;

import com.muyu.pojo.Book;
import com.muyu.pojo.BookList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BookParseUtil {

	/**
	 * 爬取每一章的章节名称，以及每一章的链接
	 * @return
	 */
	public Book obtainBook(){
		Book book = new Book();
		//书籍章节地址
		String url="http://www.xbiquge.la/56/56564/";
		//indexOf方法 查询从7位置后出现的/的索引，用于与下方的a标签中的相对路径拼接
		String subUrl=url.substring(0,url.indexOf("/",7));
		try {
			//使用Jsoup获取Document对象
			Document document = Jsoup.parse(new URL(url),30000);
			//获取document中的各项元素
			Element list = document.getElementById("list");
			Element info = document.getElementById("info");
			String bookName = info.getElementsByTag("h1").eq(0).text();
			String author = info.getElementsByTag("p").eq(0).text();
			String authorSub = author.substring(author.indexOf("：")+1);
			Elements aTags = list.getElementsByTag("a");
			Element fmimg = document.getElementById("fmimg");
			String img = fmimg.getElementsByTag("img").eq(0).attr("src");
			//创建一个存章节名称的对象
			List<BookList> bookList = new ArrayList<BookList>();
			//遍历将数据存入BookList
			for (Element a : aTags) {
				BookList b = new BookList();
				String sectionHref=subUrl+a.attr("href");
				b.setSectionHref(sectionHref);
				b.setSectionName(a.text());
				b.setSectionContent(obtainBookContent(sectionHref));
				bookList.add(b);
			}
			//存入对象
			book.setBookName(bookName);
			book.setAuthor(authorSub);
			book.setImg(img);
			book.setBookList(bookList);
		} catch (Exception e) {
			try {
				System.out.println("服务器过载，休息10秒！");
				Thread.sleep(10000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return book;
	}
	//查询书籍内容
	public static String obtainBookContent(String url){
		String content="";
		try {
			Document document = Jsoup.parse(new URL(url),30000);
			String str = document.getElementById("content").text();
			String baseContent = Jsoup.clean(str, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
			//增加换行
			String newText = baseContent.replaceAll("\\s+?", "\n");
			//去掉收尾多余的空格 再替换空格  .replaceFirst("\n", "").trim()
			content = newText.replaceAll("&nbsp;","");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

	//写入文件
	public void printTxt(Book book){
		PrintWriter printWriter = null;
		if(book!=null) {
			try {
				printWriter = new PrintWriter(new FileWriter(new File("D:/" + book.getBookName() + ".txt")));
				printWriter.print(book.getBookName()+"\n");
				printWriter.printf("作者："+book.getAuthor()+"\n\n");
				for (BookList bookList : book.getBookList()) {
					printWriter.printf(bookList.getSectionName()+"\n\n");
					printWriter.printf(bookList.getSectionContent());
				}
				System.out.println("已将小说写入D盘中，名字为："+ book.getBookName() + ".txt");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
					printWriter.close();
			}
		}
	}
}
