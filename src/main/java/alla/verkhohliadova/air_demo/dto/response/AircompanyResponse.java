package alla.verkhohliadova.air_demo.dto.response;

import alla.verkhohliadova.air_demo.entity.Aircompany;
import alla.verkhohliadova.air_demo.entity.Airplane;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class AircompanyResponse {
    private Long id;
    private String name;
    private List<Airplane> airplanesOfThisCompany;
    private String companyType;
    private LocalDate foundedAt;

    public AircompanyResponse(Aircompany aircompany){
        id = aircompany.getId();
        name = aircompany.getName();
        airplanesOfThisCompany = aircompany.getAirplanesOfThisCompany();
        companyType = aircompany.getCompanyType();
        foundedAt = aircompany.getFoundedAt();
    }
}
