package com.muyu.pojo;

import java.util.List;

public class Book {
	private String bookName;
	private String author;
	private String img;
	private List<BookList> bookList;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public List<BookList> getBookList() {
		return bookList;
	}

	public void setBookList(List<BookList> bookList) {
		this.bookList = bookList;
	}

	@Override
	public String toString() {
		return "Book{" +
				"bookName='" + bookName + '\'' +
				", author='" + author + '\'' +
				", img='" + img + '\'' +
				", bookList=" + bookList +
				'}';
	}
}
