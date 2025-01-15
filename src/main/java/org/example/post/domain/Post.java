package org.example.post.domain;

import org.example.common.domain.PositiveIntegerCount;
import org.example.post.domain.content.Content;
import org.example.post.domain.content.PostContent;
import org.example.post.domain.content.PostPublicationState;
import org.example.user.domain.User;

public class Post {
    private final Long id;
    private final User author;  //User 객체를 사용하면 활용가능한 메서드나 가독성의 장점. 테스트 세팅 시 번거로운 단점
    private final Content content;
    private final PositiveIntegerCount likeCount;
    private PostPublicationState state;

    public static Post createPost(Long id, User author, String content, PostPublicationState state) {
        return new Post(id, author, new PostContent(content), state);
    }

    public static Post createDefaultPost(Long id, User author, PostContent content) {
        return new Post(id, author, content);
    }

    public Post(Long id, User author, PostContent content) {
        if(author == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCount();
        this.state = PostPublicationState.PUBLIC;
    }

    public Post(Long id, User author, Content content, PostPublicationState state) {
        if(author == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCount();
        this.state = state;
    }

    public void like(User user) {
        if(this.author.equals(user)) {
            throw new IllegalArgumentException();
        }
        this.likeCount.increase();
    }

    public void unlike() {
        this.likeCount.decrease();
    }

    public void updatePost(User user, String updateContent, PostPublicationState state) {
        if(!author.equals(user)) {
            throw new IllegalArgumentException();
        }
        this.state = state;
        this.content.updateContent(updateContent);
    }

    public int getLikeCount() {
        return likeCount.getCount();
    }

    public String getContent() {
        return content.getContentText();
    }
}
