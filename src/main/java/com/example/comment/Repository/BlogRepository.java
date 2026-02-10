package com.example.comment.Repository;

import com.example.comment.Entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//게시글의 쿼리
@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Long> {
}
