package java16.repo;

import java16.entitys.Category;
import java16.exceptions.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepo extends JpaRepository<Category,Long> {

        default Category  findBycategoryId(Long categoryId){
        return findById(categoryId).orElseThrow(()-> new NotFoundException("not found category with id : "+categoryId));
    }

    @Query ("select count (c) >0 from Category c where c.name =:name")
    boolean existsByName(String name);
}
