package com.example.hexagonalarchitecture.infrastructure.configuration;

import com.example.hexagonalarchitecture.infrastructure.repository.mongo.SpringDataMongoPostRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * This class can contains anything related to the connection between MongoDB and the application
 */
@EnableMongoRepositories(basePackageClasses = SpringDataMongoPostRepository.class)
public class MongoConfiguration {
}
