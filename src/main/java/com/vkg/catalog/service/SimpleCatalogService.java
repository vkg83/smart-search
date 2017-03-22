package com.vkg.catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vkg.catalog.model.Product;
import com.vkg.catalog.model.ProductType;
import com.vkg.catalog.repo.CatalogDao;

@Service
public class SimpleCatalogService implements CatalogService {
	@Autowired CatalogDao catalogDao;
	
	public void add(Product p) {
		catalogDao.add(p);
	}

	public List<Product> findOfType(ProductType type) {
		return catalogDao.find(type);
	}

	public void delete(int productId) {
		Product p = new Product();
		p.setId(productId);
		catalogDao.remove(p);
	}

	@Override
	public Product find(int productId) {
		Product p = new Product();
		p.setId(productId);
		return catalogDao.find(p);
	}
}
