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
	private static AtomicInteger counter_integer = new AtomicInteger(0);

	public static Map<String, Goods> getGoodsList() throws IOException {
		if (null == g_lists) {
			g_lists = new HashMap<String, Goods>();
			File list_file = new File(GOODS_LIST);
			if (!(list_file.exists() && list_file.isFile())) {
				System.err.println("[Error]No file found " + GOODS_LIST
						+ " \ninit goods list files...");
				initGoodsList();
				System.err.println(" \ninit goods list files finished...");
			}
			for (String line : FileUtils.readLines(list_file, "UTF-8")) {
				try {
					String[] props = line.split("\\|");
					String id = props[0], name = props[1], unit = props[2], category = props[3];
					double price=Double.valueOf(props[4]);
					Goods g = new Goods(id, name, unit, category, price);
					g_lists.put(id, g);
				} catch (Exception e) {
					System.err.println("goods list config error:"+line);
				}
			}

		}
		return g_lists;
	}

	/**
	 * generate increment ID
	 * 
	 * @return bulk goods id like BULK000001
	 */
	public static String bulkGoodsIDGet() {

		return String.format("BULK%06d", counter_integer.incrementAndGet());
	}

	private static void initGoodsList() throws IOException {
		File list_file = new File(GOODS_LIST);
		List<String> contents = new ArrayList<String>();
		contents.add("ITEM000001|可口可乐|瓶|饮料|3.00");
		contents.add("ITEM000002|羽毛球|个|健身器材|1.00");
		contents.add("ITEM000003|苹果|斤|水果|5.50");
		contents.add("ITEM000004|键盘|个|电器|227.6");
		contents.add("ITEM000005|鼠标|个|电器|100.5");
		FileUtils.writeLines(list_file, contents);
	}

}
