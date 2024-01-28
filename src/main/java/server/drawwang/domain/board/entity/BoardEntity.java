package server.drawwang.domain.board.entity;

import jakarta.persistence.*;
import server.drawwang.domain.thread.entity.ThreadEntity;

@Entity
@Table(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @ManyToOne
    @JoinColumn(name = "thread_id", nullable = false)
    private ThreadEntity thread;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "likes")
    private int likes;

}
