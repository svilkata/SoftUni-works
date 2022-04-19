package com.example.restdemo.web;

import com.example.restdemo.gson.PostGsonDeserializer;
import com.example.restdemo.gson.PostGsonSerializer;
import com.example.restdemo.model.Post;
import com.example.restdemo.service.PostService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.net.URI;

//The Gson way
@CrossOrigin(origins = "http://localhost:4000", maxAge = 3600)
@RestController    //org.springframework.web.bind.annotation
@RequestMapping("/api/simple")    //org.springframework.web.bind.annotation     //http://localhost:8080/api/simple
@Slf4j
public class SimplePostController {
    @Autowired
    private PostService postService;

    private Gson gson;

    @PostConstruct   //javax.annotation - one such method only in a class can be annotated with this annotation
    private void init() {
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .registerTypeAdapter(Post.class, new PostGsonSerializer())
                .registerTypeAdapter(Post.class, new PostGsonDeserializer())
                .create();
    }

    //org.springframework.web.bind.annotation
    //org.springframework.http
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPosts() {
        return gson.toJson(postService.getPosts());
    }

    //org.springframework.web.bind.annotation
    //org.springframework.http
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addPost(@RequestBody String body) {
        log.info("Body received: {}", body);
        Post post = gson.fromJson(body, Post.class);
        log.info("Post deserialized: {}", post);
        Post created = postService.createPost(post);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .pathSegment("{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(gson.toJson(created)); //response 201
    }
}