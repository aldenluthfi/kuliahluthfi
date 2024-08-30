package id.ac.ui.cs.advprog.eshop.service;

import java.util.List;

import id.ac.ui.cs.advprog.eshop.model.Product;

public interface ProductService {
    public Product create(Product product);

    public List<Product> findAll();

    public Product edit(String productId, Product newProductData);

    public Product findById(String productId);

    public void delete(String productId);
}