package server.drawwang.domain.thread.repository;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import server.drawwang.domain.thread.entity.ThreadEntity;

import java.util.Optional;

public interface ThreadRepository extends JpaRepository<ThreadEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints(@QueryHint(name = "javax.persistence.lock.timeout", value = "1000"))
    Optional<ThreadEntity> findById(Long id);
}
