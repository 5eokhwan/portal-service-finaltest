package com.assignment.gesipan.service;

import com.assignment.gesipan.domain.entity.Board;
import com.assignment.gesipan.domain.entity.BoardRepository;
import com.assignment.gesipan.dto.BoardDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Service는 실제로 비즈니스 로직을 시행해주는 역할
@Service
public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }
    // (repository의 save는 JpaRepository -> PagingAndSortingRepository -> CrudRepository 의 인터페이스이다.)

    @Transactional
    public List<BoardDto> getBoardlist() {
        List<Board> boards = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();
        for(Board board : boards) {
            BoardDto boardDto = BoardDto.builder().id(board.getId()).title(board.getTitle())
                    .content(board.getContent()).writer(board.getWriter()).createdDate(board.getCreatedDate()).build();

            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }
    @Transactional
    public BoardDto getPost(Long id) {
        Optional<Board> boardWrapper = boardRepository.findById(id);
        Board board = boardWrapper.get();

        BoardDto boardDto = BoardDto.builder().id(board.getId()).title(board.getTitle())
                .content(board.getContent()).writer(board.getWriter()).createdDate(board.getCreatedDate()).build();

        return boardDto;
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }
}
