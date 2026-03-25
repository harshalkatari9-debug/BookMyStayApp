import java.util.*;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

class RoomInventory {

    private Map<String, Integer> availability = new HashMap<>();

    public RoomInventory() {
        availability.put("Single Room", 1);
        availability.put("Double Room", 1);
    }

    public void validate(String roomType) throws InvalidBookingException {

        if (!availability.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        if (availability.get(roomType) <= 0) {
            throw new InvalidBookingException("No availability for: " + roomType);
        }
    }

    public void decrease(String roomType) throws InvalidBookingException {

        int count = availability.get(roomType);

        if (count <= 0) {
            throw new InvalidBookingException("Cannot reduce below zero for: " + roomType);
        }

        availability.put(roomType, count - 1);
    }
}

class BookingService {

    private RoomInventory inventory;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void process(Reservation r) {

        try {
            inventory.validate(r.getRoomType());
            inventory.decrease(r.getRoomType());

            System.out.println("Booking Confirmed for " + r.getGuestName());

        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService(inventory);

        service.process(new Reservation("Alice", "Single Room"));
        service.process(new Reservation("Bob", "Suite Room"));
        service.process(new Reservation("Charlie", "Single Room"));
    }
}