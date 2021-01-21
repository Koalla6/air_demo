package alla.verkhohliadova.air_demo.dto.request;

import alla.verkhohliadova.air_demo.entity.Airplane;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class AircompanyRequest {
    private String name;
    private List<Airplane> airplanesOfThisCompany;
    private String companyType;
    private LocalDate foundedAt;
}
