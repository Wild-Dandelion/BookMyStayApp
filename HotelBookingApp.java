/*
 *Use Case 4: Room Search & Availability Check
 * @author Shikher
 * @version 3.1
 */

import java.util.HashMap;
import java.util.Map;

class RoomSearchService {

    public void searchAvailableRooms(
            RoomInventory inventory,
            SingleRoom singleRoom,
            DoubleRoom doubleRoom,
            SuiteRoom suiteRoom) {

        System.out.println("Room Search:");
        // Display Single Room
        int singleAvail = inventory.getAvailability("Single Room");
        if (singleAvail > 0) {
            singleRoom.displayRoomDetails(singleAvail);
        }

        int doubleAvail = inventory.getAvailability("Double Room");
        if (doubleAvail > 0) {
            doubleRoom.displayRoomDetails(doubleAvail);
        }

        int suiteAvail = inventory.getAvailability("Suite Room");
        if (suiteAvail > 0) {
            suiteRoom.displayRoomDetails(suiteAvail);
        }
    }
}

class RoomInventory {
    private Map<String, Integer> roomAvailability;

    RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() {
        roomAvailability.put("Single Room", 5);
        roomAvailability.put("Double Room", 3);
        roomAvailability.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return roomAvailability.getOrDefault(roomType, 0);
    }
}

abstract class Room {
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;
    protected String roomType;

    public Room(String roomType, int numberOfBeds, int squareFeet, double pricePerNight) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    public void displayRoomDetails(int availableRooms) {
        System.out.println(roomType + ":");
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + squareFeet + " sqft");
        System.out.println("Price per night: " + pricePerNight);
        System.out.println("Available: " + availableRooms);
        System.out.println();
    }
}

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 250, 1500.0);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 400, 2500.0);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 750, 5000.0);
    }
}

/**
 * MAIN CLASS UseCase4RoomSearch
 */
public class HotelBookingApp {
    public static void main(String[] args) {
        // Initialize Core Components
        RoomInventory inventory = new RoomInventory();
        RoomSearchService searchService = new RoomSearchService();

        // Instantiate Room Types
        SingleRoom sr = new SingleRoom();
        DoubleRoom dr = new DoubleRoom();
        SuiteRoom sur = new SuiteRoom();

        // Execute Search (Read-Only)
        searchService.searchAvailableRooms(inventory, sr, dr, sur);
    }
}