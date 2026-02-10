package com.example.comment.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

//연관관계 2개이상의 테이블을 조합
//ToString 1개이상 사용금지(무한루프)
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Table(name = "blogTable")
@Entity
public class BlogEntity {
    //게시글을 관리하는 테이블
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        //기본키, 번호
    private String title;   //제목
    private String content; //내용
    private LocalDateTime createdAt; //작성일자

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> comments;//게시글에 해당하는 댓글들

}
//mappedBy 는 자식테이블에 선언된 부모테이블의 이름
//cascade 는 연관관계 유지(all = 삽입,수정,삭제)에 댓글도 함꼐(삽입,수정,삭제)
//orphanRemoval = true : 부모가 삭제되면 자식도 삭제
// 1        :        N
//하나의 게시글에는 여러개의 댓글이 존재
//게시글이 없으면 댓글은 존재할 수가 없다.
//게시글을 삭제하면 댓글은 존재하면 안된다.
