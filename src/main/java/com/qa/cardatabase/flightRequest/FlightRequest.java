package com.qa.cardatabase.flightRequest;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.google.gson.JsonObject;

import java.io.*;

public class FlightRequest {
    public static void main(String[] args) throws ResponseException {
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

//        FlightOfferSearch[] flightOffersPrediction = amadeus.shopping.flightOffers.prediction.post(body);
//        FlightOfferSearch[] upsellFlightOffers = amadeus.shopping.flightOffers.upselling.post(flightOffersSearches[0]);

        if (flightOffers[0].getResponse().getStatusCode() != 200) {
            System.out.println("Wrong status code: " + flightOffers[0].getResponse().getStatusCode());
            System.exit(-1);
        }

        System.out.println(flightOffers[0]);

        for (int i = 0; i < flightOffers.length; i++) {
            System.out.println(flightOffers[i]);

            String[] flights = {String.valueOf(flightOffers[0])};

            File file = new File("./flights.txt");
            if (file.exists()) {
                PrintStream ps = null;
                try {
                    ps = new PrintStream(file);
                    for (String flight : flights) {
                        ps.println(flight);
                    }
                } catch (FileNotFoundException fnfe) {
                    System.out.println("Not found");
                } finally {
                    ps.close();
                }
            }
        }
    }
}


