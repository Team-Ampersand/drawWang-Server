package server.drawwang.domain.board.entity;

import jakarta.persistence.*;
import server.drawwang.domain.thread.entity.ThreadEntity;

@Entity
@Table(name = "Board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "UserName", nullable = false)
    private String userName;

    @ManyToOne
    @JoinColumn(name = "ThreadId", nullable = false)
    private ThreadEntity thread;

    @Column(name = "ImageUrl")
    private String imageUrl;

    @Column(name = "Likes")
    private int likes;

}
