/*
 * Use Case 12: Data Persistence & System Recovery
 * @author Shikher
 * @version 12.0
 */

import java.util.*;
import java.io.*;

class FilePersistenceService {

    public void saveInventory(RoomInventory inventory, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            Map<String, Integer> data = inventory.getInventoryMap();
            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                // Formatting: Single Room-5
                writer.write(entry.getKey() + "-" + entry.getValue());
                writer.newLine();
            }
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }


    public void loadInventory(RoomInventory inventory, String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    inventory.updateRoomCount(parts[0], Integer.parseInt(parts[1]));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Recovery failed. Using default inventory.");
        }
    }
}

class RoomInventory {
    private Map<String, Integer> availableRooms = new HashMap<>();

    public RoomInventory() {
        // Initial defaults
        availableRooms.put("Single Room", 5);
        availableRooms.put("Double Room", 3);
        availableRooms.put("Suite Room", 2);
    }

    public void updateRoomCount(String type, int count) {
        availableRooms.put(type, count);
    }

    public Map<String, Integer> getInventoryMap() {
        return availableRooms;
    }

    public void showInventory() {
        System.out.println("Current Inventory:");
        availableRooms.forEach((type, count) ->
                System.out.println(type.replace(" Room", "") + ": " + count));
    }
}

public class HotelBookingApp {
    public static void main(String[] args) {
        System.out.println("System Recovery");

        RoomInventory inventory = new RoomInventory();
        FilePersistenceService persistence = new FilePersistenceService();
        String filePath = "inventory_state.txt";

        persistence.loadInventory(inventory, filePath);
        inventory.showInventory();

        persistence.saveInventory(inventory, filePath);
    }
}