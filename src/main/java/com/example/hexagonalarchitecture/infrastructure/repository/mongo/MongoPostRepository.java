package com.example.hexagonalarchitecture.infrastructure.repository.mongo;

import com.example.hexagonalarchitecture.domain.Post;
import com.example.hexagonalarchitecture.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * Adapter that implements the out port from domain layer
 */
@Component
public class MongoPostRepository implements PostRepository {

    private SpringDataMongoPostRepository springMongoRepo;

    @Autowired
    public MongoPostRepository (SpringDataMongoPostRepository springMongoRepo) {
        this.springMongoRepo = springMongoRepo;
    }

    @Override
    public void save(Post post) {
        this.springMongoRepo.save(post);

    }

    @Override
    public void delete(Post post) {
        this.springMongoRepo.delete(post);

    }

    @Override
    public Optional<Post> findById(UUID postId) {
        return this.springMongoRepo.findById(postId);
    }
}
