package server.drawwang.domain.thread.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "thread")
@Builder
public class ThreadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "thread_name")
    private String threadName;

    @Column(name = "king_board_id")
    private Long kingBoardId;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

}
