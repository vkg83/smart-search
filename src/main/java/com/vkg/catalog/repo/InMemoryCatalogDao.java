package com.vkg.catalog.repo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.vkg.catalog.model.Product;
import com.vkg.catalog.model.ProductType;

@Repository
public class InMemoryCatalogDao implements CatalogDao {
	private Set<Product> products = new HashSet<Product>();
	int nextId = 0;
	public void add(Product p) {
		if(products.contains(p)) {
			throw new IllegalArgumentException("Product already exists" + p);
		}
			
		p.setId(getNextId());
		products.add(p);
	}

	private Integer getNextId() {
		return ++nextId;
	}

	public List<Product> find(ProductType type) {
		return products.stream()
				.filter(p -> p.getType() == type)
				.collect(Collectors.toList());
	}

	public void remove(Product product) {
		if(!products.removeIf(p -> p.equals(product))) {
			throw new IllegalArgumentException("Product not exists");
		}
	}

	@Override
	public Product find(Product product) {
		return products.stream().filter(p -> p.equals(product)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Product not Found" + product));
	}
	
	public List<Product> getAll() {
		return new ArrayList<Product>(products);
	}
}
