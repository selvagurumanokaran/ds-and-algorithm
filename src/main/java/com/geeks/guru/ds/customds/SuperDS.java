package com.geeks.guru.ds.customds;

/* Below operations should be done in O(1) */
public interface SuperDS {

	public void insert(int x);

	public void remove(int x);

	public int search(int x);

	public int getRandom();
}
