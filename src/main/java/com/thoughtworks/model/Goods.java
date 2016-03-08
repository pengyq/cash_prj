package com.thoughtworks.model;

public class Goods {

	// Commodity bar code
	private String id;
	// goods unit default 个
	private String unit = "个";
	// goods name
	private String name;
	// goods category -1=unknow
	private int category = -1;

	public Goods(String id, String name, String unit, int category) {
		this.id = id;
		this.unit = unit;
		this.name = name;
		this.category = category;
	}

	public Goods(String id, String name, String unit) {
		super();
		this.id = id;
		this.unit = unit;
		this.name = name;
		this.category = -1;
	}

	public Goods(String id, String name) {
		super();
		this.id = id;
		this.unit = "个";
		this.name = name;
		this.category = -1;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", unit=" + unit + ", name=" + name + ", category=" + category + "]";
	}

}
