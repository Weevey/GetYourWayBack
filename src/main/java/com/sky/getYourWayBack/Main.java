import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.resources.FlightOfferSearch;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter departure airport: ");
        String departure = scanner.nextLine();
        System.out.print("Enter destination airport: ");
        String destination = scanner.nextLine();
        System.out.print("Enter date (yyyy-MM-dd): ");
        String date = scanner.nextLine();
        System.out.print("Enter number of adults: ");
        int adults = scanner.nextInt();

        getFlight(departure, destination, date, adults);
    }

    public static void getFlight(String departure, String destination, String date, int adults) throws Exception {
        Amadeus amadeus = Amadeus.builder("4DoCEKYpLX4r6rh1QUWPTa4LrfCSvmIk", "Kl1wTf1LricDruPO").build();

        FlightOfferSearch[] flightOffers = amadeus.shopping.flightOffersSearch.get(
                Params.with("originLocationCode", departure)
                        .and("destinationLocationCode", destination)
                        .and("departureDate", date)
                        .and("adults", adults)
                        .and("max", 1)
        );

        JsonObject response = flightOffers[0].getResponse().getResult();
        JsonArray dataArray = response.getAsJsonArray("data");

        if (dataArray.size() == 0) {
            System.out.println("No flight offers found");
            return;
        }

        JsonObject firstOffer = dataArray.get(0).getAsJsonObject();
        JsonObject price = firstOffer.getAsJsonObject("price");

        System.out.println("Duration: " + firstOffer.getAsJsonArray("itineraries")
                .get(0).getAsJsonObject().get("duration").getAsString());
        System.out.println("Cost: " + price.get("total").getAsString() + " " + price.get("currency").getAsString());
    }
} 