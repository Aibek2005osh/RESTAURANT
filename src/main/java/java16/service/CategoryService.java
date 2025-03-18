package java16.service;

import java16.dto.request.CategoryDTO;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<?> saveCategory(CategoryDTO categoryDTO);

    ResponseEntity<?> getCategoryById(Long id);

    ResponseEntity<?> updateCategory(Long id, CategoryDTO categoryDTO);

    ResponseEntity<?> deleteCategory(Long id);

    ResponseEntity<?> getAllCategories();
}
