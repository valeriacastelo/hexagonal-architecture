package com.example.hexagonalarchitecture.infrastructure.repository.mongo;

import com.example.hexagonalarchitecture.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataMongoPostRepository extends MongoRepository<Post, UUID> {
}
