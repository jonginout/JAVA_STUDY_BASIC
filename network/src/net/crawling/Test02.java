package net.crawling;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Test02 {
	public static void main(String[] args) throws Exception {
		
		String url = "http://www.fmkorea.com";		

		Document doc = Jsoup.connect(url).get(); //get 방식
		

		Elements ele = doc.select(
				".fm_best_widget > ul > .li"
				);

		System.out.println(ele);
		
		for (Element e : ele) {
			String title = e.select(".title>a").html();
			String link = e.select(".title>a").attr("href");
			System.out.println(title + "-" + link);
		}
		
		//펨코 포텐 대문글 파싱
		
	}
}
