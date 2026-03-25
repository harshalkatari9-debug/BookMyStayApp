import java.util.HashMap;
import java.util.Map;



class RoomInventory {

    private HashMap<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();

        availability.put("Single Room", 5);
        availability.put("Double Room", 3);
        availability.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return availability.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int count) {
        availability.put(roomType, count);
    }

    public void displayInventory() {
        System.out.println("===== Room Inventory =====");

        for (Map.Entry<String, Integer> entry : availability.entrySet()) {
            System.out.println(entry.getKey() + " → Available: " + entry.getValue());
        }
    }
}


public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();


        inventory.displayInventory();

        System.out.println();

        System.out.println("Updating Single Room availability...");
        inventory.updateAvailability("Single Room", 4);

        System.out.println();

        inventory.displayInventory();
    }
}
