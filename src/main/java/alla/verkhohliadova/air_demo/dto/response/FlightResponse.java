package alla.verkhohliadova.air_demo.dto.response;

import alla.verkhohliadova.air_demo.entity.Aircompany;
import alla.verkhohliadova.air_demo.entity.Airplane;
import alla.verkhohliadova.air_demo.entity.Flight;
import alla.verkhohliadova.air_demo.entity.FlightStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class FlightResponse {
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

    public FlightResponse(Flight flight){
        flightStatus = flight.getFlightStatus();
        aircompany = flight.getAircompany();
        airplane = flight.getAirplane();
        departureCountry = flight.getDepartureCountry();
        destinationCountry = flight.getDestinationCountry();
        distance = flight.getDistance();
        estimatedFlightTime = flight.getEstimatedFlightTime();
        endedAt = flight.getEndedAt();
        delayStartedAt = flight.getDelayStartedAt();
        createdAt = flight.getCreatedAt();
    }
}
