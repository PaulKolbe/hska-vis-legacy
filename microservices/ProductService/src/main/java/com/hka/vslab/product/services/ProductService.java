package com.hka.vslab.product.services;

import com.hka.vslab.product.model.Category;
import com.hka.vslab.product.model.Product;
import com.hka.vslab.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getProduct(int id) {
        try {
            return productRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public Product getProductByName(String name) {
        try {
            return productRepository.findByName(name);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public List<Product> findProductsBySearch(String search, Double minPrice, Double maxPrice) {
        if (search == null)
            search = "";
        search = "%" + search + "%";
        try {
            if (minPrice == null && maxPrice == null)
                return productRepository.findProductsByNameLike(search);
            else if (minPrice == null)
                return productRepository.findProductsByNameLikeAndPriceLessThanEqual(search, maxPrice);
            else if (maxPrice == null)
                return productRepository.findProductsByNameLikeAndPriceGreaterThanEqual(search, minPrice);
            else
                return productRepository.findProductsByNameLikeAndPriceGreaterThanEqualAndPriceLessThanEqual(search,
                        minPrice, maxPrice);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public List<Product> getProductsByCategory(int categoryId) {
        return productRepository.getProductsByCategoryId(categoryId);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public void delProduct(Product product) {
        productRepository.deleteByName(product.getName());
    }

    public void delProduct(int id) {
        productRepository.deleteById(id);
    }

    public int deleteAllByCategory(Category cat) {
        return productRepository.deleteAllByCategoryId(cat);
    }
}
