package com.example.hexagonalarchitecture.domain.service;

import com.example.hexagonalarchitecture.domain.Post;
import com.example.hexagonalarchitecture.domain.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DomainPostServiceTest {

    private PostRepository postRepo;
    private DomainPostService tested;

    @BeforeEach
    void setUp() {
        postRepo = mock(PostRepository.class);
        tested = new DomainPostService(postRepo);
    }

    @Test
    void shouldCreatePost_thenSaveIt() {
        //WHEN
        UUID uuid = tested.submitPost("valeria", "text text text");

        //THEN
        verify(postRepo).save(any(Post.class));
        assertNotNull(uuid);
    }

    @Test
    void shouldDeletePostFromTwoDaysAgo_thenThrowException() {
        //GIVEN
        UUID postId = UUID.randomUUID();
        LocalDateTime today = LocalDateTime.now();
        Post postFromTwoDaysAgo = new Post(postId, "abc", "text from abc", today.minusDays(1));

        //WHEN
        when(postRepo.findById(postId)).thenReturn(Optional.of(postFromTwoDaysAgo));
        Executable executable = () -> tested.deletePost(postId);

        //THEN
        verifyNoInteractions(postRepo);
        assertThrows(RuntimeException.class, executable);
    }

    @Test
    void shouldDeletePostFromToday_thenDeleteIt() {
        //GIVEN
        UUID postId = UUID.randomUUID();
        LocalDateTime today = LocalDateTime.now();
        Post postFromToday = new Post(postId, "abc", "text from abc", today.plusHours(3));

        //WHEN
        when(postRepo.findById(postId)).thenReturn(Optional.of(postFromToday));
        tested.deletePost(postId);

        //THEN
        verify(postRepo).delete(any(Post.class));
    }
}
