package org.sopt.practice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.practice.service.dto.request.CreatePostRequest;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Blog blog;

    public static Post of(CreatePostRequest request, Blog blog) {
        return new Post(request.name(), request.content(), blog);
    }

    private Post(String name, String content, Blog blog) {
        this.name = name;
        this.content = content;
        this.blog = blog;
    }
}
