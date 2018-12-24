package com.geeks.guru.ds.customds;

public class SuperDSTest {

	public static void main(String[] args) {
		SuperDS superDS = new SuperDSImpl();
		superDS.insert(11);
		superDS.insert(22);
		superDS.insert(33);
		superDS.insert(44);
		System.out.println(superDS.getRandom());
		System.out.println(superDS.search(33));
		System.out.println(superDS.getRandom());
		superDS.remove(44);
		superDS.remove(55); // Invalid
		System.out.println(superDS.search(44));
	}
}
