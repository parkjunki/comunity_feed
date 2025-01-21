package org.example.post.repository;

import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.post.application.interfaces.CommentRepository;
import org.example.post.domain.comment.Comment;
import org.example.post.repository.entity.comment.CommentEntity;
import org.example.post.repository.jpa.JpaCommentRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        CommentEntity commentEntity = new CommentEntity(comment);
        if(comment.getId() != null) {
            jpaCommentRepository.updateCommentEntity(commentEntity);
            return commentEntity.toComment();
        }

        commentEntity = jpaCommentRepository.save(commentEntity);
        return commentEntity.toComment();
    }

    @Override
    public Comment findById(Long id) {
        CommentEntity commentEntity = jpaCommentRepository.findById(id).orElseThrow();
        return commentEntity.toComment();
    }
}
