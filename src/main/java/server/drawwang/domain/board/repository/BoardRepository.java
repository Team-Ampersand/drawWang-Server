package server.drawwang.domain.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.drawwang.domain.board.entity.BoardEntity;
import server.drawwang.domain.thread.entity.ThreadEntity;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findAllByThread(ThreadEntity thread);
}
