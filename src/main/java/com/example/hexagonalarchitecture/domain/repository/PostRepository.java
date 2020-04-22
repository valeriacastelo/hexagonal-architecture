package com.example.hexagonalarchitecture.domain.repository;

import com.example.hexagonalarchitecture.domain.Post;

import java.util.Optional;
import java.util.UUID;

/**
 * Out Port to be implemented by the infrastructure layer
 */
public interface PostRepository {

    void save(Post post);

    void delete (Post post);

    Optional<Post> findById(UUID postId);
}
