package java16.service.impl;

import java16.dto.request.SubCategoryDTO;
import java16.dto.response.GroupedSubCategoryResponse;
import java16.dto.response.SubCategoryResponse;
import java16.entitys.Category;
import java16.entitys.Subcategory;
import java16.exceptions.NotFoundException;
import java16.repo.CategoryRepo;
import java16.repo.SubCategoryRepo;
import java16.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepo subCategoryRepo;
    private final CategoryRepo categoryRepo;

    @Override
    public ResponseEntity<?> saveSubCategory(Long categoryId, SubCategoryDTO subCategoryDTO) {


        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("not found with ID : " + categoryId));

        Subcategory subcategory = new Subcategory();
        subcategory.setName(subCategoryDTO.getName());
        subcategory.setCategory(category);

        try {
            subCategoryRepo.save(subcategory);
            return ResponseEntity.status(HttpStatus.CREATED).body("successful subcategory seved 👌");

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("server 🧑🏻‍💻");
        }
    }

    @Override
    public SubCategoryResponse getSubCategoryById(Long subCategoryID) {

        Subcategory bysubCategoryId = subCategoryRepo.findBysubCategoryId(subCategoryID);

        try {

            return SubCategoryResponse.builder()
                    .name(bysubCategoryId.getName())
                    .category(bysubCategoryId.getCategory())
                    .build();
        } catch (RuntimeException e) {
            return SubCategoryResponse.builder()
                    .name(bysubCategoryId.getName())
                    .category(bysubCategoryId.getCategory())
                    .build();
        }

    }

    @Override
    public ResponseEntity<?> updateSubCategory(Long subCategoryID, SubCategoryDTO subCategoryDTO) {

        Subcategory bysubCategoryId = subCategoryRepo.findBysubCategoryId(subCategoryID);

        bysubCategoryId.setName(subCategoryDTO.getName());
        try {
            subCategoryRepo.save(bysubCategoryId);
            return ResponseEntity.status(HttpStatus.OK).body("successful updated subCategory");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error sever " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteSubCategory(Long subCategoryID) {

        Subcategory bysubCategoryId = subCategoryRepo.findBysubCategoryId(subCategoryID);
        try {
            subCategoryRepo.delete(bysubCategoryId);
            return ResponseEntity.status(HttpStatus.OK).body("successful deleteted subCategory 👌😏");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error server : " + e.getMessage());
        }

    }

    @Override
    public ResponseEntity<?> getSubCategoriesByCategoryId(Long categoryId) {
        try {
            List<Subcategory> subcategories = subCategoryRepo.findByCategoryIdOrderByNameAsc(categoryId);
            if (subcategories.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Категория ID боюнча субкатегориялар табылган жок: " + categoryId);
            }

            return ResponseEntity.ok(subcategories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Субкатегорияларды алууда ката кетти: " + e.getMessage());
        }
    }

//
//    @Override
    public ResponseEntity<List<?>> getGroupedSubCategories() {
    return null;}
//        try {
//            List<GroupedSubCategoryResponse> groupedSubCategories = subCategoryRepo.getGroupedSubCategories();
//            if (groupedSubCategories == null || groupedSubCategories.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//            }
//
//            return ResponseEntity.status(HttpStatus.OK).body(groupedSubCategories);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .build();
//        }
//    }/
}
