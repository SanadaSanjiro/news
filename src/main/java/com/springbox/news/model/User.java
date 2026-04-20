package com.springbox.news.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает модель пользователя
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name="user_name")
    String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "newsAuthor", cascade = CascadeType.ALL)
    @ToString.Exclude
    List<News> newsList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "commentAuthor", cascade = CascadeType.ALL)
    @ToString.Exclude
    List<Comment> comments = new ArrayList<>();

    @CreationTimestamp
    Instant createdAt;

    @UpdateTimestamp
    Instant updatedAt;
}
