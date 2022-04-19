package com.example.restdemo.web;


import com.example.restdemo.model.Post;
import com.example.restdemo.service.PostService;
import com.example.restdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

//The Jackson way
@RestController    //се сериализира като Response body
@RequestMapping("api/posts")   //http://localhost:8080/api/posts
@CrossOrigin("http://localhost:3000")  //match other port
@Slf4j
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping
    public Collection<Post> getPosts() {
        return postService.getPosts();
    }

    @PostMapping
    public ResponseEntity<Post> addPost(@RequestBody Post post){
        Post created = postService.createPost(post);
        URI location = MvcUriComponentsBuilder.fromMethodName(PostController.class, "addPost", post)
                .pathSegment("{id}").buildAndExpand(created.getId()).toUri();

        log.info("New Post created: {}", created);
        return ResponseEntity.created(location).body(created);
    }
}
