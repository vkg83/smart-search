package com.vkg.catalog.web;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.vkg.catalog.model.EnumFixture;
import com.vkg.catalog.model.Product;
import com.vkg.catalog.model.ProductFixture;
import com.vkg.catalog.model.ProductType;
import com.vkg.catalog.service.CatalogService;

public class ProductControllerTest {
	@InjectMocks private ProductController unit;
	@Mock CatalogService catalogService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAdd() {
		Product p = ProductFixture.createRandomProduct();
		unit.add(p);
		Mockito.verify(catalogService).add(p);
	}

	@Test
	public void testDelete() {
		int productId = RandomUtils.nextInt();
		unit.delete(productId);
		Mockito.verify(catalogService).delete(productId);
	}

	@Test
	public void testFilter() {
		ProductType type = EnumFixture.randomEnum(ProductType.class);
		unit.find(type);
		Mockito.verify(catalogService).findOfType(type);
	}

	@Test
	public void testHandler() {
		String msg = RandomStringUtils.randomAlphanumeric(10);
		ErrorResponse res = unit.exHandler(new IllegalArgumentException(msg));
		Assert.assertEquals(msg, res.getMessage());
		Assert.assertTrue(res.isError());
	}
}
