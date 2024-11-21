package com.ecommerce.api.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.api.Exceptions.CategoryNotFound;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Fetch all categories with pagination
    @GetMapping
    public ResponseEntity<?> getAllCategory(@RequestParam(defaultValue = "0") int page) {
        try {
            Page<Category> categories = categoryService.getAllCategories(page, 4);

            if (categories.getContent().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("Categories are empty.");
            }

            return ResponseEntity.status(HttpStatus.OK).body(categories.getContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("An error occurred while fetching categories: " + e.getMessage());
        }
    }

    // Add a new category
    @PostMapping
    public ResponseEntity<String> addCategory(@RequestBody Category category) {
        try {
            Category addedCategory = categoryService.addCategory(category);

            if (addedCategory == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                     .body("The category could not be added due to a server issue.");
            }

            return ResponseEntity.status(HttpStatus.CREATED)
                                 .body("Category added successfully: " + addedCategory.getName());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("An error occurred while adding the category: " + e.getMessage());
        }
    }

    // Fetch category by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable int id) {
        try {
            Category category = categoryService.getCategoryById(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(category);
        } catch (CategoryNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("An error occurred while fetching the category: " + e.getMessage());
        }
    }
    
    //update category by id
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable int id, @RequestBody Category category) {
        try {
            return ResponseEntity.ok(categoryService.updateCategory(id, category));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The id :"+id+ " is not present");
        }
    }
    
    
    
//    delete category by id

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("The Category has been deleted");
    }
}
