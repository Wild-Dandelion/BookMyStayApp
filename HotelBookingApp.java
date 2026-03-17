/*
 *Use Case 6: Reservation Confirmation & Room Allocation
 * @author Shikher
 * @version 6.0
 */

import java.util.*;


class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
}


class RoomInventory {
    public boolean checkAndReduce(String type) {

        return true;
    }
}


class RoomAllocationService {
    private Set<String> allocatedRoomIds;
    private Map<String, Set<String>> assignedRoomsByType;

    public RoomAllocationService() {
        this.allocatedRoomIds = new HashSet<>();
        this.assignedRoomsByType = new HashMap<>();
    }

    public void allocateRoom(Reservation reservation, RoomInventory inventory) {
        String type = reservation.getRoomType();


        if (inventory.checkAndReduce(type)) {

            String roomId = generateRoomId(type);

            allocatedRoomIds.add(roomId);
            assignedRoomsByType.computeIfAbsent(type, k -> new HashSet<>()).add(roomId);

            System.out.println("Booking confirmed for Guest: " + reservation.getGuestName() +
                    ", Room ID: " + roomId);
        }
    }

    private String generateRoomId(String roomType) {
        int count = assignedRoomsByType.getOrDefault(roomType, new HashSet<>()).size() + 1;
        String prefix = roomType.split(" ")[0];
        return prefix + "-" + count;
    }
}



class BookingRequestQueue {
    private Queue<Reservation> requestQueue = new LinkedList<>();

    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }
}



public class HotelBookingApp {
    public static void main(String[] args) {
        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        RoomAllocationService allocationService = new RoomAllocationService();
        RoomInventory inventory = new RoomInventory();

        bookingQueue.addRequest(new Reservation("Abhi", "Single Room"));
        bookingQueue.addRequest(new Reservation("Subha", "Single Room")); // Changed to Single to show increment
        bookingQueue.addRequest(new Reservation("Vanmathi", "Suite Room"));

        System.out.println("--- Room Allocation Processing ---");

        while (bookingQueue.hasPendingRequests()) {
            Reservation request = bookingQueue.getNextRequest();
            allocationService.allocateRoom(request, inventory);
        }
    }
}