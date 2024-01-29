package server.drawwang.domain.thread.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name = "king_board_id")
    private Long kingBoardId;

    public ThreadEntity(String threadName, String expiredTime, Long kingBoardId) {
        this.threadName = threadName;
        this.expiredTime = expiredTime;
        this.kingBoardId = kingBoardId;
    }
}
