package com.vkg.catalog.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.vkg.catalog.model.Product;
import com.vkg.catalog.model.ProductType;
import com.vkg.catalog.service.CatalogService;

@Controller
@RequestMapping("/product")
public class ProductController { 
	@Autowired CatalogService catalogService;
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Integer add(@RequestBody Product p) {
		catalogService.add(p);
		return p.getId();
	}

	@RequestMapping(method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Product> find(@RequestParam ProductType type) {
		return catalogService.findOfType(type);
	}

	@RequestMapping(value="/{productId}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable int productId) {
		catalogService.delete(productId);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse exHandler(IllegalArgumentException ex) {
		return new ErrorResponse(ex.getMessage());
	}
}
