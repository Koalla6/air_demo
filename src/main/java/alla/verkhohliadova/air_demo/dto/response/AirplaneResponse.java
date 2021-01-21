package alla.verkhohliadova.air_demo.dto.response;

import alla.verkhohliadova.air_demo.entity.Aircompany;
import alla.verkhohliadova.air_demo.entity.Airplane;
import alla.verkhohliadova.air_demo.entity.Flight;
import alla.verkhohliadova.air_demo.service.AirplaneService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class AirplaneResponse {
    private String name;
    private String factorySerialNumber;
    private Aircompany aircompany;
    private List<Flight> numberOfFlights;
    private Integer flightDistance;
    private Integer fuelCapacity;
    private String type;
    private LocalDate createdAt;

    public AirplaneResponse(Airplane airplane) {
        name = airplane.getName();
        factorySerialNumber = airplane.getFactorySerialNumber();
        aircompany = airplane.getAircompany();
        numberOfFlights = airplane.getNumberOfFlights();
        flightDistance = airplane.getFlightDistance();
        fuelCapacity = airplane.getFuelCapacity();
        type = airplane.getType();
        createdAt = airplane.getCreatedAt();
    }
}
