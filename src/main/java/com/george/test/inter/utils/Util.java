package com.george.test.inter.utils;

import java.util.List;
import java.util.Map.Entry;

import com.george.test.inter.chache.InMemoryCache;
import com.george.test.inter.domain.SingleDigit;

public class Util {
	public static Util unique = new Util();
	
	private Util() {
		
	}
	
	public static synchronized Util getUnique() {
		return unique;
	}
	
	public void addInCache(SingleDigit singleDigit) {
		InMemoryCache cache = InMemoryCache.getUnique();
		
		// check if single digit exists in cache
		if (cache.get(singleDigit.getDigit().toString()) == null) {
			// if not, set single digit
			singleDigit.sumDigit();
			
			// check if cache size is between 0 and 10
			if(cache.size() >= 0 && cache.size() <= 10) {
				
				//check if cache size is equal to 10
				if (cache.size() == 10) {
					// build a ArrayList
					List<Entry<String, Object>> list = cache.buildList();
					// get first item in cache and parse to an array of string (the list is inverted)
					String[] results = list.get(list.size() - 1).toString().split("=");
					
					// remove the first item from cache
					cache.remove(results[0]);
					
					// add new item
					cache.add(singleDigit.getDigit().toString(), singleDigit.getResult());
				} else {
					// if cache is not equal to 10 add new item
					cache.add(singleDigit.getDigit().toString(), singleDigit.getResult());
				}
			}
		} else {
			 singleDigit.setResult((Integer) cache.get(singleDigit.getDigit().toString()));
		}
	}
}
