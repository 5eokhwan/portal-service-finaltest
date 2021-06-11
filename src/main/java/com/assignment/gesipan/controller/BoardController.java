package com.assignment.gesipan.controller;

import com.assignment.gesipan.dto.BoardDto;
import com.assignment.gesipan.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    //@RequestParam으로 page값을 받음 (디폴트 1)
    public String list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {
        List<BoardDto> boardDtoList = boardService.getBoardlist(pageNum);
        //pageList - 게시글 수로 페이지 목록 수 결정 (service서 구현)
        Integer[] pageList = boardService.getPageList(pageNum);

        model.addAttribute("boardList", boardDtoList);
        model.addAttribute("pageList", pageList);

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
    //디테일 페이지에서 썼던 getPost를 그대로 사용한다. 이전 데이터 값을 유지한 상태에서 수정된 부분만 다시 저장해야하기 때문이다.
    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);

        model.addAttribute("boardDto", boardDto);
        return "board/update.html";
    }
    @PutMapping("/post/edit/{no}")
    public String update(BoardDto boardDto) {
            //글쓰기를 구현할 때, 구현했던 savePost를 이용하여 DB에 새로 저장을 한다.
            boardService.savePost(boardDto);
            return "redirect:/";
    }
    //디테일 페이지에서 삭제 버튼을 누르면 '/post/id값'으로 delete 요청이 들어온다
    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long id) {
        boardService.deletePost(id);

        return "redirect:/";
    }

}
