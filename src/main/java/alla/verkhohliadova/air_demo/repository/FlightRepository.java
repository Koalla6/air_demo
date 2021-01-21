package alla.verkhohliadova.air_demo.repository;

import alla.verkhohliadova.air_demo.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
}
