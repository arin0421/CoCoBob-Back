package com.cocobob.server.domain;

@Setter
@Getter
@RequiredArgsConstructor
public class BoardRequestDto {
    private final String title;
    private final String username;
    private final String contents;
    private final String tag;
    private final String deadline;
}