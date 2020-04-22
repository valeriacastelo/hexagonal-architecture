package com.example.hexagonalarchitecture.application;

import com.example.hexagonalarchitecture.domain.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

/**
 * It allow the user to communicate with the application.
 * Adapts the outside to the application by calling the methods from PostService (port)
 */

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<Void> submitPost(@RequestAttribute String author, @RequestAttribute String text) {
        UUID uuid = this.postService.submitPost(author, text);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(uuid)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public void deletePost(@PathVariable UUID id) {
        this.postService.deletePost(id);
    }

    @GetMapping(value = "/{id}")
    public void getPost(@PathVariable UUID id) {
        this.postService.findById(id);
    }
}
