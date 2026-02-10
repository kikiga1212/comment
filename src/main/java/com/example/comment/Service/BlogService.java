package com.example.comment.Service;

import com.example.comment.DTO.BlogDTO;
import com.example.comment.Entity.BlogEntity;
import com.example.comment.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
//게시글 비지니스 로직(부모테이블 작업은 변경내용X)
public class BlogService {
    private final BlogRepository blogRepository;
    private final ModelMapper modelMapper;

    //전체게시글 조회
    public List<BlogDTO> findAll(){
        return blogRepository.findAll()
                .stream()//순서대로 읽어서
                .map(entity->modelMapper.map(entity, BlogDTO.class))
                .toList();
    }
    //개별게시글 조회
    public BlogDTO findById(Long id){
        BlogEntity entity = blogRepository.findById(id)
                //.orElse(null); null을 안주는이유 ->
                .orElseThrow(()->new IllegalArgumentException("게시글 없음"));
        return modelMapper.map(entity, BlogDTO.class);
    }

    //등록
    public Long save(BlogDTO blogDTO){
        BlogEntity entity = modelMapper.map(blogDTO, BlogEntity.class);
        entity.setCreatedAt(LocalDateTime.now());//현재 시간을 저장

        return blogRepository.save(entity).getId();
    }

    //수정(수정시 수정대상을 변수로 따로 받는 경우, rest방식)
    //@Transactional (서버안정적으로 운영할때 도움)
    public void update(Long id, BlogDTO blogDTO){
        BlogEntity entity = blogRepository.findById(id)//blogDTO.getId()
                .orElseThrow(()->new IllegalArgumentException("대상 없음"));
        //@Transactional 선언하면 읽어온 entity에 변경할 내용만 저장
        entity.setTitle(blogDTO.getTitle());
        entity.setContent(blogDTO.getContent());
        //예전방식
//        BlogEntity updateEntity = modelMapper.map(blogDTO, BlogEntity.class);
//        blogRepository.save(entity);
    }

    //삭제
    public void delete(Long id){
        BlogEntity entity = blogRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("삭제할 데이터 없음"));
        //예전방식 blogRepository.deleteById(id);
        //@Transactional
        blogRepository.delete(entity);
    }
}
