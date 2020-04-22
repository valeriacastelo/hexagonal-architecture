package com.example.hexagonalarchitecture.infrastructure.configuration;

import com.example.hexagonalarchitecture.domain.repository.PostRepository;
import com.example.hexagonalarchitecture.domain.service.DomainPostService;
import com.example.hexagonalarchitecture.domain.service.PostService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    PostService postService(PostRepository postRepo) {
        return new DomainPostService(postRepo);
    }

}
