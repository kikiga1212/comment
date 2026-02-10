package com.example.comment.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogDTO {
    private Long id;        //기본키, 번호
    private String title;   //제목
    private String content; //내용
    private LocalDateTime createdAt; //작성일자


}
