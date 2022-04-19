package com.example.restdemo.service;

import com.example.restdemo.model.Post;

import java.util.Collection;
import java.util.List;

public interface PostService {
    Collection<Post> getPosts();
    Post getPostById(Long id);
    Post createPost(Post post);
    Post updatePost(Post post);
    Post deletePost(Long id);
    long getPostsCount();
//    List<Post> createPostsBatch(List<Post> posts);
}
