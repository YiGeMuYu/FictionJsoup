#Jsoup
pom.xml
```xml
<!--jsoup解析网页-->
        <!--如果想爬一些电影音乐，可以学tika-->
        <dependency>
            <groupId>org.jsoup</groupId>
            <artifactId>jsoup</artifactId>
            <version>1.10.2</version>
        </dependency>
```


```java
public List<Content> queryKey(String key){
		List<Content> list = new ArrayList<Content>();
		//获得请求   https://search.jd.com/Search?keyword=java
		//前提，需要联网！ ajax获取不到
		String url = "https://search.jd.com/Search?keyword="+key;
		//解析网页 Jsoup返回Document就是浏览器Document对象
		Document document = null;
		try {
			document = Jsoup.parse(new URL(url), 30000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//所有你在js中可以使用的方法这里都可以使用
		Element element = document.getElementById("J_goodsList");
		Elements lis = element.getElementsByTag("li");
		for (Element li : lis) {
			//关于图片特别多的网站，所有的图片都是延迟加载的
			//放在了source-data-lazy-img中
			String img = li.getElementsByTag("img").eq(0).attr("data-lazy-img");
			String price = li.getElementsByClass("p-price").text();
			String name = li.getElementsByClass("p-name").text();
			Content content = new Content();
			content.setImg(img);
			content.setName(name);
			content.setPrice(price);
			list.add(content);
		}
		return list;
	}
```