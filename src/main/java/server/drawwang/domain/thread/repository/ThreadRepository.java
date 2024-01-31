package server.drawwang.domain.thread.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.drawwang.domain.thread.entity.ThreadEntity;

public interface ThreadRepository extends JpaRepository<ThreadEntity, Long> {
}
