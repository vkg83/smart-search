package com.vkg.catalog.service;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.vkg.catalog.model.EnumFixture;
import com.vkg.catalog.model.Product;
import com.vkg.catalog.model.ProductFixture;
import com.vkg.catalog.model.ProductType;
import com.vkg.catalog.repo.CatalogDao;

public class SimpleCatalogServiceTest {
	@InjectMocks private SimpleCatalogService unit;
	@Mock private CatalogDao catalogDao;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAdd() {
		Product p = ProductFixture.createRandomProduct();
		unit.add(p);
		Mockito.verify(catalogDao).add(p);
	}

	@Test
	public void testFindByType() {
		ProductType type = EnumFixture.randomEnum(ProductType.class);
		Mockito.when(catalogDao.find(type)).thenReturn(Arrays.asList(ProductFixture.createRandomProduct()));
		List<Product> list = unit.findOfType(type);
		Assert.assertEquals(1, list.size());
	}
	
	@Test
	public void testFindById() {
		Integer productId = RandomUtils.nextInt();
		Product p = ProductFixture.createRandomProduct();
		Mockito.when(catalogDao.find(Mockito.any(Product.class))).thenReturn(p);
		Product result = unit.find(productId);
		ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
		Mockito.verify(catalogDao).find(captor.capture());
		Product key = captor.getValue();
		Assert.assertEquals(productId, key.getId());
		ProductFixture.compare(p, result);
	}

	@Test
	public void testDeleteById() {
		Integer productId = RandomUtils.nextInt();
		unit.delete(productId);
		ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
		Mockito.verify(catalogDao).remove(captor.capture());
		Product key = captor.getValue();
		Assert.assertEquals(productId, key.getId());
	}
}
