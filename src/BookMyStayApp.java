import java.util.LinkedList;
import java.util.Queue;



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

class BookingQueue {

    private Queue<Reservation> queue;

    public BookingQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Request added: " + reservation.getGuestName() +
                " → " + reservation.getRoomType());
    }

    public void displayQueue() {

        System.out.println("\n===== Booking Request Queue =====");

        for (Reservation r : queue) {
            System.out.println(r.getGuestName() + " → " + r.getRoomType());
        }
    }
}



public class BookMyStayApp {

    public static void main(String[] args) {

        BookingQueue bookingQueue = new BookingQueue();

        // Guest booking requests
        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite Room"));

        // Display queue
        bookingQueue.displayQueue();
    }
}