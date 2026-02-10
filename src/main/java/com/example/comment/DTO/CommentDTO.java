package com.example.comment.DTO;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CommentDTO {
    private Long id;    //기본키, 댓글번호
    private String writer;  //작성자
    private String content; //댓글내용
    private LocalDateTime createdAt;    //작성일자

    private Long blogId; //게시글 번호(부모테이블의 번호가 필요***)
}
