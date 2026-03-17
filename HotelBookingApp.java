/*
 *Use Case 7: Add-On Service Selection
 * @author Shikher
 * @version 7.0
 */

import java.util.*;

class AddOnService{
    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost){
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName(){
        return serviceName;
    }

    public double getCost(){
        return cost;
    }
}

class AddOnServiceManager {
    private Map<String, List<AddOnService>> servicesByReservation;

    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    public void addService(String reservationID, AddOnService service) {

        servicesByReservation
                .computeIfAbsent(reservationID, k -> new ArrayList<>())
                .add(service);
    }


    public double calculateTotalServiceCost(String reservationID) {
        List<AddOnService> services = servicesByReservation.get(reservationID);

        if (services == null || services.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;
        for (AddOnService service : services) {
            total += service.getCost();
        }
        return total;
    }
}

public class HotelBookingApp {
    public static void main(String[] args) {
        System.out.println("Add-On Service Selection");

        AddOnServiceManager serviceManager = new AddOnServiceManager();


        String reservationID = "Single-1";

        serviceManager.addService(reservationID, new AddOnService("Spa Treatment", 1000.0));
        serviceManager.addService(reservationID, new AddOnService("Late Check-out", 500.0));

        System.out.println("Reservation ID: " + reservationID);
        double totalCost = serviceManager.calculateTotalServiceCost(reservationID);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}