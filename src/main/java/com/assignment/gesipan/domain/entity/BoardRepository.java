package com.assignment.gesipan.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
//Repository는 데이터 조작을 담당. JpaRepository를 extends
public interface BoardRepository extends JpaRepository<Board, Long> {

}
