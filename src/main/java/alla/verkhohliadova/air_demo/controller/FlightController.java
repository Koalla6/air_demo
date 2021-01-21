package alla.verkhohliadova.air_demo.controller;

import alla.verkhohliadova.air_demo.dto.request.AirplaneRequest;
import alla.verkhohliadova.air_demo.dto.request.FlightRequest;
import alla.verkhohliadova.air_demo.dto.response.AirplaneResponse;
import alla.verkhohliadova.air_demo.dto.response.FlightResponse;
import alla.verkhohliadova.air_demo.entity.Aircompany;
import alla.verkhohliadova.air_demo.entity.Flight;
import alla.verkhohliadova.air_demo.entity.FlightStatus;
import alla.verkhohliadova.air_demo.service.AircompanyService;
import alla.verkhohliadova.air_demo.service.AirplaneService;
import alla.verkhohliadova.air_demo.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;
    @Autowired
    private AircompanyService aircompanyService;

    @PostMapping(value = "/p")
    public void create(@RequestBody FlightRequest request) {
        flightService.create(request);
    }

    @GetMapping(value = "/g")
    public List<FlightResponse> findAll(){
        return flightService.findAll();
    }

    @PutMapping(value = "/{id}/{flightStatus}")
    public Flight changeStatus(@PathVariable Long id, @PathVariable FlightStatus flightStatus){
        return flightService.changeStatus(id, flightStatus);
    }

    @GetMapping(value = "/{aircompanyName}/{flightStatus}") //Iberia/PENDING
    public List<FlightResponse> findFlightOfAircompanyByStatus(@PathVariable String aircompanyName, @PathVariable FlightStatus flightStatus){
    //public Aircompany findFlightOfAircompanyByStatus(@PathVariable String aircompanyName, @PathVariable FlightStatus flightStatus){
        return aircompanyService.findFlightOfAircompanyByStatus(aircompanyName, flightStatus);
    }

    @GetMapping(value = "/active")
    public List<FlightResponse> findAllActiveFlights(){
        return flightService.findAllActiveFlights();
    }

    @GetMapping(value = "/completed")
    public List<FlightResponse> findCompletedWithIF(){
        return flightService.findCompletedWithIF();
    }
}
