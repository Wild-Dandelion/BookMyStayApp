/*
 *Use Case 5: Booking Request (First-Come-First-Served)
 * @author Shikher
 * @version 5.1
 */

import java.util.LinkedList;
import java.util.Queue;

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

class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

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

        System.out.println("Booking Request Queue");
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        bookingQueue.addRequest(new Reservation("Abhi", "Single Room"));
        bookingQueue.addRequest(new Reservation("Subha", "Double Room"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Suite Room"));

        while (bookingQueue.hasPendingRequests()) {
            Reservation request = bookingQueue.getNextRequest();
            System.out.println("Processing booking for Guest: " + request.getGuestName() +
                    ", Room Type: " + request.getRoomType());
        }
    }
}