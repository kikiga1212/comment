package com.example.comment.Controller;


import com.example.comment.DTO.CommentDTO;
import com.example.comment.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
//게시글의 상세페이징서 삽입,삭제,조회를 비돟기식으로 처리
public class CommentRestController {
    private final CommentService commentService;

    //댓글 조회
    @GetMapping("/{blogId}")
    public List<CommentDTO> list(@PathVariable Long blogId){
        return commentService.findByBlogId(blogId);
        //List<CommentDTO> list = commentService.findByBlogId(blogId);
        //return list;
    }
    //댓글 저장(저장할 DTO를 받아서 저장하고 결과를 DTO로 전달)
    @PostMapping
    public CommentDTO write(@RequestBody CommentDTO commentDTO){
        return commentService.save(commentDTO);
    }
    //댓글 삭제
    @DeleteMapping("/{commentId}")
    public void delete(@PathVariable Long commentId){
        commentService.delete(commentId);
    }

}
//@Controller는 model로 값을 전달하고, return으로 이동페이지
//public String(이동페이지의 문자열)
//DTO전달 받을때 @ModelAttribute를 선언하거나 생략
//매핑 :  GetMapping(목록, 상세보기,삽입/수정페이지), PostMapping(삽입처리,수정처리, 삭제처리), DeleteMapping(삭제)
//@RestController는 model로 값을 전달하지 않고, return으로 json데이터를 전달
//(이동페이지는 호출한 페이지로 이동)
//public 전달할 값의 유형
//DTO전달 받을때 @RequestBody를 선언
//매핑 : GetMapping(목록,상세)
//       PostMapping(삽입처리)
//       PutMapping(
//       DeleteMapping(