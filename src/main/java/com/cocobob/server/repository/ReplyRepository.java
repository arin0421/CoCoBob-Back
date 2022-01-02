package com.cocobob.server.repository;

import com.cocobob.server.domain.Board;
import com.cocobob.server.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findReplyByBoard(Board board);
};