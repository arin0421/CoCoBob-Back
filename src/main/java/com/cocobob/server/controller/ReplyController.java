package com.cocobob.server.controller;

import com.cocobob.server.domain.Board;
import com.cocobob.server.domain.Reply;
import com.cocobob.server.repository.BoardRepository;
import com.cocobob.server.repository.ReplyRepository;
import com.cocobob.server.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class ReplyController {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @PostMapping("/api/boards/{id}/reply")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Reply createReply(@PathVariable Long id, @RequestBody Reply reply){
        Optional<Board> boardItem = boardRepository.findById(id);
        Optional<String> name = SecurityUtil.getCurrentUsername();
        reply.setBoard(boardItem.get());
        reply.setUsername(name.get());
        replyRepository.save(reply);
        return reply;
    }

    @GetMapping("/api/boards/{id}/reply")
    public List<Reply> getreplys(@PathVariable Long id) {
        Board board = boardRepository.findById(id).get();
        return replyRepository.findReplyByBoard(board);
    }

}
