package com.springboot.blog.services.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.PostRepsitory;
import com.springboot.blog.services.PostService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostServiceImpl implements PostService {
    private final PostRepsitory postRepsitory;
    private final ModelMapper mapper;

    public PostServiceImpl(PostRepsitory postRepsitory,ModelMapper mapper)
    {
        this.postRepsitory = postRepsitory;
        this.mapper = mapper;
    }


    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = mapToEntity(postDto);

        //  postRepsitory.save(post) response is Entity
        Post newPost =  postRepsitory.save(post);

        return mapToDTO(newPost);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();
        // create pageable instance
        Pageable page = PageRequest.of(pageNo,pageSize, sort);
        Page<Post> posts = postRepsitory.findAll(page);
        //get content for page object
        List<Post> listOfPosts = posts.getContent();
        List<PostDto> content = listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setTotalElements((posts.getTotalElements()));
        postResponse.setLast(posts.isLast());
        return postResponse;
        /* using method reference
         * return posts.stream().map(this::mapToDTO).toList(); */
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post =  postRepsitory.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
        return mapToDTO(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        // get post by id
        Post post =  postRepsitory.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatedPost =postRepsitory.save(post);

        return mapToDTO(updatedPost);
    }


    @Override
    public void deletePostById(Long id) {
        Post post =  postRepsitory.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
        postRepsitory.delete(post);
    }

    // convert Entity to DTO
    private PostDto mapToDTO(Post post){
//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
        return mapper.map(post,PostDto.class);
    }

    // convert DTO to Entity
    private Post mapToEntity(PostDto postDto){
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return mapper.map(postDto,Post.class);
    }
}
