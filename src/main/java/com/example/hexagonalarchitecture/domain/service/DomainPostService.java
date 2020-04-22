package com.example.hexagonalarchitecture.domain.service;

import com.example.hexagonalarchitecture.domain.DomainException;
import com.example.hexagonalarchitecture.domain.Post;
import com.example.hexagonalarchitecture.domain.repository.PostRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain Service: it is a adapter that implements the in port
 * Contains logic that are not present in the root
 * Access the repository
 */
@RequiredArgsConstructor
public class DomainPostService implements PostService {

    @NonNull
    private PostRepository postRepo;

    @Override
    public UUID submitPost(String author, String text) {
        Post newPost = new Post(UUID.randomUUID(), author, text, LocalDateTime.now());
        this.postRepo.save(newPost);

        return newPost.getId();
    }

    /**
     * Business rule: Only posts of the same day can be deleted
     * @param postId post id
     */
    @Override
    public void deletePost(UUID postId) {
        Post post = getPost(postId);

        Duration duration = Duration.between(post.getDateTime(), LocalDateTime.now());

        if (duration.toDays() < 1) {
            this.postRepo.delete(post);
        } else {
            throw new DomainException(String.format("Post id[%s] can not be deleted", postId));
        }
    }

    @Override
    public Post findById(UUID postId) {
        return getPost(postId);
    }

    private Post getPost(UUID postId) {
        return this.postRepo.findById(postId)
                   .orElseThrow(() -> new DomainException(String.format("Post id [%s] not found", postId)));
    }
}
