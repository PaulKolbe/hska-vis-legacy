package com.hka.vs.CategoryService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hka.vs.CategoryService.model.Category;
import com.hka.vs.CategoryService.repository.CategoryRepository;

import jakarta.transaction.Transactional;

import java.util.*;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<Category>();
        categoryRepository.findAll().forEach(categories::add);
        return categories;
    }

    public Category getCategory(int id) {
        try {
            return categoryRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public Category getCategoryByName(String name) {
        try {
            return categoryRepository.findByName(name);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    @Transactional
    public void delCategory(Category category) {
        categoryRepository.deleteById(category.getId());
    }

    public void delCategory(int id) {
        categoryRepository.deleteById(id);
    }

}
