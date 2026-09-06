package hr.tvz.laploggerapp.repository;

import hr.tvz.laploggerapp.model.Lap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LapRepository extends JpaRepository<Lap,Long> {
    List<Lap> findAllBySessionId(Long sessionId);
}
