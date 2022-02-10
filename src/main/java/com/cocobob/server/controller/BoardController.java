package com.cocobob.server.controller;

import com.cocobob.server.domain.Board;
import com.cocobob.server.domain.BoardRequestDto;
import com.cocobob.server.repository.BoardRepository;
import com.cocobob.server.service.BoardService;
import com.cocobob.server.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;

    @PostMapping("/api/boards")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Board createBoard(@RequestBody BoardRequestDto requestDto){
        Board board = new Board(requestDto);
        Optional<String> name = SecurityUtil.getCurrentUsername();
        //String username = String.valueOf(SecurityUtil.getCurrentUsername());
        board.setUsername(name.get());
        return boardRepository.save(board);
    }

    @GetMapping("/api/boards/{id}")
    public Board getBoard(@PathVariable Long id){
        Optional<Board> board = boardRepository.findById(id);
        boardService.updateView(id);
        return board.get();
    }

    @GetMapping("/api/boards")
    public Page<Board> getBoards(@PageableDefault(size=10,sort="id",direction = Sort.Direction.DESC) Pageable pageable){
        return boardService.getBoardList(pageable);
    }

    @PutMapping("/api/boards/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto){
        return boardService.update(id,requestDto);
    }

    @DeleteMapping("/api/boards/{id}")
    public Long deleteBoard(@PathVariable Long id){
        boardRepository.deleteById(id);
        return id;
    }

    @GetMapping("/api/boards/search")
    public Page<Board> search(@RequestParam(value = "keyword") String keyword, @PageableDefault(size=10,sort="id",direction = Sort.Direction.DESC) Pageable pageable) {

        //Page<Board> searchList=boardService.search(keyword,pageable);
         //model.addAttribute("searchList",searchList);

         return boardService.search(keyword,pageable);
    }
}
