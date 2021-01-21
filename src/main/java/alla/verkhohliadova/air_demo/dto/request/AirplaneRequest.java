package alla.verkhohliadova.air_demo.dto.request;

import alla.verkhohliadova.air_demo.entity.Aircompany;
import alla.verkhohliadova.air_demo.entity.Flight;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class AirplaneRequest {
    private String name;
    private String factorySerialNumber;
    private Aircompany aircompany;
    private List<Flight> numberOfFlights;
    private Integer flightDistance;
    private Integer fuelCapacity;
    private String type;
    private LocalDate createdAt;
}
