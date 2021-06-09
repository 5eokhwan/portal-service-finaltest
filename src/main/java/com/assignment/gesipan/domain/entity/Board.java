package com.assignment.gesipan.domain.entity;
//Entity는 DB 테이블과 매핑되는 객체이다.
//@Builder는 setter 대신 사용하는 것이다.
//@Table 애노테이션으로 DB 테이블 이름을 정해줄 수 있지만, 없는 경우 클래스 이름으로 자동 매핑된다.

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends TimeEntity{

    @GeneratedValue
    @Id
    private Long id;

    @Column(length = 10, nullable = false)
    private String writer;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder
    public Board(Long id, String title, String content, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
