package com.example.hexagonalarchitecture.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Getter
@RequiredArgsConstructor
public class Post {
    @NonNull private UUID id;
    @NonNull private String author;
    @NonNull private String text;
    @NonNull private LocalDateTime dateTime;
}
