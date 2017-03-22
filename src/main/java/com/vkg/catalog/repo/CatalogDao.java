package com.vkg.catalog.repo;

import java.util.List;

import com.vkg.catalog.model.Product;
import com.vkg.catalog.model.ProductType;

public interface CatalogDao {
	void add(Product p);

	List<Product> find(ProductType type);

	void remove(Product p);

	Product find(Product p);
}
