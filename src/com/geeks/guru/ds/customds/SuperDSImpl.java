package com.geeks.guru.ds.customds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SuperDSImpl implements SuperDS {

	Map<Integer, Integer> map;
	List<Integer> list;

	public SuperDSImpl() {
		map = new HashMap<>();
		list = new ArrayList<>();
	}

	@Override
	public void insert(int x) {
		if (!map.containsKey(x)) {
			list.add(x);
			map.put(x, list.size() - 1);
		}
	}

	@Override
	public void remove(int x) {
		Integer li = map.remove(x);
		if (li != null) {
			if (li != list.size() - 1) {
				Integer last = list.get(list.size() - 1);
				Collections.swap(list, li, list.size() - 1);
				map.put(last, li);
			}
			list.remove(list.size() - 1);
		}
	}

	@Override
	public int search(int x) {
		Integer li = map.get(x);
		return li != null ? li.intValue() : -1;
	}

	@Override
	public int getRandom() {
		Random random = new Random();
		int ri = random.nextInt(list.size());
		return list.get(ri);
	}

}
