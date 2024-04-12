package com.hka.vs.CategoryService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.hka.vs.CategoryService.model.Category;
import com.hka.vs.CategoryService.service.CategoryService;

import com.hka.vs.CategoryService.model.Product;
import com.hka.vs.CategoryService.service.ProductService;

@RestController
public class CategoryServiceController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/categories/{id}")
    public Category getCategory(@PathVariable(required = true, name = "id") int id) {
        return categoryService.getCategory(id);
    }

    @GetMapping("/categories/name/{name}")
    public Category getCategoryByName(@PathVariable(required = true, name = "name") String name) {
        return categoryService.getCategoryByName(name);
    }

    @PostMapping("/categories")
    public void addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
    }

    @DeleteMapping("/categories")
    public void delCategory(@RequestBody Category category) throws RuntimeException {
        List<Product> products = productService.getProductsByCategory(category.getId());

        if (!products.isEmpty()) {
            throw new RuntimeException("Category is not empty");
        }
        categoryService.delCategory(category);
    }

    @DeleteMapping("/categories/{id}")
    public void delCategoryById(@PathVariable(required = true, name = "id") int id) throws RuntimeException {
        List<Product> products = productService.getProductsByCategory(id);

        if (!products.isEmpty()) {
            throw new RuntimeException("Category is not empty");
        }
        categoryService.delCategory(id);
    }

}
