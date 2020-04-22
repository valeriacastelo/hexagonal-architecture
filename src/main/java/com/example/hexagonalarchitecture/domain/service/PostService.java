package com.example.hexagonalarchitecture.domain.service;

import com.example.hexagonalarchitecture.domain.Post;

import java.util.UUID;

/**
 * In port to be used by the the application layer
 */
public interface PostService {

    UUID submitPost(String author, String text);

    void deletePost(UUID postId);

    Post findById(UUID postId);
}
