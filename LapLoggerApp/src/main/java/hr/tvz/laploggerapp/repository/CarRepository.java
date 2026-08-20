package hr.tvz.laploggerapp.repository;

import hr.tvz.laploggerapp.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByMakeIgnoreCase(String make);
}
