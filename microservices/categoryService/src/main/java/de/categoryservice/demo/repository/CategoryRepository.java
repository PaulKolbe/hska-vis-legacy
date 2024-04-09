package de.categoryservice.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.categoryservice.demo.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Category findByName(String name);

    void deleteByName(String name);
}