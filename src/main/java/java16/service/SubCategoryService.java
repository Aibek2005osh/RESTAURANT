package java16.service;

import java16.dto.request.SubCategoryDTO;
import java16.dto.response.SubCategoryResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubCategoryService {
    ResponseEntity<?> saveSubCategory(Long categoryId, SubCategoryDTO subCategoryDTO);

    SubCategoryResponse getSubCategoryById(Long subCategoryID);

    ResponseEntity<?> updateSubCategory(Long subCategoryID, SubCategoryDTO subCategoryDTO);

    ResponseEntity<?>   deleteSubCategory(Long subCategoryID);

    ResponseEntity<?> getSubCategoriesByCategoryId(Long categoryId);

    ResponseEntity<List<?>>  getGroupedSubCategories();
}
