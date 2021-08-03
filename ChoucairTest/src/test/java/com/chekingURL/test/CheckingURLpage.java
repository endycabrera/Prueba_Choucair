package com.chekingURL.test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckingURLpage {
	
	private WebDriver driver;
	
	public CheckingURLpage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean checkingPageURL() {
		List<WebElement> link = driver.findElements(By.tagName("a"));
		String url = "";
		List<String> failslink = new ArrayList<String>();
		List<String> oklink = new ArrayList<String>();
	
		HttpURLConnection httpConect = null;
		int responsecode  = 200;
		Iterator<WebElement> it = link.iterator();
		
		while (it.hasNext()) {
			url = it.next().getAttribute("href");
			if (url==null || url.isEmpty()) {
				System.out.print(url + "url no esta configurada o esta vacia");
				continue;
			}
			
			try {
				httpConect = (HttpURLConnection)(new URL(url).openConnection());
				httpConect.setRequestMethod("HEAD");
				httpConect.connect();
				responsecode =  httpConect.getResponseCode();
				
				if (responsecode >400) {
					System.out.print("Error en el link: -- " + url);
					failslink.add(url);
					
				} else {
					System.out.print("Link Valido: -- " + url);
					oklink.add(url);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			
		}
		
		System.out.print("Links validos : "+ oklink.size());
		System.out.print("Links invalidos : "+ failslink.size());
		
		if (failslink.size()>0) {
			System.out.println("****ERROR ----------------- Links Fails");	
			for (int i = 0; i < failslink.size(); i++) {
				System.out.println(failslink.get(i));
			}
			return false;
		} else {
		
			return true;
		}
	}

}
