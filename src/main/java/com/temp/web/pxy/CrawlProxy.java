package com.temp.web.pxy;

import java.util.HashMap;
import java.util.regex.Pattern;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component("crawl") @Lazy
public class CrawlProxy extends Proxy{
	@Autowired Inventory<HashMap<?,?>> l;
	@Autowired Proxy pxy;
	@Autowired Json<?> m;
	
	public Inventory<?> crawlSelect(String domain) {
		System.out.println("크롤셀렉트 : "+domain);
		pxy.pt(domain+"\n");
		l.clear();
		switch (domain) {
		case "bugs": return crawlBugs("https://music.bugs.co.kr/chart");
		case "naver": return crawlNaver("https://endic.naver.com/rank.nhn?sLn=kr&pubLev=2&firstWord=all&posp=%EB%AA%85%EC%82%AC&pageNo=1");
		case "cgv": return crawlCgv("http://www.cgv.co.kr/movies/?lt=1");
		/*case "Stadium": return crawlFut("https://map.naver.com/v5/api/search?caller=pcweb&query=풋살%20경기장&type=all&isPlaceRecommendationReplace=true&lang=ko&"+
						"page=1&displayCount=100");*/
		default:
			break;
		}
		return null;
	}
	
	public Inventory<?> crawlBugs(String url){
	        try {
	        	pxy.pt("크롤링 url : "+url+"\n");
	            Document rawData = Jsoup.connect(url).timeout(10 * 1000).get();
	            Elements rank = rawData.select("div[class=\"ranking\"] strong");
	            Elements artist = rawData.select("p[class=\"artist\"] a");
	            Elements title = rawData.select("p[class=\"title\"] a");
	            Elements img = rawData.select("a[class=\"thumbnail\"] img");
	            for(int i = 0 ; i < rank.size(); i++) {
	            	HashMap<String, String> xx = new HashMap<>();
	            	xx.put("a", rank.get(i).text());
	            	xx.put("b", title.get(i).text());
	            	xx.put("c", artist.get(i).text());
	            	xx.put("img", img.get(i).attr("src"));
	            	l.add(xx);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return l;
	}
	public Inventory<?> crawlNaver(String url){
		try {
			pxy.pt("크롤링 url : "+url+"\n");
			Document rawData = Jsoup.connect(url).timeout(10 * 1000).get();
			Elements origin = rawData.select("div[class=\"w_baseInfo_en\"] a span");
			Elements text = rawData.select("td[class=\"f_con\"]");
			for(int i = 0 ; i < origin.size(); i++) {
				HashMap<String, String> xx = new HashMap<>();
				xx.put("a", origin.get(i).text());
				xx.put("b", text.get(i).text());
				l.add(xx);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}
	public Inventory<?> crawlCgv(String url){
		try {
			pxy.pt("크롤링 url : "+url+"\n");
			Document rawData = Jsoup.connect(url).timeout(10 * 1000).get();
			Elements rank = rawData.select("div strong[class=\"rank\"]");
			Elements artist = rawData.select("a strong[class=\"title\"]");
			Elements image = rawData.select("span.thumb-image");
			Elements percent = rawData.select("span.percent");
			System.out.println(rank.size());
			for(int i = 0 ; i < rank.size(); i++) {
				HashMap<String, String> xx = new HashMap<>();
				xx.put("a", rank.get(i).text());
				xx.put("b", artist.get(i).text());
				xx.put("c", percent.get(i).text());
				xx.put("img", image.get(i).select("img").attr("src"));
				l.add(xx);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}
	/*public Inventory<?> crawlFut(String url){
		try {
			pxy.pt("크롤링 url : "+url+"\n");
			Document rawData = Jsoup.connect(url).timeout(10 * 1000).ignoreContentType(true).get();
			JSONParser jp = new JSONParser();
			JSONArray arr = (JSONArray)jp.parse(rawData.text());
			
			for(int i = 0 ; i < arr.size(); i++) {
				HashMap<String, String> xx = new HashMap<>();
				JSONObject temp = (JSONObject) arr.get(i);
				System.out.println("json이란다 : "+temp.get("name"));
				xx.put("a",(String) temp.get("name"));
				l.add(xx);
			}
			PatternParser yy = new PatternParser("name\":\"(.*?)\\\".*?\\\"tel\":\"(.*?)\"");
			Elements xx = rawData.getElementsMatchingOwnText(yy);
			//System.out.println(xx.
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}*/
}
