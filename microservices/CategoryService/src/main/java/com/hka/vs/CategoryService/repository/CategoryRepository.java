package com.hka.vs.CategoryService.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hka.vs.CategoryService.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Category findByName(String name);

    void deleteByName(String name);
}
