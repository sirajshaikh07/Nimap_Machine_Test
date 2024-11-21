package com.ecommerce.api.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.api.Category.Category;
import com.ecommerce.api.Category.CategoryRepository;
import com.ecommerce.api.Exceptions.CategoryNotFound;
import com.ecommerce.api.Exceptions.ProductNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // GET all products with pagination
    public Page<Product> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }

    // POST create a new product
    public Product addProduct(Product product) {
        System.out.println("Received category id: " + product.getCategory().getId()); // Debugging karte hey 

        
        Category category = categoryRepository.findById(product.getCategory().getId())
                .orElseThrow(() -> new CategoryNotFound("Category not found for id: " + product.getCategory().getId()));

    
        product.setCategory(category);

    
        return productRepository.save(product);
    }

    // GET product by id
    public Product getProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    // PUT - update product by id
    public Product updateProduct(int id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setDescription(updatedProduct.getDescription());
                    product.setPrice(updatedProduct.getPrice());

                    
                    Category category = categoryRepository.findById(updatedProduct.getCategory().getId())
                            .orElseThrow(() -> new CategoryNotFound("Category not found for id: " + updatedProduct.getCategory().getId()));

                  
                    product.setCategory(category);

                    return productRepository.save(product);
                })
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }
    // DELETE - Delete product by id
    public void deleteProduct(int id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}
