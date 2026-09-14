package com.springboot.blog.payload;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

@JsonPropertyOrder({"id", "name", "email","body"})
public class CommentDto {
    private Long id;

    @NotEmpty(message = "Name must not be Empty")
    private String name;
    @NotEmpty (message = "email must not be Empty")
    @Email
    private String email;
    @NotEmpty(message = "body must not be Empty")
    @Size(min = 10, message = "comment body must be at minimum 10 characters!")
    private String body;
}
