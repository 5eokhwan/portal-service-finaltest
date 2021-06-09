package com.assignment.gesipan.controller;

import com.assignment.gesipan.dto.BoardDto;
import com.assignment.gesipan.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    //BoardDto를 이용해 DB에 저장된 데이터를 List로 불러올 것이다. 실제 로직은 Service에서 구현해준다.
    //만든 List를 boardList 라는 이름으로 View에 전달해준다.
    @GetMapping("/")
    public String list(Model model) {
        List<BoardDto> boardDtoList = boardService.getBoardlist();
        model.addAttribute("boardList", boardDtoList);
        return "board/list.html";
    }

    @GetMapping("/post")
    public String write() {
        return "board/write.html";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/";
    }
    //Post방식의 요청을 받아서, 실제로는 Service에서 처리되도록 할 것이다.
    //Dto를 사용해 Controller와 Service 사이에서 데이터를 주고 받는다.

    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        //각 게시글을 클릭하면, '/post/id값'으로 요청을 한다.
        //따라서, 각 게시글의 id 값을 받아서 해당 게시글의 요소들만 Dto타입으로 만들어서 전달해줘야한다.
        //@PathVariable을 통해 요청에 오는 id값을 받아 getPost로 전달한다.
        // getPost는 각 게시글의 정보를 가져오는 기능인데 Service에서 구현해줄 것이다.
        model.addAttribute("boardDto", boardDto);
        //model을 통해 boardDto 타입의 데이터를 View에 전달해준다.
        return "board/detail.html";
    }
}
