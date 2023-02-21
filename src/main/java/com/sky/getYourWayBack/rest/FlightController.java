package com.sky.getYourWayBack.rest;

import com.sky.getYourWayBack.data.entity.Flight;
import com.sky.getYourWayBack.service.FlightService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin (origins = "http://localhost:3000")

public class FlightController {

    private FlightService service;
    public FlightController(FlightService service){this.service = service;}
    @GetMapping("/flight")
    public String flightRequest(@RequestBody Flight flight){

        Flight toCreate = new Flight(flight.getDeparture(), flight.getDestination(),
                flight.getDate(), flight.getAdults());
        String created = "";
        try {


            created = this.service.getFlightAsString(toCreate);
        }
        catch(Exception e){

        }

        return created;

    }



}
