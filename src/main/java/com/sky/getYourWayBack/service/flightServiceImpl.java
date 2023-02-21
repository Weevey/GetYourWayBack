package com.sky.getYourWayBack.service;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

@Service
public class flightServiceImpl implements flightService{
    public Flight getFlight(String departure, String destination, String date, int adults) throws Exception {
        Amadeus amadeus = Amadeus.builder("4DoCEKYpLX4r6rh1QUWPTa4LrfCSvmIk", "Kl1wTf1LricDruPO").build();

        FlightOfferSearch[] flightOffers = amadeus.shopping.flightOffersSearch.get(
                Params.with("originLocationCode", "LGW")
                        .and("destinationLocationCode", "RAK")
                        .and("departureDate", "2023-02-25")
                        .and("adults", 1)
                        .and("max", 1)
        );

        JsonObject body = flightOffers[0].getResponse().getResult();
        System.out.println(body);

        if (flightOffers[0].getResponse().getStatusCode() != 200) {
            System.out.println("Wrong status code: " + flightOffers[0].getResponse().getStatusCode());
            System.exit(-1);
        }

        return null;
    }
}
