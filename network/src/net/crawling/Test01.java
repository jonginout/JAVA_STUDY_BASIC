package net.crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Test01 {
	public static void main(String[] args) {
		
		String html = "";
		html += "<html>";
		html += "	<head>";
		html += "		<title>연습 제이숲</title>";
		html += "	</head>";
		html += "	<body>";
		html += "		<p>파싱 html</p>";
		html += "		<p id='a'>id로 접근</p>";
		html += "		<p class='c'>class로 접근</p>";
		html += "	</body>";
		html += "</html>";
		
		
		Document doc = Jsoup.parse(html);
		Elements ele = doc.select("p#a");
		System.out.println(ele.size());
		
		for (Element e : ele) {
			System.out.println(e);
		}
		
	}
}
