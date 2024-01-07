import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Bus {
    private String busNumber;
    private int totalSeats;
    private int availableSeats;

    public Bus(String busNumber, int totalSeats) {
        this.busNumber = busNumber;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public boolean bookSeats(int numSeats) {
        if (numSeats <= availableSeats) {
            availableSeats -= numSeats;
            return true;
        }
        return false;
    }
}

class Reservation {
    private int reservationId;
    private String passengerName;
    private String busNumber;
    private int numSeats;

    public Reservation(int reservationId, String passengerName, String busNumber, int numSeats) {
        this.reservationId = reservationId;
        this.passengerName = passengerName;
        this.busNumber = busNumber;
        this.numSeats = numSeats;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public int getReservationId() {
        return reservationId;
    }
}

public class BusReservationSystem {
    private static List<Bus> buses = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static int reservationIdCounter = 1;

    public static void main(String[] args) {
        initializeBuses();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Bus Reservation System");
            System.out.println("1. Show Available Buses");
            System.out.println("2. Make a Reservation");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showAvailableBuses();
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    System.out.println("Thank you for using the Bus Reservation System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeBuses() {
        buses.add(new Bus("BUS001", 20));
        buses.add(new Bus("BUS002", 15));
        buses.add(new Bus("BUS003", 30));
    }

    private static void showAvailableBuses() {
        System.out.println("Available Buses:");
        for (Bus bus : buses) {
            System.out.println("Bus Number: " + bus.getBusNumber());
            System.out.println("Available Seats: " + bus.getAvailableSeats());
            System.out.println();
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("Enter your name: ");
        String passengerName = scanner.next();

        System.out.print("Enter the bus number: ");
        String busNumber = scanner.next();

        System.out.print("Enter the number of seats to book: ");
        int numSeats = scanner.nextInt();

        Bus selectedBus = null;
        for (Bus bus : buses) {
            if (bus.getBusNumber().equals(busNumber)) {
                selectedBus = bus;
                break;
            }
        }

        if (selectedBus != null && selectedBus.bookSeats(numSeats)) {
            Reservation reservation = new Reservation(reservationIdCounter++, passengerName, busNumber, numSeats);
            reservations.add(reservation);
            System.out.println("Reservation successful. Your reservation ID is: " + reservation.getReservationId());
        } else {
            System.out.println("Reservation failed. Please check bus availability or seat count.");
        }
    }
}
