package com.cocobob.server.repository;

import com.cocobob.server.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Modifying
    @Query("update Board b set b.view = b.view+1 where b.id=:id")
    int updateView(Long id);

    @Query(value = "SELECT b FROM Board b WHERE b.title LIKE %:keyword% OR b.contents LIKE %:keyword%")
    Page<Board> findAllSearch(String keyword, Pageable pageable);

    /*@Query(value = "SELECT b FROM Board b WHERE b.title LIKE %:keyword% OR b.contents LIKE %:keyword%"
    )
    List<Board> findAllSearch(String keyword);
}*/
}