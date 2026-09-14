package com.springboot.blog.controller;


import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/")
public class CommentController {
    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> createNewComment(@PathVariable(value = "postId") Long postId,
                                                        @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    public List<CommentDto> getCommentsByID(@PathVariable(value = "postId") Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public CommentDto getCommentById(@PathVariable(value = "postId") Long postId
            , @PathVariable(value = "commentId") Long commentId) {
        return commentService.getCommentById(postId, commentId);
    }

    @PutMapping("/{postId}/comments/{commentId}")
    public CommentDto updateComment(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "commentId") Long commentId,
            @Valid @RequestBody CommentDto commentDto
    ) {
        return commentService.updateComment(postId, commentId, commentDto);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "commentId") Long commentId
    ){
        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<>("comment is deleted succesfully!",HttpStatus.OK);
    }

}
