import java.util.ArrayList;
import java.util.Scanner;

class Room {
    int roomNumber;
    String category;
    boolean isAvailable;
    double price;

    Room(int roomNumber, String category, boolean isAvailable, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = isAvailable;
        this.price = price;
    }

    public String toString() {
        return "Room " + roomNumber + " (" + category + ") - $" + price + " per night";
    }
}

class Reservation {
    int roomNumber;
    String guestName;
    int numberOfNights;
    double totalAmount;

    Reservation(int roomNumber, String guestName, int numberOfNights, double totalAmount) {
        this.roomNumber = roomNumber;
        this.guestName = guestName;
        this.numberOfNights = numberOfNights;
        this.totalAmount = totalAmount;
    }

    public String toString() {
        return "Reservation for " + guestName + " - Room " + roomNumber + " for " + numberOfNights + " nights. Total: $" + totalAmount;
    }
}

public class HotelReservationSystem {
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeRooms();
        System.out.println("Welcome to the Balaji Hotel Reservation System!");

        boolean exit = false;
        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Search for Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    searchAvailableRooms();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    viewReservations();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }

    private static void initializeRooms() {
        // Sample rooms initialization
        rooms.add(new Room(101, "Standard", true, 100));
        rooms.add(new Room(102, "Deluxe", true, 150));
        rooms.add(new Room(103, "Suite", true, 250));
        rooms.add(new Room(201, "Standard", true, 100));
        rooms.add(new Room(202, "Deluxe", true, 150));
        rooms.add(new Room(203, "Suite", false, 250)); // Booked room
    }

    private static void searchAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println(room);
            }
        }
    }

    private static void makeReservation() {
        System.out.print("Enter room number to book: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && room.isAvailable) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Room not available or invalid room number.");
            return;
        }

        System.out.print("Enter guest name: ");
        String guestName = scanner.nextLine();

        System.out.print("Enter number of nights: ");
        int numberOfNights = scanner.nextInt();

        double totalAmount = selectedRoom.price * numberOfNights;
        Reservation reservation = new Reservation(roomNumber, guestName, numberOfNights, totalAmount);
        reservations.add(reservation);

        selectedRoom.isAvailable = false; // Mark room as booked
        System.out.println("Reservation confirmed! Total amount: $" + totalAmount);
    }

    private static void viewReservations() {
        System.out.println("\nCurrent Reservations:");
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }
}