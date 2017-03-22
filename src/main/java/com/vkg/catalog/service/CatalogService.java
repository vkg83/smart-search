package com.vkg.catalog.service;

import java.util.List;

import com.vkg.catalog.model.Product;
import com.vkg.catalog.model.ProductType;

public interface CatalogService {

	void add(Product p);

	List<Product> findOfType(ProductType type);

	void delete(int productId);

	Product find(int productId); 
}
