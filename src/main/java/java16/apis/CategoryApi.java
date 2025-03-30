package java16.apis;

import java16.dto.request.CategoryDTO;
import java16.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/category")
@RequiredArgsConstructor
public class CategoryApi {

    private final CategoryService categoryService;

    @PreAuthorize("hasAuthority('ADMIN') ")

    @PostMapping("/save")

    public ResponseEntity<?> saveCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.saveCategory(categoryDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryDTO categoryDTO) {
        return categoryService.updateCategory(categoryId, categoryDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        return categoryService.deleteCategory(categoryId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getallCategory")
    public ResponseEntity<?> getAllCategories() {
        return categoryService.getAllCategories();
    }
}