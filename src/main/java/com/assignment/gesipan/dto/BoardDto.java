package com.assignment.gesipan.dto;

import com.assignment.gesipan.domain.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Board toEntity() {
        Board build = Board.builder().id(id).writer(writer).title(title).content(content).build();
        return build;
    }

    @Builder
    public BoardDto(Long id, String title, String content, String writer, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
    //Controller와 Service 사이에서 데이터를 주고 받는 DTO를 구현해줘야한다.
   //     DTO를 통해 service의 savePost에서 Repository에 데이터를 집어넣는다.
//toEntity()는 dto에서 필요한 부분을 빌더패턴을 통해 entity로 만드는 역할이다.