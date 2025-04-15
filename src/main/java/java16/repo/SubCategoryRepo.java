package java16.repo;

import java16.dto.response.GroupedSubCategoryResponse;
import java16.dto.response.SubCategoryResponse;
import java16.entitys.Subcategory;
import java16.exceptions.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubCategoryRepo extends JpaRepository<Subcategory, Long> {


    default Subcategory findBysubCategoryId(Long subCategoryID){
       return findById(subCategoryID).orElseThrow(()->new NotFoundException("not found subCategory with ID "+subCategoryID));
    };


    @Query("select s from Subcategory s where s.category.id = :categoryId order by s.name")

    List<Subcategory> findByCategoryIdOrderByNameAsc(@Param("categoryId") Long categoryId);

}
