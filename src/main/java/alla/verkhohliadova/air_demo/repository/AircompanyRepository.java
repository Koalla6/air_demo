package alla.verkhohliadova.air_demo.repository;

import alla.verkhohliadova.air_demo.entity.Aircompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AircompanyRepository extends JpaRepository<Aircompany, Long> {
//    Optional<Aircompany> findAllByAirplanesOfThisCompany(String name);
}
