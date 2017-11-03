package net.crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test03 {
	public static void main(String[] args) 
			throws Exception {
		Document doc = Jsoup.connect("http://www.naver.com").get();
		System.out.println(doc.html());
		/*
        <ul class="ah_l"> 
         <li class="ah_item"> 
         	<a href="#" class="ah_a" 
         	            data-clk="lve.keyword"> 
         	   <span class="ah_r">1</span> 
         	   <span class="ah_k">녹농균</span> 
         	 </a> 
         </li>
		 */
		Elements ele = doc.select(
				"ul.ah_l > li.ah_item > a[href='#']");
		for (Element e : ele) {
//			System.out.println(e.html());
			String rank = e.select("span.ah_r").html();
			String keyword = e.select("span.ah_k").html();
			System.out.println(rank + "-" + keyword);
		}
	}
}
















