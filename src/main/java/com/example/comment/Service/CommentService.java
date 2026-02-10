package com.example.comment.Service;

import com.example.comment.DTO.CommentDTO;
import com.example.comment.Entity.BlogEntity;
import com.example.comment.Entity.CommentEntity;
import com.example.comment.Repository.BlogRepository;
import com.example.comment.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    //연관관계로 구속된 부모테이블의 레포지토리***
    private final BlogRepository blogRepository;

    //댓글조회(해당게시글의 전체댓글)  1:N
    //private List<CommentDTO>
    //댓글조회시 (부모의 id번호를 함께 가지고 전달)
    public List<CommentDTO> findByBlogId(Long blogId){
        return commentRepository.findByBlogId(blogId) //게시글번호로 댓글을 조회
            .stream()
            .map(entity->{
                //여러작을 할때 { } 로 구성
                //댓글 Entity->DTO로 변환
                CommentDTO commentDTO = modelMapper.map(entity, CommentDTO.class);
                //부모Entity에 id를 blogId저장
                commentDTO.setBlogId(entity.getBlog().getId());
                return commentDTO;
            })
            .toList();
    }

    //개별조회(저장시->스크립트로 추가)
    //댓글저장 , 저장후 스크립트에 저장한 내용을 HTML에 추가
    public CommentDTO save(CommentDTO commentDTO){
        //반드시 부모정보를 읽는다.
        BlogEntity blog = blogRepository.findById(commentDTO.getBlogId())
                .orElseThrow(()->new IllegalArgumentException("부모데이터가 없음"));

        CommentEntity entity = modelMapper.map(commentDTO, CommentEntity.class);
        //자식 Entity에 부모 Entity를 저장
        entity.setBlog(blog); //부모Entity를 이용해서 외래키값을 저장(부모Entity id)
        entity.setCreatedAt(LocalDateTime.now());//생성일자
        entity.setId(null);

        CommentEntity saved = commentRepository.save(entity);
        CommentDTO result = modelMapper.map(saved, CommentDTO.class);
       result.setBlogId(blog.getId());

       return result;
    }
    //댓글삭제
    public void delete(Long commentId){
        if(!commentRepository.existsById(commentId)){
            throw new IllegalArgumentException("댓글 없음");
        }
        commentRepository.deleteById(commentId);
    }
}
