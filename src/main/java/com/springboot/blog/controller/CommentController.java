package com.springboot.blog.controller;


import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.services.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@Tag(name = "CRUD REST APIs For Comment Resource")
public class CommentController {
    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(
            summary = "Create Comment REST API",
            description = "Create Comment REST API is used to save Comment into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping("v1/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createNewComment(@PathVariable(value = "postId") Long postId,
                                                        @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Comments REST API",
            description = "Get Comment REST API is used to Get Comments from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("v1/posts/{postId}/comments")
    public List<CommentDto> getCommentsByID(@PathVariable(value = "postId") Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @Operation(
            summary = "Get Comment REST API",
            description = "Get Comment REST API is used to Get Comment from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("v1/posts/{postId}/comments/{commentId}")
    public CommentDto getCommentById(@PathVariable(value = "postId") Long postId
            , @PathVariable(value = "commentId") Long commentId) {
        return commentService.getCommentById(postId, commentId);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(
            summary = "Update Post REST API",
            description = "Update Comment REST API is used to update post in database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PutMapping("v1/posts/{postId}/comments/{commentId}")
    public CommentDto updateComment(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "commentId") Long commentId,
            @Valid @RequestBody CommentDto commentDto
    ) {
        return commentService.updateComment(postId, commentId, commentDto);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(
            summary = "Delete Comment REST API",
            description = "Delete Comment REST API is used to delete Comment from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "commentId") Long commentId
    ){
        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<>("comment is deleted succesfully!",HttpStatus.OK);
    }

}
