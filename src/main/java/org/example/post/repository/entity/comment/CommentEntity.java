package org.example.post.repository.entity.comment;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.common.domain.PositiveIntegerCount;
import org.example.common.repository.entity.TimeBaseEntity;
import org.example.post.domain.comment.Comment;
import org.example.post.domain.content.CommentContent;
import org.example.post.repository.entity.post.PostEntity;
import org.example.user.repository.entity.UserEntity;

@Entity
@Table(name = "community_comment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private PostEntity post;

    private String content;

    private Integer likeCount;

    public CommentEntity(Comment comment) {
        this.id = comment.getId();
        this.author = new UserEntity(comment.getAuthor());
        this.post = new PostEntity(comment.getPost());
        this.content = comment.getContent();
        this.likeCount = comment.getLikeCount();
    }

    public Comment toComment() {
        return Comment.builder()
                .id(id)
                .post(post.toPost())
                .author(author.toUser())
                .content(new CommentContent(content))
                .likeCount(new PositiveIntegerCount(likeCount))
                .build();
    }
}
