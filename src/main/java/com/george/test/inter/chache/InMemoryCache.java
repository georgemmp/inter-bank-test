package com.george.test.inter.chache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class InMemoryCache {
	
	private static InMemoryCache unique = new InMemoryCache();

	public static final int PERIOD_IN_SEC = 5;

	private final Map<String, Object> cache = new HashMap<>();

	private InMemoryCache() {
		Thread cleanerThread = new Thread(() -> {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					Thread.sleep(PERIOD_IN_SEC * 1000);
				} catch (Exception e) {
					Thread.currentThread().interrupt();
				}
			}
		});
		cleanerThread.setDaemon(true);
		cleanerThread.start();
	}
	
	public static synchronized InMemoryCache getUnique() {
		return unique;
	}

	public void add(String key, Object value) {
		if (key == null) {
			return;
		}

		if (value == null) {
			cache.remove(key);
		} else {
			cache.put(key, value);
		}
	}

	public void remove(String key) {
		cache.remove(key);
	}

	public Object get(String key) {
		return cache.get(key);
	}

	public void clear() {
		cache.clear();
	}

	public long size() {
		return cache.size();
	}

	public List<Entry<String, Object>> buildList() {
		Set<Entry<String, Object>> set = cache.entrySet();
		List<Entry<String, Object>> list = new ArrayList<Entry<String,Object>>(set);
		return list;
	}

}
