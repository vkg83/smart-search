package com.vkg.catalog.repo;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;
import org.junit.Test;

import com.vkg.catalog.model.EnumFixture;
import com.vkg.catalog.model.Product;
import com.vkg.catalog.model.ProductFixture;
import com.vkg.catalog.model.ProductType;

public class InMemoryCatalogDaoTest {
	private InMemoryCatalogDao unit;
	
	@Before
	public void setup() {
		unit = new InMemoryCatalogDao();
	}

	@Test
	public void testInitialize() {
		assertEquals(0, getCurrentSize());
	}

	@Test
	public void testAdd() {
		int previousSize = getCurrentSize();
		
		Product p = ProductFixture.createRandomProduct();
		unit.add(p);
		
		int newSize = getCurrentSize();
		
		assertEquals(1, newSize - previousSize);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNotAddExisting() {
		Product p = ProductFixture.createRandomProduct();
		unit.add(p);
		unit.add(p);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNotRemoveNonExisting() {
		Product p = ProductFixture.createRandomProduct();
		unit.remove(p);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNotFindNonExisting() {
		Product p = ProductFixture.createRandomProduct();
		unit.find(p);
	}

	@Test
	public void testRemove() {
		for(int i = 0; i < 5; i++) {
			unit.add(ProductFixture.createRandomProduct());
		}
		
		Product product = new Product();
		product.setId(1 + RandomUtils.nextInt(5));
		
		int previousSize = getCurrentSize();
		unit.remove(product);
		int newSize = getCurrentSize();
		assertEquals(1, previousSize - newSize);
	}
	
	@Test
	public void testFindSingleProduct() {
		int productId = 1 + RandomUtils.nextInt(5);
		Product p = null;
		for(int i = 0; i < 5; i++) {
			Product tmp = ProductFixture.createRandomProduct();
			unit.add(tmp);
			if(tmp.getId() == productId) {
				p = tmp; 
			}
		}
		
		Product product = new Product();
		product.setId(productId);
		
		product = unit.find(product);

		assertEquals(p.getId(), product.getId());
		assertEquals(p.getName(), product.getName());
		assertEquals(p.getPrice(), product.getPrice(), 0.00);
		assertEquals(p.getType(), product.getType());
	}
	
	@Test
	public void testFilter() {
		ProductType type = EnumFixture.randomEnum(ProductType.class);
		int typeCount = 0;
		for(int i = 0; i < 5; i++) {
			Product p = ProductFixture.createRandomProduct();
			unit.add(p);
			if(type == p.getType()) {
				typeCount++;
			}
		}
		
		List<Product> list = unit.find(type);

		assertEquals(typeCount, list.size());
	}

	private int getCurrentSize() {
		return unit.getAll().size();
	}
}
