package java16.service.impl;

import java16.dto.request.CategoryDTO;
import java16.entitys.Category;
import java16.exceptions.BadRequestException;
import java16.repo.CategoryRepo;
import java16.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public ResponseEntity<?> saveCategory(CategoryDTO categoryDTO) {
        try {

            if (categoryRepo.existsByName(categoryDTO.getName())) {
                return ResponseEntity.badRequest().body("msyndai caregory bar ! ");
            }

            Category category = new Category();

            category.setName(categoryDTO.getName());
            categoryRepo.save(category);
            return ResponseEntity.ok("Category successfully saved ✅");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error : " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getCategoryById(Long id) {
        try {
            Category category = categoryRepo.findBycategoryId(id);
            return ResponseEntity.ok(category);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error :  " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateCategory(Long id, CategoryDTO categoryDTO) {
        try {
            Category category = categoryRepo.findBycategoryId(id);
            category.setName(categoryDTO.getName());
            categoryRepo.save(category);
            return ResponseEntity.ok("Category successfully updated 🤓");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating category: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteCategory(Long id) {
        try {
            if (!categoryRepo.existsById(id)) {
                return ResponseEntity.badRequest().body("Category not found with id: " + id);
            }
            categoryRepo.deleteById(id);
            return ResponseEntity.ok("Category successfully deleted ✖️");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error : -> " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getAllCategories() {
        try {
            return ResponseEntity.ok(categoryRepo.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error  " + e.getMessage());
        }
    }
}
