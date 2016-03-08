package com.thoughtworks.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.FileUtils;

import com.thoughtworks.model.Goods;

public class GoodsUtils {

	private final static String GOODS_LIST = "data/goods_list.txt";

	private static Map<String, Goods> g_lists;
	public static AtomicInteger counter_integer = new AtomicInteger(0);

	public static Map<String, Goods> getGoodsList() throws IOException {
		if (null == g_lists) {
			g_lists = new HashMap<String, Goods>();
			File list_file = new File(GOODS_LIST);
			if (!(list_file.exists() && list_file.isFile())) {
				System.err.println("[Error]No file found " + GOODS_LIST + " \ninit goods list files...");
				initGoodsList();
				System.err.println(" \ninit goods list files finished...");
			}
			for (String line : FileUtils.readLines(new File(GOODS_LIST), "UTF-8")) {
				String[] props = line.split("\\|");
				String id = props[0], name = props[1], unit = props[2];
				int category = Integer.valueOf(props[3]);
				Goods g = new Goods(id, name, unit, category);
				g_lists.put(id, g);
			}

		}
		return g_lists;
	}

	private String bulkGoodsIDGet() {
		return "";
	}

	private static void initGoodsList() throws IOException {
		File list_file = new File(GOODS_LIST);
		List<String> contents = new ArrayList<String>();
		contents.add("ITEM000001|牙刷|支|1");
		contents.add("ITEM000002|牙膏|支|1");
		contents.add("ITEM000003|拖鞋|双|2");
		contents.add("ITEM000004|水杯|个|3");
		contents.add("ITEM000005|篮球|个|4");
		contents.add("ITEM000006|水浒传|本|5");
		FileUtils.writeLines(list_file, contents);
	}

	public static void main(String[] args) {

	}

}
