package alla.verkhohliadova.air_demo.service;

import alla.verkhohliadova.air_demo.dto.request.AirplaneRequest;
import alla.verkhohliadova.air_demo.dto.request.FlightRequest;
import alla.verkhohliadova.air_demo.dto.response.AirplaneResponse;
import alla.verkhohliadova.air_demo.dto.response.FlightResponse;
import alla.verkhohliadova.air_demo.entity.Aircompany;
import alla.verkhohliadova.air_demo.entity.Airplane;
import alla.verkhohliadova.air_demo.entity.Flight;
import alla.verkhohliadova.air_demo.entity.FlightStatus;
import alla.verkhohliadova.air_demo.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AircompanyService aircompanyService;
    @Autowired
    private AirplaneService airplaneService;

    public void create(FlightRequest request) {
        Flight flight = requestToFlight(new Flight(), request);
        flightRepository.save(flight);
    }

    private Flight requestToFlight(Flight flight, FlightRequest request) {
        //flight.setFlightStatus(flightStatus());
        flight.setFlightStatus(FlightStatus.PENDING);
        //flight.setFlightStatus(request.getFlightStatus());
        flight.setAircompany(aircompanyService.chooseOne());
        flight.setAirplane(airplaneService.chooseOne());
        flight.setDepartureCountry(request.getDepartureCountry());
        flight.setDestinationCountry(request.getDestinationCountry());
        flight.setDistance(request.getDistance());
        flight.setEstimatedFlightTime(request.getEstimatedFlightTime());
        flight.setEndedAt(request.getEndedAt());
        flight.setDelayStartedAt(request.getDelayStartedAt());
        flight.setCreatedAt(request.getCreatedAt());
        return flight;
    }

    public List<FlightResponse> findAll() {
        List<Flight> all = flightRepository.findAll();
        return all.stream()
                .map(this::flightToFlightResponse)
                .collect(Collectors.toList());
    }

    private FlightResponse flightToFlightResponse(Flight flight) {
        FlightResponse flightResponse = new FlightResponse(flight);
        flightResponse.setFlightStatus(flight.getFlightStatus());
        flightResponse.setAircompany(flight.getAircompany());
        flightResponse.setAirplane(flight.getAirplane());
        flightResponse.setDepartureCountry(flight.getDepartureCountry());
        flightResponse.setDestinationCountry(flight.getDestinationCountry());
        flightResponse.setDistance(flight.getDistance());
        flightResponse.setEstimatedFlightTime(flight.getEstimatedFlightTime());
        flightResponse.setEndedAt(flight.getEndedAt());
        flightResponse.setDelayStartedAt(flight.getDelayStartedAt());
        flightResponse.setCreatedAt(flight.getCreatedAt());
        return flightResponse;
    }

    public Flight changeStatus(Long id, FlightStatus flightStatus) {
        Flight flight = flightRepository.getOne(id);
        switch (flightStatus) {
            case ACTIVE:
                flight.setFlightStatus(FlightStatus.ACTIVE);
                flight.setCreatedAt(LocalDateTime.now());
                break;
            case DELAYED:
                flight.setFlightStatus(FlightStatus.DELAYED);
                flight.setDelayStartedAt(LocalDateTime.now());
                break;
            case COMPLETED:
                flight.setFlightStatus(FlightStatus.COMPLETED);
                flight.setEndedAt(LocalDateTime.now());
                break;
        }
        flight = flightRepository.save(flight);
        return flight;
    }

    public List<FlightResponse> findAllByStatus(Aircompany aircompany, FlightStatus flightStatus) {
        List<Flight> all = aircompany.getFlights();
        List<Flight> flightsWithNeedStatus = new ArrayList<>();
        for (Flight flight : all) {
            if (flight.getFlightStatus().equals(flightStatus)) {
                flightsWithNeedStatus.add(flight);
            }
        }

        return flightsWithNeedStatus.stream()
                .map(this::flightToFlightResponse)
                .collect(Collectors.toList());
    }

    public List<FlightResponse> findAllActiveFlights() {
        List<Flight> all = flightRepository.findAll();
        List<Flight> flightsWithNeedStatus = new ArrayList<>();
        //LocalDateTime checkTime = ;
        for (Flight flight : all) {
            if (flight.getCreatedAt() != null) {//.compareTo(flight.getCreatedAt().minusHours(24))<0){
                if (flight.getFlightStatus().equals(FlightStatus.ACTIVE) || flight.getCreatedAt().compareTo(flight.getCreatedAt().minusHours(24)) < 0) {
                    flightsWithNeedStatus.add(flight);
                }
            }
        }
        return flightsWithNeedStatus.stream()
                .map(this::flightToFlightResponse)
                .collect(Collectors.toList());
    }

    public List<FlightResponse> findCompletedWithIF(){
        LocalTime localTime = LocalTime.of(0,0);
        Long plL, ctL;
        Duration planed;
        Duration checkTime;

        List<Flight> all = flightRepository.findAll();
        List<Flight> flightsWithNeedStatus = new ArrayList<>();
        for (Flight flight: all){
            if(flight.getFlightStatus().equals(FlightStatus.COMPLETED)){
                checkTime = Duration.between(flight.getEndedAt(), flight.getCreatedAt());
                ctL = Math.abs(checkTime.toMinutes());
                planed = Duration.between(flight.getEstimatedFlightTime(), localTime);
                plL = Math.abs(planed.toMinutes());

                if (ctL > plL) {
                    flightsWithNeedStatus.add(flight);
                }
            }
        }
        return flightsWithNeedStatus.stream()
                .map(this::flightToFlightResponse)
                .collect(Collectors.toList());
    }
}
