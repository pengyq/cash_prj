package com.thoughtworks.model;

public class Goods {

	// Commodity bar code
	private String id;

	public String getId() {
		return id;
	}

	public String getUnit() {
		return unit;
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	public double getPrice() {
		return price;
	}

	// goods unit default 个
	private String unit = "个";
	// goods name
	private String name;
	// goods category
	private String category = "UNKNOW";

	// goods price
	private double price;

	public Goods(String id, String name, String unit, String category,
			double price) {
		this.id = id;
		this.unit = unit;
		this.name = name;
		this.category = category;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", unit=" + unit + ", name=" + name
				+ ", category=" + category + ", price=" + price + "]";
	}

	public double cal(int cnt) {
		return cnt * price;

	}

}
