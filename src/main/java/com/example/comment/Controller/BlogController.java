package com.example.comment.Controller;

import com.example.comment.DTO.BlogDTO;
import com.example.comment.Service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/blogs")
//게시글 목록, 상세, 수정, 삽입, 삭제
public class BlogController {
    private final BlogService blogService;
    //전체조회
    @GetMapping
    public String list(Model model){
        //List<BlogDTO> result = blogService.findAll();
        //model.addAttribute("blogs", result);
        model.addAttribute("blogs", blogService.findAll());
        return "blog/list";

    }

    //상세보기
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model){
        model.addAttribute("blog", blogService.findById(id));
        return "blog/detail";
    }
    //삭제
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        blogService.delete(id);
        return "redirect:/blogs";
    }

    //삽입
    @GetMapping("/new")
    public String writeForm(Model model){
        model.addAttribute("blog", new BlogDTO());
        return "blog/write";
    }
    @PostMapping("/new")  //@ModelAttribute 생략가능
    public String writerProc(@ModelAttribute BlogDTO blogDTO){
        blogService.save(blogDTO);
        return "redirect:/blogs";
    }
    //수정
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model){
        model.addAttribute("blog", blogService.findById(id));
        return "blog/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute BlogDTO blogDTO){
        blogService.update(id, blogDTO);
        return "redirect:/blogs/"+id;//상세보기로 이동
    }
}
