import java.util.*;

class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

class BookingHistory {

    private List<Reservation> history = new ArrayList<>();

    public void addReservation(Reservation r) {
        history.add(r);
    }

    public List<Reservation> getAllReservations() {
        return history;
    }
}

class BookingReportService {

    public void displayAllBookings(List<Reservation> list) {

        System.out.println("===== Booking History =====");

        for (Reservation r : list) {
            System.out.println(r.getReservationId() + " | " +
                    r.getGuestName() + " | " +
                    r.getRoomType());
        }
    }

    public void generateSummary(List<Reservation> list) {

        Map<String, Integer> summary = new HashMap<>();

        for (Reservation r : list) {
            summary.put(r.getRoomType(),
                    summary.getOrDefault(r.getRoomType(), 0) + 1);
        }

        System.out.println("\n===== Booking Summary =====");

        for (Map.Entry<String, Integer> entry : summary.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        history.addReservation(new Reservation("RES1", "Alice", "Single Room"));
        history.addReservation(new Reservation("RES2", "Bob", "Double Room"));
        history.addReservation(new Reservation("RES3", "Charlie", "Single Room"));

        BookingReportService report = new BookingReportService();

        report.displayAllBookings(history.getAllReservations());
        report.generateSummary(history.getAllReservations());
    }
}