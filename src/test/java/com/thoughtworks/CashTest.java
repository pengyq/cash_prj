package com.thoughtworks;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Test;

public class CashTest {

//	有两个商品参与95折优惠
//	***<没钱赚商店>购物清单***
//	名称：鼠标，数量：3 个，单价：100.50 (元)，小计：301.50 (元)
//	名称：苹果，数量：2 斤，单价：5.50 (元)，小计：11.00 (元)
//	名称：可口可乐，数量：4 瓶，单价：3.00(元)，小计：11.40 (元),节省 0.60 (元)
//	名称：羽毛球，数量：3 个，单价：1.00(元)，小计：2.85 (元),节省 0.15 (元)
//	----------------------
//	总计：326.75(元)
//	节省：0.75(元)
//	**********************
	@Test
	public void testOnlyDiscount() throws IOException {
		System.err.println("\n\n有两个商品参与95折优惠");
		Cash c=new Cash();
		c.addDiscount_id("ITEM000001");
		c.addDiscount_id("ITEM000002");
		String pay_json="["
				+"    'ITEM000001',"
				+"    'ITEM000001',"
				+"    'ITEM000001',"
				+"    'ITEM000001',"
				+"    'ITEM000002',"
				+"    'ITEM000002',"
				+"    'ITEM000002',"
				+"    'ITEM000003-2',"
				+"    'ITEM000005',"
				+"    'ITEM000005',"
				+"    'ITEM000005'"
				+"]";
		System.out.println(c.getPayList(pay_json));
		fail("Error");
	}

	
//	没有任何优惠
//	***<没钱赚商店>购物清单***
//	名称：鼠标，数量：3 个，单价：100.50 (元)，小计：301.50 (元)
//	名称：苹果，数量：2 斤，单价：5.50 (元)，小计：11.00 (元)
//	名称：可口可乐，数量：4 瓶，单价：3.00 (元)，小计：12.00 (元)
//	名称：羽毛球，数量：3 个，单价：1.00 (元)，小计：3.00 (元)
//	----------------------
//	总计：327.50(元)
//	**********************
	@Test
	public void testNothing() throws IOException {
		System.err.println("\n\n没有任何优惠");
		Cash c=new Cash();
		String pay_json="["
				+"    'ITEM000001',"
				+"    'ITEM000001',"
				+"    'ITEM000001',"
				+"    'ITEM000001',"
				+"    'ITEM000002',"
				+"    'ITEM000002',"
				+"    'ITEM000002',"
				+"    'ITEM000003-2',"
				+"    'ITEM000005',"
				+"    'ITEM000005',"
				+"    'ITEM000005'"
				+"]";
		System.out.println(c.getPayList(pay_json));
		fail("Error");
	}
	
//	只有买二赠一的
//	***<没钱赚商店>购物清单***
//	名称：鼠标，数量：3 个，单价：100.50 (元)，小计：301.50 (元)
//	名称：苹果，数量：2 斤，单价：5.50 (元)，小计：11.00 (元)
//	名称：可口可乐，数量：4 瓶，单价：3.00(元)，小计：6.00 (元)
//	名称：羽毛球，数量：3 个，单价：1.00(元)，小计：2.00 (元)
//	----------------------
//	买二赠一商品：
//	名称：可口可乐，数量：2 瓶
//	名称：羽毛球，数量：1 个
//	----------------------
//	总计：320.50(元)
//	节省：7.00(元)
//	**********************
	@Test
	public void testOnlyTFF() throws IOException {
		System.err.println("\n\n只有买二赠一的");
		
		Cash c=new Cash();
		c.addThreeForFree_id("ITEM000001");
		c.addThreeForFree_id("ITEM000002");
		String pay_json="["
				+"    'ITEM000001',"
				+"    'ITEM000001',"
				+"    'ITEM000001',"
				+"    'ITEM000001',"
				+"    'ITEM000002',"
				+"    'ITEM000002',"
				+"    'ITEM000002',"
				+"    'ITEM000003-2',"
				+"    'ITEM000005',"
				+"    'ITEM000005',"
				+"    'ITEM000005'"
				+"]";
		System.out.println(c.getPayList(pay_json));
		fail("Error");
	}
	
//	有商品同时参与两种优惠
//	***<没钱赚商店>购物清单***
//	名称：鼠标，数量：3 个，单价：100.50 (元)，小计：301.50 (元)
//	名称：苹果，数量：2 斤，单价：5.50 (元)，小计：11.00 (元)
//	名称：可口可乐，数量：4 瓶，单价：3.00(元)，小计：11.40 (元),节省 0.60 (元)
//	名称：羽毛球，数量：3 个，单价：1.00(元)，小计：2.00 (元)
//	----------------------
//	买二赠一商品：
//	名称：羽毛球，数量：1 个
//	----------------------
//	总计：325.90(元)
//	节省：1.60(元)
//	**********************
	@Test
	public void testBoth() throws IOException {
		System.err.println("\n\n有商品同时参与两种优惠");
		Cash c=new Cash();
		c.addDiscount_id("ITEM000001");
		c.addDiscount_id("ITEM000002");
		
		c.addThreeForFree_id("ITEM000002");
		String pay_json="["
				+"    'ITEM000001',"
				+"    'ITEM000001',"
				+"    'ITEM000001',"
				+"    'ITEM000001',"
				+"    'ITEM000002',"
				+"    'ITEM000002',"
				+"    'ITEM000002',"
				+"    'ITEM000003-2',"
				+"    'ITEM000005',"
				+"    'ITEM000005',"
				+"    'ITEM000005'"
				+"]";
		System.out.println(c.getPayList(pay_json));
		fail("Error");
	}
	
	

}
