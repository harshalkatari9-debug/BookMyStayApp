import java.util.*;

class Reservation {
    private String reservationId;
    private String roomType;
    private String roomId;

    public Reservation(String reservationId, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }
}

class RoomInventory {

    private Map<String, Integer> availability = new HashMap<>();

    public RoomInventory() {
        availability.put("Single Room", 0);
        availability.put("Double Room", 1);
    }

    public void increase(String type) {
        availability.put(type, availability.getOrDefault(type, 0) + 1);
    }

    public void display() {
        System.out.println("\nInventory:");
        for (Map.Entry<String, Integer> e : availability.entrySet()) {
            System.out.println(e.getKey() + " → " + e.getValue());
        }
    }
}

class BookingHistory {

    private Map<String, Reservation> confirmed = new HashMap<>();

    public void add(Reservation r) {
        confirmed.put(r.getReservationId(), r);
    }

    public Reservation get(String id) {
        return confirmed.get(id);
    }

    public void remove(String id) {
        confirmed.remove(id);
    }
}

class CancellationService {

    private RoomInventory inventory;
    private BookingHistory history;
    private Stack<String> releasedRooms = new Stack<>();

    public CancellationService(RoomInventory inventory, BookingHistory history) {
        this.inventory = inventory;
        this.history = history;
    }

    public void cancel(String reservationId) {

        Reservation r = history.get(reservationId);

        if (r == null) {
            System.out.println("Cancellation Failed: Reservation not found");
            return;
        }

        releasedRooms.push(r.getRoomId());

        inventory.increase(r.getRoomType());

        history.remove(reservationId);

        System.out.println("Cancelled Reservation: " + reservationId);
        System.out.println("Released Room ID: " + r.getRoomId());
    }

    public void showRollbackStack() {
        System.out.println("\nRollback Stack: " + releasedRooms);
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingHistory history = new BookingHistory();

        Reservation r1 = new Reservation("RES1", "Single Room", "SI-101");
        Reservation r2 = new Reservation("RES2", "Double Room", "DO-201");

        history.add(r1);
        history.add(r2);

        CancellationService service = new CancellationService(inventory, history);

        service.cancel("RES1");
        service.cancel("RES3");

        inventory.display();
        service.showRollbackStack();
    }
}