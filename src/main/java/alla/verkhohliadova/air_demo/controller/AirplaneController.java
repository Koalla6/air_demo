package alla.verkhohliadova.air_demo.controller;

import alla.verkhohliadova.air_demo.dto.request.AirplaneRequest;
import alla.verkhohliadova.air_demo.dto.response.AirplaneResponse;
import alla.verkhohliadova.air_demo.entity.Aircompany;
import alla.verkhohliadova.air_demo.entity.Airplane;
import alla.verkhohliadova.air_demo.service.AircompanyService;
import alla.verkhohliadova.air_demo.service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/airplane")
public class AirplaneController {

        @Autowired
        private AirplaneService airplaneService;
        @Autowired
        private AircompanyService aircompanyService;

        @PostMapping(value = "/p")
        public void create(@RequestBody AirplaneRequest request) {
            airplaneService.create(request);
        }

        @GetMapping(value = "/g")
        public List<AirplaneResponse> findAll(){
            return airplaneService.findAll();
        }

        @GetMapping(value = "/{factorySerialNumber}")
        public Aircompany findByAirplane(@PathVariable String factorySerialNumber){
            return airplaneService.findOne(factorySerialNumber);
        }

        @PutMapping(value = "/{id}/{newAircompanyId}")
        public Airplane changeAircompany(@PathVariable Long id, @PathVariable Long newAircompanyId){
            return airplaneService.changeAircompany(id, newAircompanyId);
        }

    }
