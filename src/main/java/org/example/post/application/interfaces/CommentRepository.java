package org.example.post.application.interfaces;

import java.util.Optional;
import org.example.post.domain.comment.Comment;

public interface CommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
}
