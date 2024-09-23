package org.example.mywebservice.dto.response;

import lombok.Getter;
import org.example.mywebservice.domain.Post;

@Getter
public class PostResDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String author;
    private final boolean isOwner;

    public PostResDto(Post post, String nickName) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
        this.isOwner = author.equals(nickName);
    }
}
