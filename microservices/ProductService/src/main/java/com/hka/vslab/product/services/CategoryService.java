package com.hka.vslab.product.services;

import com.hka.vslab.product.model.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "category", url = "${category.ribbon.listOfServers}")
public interface CategoryService {
    @RequestMapping(method = RequestMethod.GET, value = "/categories")
    List<Category> getCategories();

    @RequestMapping(method = RequestMethod.GET, value = "/categories/{id}", produces = "application/json")
    Category getCategory(@PathVariable("id") int id);

    @RequestMapping(method = RequestMethod.GET, value = "/categories/name/{name}", produces = "application/json")
    Category getCategoryByName(@PathVariable("name") String name);
}
