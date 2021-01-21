package alla.verkhohliadova.air_demo.dto.request;

import alla.verkhohliadova.air_demo.entity.Aircompany;
import alla.verkhohliadova.air_demo.entity.Airplane;
import alla.verkhohliadova.air_demo.entity.FlightStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class FlightRequest {
    private FlightStatus flightStatus; // = FlightStatus.PENDING;
    private Aircompany aircompany;
    private Airplane airplane;
    private String departureCountry;
    private String destinationCountry;
    private Float distance;
    private LocalTime estimatedFlightTime;
    private LocalDateTime endedAt;
    private LocalDateTime delayStartedAt;
    private LocalDateTime createdAt;
}
