package com.example.comment.Repository;

import com.example.comment.Entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    //게시글에 해당하는 댓글 조회
    List<CommentEntity> findByBlogId(Long blogId);

    //private BlogEntity blog; entiyt를 선언한 필드 사용
    //findBy필드명(Entity 변수명(Entity에 속하는 변수명)
    //Blog(entity)+Id(blogEntity내에 있는 id)
}
