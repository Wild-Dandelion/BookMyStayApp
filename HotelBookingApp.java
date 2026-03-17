/*
 * Use Case 8: Booking History & Reporting
 * @author Shikher
 * @version 8.0
 */

import java.util.*;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class ReservationValidator {
    public void validate(String guestName, String roomType, RoomInventory inventory)
            throws InvalidBookingException {

        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        List<String> validTypes = Arrays.asList("Single", "Double", "Suite");
        if (!validTypes.contains(roomType)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }
    }
}

// --- Main Application ---
public class HotelBookingApp {
    public static void main(String[] args) {
        System.out.println("Booking Validation");
        Scanner scanner = new Scanner(System.in);
        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();

        try {
            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();
            validator.validate(guestName, roomType, inventory);

            System.out.println("Validation successful for " + guestName);

        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

class RoomInventory {
}