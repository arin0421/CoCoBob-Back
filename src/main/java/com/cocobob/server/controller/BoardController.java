package com.cocobob.server.controller;

import com.cocobob.server.domain.Board;
import com.cocobob.server.domain.BoardRequestDto;
import com.cocobob.server.repository.BoardRepository;
import com.cocobob.server.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;

    @PostMapping("/api/boards")
    public Board createBoard(@RequestBody BoardRequestDto requestDto){
        Board board = new Board(requestDto);

        return boardRepository.save(board);
    }

    @GetMapping("/api/boards/{id}")
    public Board getBoard(@PathVariable Long id){
        Optional<Board> board = boardRepository.findById(id);

        return board.get();
    }

    @GetMapping("/api/boards")
    public Page<Board> getBoards(@PageableDefault(size=10,sort="id",direction = Sort.Direction.DESC) Pageable pageable){
        return boardService.getBoardList(pageable);
    }


}
