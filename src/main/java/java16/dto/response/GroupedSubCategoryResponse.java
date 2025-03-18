package java16.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupedSubCategoryResponse {
    private String categoryName; //
    private Long count;
}