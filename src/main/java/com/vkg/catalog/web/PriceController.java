package com.vkg.catalog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.vkg.catalog.model.Product;
import com.vkg.catalog.service.CatalogService;

@Controller
@RequestMapping("/price")
public class PriceController {
	@Autowired CatalogService catalogService;
	
	@RequestMapping(value="/{productId}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Double findPrice(@PathVariable int productId) {
		Product p = catalogService.find(productId);
		return p.getPrice();
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse exHandler(IllegalArgumentException ex) {
		return new ErrorResponse(ex.getMessage());
	}
}
