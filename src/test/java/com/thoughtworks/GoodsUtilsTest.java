package com.thoughtworks;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.model.Goods;
import com.thoughtworks.utils.GoodsUtils;

public class GoodsUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetGoodsList() throws IOException {
		Map<String, Goods> goos_list = GoodsUtils.getGoodsList();
		for (String id : GoodsUtils.getGoodsList().keySet()) {
			System.out.println(goos_list.get(id));
		}
		fail("Not yet implemented");
	}

}
