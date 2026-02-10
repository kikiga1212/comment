package com.example.comment.Service;

import com.example.comment.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BlogService {
    private final BlogRepository blogRepository;
    private final ModelMapper modelMapper;

    //전체게시글 조회
    private List
    //개별게시글 조회

    //등록

    //수정

    //삭제
}
