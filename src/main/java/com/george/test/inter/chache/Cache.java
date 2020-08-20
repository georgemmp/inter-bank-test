package com.george.test.inter.chache;

import java.util.List;
import java.util.Map.Entry;

public interface Cache {
	void add(String key, Object value);
	
	void remove(String key);
	
	Object get(String key);
	
	void clear();
	
	long size();
	
	List<Entry<String, Object>> buildList();
}
