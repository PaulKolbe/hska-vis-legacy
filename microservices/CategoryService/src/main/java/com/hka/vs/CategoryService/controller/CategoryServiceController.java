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

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<Object> addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
        return new ResponseEntity<>("The category was created", HttpStatus.CREATED);
    }

    @DeleteMapping("/categories")
    public ResponseEntity<Object> deleteCategory(@RequestBody Category category) {
        int id = category.getId();
        boolean isDeleted = productService.deleteProductsByCategoryId(id);
        if (isDeleted) {
            categoryService.deleteCategory(id);
        }
        return new ResponseEntity<>("The category was deleted", HttpStatus.OK);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Object> deleteCategoryById(@PathVariable(required = true, name = "id") int id) {
        categoryService.deleteCategory((int) id);
//        boolean isDeleted = productService.deleteProductsByCategoryId((int)id);
//        if (isDeleted) {
//            categoryService.deleteCategory((int)id);
//        }
        return new ResponseEntity<>("The category was deleted", HttpStatus.OK);
    }
}
