/*
 * Use Case 10: Booking Cancellation & Inventory Rollback
 * @author Shikher
 * @version 10.0
 */

import java.util.*;

class CancellationService {
    private Stack<String> releasedRoomIds;
    private Map<String, String> reservationRoomTypeMap;

    public CancellationService() {
        releasedRoomIds = new Stack<>();
        reservationRoomTypeMap = new HashMap<>();
    }


    public void registerBooking(String reservationId, String roomType) {
        reservationRoomTypeMap.put(reservationId, roomType);
    }


    public void cancelBooking(String reservationId, RoomInventory inventory) {
        if (!reservationRoomTypeMap.containsKey(reservationId)) {
            System.out.println("Error: Reservation ID not found.");
            return;
        }

        String roomType = reservationRoomTypeMap.get(reservationId);

        inventory.addRoom(roomType);

        releasedRoomIds.push(reservationId);

        reservationRoomTypeMap.remove(reservationId);

        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
    }

    public void showRollbackHistory() {
        System.out.println("Rollback History (Most Recent First):");
        for (int i = releasedRoomIds.size() - 1; i >= 0; i--) {
            System.out.println("Released Reservation ID: " + releasedRoomIds.get(i));
        }
    }
}

class RoomInventory {
    private Map<String, Integer> availableRooms = new HashMap<>();

    public RoomInventory() {
        // Initializing with some dummy data
        availableRooms.put("Single", 5);
    }

    public void addRoom(String type) {
        availableRooms.put(type, availableRooms.getOrDefault(type, 0) + 1);
    }

    public int getAvailability(String type) {
        return availableRooms.getOrDefault(type, 0);
    }
}

public class HotelBookingApp {
    public static void main(String[] args) {
        System.out.println("Booking Cancellation");

        RoomInventory inventory = new RoomInventory();
        CancellationService cancellationService = new CancellationService();

        String resId = "Single-1";
        cancellationService.registerBooking(resId, "Single");

        cancellationService.cancelBooking(resId, inventory);

        cancellationService.showRollbackHistory();
        System.out.println("Updated Single Room Availability: " + inventory.getAvailability("Single"));
    }
}