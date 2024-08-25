import java.util.ArrayList;
import java.util.Scanner;

class Room {
    int roomNumber;
    String category;
    boolean isAvailable;
    double price;

    public Room(int roomNumber, String category, boolean isAvailable, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = isAvailable;
        this.price = price;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public double getPrice() {
        return price;
    }
}

class Reservation {
    String guestName;
    Room room;
    int numberOfNights;
    double totalAmount;

    public Reservation(String guestName, Room room, int numberOfNights) {
        this.guestName = guestName;
        this.room = room;
        this.numberOfNights = numberOfNights;
        this.totalAmount = room.getPrice() * numberOfNights;
    }

    public void displayBookingDetails() {
        System.out.println("\nBooking Details:");
        System.out.println("Guest Name: " + guestName);
        System.out.println("Room Number: " + room.getRoomNumber());
        System.out.println("Room Category: " + room.getCategory());
        System.out.println("Number of Nights: " + numberOfNights);
        System.out.println("Total Amount: $" + totalAmount);
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Reservation> reservations = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Add rooms
        rooms.add(new Room(101, "Single", true, 100));
        rooms.add(new Room(102, "Double", true, 150));
        rooms.add(new Room(103, "Suite", true, 250));
        rooms.add(new Room(104, "Single", true, 100));
        rooms.add(new Room(105, "Double", true, 150));

        while (true) {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. Search for available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View booking details");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.println("Enter room category (Single, Double, Suite):");
                String category = scanner.nextLine();

                System.out.println("\nAvailable Rooms:");
                for (Room room : rooms) {
                    if (room.getCategory().equalsIgnoreCase(category) && room.isAvailable()) {
                        System.out.println("Room Number: " + room.getRoomNumber() + ", Price: $" + room.getPrice());
                    }
                }
            } else if (choice == 2) {
                System.out.println("Enter your name:");
                String guestName = scanner.nextLine();

                System.out.println("Enter room number to book:");
                int roomNumber = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                Room selectedRoom = null;
                for (Room room : rooms) {
                    if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                        selectedRoom = room;
                        break;
                    }
                }

                if (selectedRoom != null) {
                    System.out.println("Enter number of nights:");
                    int numberOfNights = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    selectedRoom.setAvailable(false);
                    Reservation reservation = new Reservation(guestName, selectedRoom, numberOfNights);
                    reservations.add(reservation);

                    System.out.println("Reservation successful!");
                    reservation.displayBookingDetails();
                } else {
                    System.out.println("Room not available or invalid room number.");
                }
            } else if (choice == 3) {
                System.out.println("Enter your name to view booking details:");
                String guestName = scanner.nextLine();

                boolean found = false;
                for (Reservation reservation : reservations) {
                    if (reservation.guestName.equalsIgnoreCase(guestName)) {
                        reservation.displayBookingDetails();
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("No booking found for the given name.");
                }
            } else if (choice == 4) {
                System.out.println("Exiting the system. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
