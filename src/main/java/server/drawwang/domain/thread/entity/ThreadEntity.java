package server.drawwang.domain.thread.entity;

import jakarta.persistence.*;

import lombok.Getter;
import server.drawwang.domain.board.entity.BoardEntity;
import java.util.List;


@Entity
@Getter
@Table(name = "thread")
public class ThreadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "thread_name", nullable = false)
    private String threadName;

    @Column(name = "expiry_time", nullable = false)
    private String expiryTime;

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL)
    private List<BoardEntity> boardList;

    @OneToOne
    @JoinColumn(name = "king")
    private BoardEntity king;

}
