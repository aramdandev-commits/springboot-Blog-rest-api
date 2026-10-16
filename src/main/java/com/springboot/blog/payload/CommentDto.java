package com.springboot.blog.payload;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

@JsonPropertyOrder({"id", "name", "email","body"})
@Schema(
        description = "Blog commentDto Information"
)
public class CommentDto {
    private Long id;

    @NotEmpty(message = "Name must not be Empty")
    @Schema(description = "Blog Comment Name")
    private String name;
    @NotEmpty (message = "email must not be Empty")
    @Email
    @Schema(description = "Blog Comment Email")
    private String email;
    @NotEmpty(message = "body must not be Empty")
    @Size(min = 10, message = "comment body must be at minimum 10 characters!")
    @Schema(description = "Blog Comment Content")
    private String body;
}
