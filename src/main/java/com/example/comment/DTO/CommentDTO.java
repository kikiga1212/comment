package com.example.comment.DTO;

import java.time.LocalDateTime;

public class CommentDTO {
    private Long id;    //기본키, 댓글번호
    private String writer;  //작성자
    private String content; //댓글내용
    private LocalDateTime createdAt;    //작성일자
}
