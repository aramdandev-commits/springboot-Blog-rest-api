package com.springboot.blog.repository;

import com.springboot.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepsitory extends JpaRepository<Post,Long> {
}
