package java16.dto.response;

import java16.entitys.Category;
import lombok.Builder;
import lombok.Data;

@Data @Builder

public class SubCategoryResponse {

    private String name;

    private Category category;

}
