package com.temp.web.crawl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.temp.web.pxy.CrawlProxy;
import com.temp.web.pxy.Inventory;
import com.temp.web.pxy.Proxy;

@RestController
@RequestMapping("/crawl")
public class CrawlCtrl {
	@Autowired CrawlProxy cp;
	@Autowired Proxy pxy;
	@Autowired Inventory<?> inven;
	@GetMapping("/{domain}")
	public List<?> crawl(@PathVariable String domain){
		pxy.pt("컨트롤러에서 domain : "+domain+"\n");
		inven = cp.crawlSelect(domain);
		System.out.println("컨트롤러에서 domain : "+domain+"\n"+inven.get());
		return inven.get();
	}
	@GetMapping("/{domain}/db")
	public List<?> crawlDb(@PathVariable String domain){
		pxy.pt("컨트롤러에서 domain : "+domain+"\n");
		inven = cp.crawlSelect(domain);
		System.out.println("컨트롤러에서 domain : "+domain+"\n"+inven.get());
		return inven.get();
	}
}
