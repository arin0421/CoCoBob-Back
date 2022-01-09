package com.cocobob.server.domain;

import lombok.Setter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Setter
@Getter
@RequiredArgsConstructor
public class BoardRequestDto {
    private final String title;
    //private final String username;
    private final String contents;
    private final String tag;
    private final String deadline;
}