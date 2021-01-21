package alla.verkhohliadova.air_demo.service;

import alla.verkhohliadova.air_demo.dto.request.AircompanyRequest;
import alla.verkhohliadova.air_demo.dto.response.AircompanyResponse;
import alla.verkhohliadova.air_demo.dto.response.FlightResponse;
import alla.verkhohliadova.air_demo.entity.Aircompany;
import alla.verkhohliadova.air_demo.entity.Airplane;
import alla.verkhohliadova.air_demo.entity.Flight;
import alla.verkhohliadova.air_demo.entity.FlightStatus;
import alla.verkhohliadova.air_demo.repository.AircompanyRepository;
import alla.verkhohliadova.air_demo.repository.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AircompanyService {
    @Autowired
    private AircompanyRepository aircompanyRepository;
    @Autowired
    private AirplaneRepository airplaneRepository;
    @Autowired
    private FlightService flightService;

    public void create(AircompanyRequest request) {
        Aircompany aircompany = requestToAircompany(new Aircompany(), request);
        aircompanyRepository.save(aircompany);
    }

    public List<AircompanyResponse> findAll() {
        List<Aircompany> all = aircompanyRepository.findAll();
        return all.stream()
                .map(this::aircompanyToAircompanyResponse)
                .collect(Collectors.toList());
    }

    public Aircompany findOne(Long id) {
        return aircompanyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aircompany with id " + id + " not exists"));
    }

    private AircompanyResponse aircompanyToAircompanyResponse(Aircompany aircompany) {
        AircompanyResponse aircompanyResponse = new AircompanyResponse(aircompany);
        aircompanyResponse.setName(aircompany.getName());
        aircompanyResponse.setAirplanesOfThisCompany(aircompany.getAirplanesOfThisCompany());
        aircompanyResponse.setCompanyType(aircompany.getCompanyType());
        aircompanyResponse.setFoundedAt(aircompany.getFoundedAt());
        return aircompanyResponse;
    }

    private Aircompany requestToAircompany(Aircompany aircompany, AircompanyRequest request) {
        aircompany.setName(request.getName());
        //aircompany.setAirplanesOfThisCompany(request.getAirplanesOfThisCompany());
        aircompany.setCompanyType(request.getCompanyType());
        aircompany.setFoundedAt(request.getFoundedAt());
        return aircompany;
    }

    public Aircompany chooseOne() {
        //public Long chooseOneForAirplane() {
        Random random = new Random();
        List<Aircompany> all = aircompanyRepository.findAll();
        int min = 1;
        int max = all.size();
        int res = max - min;
        int ai = random.nextInt(res + 1);
        Long AI = Long.valueOf(ai);
        if (ai==0) {
            AI = 1L;
        }
        return findOne(AI);
        //return AI;
    }

    public List<FlightResponse> findFlightOfAircompanyByStatus(String aircompanyName, FlightStatus flightStatus){
        Aircompany company = new Aircompany();
        List<Aircompany> all = aircompanyRepository.findAll();
        for (Aircompany aircompany:all){
            if(aircompany.getName().equals(aircompanyName)){
                company = aircompany;
            }
        }
        return flightService.findAllByStatus(company, flightStatus);
    }
    //
}
