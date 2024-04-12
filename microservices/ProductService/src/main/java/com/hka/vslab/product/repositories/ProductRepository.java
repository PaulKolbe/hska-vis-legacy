package com.hka.vslab.product.repositories;

import com.hka.vslab.product.model.Category;
import com.hka.vslab.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByName(String name);

    void deleteByName(String name);

    List<Product> findProductsByNameLikeAndPriceGreaterThanEqualAndPriceLessThanEqual(
            String search, Double minPrice, Double maxPrice);

    List<Product> findProductsByNameLike(String search);

    List<Product> findProductsByNameLikeAndPriceLessThanEqual(
            String search, Double maxPrice);

    List<Product> findProductsByNameLikeAndPriceGreaterThanEqual(
            String search, Double minPrice);

    int deleteAllByCategoryId(Category cat);

    List<Product> getProductsByCategoryId(int categoryId);
}
