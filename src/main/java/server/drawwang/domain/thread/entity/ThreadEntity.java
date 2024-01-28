package server.drawwang.domain.thread.entity;

import jakarta.persistence.*;

import lombok.Getter;
import server.drawwang.domain.board.entity.BoardEntity;
import java.util.List;


@Entity
@Getter
@Table(name = "Thread")
public class ThreadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ThreadName", nullable = false)
    private String threadName;

    @Column(name = "ExpiryTime", nullable = false)
    private String expiryTime;

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL)
    private List<BoardEntity> boardList;

    @OneToOne
    @JoinColumn(name = "King")
    private BoardEntity king;

}
