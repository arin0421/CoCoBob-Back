package com.cocobob.server.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Getter
@NoArgsConstructor
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = true)
    private String tag;


    @Column(nullable = false)
    private String deadline;

    public Long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
//  @OneToMany(mappedBy = "board")
//    private List<Reply> reply;

    public Board(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        //this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.tag = requestDto.getTag();
        this.deadline = requestDto.getDeadline();
    }

    public Board(String title, Optional<String> username, String contents) {
        this.title = title;
        //this.username = username;
        this.contents = contents;
    }

    public Board(String title, String username, String contents, String tag, String deadline) {
        this.title = title;
        //this.username = username;
        this.contents = contents;
        this.tag = tag;
        this.deadline = deadline;
    }

    public void update(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        //this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.tag = requestDto.getTag();
    }


}