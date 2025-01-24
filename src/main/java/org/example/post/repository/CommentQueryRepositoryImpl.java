package org.example.post.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.post.repository.entity.comment.QCommentEntity;
import org.example.post.repository.entity.like.LikeTarget;
import org.example.post.repository.entity.like.QLikeEntity;
import org.example.post.ui.dto.GetContentResponseDto;
import org.example.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentQueryRepositoryImpl {
    private final JPAQueryFactory queryFactory;
    private static final QCommentEntity commentEntity = QCommentEntity.commentEntity;
    private static final QUserEntity userEntity = QUserEntity.userEntity;
    private static final QLikeEntity likeEntity = QLikeEntity.likeEntity;

    public List<GetContentResponseDto> getCommentList(Long postId, Long lastContentId) {
        return queryFactory
                .select(
                        Projections.fields(
                                GetContentResponseDto.class,
                                commentEntity.id.as("id"),
                                commentEntity.content.as("content"),
                                userEntity.id.as("userId"),
                                userEntity.name.as("userName"),
                                userEntity.profileImageUrl.as("userProfileImage"),
                                commentEntity.likeCount.as("likeCount"),
                                commentEntity.regDt.as("createdAt"),
                                commentEntity.updDt.as("updatedAt")
                        )
                )
                .from(commentEntity)
                .join(userEntity).on(commentEntity.author.id.eq(userEntity.id))
                .where(
                        commentEntity.post.id.eq(postId),
                        hasLastData(lastContentId)
                )
                .orderBy(commentEntity.id.desc())
                .limit(10)
                .fetch();
    }

    private BooleanExpression hasLastData(Long lastId) {
        if (lastId == null) {
            return null;
        }

        return commentEntity.id.lt(lastId);
    }
}
