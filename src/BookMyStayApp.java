import java.util.*;

// ----------- Reservation -----------

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

// ----------- Inventory -----------

class RoomInventory {

    private Map<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
        availability.put("Single Room", 2);
        availability.put("Double Room", 1);
        availability.put("Suite Room", 1);
    }

    public int getAvailability(String type) {
        return availability.getOrDefault(type, 0);
    }

    public void decrease(String type) {
        availability.put(type, getAvailability(type) - 1);
    }
}

// ----------- Booking Queue -----------

class BookingQueue {

    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) {
        queue.offer(r);
    }

    public Reservation getNextRequest() {
        return queue.poll(); // FIFO
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

// ----------- Booking Service -----------

class BookingService {

    private RoomInventory inventory;

    // Track allocated room IDs
    private Map<String, Set<String>> allocatedRooms = new HashMap<>();

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void processBookings(BookingQueue queue) {

        while (!queue.isEmpty()) {

            Reservation r = queue.getNextRequest();
            String type = r.getRoomType();

            System.out.println("\nProcessing: " + r.getGuestName());

            if (inventory.getAvailability(type) > 0) {

                // Generate unique room ID
                String roomId = generateRoomId(type);

                // Initialize set if not exists
                allocatedRooms.putIfAbsent(type, new HashSet<>());

                Set<String> roomSet = allocatedRooms.get(type);

                // Ensure uniqueness
                if (!roomSet.contains(roomId)) {

                    roomSet.add(roomId);

                    // Update inventory immediately
                    inventory.decrease(type);

                    System.out.println("Booking Confirmed!");
                    System.out.println("Guest: " + r.getGuestName());
                    System.out.println("Room Type: " + type);
                    System.out.println("Room ID: " + roomId);

                } else {
                    System.out.println("Duplicate Room ID detected!");
                }

            } else {
                System.out.println("Booking Failed (No availability)");
            }
        }
    }

    // Room ID generator
    private String generateRoomId(String type) {
        return type.substring(0, 2).toUpperCase() + "-" + UUID.randomUUID().toString().substring(0, 5);
    }
}

// ----------- Main Class -----------

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingQueue queue = new BookingQueue();

        // Add requests
        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Single Room"));
        queue.addRequest(new Reservation("Charlie", "Single Room")); // may fail

        // Process bookings
        BookingService service = new BookingService(inventory);
        service.processBookings(queue);
    }
}