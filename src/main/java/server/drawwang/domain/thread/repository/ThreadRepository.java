package server.drawwang.domain.thread.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.drawwang.domain.thread.entity.ThreadEntity;

import java.util.Optional;

public interface ThreadRepository extends JpaRepository<ThreadEntity, Long> {
    Optional<ThreadEntity> findById(Long id);
}
