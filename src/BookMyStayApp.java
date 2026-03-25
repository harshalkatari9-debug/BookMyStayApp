import java.io.*;
import java.util.*;

class Reservation implements Serializable {
    private String reservationId;
    private String roomType;

    public Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomType() {
        return roomType;
    }
}

class RoomInventory implements Serializable {

    private Map<String, Integer> availability = new HashMap<>();

    public RoomInventory() {
        availability.put("Single Room", 2);
        availability.put("Double Room", 1);
    }

    public Map<String, Integer> getAvailability() {
        return availability;
    }

    public void display() {
        System.out.println("Inventory: " + availability);
    }
}

class BookingHistory implements Serializable {

    private List<Reservation> history = new ArrayList<>();

    public void add(Reservation r) {
        history.add(r);
    }

    public List<Reservation> getAll() {
        return history;
    }
}

class AppState implements Serializable {

    RoomInventory inventory;
    BookingHistory history;

    public AppState(RoomInventory inventory, BookingHistory history) {
        this.inventory = inventory;
        this.history = history;
    }
}

class PersistenceService {

    private static final String FILE = "appstate.ser";

    public void save(AppState state) {

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(state);
            System.out.println("State saved successfully");
        } catch (Exception e) {
            System.out.println("Error saving state");
        }
    }

    public AppState load() {

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
            System.out.println("State loaded successfully");
            return (AppState) in.readObject();
        } catch (Exception e) {
            System.out.println("No previous state found, starting fresh");
            return null;
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        PersistenceService service = new PersistenceService();

        AppState state = service.load();

        RoomInventory inventory;
        BookingHistory history;

        if (state == null) {
            inventory = new RoomInventory();
            history = new BookingHistory();

            history.add(new Reservation("RES1", "Single Room"));
            history.add(new Reservation("RES2", "Double Room"));

        } else {
            inventory = state.inventory;
            history = state.history;
        }

        inventory.display();

        System.out.println("Bookings:");
        for (Reservation r : history.getAll()) {
            System.out.println(r.getReservationId() + " → " + r.getRoomType());
        }

        service.save(new AppState(inventory, history));
    }
}