package org.example.post.application;

import org.example.fake.FakeObjectFactory;
import org.example.post.application.dto.CreateCommentRequestDto;
import org.example.post.application.dto.CreatePostRequestDto;
import org.example.post.domain.Post;
import org.example.post.domain.comment.Comment;
import org.example.post.domain.content.PostPublicationState;
import org.example.user.application.UserService;
import org.example.user.application.dto.CreateUserRequestDto;
import org.example.user.domain.User;

public class PostApplicationTestTemplate {
    final UserService userService = FakeObjectFactory.getUserService();
    final PostService postService = FakeObjectFactory.getPostService();
    final CommentService commentService = FakeObjectFactory.getCommentService();

    final User user = userService.createUser(new CreateUserRequestDto("name", ""));
    final User otherUser = userService.createUser(new CreateUserRequestDto("name", ""));

    CreatePostRequestDto postRequestDto = new CreatePostRequestDto(user.getId(), "content", PostPublicationState.PUBLIC);
    final Post post = postService.createPost(postRequestDto);

    final String commentContentText = "this is test comment";
    CreateCommentRequestDto commentRequestDto = new CreateCommentRequestDto(post.getId(), user.getId(), "this is test comment");
    final Comment comment = commentService.createComment(commentRequestDto);

}
