import java.util.HashMap;
import java.util.Map;


// ----------- Room Hierarchy -----------

abstract class Room {
    protected String type;
    protected double price;

    public Room(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public abstract void displayDetails();
}

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1000);
    }

    public void displayDetails() {
        System.out.println(type + " | Price: ₹" + price);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2000);
    }

    public void displayDetails() {
        System.out.println(type + " | Price: ₹" + price);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 5000);
    }

    public void displayDetails() {
        System.out.println(type + " | Price: ₹" + price);
    }
}

// ----------- Inventory (State Holder) -----------

class RoomInventory {

    private HashMap<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
        availability.put("Single Room", 5);
        availability.put("Double Room", 3);
        availability.put("Suite Room", 0); // Example unavailable
    }

    public int getAvailability(String roomType) {
        return availability.getOrDefault(roomType, 0);
    }

    public Map<String, Integer> getAllAvailability() {
        return availability;
    }
}

// ----------- Search Service -----------

class SearchService {

    public void searchAvailableRooms(RoomInventory inventory) {

        System.out.println("===== Available Rooms =====");

        for (Map.Entry<String, Integer> entry : inventory.getAllAvailability().entrySet()) {

            String roomType = entry.getKey();
            int count = entry.getValue();

            // Filter unavailable rooms
            if (count > 0) {

                Room room = null;

                // Create room object (domain usage)
                if (roomType.equals("Single Room")) {
                    room = new SingleRoom();
                } else if (roomType.equals("Double Room")) {
                    room = new DoubleRoom();
                } else if (roomType.equals("Suite Room")) {
                    room = new SuiteRoom();
                }

                if (room != null) {
                    room.displayDetails();
                    System.out.println("Available: " + count);
                    System.out.println();
                }
            }
        }
    }
}

// ----------- Main Class -----------

public class BookMyStayApp {

    public static void main(String[] args) {

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Perform search (read-only)
        SearchService searchService = new SearchService();
        searchService.searchAvailableRooms(inventory);
    }
}