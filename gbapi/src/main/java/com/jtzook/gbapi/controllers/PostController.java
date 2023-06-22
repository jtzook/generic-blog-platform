package com.jtzook.gbapi.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @GetMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String getAllPosts() {
        // TODO: implementation to get all posts
        return "GetAllPosts";
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('USER')")
    public String createPost() {
        // TODO: implementation to create a new post
        return "createPost";
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updatePost(@PathVariable Long id) {
        // TODO: implementation to update a post with a given id
        return "updatePost";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deletePost(@PathVariable Long id) {
        // TODO: implementation to delete a post with a given id
        return "deletePost";
    }
}

