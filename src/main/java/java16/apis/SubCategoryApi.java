package java16.apis;

import java16.dto.request.SubCategoryDTO;
import java16.dto.response.SubCategoryResponse;
import java16.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/subcategory")
@RequiredArgsConstructor
public class SubCategoryApi {

    private final SubCategoryService subCategoryService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/saveSubCategory/{categoryId}")
    public ResponseEntity<?> saveSubCategory(   @PathVariable Long categoryId ,  @RequestBody SubCategoryDTO subCategoryDTO) {
        return subCategoryService.saveSubCategory(categoryId, subCategoryDTO);
    }

    @GetMapping("/{subCategoryID}")
    public SubCategoryResponse getSubCategoryById(@PathVariable Long subCategoryID) {
        return subCategoryService.getSubCategoryById(subCategoryID);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{subCategoryID}")
    public ResponseEntity<?> updateSubCategory(@PathVariable Long subCategoryID, @RequestBody SubCategoryDTO subCategoryDTO) {
        return subCategoryService.updateSubCategory(subCategoryID, subCategoryDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{subCategoryID}")
    public ResponseEntity<?> deleteSubCategory(@PathVariable Long subCategoryID) {
        return subCategoryService.deleteSubCategory(subCategoryID);
    }

    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<?> getSubCategoriesByCategoryId(@PathVariable Long categoryId) {
        return subCategoryService.getSubCategoriesByCategoryId(categoryId);
    }

//    @GetMapping("/grouped")
//    public ResponseEntity<List<?>>  getGroupedSubCategories() {
//        return subCategoryService.getGroupedSubCategories();
//    }
}