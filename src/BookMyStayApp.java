


abstract class Room {
    protected String type;
    protected int beds;
    protected double price;

    public Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }


    public abstract void displayDetails();
}

class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 1000);
    }

    public void displayDetails() {
        System.out.println(type + " | Beds: " + beds + " | Price: ₹" + price);
    }
}


class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 2000);
    }

    public void displayDetails() {
        System.out.println(type + " | Beds: " + beds + " | Price: ₹" + price);
    }
}

class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 5000);
    }

    public void displayDetails() {
        System.out.println(type + " | Beds: " + beds + " | Price: ₹" + price);
    }
}


public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("===== BookMyStay Room Availability =====");

        Room r1 = new SingleRoom();
        Room r2 = new DoubleRoom();
        Room r3 = new SuiteRoom();

        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        r1.displayDetails();
        System.out.println("Available: " + singleAvailable);
        System.out.println();

        r2.displayDetails();
        System.out.println("Available: " + doubleAvailable);
        System.out.println();

        r3.displayDetails();
        System.out.println("Available: " + suiteAvailable);
    }
}
