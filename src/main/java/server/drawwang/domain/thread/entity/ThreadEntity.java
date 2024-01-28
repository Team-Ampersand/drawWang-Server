package server.drawwang.domain.thread.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.drawwang.domain.board.entity.BoardEntity;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "thread")
public class ThreadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "thread_name")
    private String threadName;

    @Column(name = "expired_time")
    private String expiredTime;

    @OneToMany(mappedBy = "thread_id", cascade = CascadeType.ALL)
    private List<BoardEntity> boardList;

    @Column(name = "king_board_id")
    private Long kingBoardId;

}
