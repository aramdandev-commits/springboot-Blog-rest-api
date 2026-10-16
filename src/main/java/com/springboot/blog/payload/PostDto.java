package com.springboot.blog.payload;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;


import java.util.Set;

@Data
// الأنوتيشن ده بيجبر الـ JSON يترتب بالشكل ده
@JsonPropertyOrder({"id", "title", "description", "content","comments"})
@Schema(
        description = "Blog PostDto Information"
)

public class PostDto {
    private Long id;

    // title should not be null or empty
    // title should at least 2 chars
    @NotEmpty(message = "Post title should not be Empty!")
    @Size(min = 2 , message = "Post title should have at least 2 Characters!")
    @Schema(description = "Blog Post Title")
    private String title;

    // description should not be null or empty
    // description should at least 2 chars
    @NotEmpty(message = "Post description should not be Empty!")
    @Size(min = 10 , message = "Post description should have at least 10 Characters!")
    @Schema(description = "Blog Post Description")
    private String description;

    // content should not be null or empty
    @NotEmpty(message = "Post content should not be Empty!")
    @Schema(description = "Blog Post Content")
    private String content;

    private Set<CommentDto> comments;
}
