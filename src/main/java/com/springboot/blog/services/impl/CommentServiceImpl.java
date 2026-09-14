package com.springboot.blog.services.impl;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogApiException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepsitory;
import com.springboot.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CommentServiceImpl implements CommentService {
    CommentRepository commentRepository;
    private final PostRepsitory postRepsitory;
    private final ModelMapper mapper ;

    public CommentServiceImpl(ModelMapper mapper,CommentRepository commentRepository,PostRepsitory postRepsitory) {
        this.commentRepository = commentRepository;
        this.postRepsitory = postRepsitory;
        this.mapper = mapper;
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        // retrieve post entity by ID
        Post post = postRepsitory.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("post","id", postId)
        );
        // set post to comment entity
        comment.setPost(post);

        // save comment id DB
        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        //retrieve comments by postId
        List<Comment> comments = commentRepository.findByPostId(postId);
        // retrieve list of comment entities as CommentDto
        return comments.stream().map(this::mapToDto).toList();
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        // retrieve post entity by id
        Post post = getPostEntityById(postId);

        // retrieve comment entity by id
        Comment comment = getCommentEntityById(commentId);

        isBelongToPost(comment,post);

        return mapToDto(comment);

    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {
        // retrieve post entity by id
        Post post = getPostEntityById(postId);

        // retrieve comment entity by id
        Comment comment = getCommentEntityById(commentId);

        // check if comment belong to post
        isBelongToPost(comment,post);

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        Comment updatedComment = commentRepository.save(comment);

        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        // retrieve post entity by id
        Post post = getPostEntityById(postId);
        // retrieve comment entity by id
        Comment comment = getCommentEntityById(commentId);
        // check if comment belong to post
        isBelongToPost(comment,post);
        commentRepository.delete(comment);

    }

    private CommentDto mapToDto(Comment comment){
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());

        return mapper.map(comment,CommentDto.class);
    }

    private Comment mapToEntity(CommentDto commentDto){
//        Comment comment = new Comment();
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());

        return mapper.map(commentDto,Comment.class);
    }

    private Post getPostEntityById(Long postId){
        return postRepsitory.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("post","id",postId)
        );
    }
    private Comment getCommentEntityById(Long commentId){
        return  commentRepository.findById(commentId).orElseThrow(
                ()->new ResourceNotFoundException("comment","id",commentId));
    }

    private void isBelongToPost(Comment comment, Post post){
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }
    }
}
