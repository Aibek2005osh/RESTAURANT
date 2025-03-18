    package java16.dto.request;

    import jakarta.validation.constraints.NotNull;
    import lombok.Builder;
    import lombok.Data;
    import jakarta.validation.constraints.NotBlank;


    @Data
    @Builder
    public class SubCategoryDTO {
        @NotBlank(message = "Сабкатегориянын аты бош болбошу керек")
        private String name;

    }