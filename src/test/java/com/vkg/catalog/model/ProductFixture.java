package com.vkg.catalog.model;

import org.junit.Assert;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

import com.vkg.catalog.model.Product;
import com.vkg.catalog.model.ProductType;

public class ProductFixture {
	public static Product createRandomProduct() {
		return createProduct(RandomStringUtils.randomAlphanumeric(5), RandomUtils.nextDouble(), EnumFixture.randomEnum(ProductType.class));
	}

	public static Product createProduct(String name, double price, ProductType type) {
		Product p = new Product();
		p.setName(name);
		p.setPrice(price);
		p.setType(type);
		return p;
	}

	public static void compare(Product p1, Product p2) {
		if(p1 == p2) {
			return;
		}
		if(p1 == null || p2 == null) {
			Assert.fail("Null Object");
		}
		
		Assert.assertEquals(p1.getId(), p1.getId());
		Assert.assertEquals(p1.getName(), p1.getName());
		Assert.assertEquals(p1.getPrice(), p1.getPrice(), 0.0);
		Assert.assertEquals(p1.getType(), p1.getType());	
	}
}