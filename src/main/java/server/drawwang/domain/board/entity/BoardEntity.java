package server.drawwang.domain.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.drawwang.domain.thread.entity.ThreadEntity;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "board")
@Builder
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @ManyToOne
    @JoinColumn(name = "thread")
    private ThreadEntity thread;

    @Column(name = "image_id")
    private String imageId;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "reports")
    private Integer reports;

    public void plusLike() {
        this.likes++;
    }

    public void plusReports() {
        this.reports++;
    }
}
