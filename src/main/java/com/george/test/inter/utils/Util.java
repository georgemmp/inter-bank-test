package com.george.test.inter.utils;

import java.util.List;
import java.util.Map.Entry;

import com.george.test.inter.chache.InMemoryCache;
import com.george.test.inter.domain.SingleDigit;

public class Util {
	
	public static void addInCache(SingleDigit singleDigit) {
//		InMemoryCache cache = new InMemoryCache();
		
		// check if single digit exists in cache
		if (InMemoryCache.get(singleDigit.getDigit().toString()) == null) {
			// if not, set single digit
			singleDigit.sumDigit();
			
			// check if cache size is between 0 and 10
			if(InMemoryCache.size() >= 0 && InMemoryCache.size() <= 10) {
				
				//check if cache size is equal to 10
				if (InMemoryCache.size() == 10) {
					// build a ArrayList
					List<Entry<String, Object>> list = InMemoryCache.buildList();
					// get first item in cache and parse to an array of string (the list is inverted)
					String[] results = list.get(list.size() - 1).toString().split("=");
					
					// remove the first item from cache
					InMemoryCache.remove(results[0]);
					
					// add new item
					InMemoryCache.add(singleDigit.getDigit().toString(), singleDigit.getResult());
				} else {
					// if cache is not equal to 10 add new item
					InMemoryCache.add(singleDigit.getDigit().toString(), singleDigit.getResult());
				}
			}
		} else {
			 singleDigit.setResult((Integer) InMemoryCache.get(singleDigit.getDigit().toString()));
		}
	}
}
