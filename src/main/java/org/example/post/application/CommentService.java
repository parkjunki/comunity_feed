package org.example.post.application;

import java.util.Optional;
import org.example.post.application.dto.CreateCommentRequestDto;
import org.example.post.application.dto.LikeRequestDto;
import org.example.post.application.dto.UpdateCommentRequestDto;
import org.example.post.application.interfaces.CommentRepository;
import org.example.post.application.interfaces.LikeRepository;
import org.example.post.domain.Post;
import org.example.post.domain.comment.Comment;
import org.example.user.application.UserService;
import org.example.user.domain.User;

public class CommentService {

    private final UserService userService;
    private final PostService postService;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;

    public CommentService(UserService userService, PostService postService,
            LikeRepository likeRepository, CommentRepository commentRepository) {
        this.userService = userService;
        this.postService = postService;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
    }

    public Comment getComment(Long id) {
        return commentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Comment createComment(CreateCommentRequestDto dto) {
        Post post = postService.getPost(dto.postId());
        User user = userService.getUser(dto.userId());
        Comment comment = Comment.createComment(post, user, dto.content());

        return commentRepository.save(comment);
    }

    public Comment updateComment(UpdateCommentRequestDto dto) {
        Comment comment = getComment(dto.commentId());
        User user = userService.getUser(dto.userId());
        comment.updateComment(user, dto.content());
        return commentRepository.save(comment);
    }

    public void likeComment(LikeRequestDto dto) {
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(comment, user)){
            return;
        }

        comment.like(user);
        likeRepository.like(comment, user);
    }

    public void unlikeComment(LikeRequestDto dto) {
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(comment, user)){
            comment.unlike();
            likeRepository.unlike(comment, user);
        }
    }
}
