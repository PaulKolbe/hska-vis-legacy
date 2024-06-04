package com.hka.vs.CategoryService.service;

import com.hka.vs.CategoryService.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "product", url = "${product.ribbon.listOfServers}")
public interface ProductService {
    @RequestMapping(method = RequestMethod.GET, value = "/products")
    List<Product> getProductsByCategory(@RequestParam("categoryId") int id);
}
