package com.example.comment.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "commentTable")
@Entity
public class CommentEntity {
    //댓글관리 테이블
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //기본키, 댓글번호
    private String writer;  //작성자
    private String content; //댓글내용
    private LocalDateTime createdAt;    //작성일자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id")//부모테이블의 기본키와 연결할 외래키이름
    private BlogEntity blog;// mappedBy = "blog" 일치

}
//외래키는 중복 불가능, 연관관계를 잘못 설정했을때 외래키는 데이터베이스에서 수동으로 삭제를 한다.
//외래키 이름 fk_comment(자식테이블)toBlog_id(부모테이블)