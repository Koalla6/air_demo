package alla.verkhohliadova.air_demo.controller;

import alla.verkhohliadova.air_demo.dto.request.AircompanyRequest;
import alla.verkhohliadova.air_demo.dto.response.AircompanyResponse;
import alla.verkhohliadova.air_demo.service.AircompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/aircompany")
public class AircompanyController {
    @Autowired
    private AircompanyService aircompanyService;

    @PostMapping(value = "/p")
    public void create(@RequestBody AircompanyRequest request) {
        aircompanyService.create(request);
    }

    @GetMapping(value = "/g")
    public List<AircompanyResponse> findAll(){
        return aircompanyService.findAll();
    }


}
