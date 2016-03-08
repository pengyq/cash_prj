package com.thoughtworks;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.thoughtworks.model.Goods;
import com.thoughtworks.utils.GoodsUtils;

public class Cash {

	private Set<String> discount_id = new HashSet<String>();
	private Set<String> three_for_free = new HashSet<String>();

	public void addDiscount_id(String id) {
		discount_id.add(id);
	}

	public void addThreeForFree_id(String id) {
		three_for_free.add(id);
	}

	public Map<String, Integer> good_car(String json) {
		JSONArray jsonArr = JSON.parseArray(json);
		Map<String, Integer> good_car = new HashMap<String, Integer>();

		for (Object o : jsonArr) {
			String[] kv = o.toString().split("-");
			String g_id = kv[0];
			int cnt = 1;
			try {
				cnt = kv.length > 1 ? Integer.valueOf(kv[1]) : 1;
			} catch (Exception e) {
				System.out.println("pay lsit data error:" + o.toString());
			}
			if (good_car.containsKey(g_id)) {
				good_car.put(g_id, good_car.get(g_id) + cnt);
			} else {
				good_car.put(g_id, cnt);
			}
		}
		return good_car;
	}

	public String getPayList(String json) throws IOException {
		StringBuilder result_str = new StringBuilder("***<没钱赚商店>购物清单***\n");
		StringBuilder tree_for_free = new StringBuilder();
		double total = 0;
		double discount = 0;
		boolean has_tree_for_free = false;

		Map<String, Integer> car_list = good_car(json);
		for (String g_id : car_list.keySet()) {

			int cnt = car_list.get(g_id);
			Goods g = GoodsUtils.getGoodsList().get(g_id);
			if (three_for_free.contains(g_id)) {
				result_str.append(String.format(
						"名称：%s，数量：%d %s，单价：%.2f(元)，小计：%.2f (元)\n", g.getName(), cnt,
						g.getUnit(), g.getPrice(), g.cal(cnt / 2 + 1)));
				total += g.cal(cnt / 2 + 1);
				if (cnt > 1) {
					if (!has_tree_for_free) {
						tree_for_free
								.append("----------------------\n买二赠一商品：\n");
					}
					tree_for_free.append(String.format("名称：%s，数量：%d %s\n",
							g.getName(), (cnt / 2), g.getUnit()));
					discount += (cnt / 2) * g.getPrice();
				}

			} else if (discount_id.contains(g_id)) {
				result_str.append(String.format(
						"名称：%s，数量：%d %s，单价：%.2f(元)，小计：%.2f (元),节省 %.2f (元)\n",
						g.getName(), cnt, g.getUnit(), g.getPrice(),
						g.cal(cnt) * 0.95, g.cal(cnt) * 0.05));
				total += g.cal(cnt) * 0.95;
				discount += g.cal(cnt) * 0.05;
			} else {
				result_str.append(String.format(
						"名称：%s，数量：%d %s，单价：%.2f (元)，小计：%.2f (元)\n", g.getName(), cnt,
						g.getUnit(), g.getPrice(), g.cal(cnt)));
				total += g.cal(cnt);
			}

		}
		result_str.append(tree_for_free);
		result_str.append("----------------------\n");
		result_str.append(String.format("总计：%.2f(元)\n", total));
		if (discount > 0) {
			result_str.append(String.format("节省：%.2f(元)\n", discount));
		}
		result_str.append("**********************");
		return result_str.toString();

	}

}
