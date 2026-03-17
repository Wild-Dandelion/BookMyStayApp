/*
 * Use Case 8: Booking History & Reporting
 * @author Shikher
 * @version 8.0
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

class BookingHistory {
    private List<Reservation> confirmedReservations;

    public BookingHistory() {
        confirmedReservations = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
    }

    public List<Reservation> getConfirmedReservations() {
        return confirmedReservations;
    }
}

class BookingReportService {
    public void generateReport(BookingHistory history) {
        System.out.println("Booking History Report");
        List<Reservation> reservations = history.getConfirmedReservations();
        for (Reservation res : reservations) {
            String shortType = res.getRoomType().replace(" Room", "");
            System.out.println("Guest: " + res.getGuestName() +
                    ", Room Type: " + shortType);
        }
    }
}

public class HotelBookingApp {
    public static void main(String[] args) {
        System.out.println("Booking History and Reporting");
        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();
        history.addReservation(new Reservation("Abhi", "Single Room"));
        history.addReservation(new Reservation("Subha", "Double Room"));
        history.addReservation(new Reservation("Vanmathi", "Suite Room"));
        reportService.generateReport(history);
    }
}