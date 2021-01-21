package alla.verkhohliadova.air_demo.repository;

import alla.verkhohliadova.air_demo.entity.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
}
