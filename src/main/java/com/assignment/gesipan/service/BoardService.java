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
    //savePost는 Repository를 통해 실제로 데이터를 저장하게 된다.
    // (repository의 save는 JpaRepository -> PagingAndSortingRepository -> CrudRepository 의 인터페이스이다.)

    //Service에서 getBoardlist를 구현해준다. getBoardlist는 DB에 저장되어 있는 전체 데이터를 불러온다.
    @Transactional
    public List<BoardDto> getBoardlist() {
        List<Board> boards = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();
        //repository에서 모든 데이터를 가져와, 데이터 만큼 반복하면서,
        //BoardDto 타입의 List에 데이터를 파싱하여 집어넣고, 완성된 BoardDto 타입의 List을 리턴해준다.
        for(Board board : boards) {
            BoardDto boardDto = BoardDto.builder().id(board.getId()).title(board.getTitle())
                    .content(board.getContent()).writer(board.getWriter()).createdDate(board.getCreatedDate()).build();

            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    //게시글의 id 값을 받아 해당 게시글의 정보만 repository에서 findById로 가져온다.
    //그리고, BoardDto 타입으로 만들어 return 해준다.
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
