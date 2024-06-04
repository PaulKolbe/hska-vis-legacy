package com.hka.vs.CategoryService.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hka.vs.CategoryService.model.Category;
import com.hka.vs.CategoryService.service.CategoryService;

import com.hka.vs.CategoryService.model.Product;
import com.hka.vs.CategoryService.service.ProductService;

import java.util.Random;

@RestController
public class CategoryServiceController {

    Random random = new Random();
    private int podId = random.nextInt(100);

    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.add("Baeldung-Example-Header",
            "Value-ResponseEntityBuilderWithHttpHeaders");

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(categoryService.getAllCategories());
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
    public void deleteCategory(@RequestBody Category category) throws RuntimeException {
        List<Product> products = productService.getProductsByCategory(category.getId());

        if (!products.isEmpty()) {
            throw new RuntimeException("Category is not empty");
        }
        categoryService.deleteCategory(category);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategoryById(@PathVariable(required = true, name = "id") int id) throws RuntimeException {
        List<Product> products = productService.getProductsByCategory(id);

        if (!products.isEmpty()) {
            throw new RuntimeException("Category is not empty");
        }
        categoryService.deleteCategory(id);
    }

}
