package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "Blog PostDto Information"
)
public class CategoryDto {
    private Long id;
    @Schema(description = "Post Category Name")
    private String name;
    @Schema(description = "Post Category description")
    private String description;
}
