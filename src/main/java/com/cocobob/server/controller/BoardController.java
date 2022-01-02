package com.cocobob.server.controller;

public class BoardController {

    private final BoardRepository boardRepository;

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
    


}
