package com.ecommerce.api.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.api.Exceptions.CategoryNotFound;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Fetch all categories with pagination
    public Page<Category> getAllCategories(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            return categoryRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching categories", e);
        }
    }

    // Add a new category
    public Category addCategory(Category category) {
        try {
            return categoryRepository.save(category);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while saving the category", e);
        }
    }

    // Fetch category by ID
    public Category getCategoryById(int id) {
        try {
            return categoryRepository.findById(id)
                                     .orElseThrow(() -> new CategoryNotFound("Category with Id " + id + " not found"));
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching the category", e);
        }
    }
//    update
    public Category updateCategory(int id, Category updatedCategory) {
        return categoryRepository.findById(id).map(category -> {
            category.setName(updatedCategory.getName());
            category.setDescription(updatedCategory.getDescription());
            return categoryRepository.save(category);
        }).orElseThrow(() -> new CategoryNotFound("Category not found"));
    }
    
    public void deleteCategory(int id) {
    	boolean category=categoryRepository.findById(id).isPresent();
    	if(category)
    	{
    		 categoryRepository.deleteById(id);
    	}
    	else
    	{
    		throw new CategoryNotFound("Id : " +id+ " is not present or it may have already been deleted");
    	}
       
    }
}
