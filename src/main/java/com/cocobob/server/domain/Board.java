package com.cocobob.server.domain;


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

    //  @OneToMany(mappedBy = "board")
//    private List<Reply> reply;

    public Board(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.tag = requestDto.getTag();
        this.deadline = requestDto.getDeadline();
    }

    public Board(String title, String username, String contents) {
        this.title = title;
        this.username = username;
        this.contents = contents;
    }

    public Board(String title, String username, String contents, String tag, String deadline) {
        this.title = title;
        this.username = username;
        this.contents = contents;
        this.tag = tag;
        this.deadline = deadline;
    }

}