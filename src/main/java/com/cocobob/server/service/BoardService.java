package com.cocobob.server.service;

import com.cocobob.server.domain.Board;
import com.cocobob.server.domain.BoardRequestDto;
import com.cocobob.server.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Page<Board> getBoardList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    @Transactional
    public Long update(Long id, BoardRequestDto requestDto) {
        Board board1 = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        board1.update(requestDto);
        return board1.getId();
    }

    /* 조회수 카운트 */
    @Transactional
    public int updateView(Long id){
        return boardRepository.updateView(id);
    }

    /* 검색 기능 */
    @Transactional
    public Page<Board> search(String keyword,Pageable pageable){
       Page<Board> BoardList = boardRepository.findAllSearch(keyword,pageable);
       return BoardList;
    }
}
