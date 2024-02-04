package server.drawwang.domain.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.drawwang.domain.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
