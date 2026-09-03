package hr.tvz.laploggerapp.repository;

import hr.tvz.laploggerapp.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findAllByCarId(Long carId);
}
