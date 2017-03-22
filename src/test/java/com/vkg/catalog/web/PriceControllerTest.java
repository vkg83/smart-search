package com.vkg.catalog.web;

import org.junit.Assert;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.vkg.catalog.model.Product;
import com.vkg.catalog.model.ProductFixture;
import com.vkg.catalog.service.CatalogService;

public class PriceControllerTest {
	@InjectMocks private PriceController unit;
	@Mock private CatalogService catalogService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetPrice() {
		int productId = RandomUtils.nextInt();
		Product p = ProductFixture.createRandomProduct();
		Mockito.when(catalogService.find(productId)).thenReturn(p);
		double price = unit.findPrice(productId);
		Assert.assertEquals(p.getPrice(), price, 0.00);
	}

	@Test
	public void testHandler() {
		String msg = RandomStringUtils.randomAlphanumeric(10);
		ErrorResponse res = unit.exHandler(new IllegalArgumentException(msg));
		Assert.assertEquals(msg, res.getMessage());
		Assert.assertTrue(res.isError());
	}
}
