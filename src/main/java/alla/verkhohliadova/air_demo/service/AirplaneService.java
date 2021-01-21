package alla.verkhohliadova.air_demo.service;

import alla.verkhohliadova.air_demo.dto.request.AirplaneRequest;
import alla.verkhohliadova.air_demo.dto.response.AirplaneResponse;
import alla.verkhohliadova.air_demo.entity.Aircompany;
import alla.verkhohliadova.air_demo.entity.Airplane;
import alla.verkhohliadova.air_demo.repository.AircompanyRepository;
import alla.verkhohliadova.air_demo.repository.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AirplaneService {
    @Autowired
    private AirplaneRepository airplaneRepository;
    @Autowired
    private AircompanyService aircompanyService;
    @Autowired
    private AircompanyRepository aircompanyRepository;


    public void create(AirplaneRequest request){
        Airplane airplane = requestToAirplane(new Airplane(), request);
        airplaneRepository.save(airplane);
    }

    private Airplane requestToAirplane(Airplane airplane, AirplaneRequest request){
        airplane.setName(request.getName());
        airplane.setFactorySerialNumber(request.getFactorySerialNumber());
        airplane.setAircompany(aircompanyService.chooseOne());
        //airplane.setNumberOfFlights(request.getNumberOfFlights());
        airplane.setFlightDistance(request.getFlightDistance());
        airplane.setFuelCapacity(request.getFuelCapacity());
        airplane.setType(request.getType());
        airplane.setCreatedAt(request.getCreatedAt());
        return airplane;
    }

    public List<AirplaneResponse> findAll() {
        List<Airplane> all = airplaneRepository.findAll();
        return all.stream()
                .map(this::airplaneToAirplaneResponse)
                .collect(Collectors.toList());
    }

    private AirplaneResponse airplaneToAirplaneResponse(Airplane airplane) {

        AirplaneResponse airplaneResponse = new AirplaneResponse(airplane);
        airplaneResponse.setName(airplane.getName());
        airplaneResponse.setFactorySerialNumber(airplane.getFactorySerialNumber());
        //
        airplaneResponse.setAircompany(airplane.getAircompany());
        //
        airplaneResponse.setNumberOfFlights(airplane.getNumberOfFlights());
        airplaneResponse.setFlightDistance(airplane.getFlightDistance());
        airplaneResponse.setFuelCapacity(airplane.getFuelCapacity());
        airplaneResponse.setType(airplane.getType());
        airplaneResponse.setCreatedAt(airplane.getCreatedAt());
        return airplaneResponse;
    }

    public Airplane findOne(Long id) {
        return airplaneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Airplane with id " + id + " not exists"));
    }

    public Airplane chooseOne() {
        Random random = new Random();
        List<Airplane> all = airplaneRepository.findAll();
        int min = 1;
        int max = all.size();
        int res = max - min;
        int ai = random.nextInt(res + 1);
        Long AI = Long.valueOf(ai);
        if (ai==0) {
            AI = 1L;
        }
        return findOne(AI);
    }

    public Aircompany findOne(String factorySerialNumber) {
        Aircompany aircompany;
        Airplane air = new Airplane();
        List<Airplane> all = airplaneRepository.findAll();
        for (Airplane airplane : all) {
            if (airplane.getFactorySerialNumber().equals(factorySerialNumber)) {
                air = airplane;
            }
        }
        aircompany = air.getAircompany();
        return aircompany;
    }

    public Airplane changeAircompany(Long airplaneId, Long newAircompanyId){
        Airplane airplane;
        Aircompany aircompany;
        //System.out.println(aircompany);
        airplane = airplaneRepository.getOne(airplaneId);
        aircompany = aircompanyRepository.getOne(newAircompanyId);
        //System.out.println(aircompany);
        airplane.setAircompany(aircompany);
        airplane = airplaneRepository.save(airplane);
        return airplane;
    }


    //
    public Long aircompanyId(Airplane airplane){
        Long companyId;
        Aircompany aircompany = airplane.getAircompany();
        companyId = aircompany.getId();
        return companyId;
    }
    //


}
