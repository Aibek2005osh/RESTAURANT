package java16.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.http.ResponseEntity;

@Data @Builder
public class CategoryDTO {

    @NotBlank
    private String name;

    public String getName() {
        if (name.isEmpty()){
            new RuntimeException("mundai bar");
        }
        return name;

    }
}
