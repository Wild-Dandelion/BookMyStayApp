/*
 *Use Case 3: Centralized Room Inventory Management
 * @author Shikher
 * @version 3.1
 */

import java.util.HashMap;
import java.util.Map;

class RoomInventory {
    private Map<String, Integer> roomAvailability;

    RoomInventory(){
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory(){
        roomAvailability.put("Single Room", 5);
        roomAvailability.put("Double Room", 3);
        roomAvailability.put("Suite Room", 2);
    }

    public int getAvailability(String roomType){
        return roomAvailability.get(roomType);
    }
}

abstract class Room{
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;
    protected String roomType;

    public Room(String roomType, int numberOfBeds, int squareFeet, double pricePerNight){
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    public void displayRoomDetails(int availableRooms){
        System.out.println(roomType + ":");
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + squareFeet + " sqft");
        System.out.println("Price per night: " + pricePerNight);
        System.out.println("Available Rooms: " + availableRooms);
        System.out.println();
    }
}

class SingleRoom extends Room{
    public SingleRoom(){
        super("Single Room",1,250,1500.0);
    }
}

class DoubleRoom extends Room{
    public DoubleRoom(){
        super("Double Room",2,400,2500.0);
    }
}

class SuiteRoom extends Room{
    public SuiteRoom(){
        super("Suite Room",3,750,5000.0);
    }
}

public class HotelBookingApp{
    public static void main(String [] args){

        RoomInventory inventory = new RoomInventory();
        System.out.println("Hotel Room Inventory Status\n");
        SingleRoom sr = new SingleRoom();
        sr.displayRoomDetails(inventory.getAvailability("Single Room"));
        DoubleRoom dr = new DoubleRoom();
        dr.displayRoomDetails(inventory.getAvailability("Double Room"));
        SuiteRoom sur = new SuiteRoom();
        sur.displayRoomDetails(inventory.getAvailability("Suite Room"));
    }
}