package org.MiniProject;

import java.util.Scanner;

public class MovieTicketBooking {

    static Scanner sc = new Scanner(System.in);

    static boolean[] seats = new boolean[20];

    
    public static void showSeats() {

        String GREEN = "\u001B[32m";
        String RED = "\u001B[31m";
        String RESET = "\u001B[0m";

        System.out.println("\n================ SCREEN ===================");
        System.out.println("=========================================");

        for (int i = 0; i < seats.length; i++) {

            if (seats[i] == false) {

                System.out.print(
                    GREEN + "[ " + (i + 1) + " ]" + RESET + " "
                );

            } else {

                System.out.print(
                    RED + "[ X ]" + RESET + " "
                );
            }

           
            if ((i + 1) % 5 == 0) {
                System.out.println();
                System.out.println();
            }
        }

        System.out.println("=======================================");
        System.out.println("Green = Available");
        System.out.println("Red X = Booked");
    }

    public static void bookTicket() {

        showSeats();

        System.out.print("\nHow many tickets do you want? ");
        int numberOfTickets = sc.nextInt();

        if (numberOfTickets <= 0 || numberOfTickets > 20) {
            System.out.println("Invalid number of tickets");
            return;
        }

        int[] selectedSeats = new int[numberOfTickets];


        for (int i = 0; i < numberOfTickets; i++) {

            System.out.print("Enter seat number for ticket "
                    + (i + 1) + ": ");

            int seat = sc.nextInt();

            if (seat < 1 || seat > 20) {

                System.out.println("Invalid seat number");
                i--;
                continue;
            }

            if (seats[seat - 1] == true) {

                System.out.println("Seat " + seat
                        + " is already booked. Select another seat.");

                i--;
                continue;
            }

   
            boolean alreadySelected = false;

            for (int j = 0; j < i; j++) {

                if (selectedSeats[j] == seat) {
                    alreadySelected = true;
                    break;
                }
            }

            if (alreadySelected) {

                System.out.println(
                        "You already selected this seat."
                );

                i--;
                continue;
            }

            selectedSeats[i] = seat;
        }

     
        sc.nextLine();

        System.out.print("\nEnter customer name: ");
        String name = sc.nextLine();

        double price = numberOfTickets * 150;

        System.out.print("Is it weekend? (yes/no): ");
        String weekend = sc.next();

        if (weekend.equalsIgnoreCase("yes")) {
            price = numberOfTickets * 200;
        }

        System.out.print("Do you have coupon? (yes/no): ");
        String coupon = sc.next();

        if (coupon.equalsIgnoreCase("yes")) {

            price = price - (price * 10 / 100);

            System.out.println("10% discount applied");
        }

        for (int i = 0; i < numberOfTickets; i++) {

            seats[selectedSeats[i] - 1] = true;
        }

        System.out.println("\n========== BOOKING SUCCESSFUL ===========");

        System.out.println("Customer Name : " + name);
        System.out.println("Number of Tickets : " + numberOfTickets);

        System.out.print("Selected Seats : ");

        for (int i = 0; i < numberOfTickets; i++) {
            System.out.print(selectedSeats[i] + " ");
        }

        System.out.println();

        System.out.println("Total Amount : Rs." + price);
    }

    public static void showBookedSeats() {

        boolean found = false;

        System.out.println("\n========= BOOKED SEATS =========");

        for (int i = 0; i < seats.length; i++) {

            if (seats[i] == true) {

                System.out.println("Seat " + (i + 1) + " - Booked");

                found = true;
            }
        }

        if (found == false) {
            System.out.println("No tickets are booked.");
        }
    }

    public static void cancelTicket() {

        showBookedSeats();

        boolean found = false;

        for (int i = 0; i < seats.length; i++) {

            if (seats[i] == true) {
                found = true;
                break;
            }
        }

        if (found == false) {
            return;
        }

        System.out.print(
                "\nWhich seat do you want to cancel? "
        );

        int seat = sc.nextInt();

        if (seat < 1 || seat > 20) {

            System.out.println("Invalid seat number");
            return;
        }

        if (seats[seat - 1] == false) {

            System.out.println(
                    "This seat is not currently booked."
            );

            return;
        }

        seats[seat - 1] = false;

        System.out.println(
                "Seat " + seat + " ticket cancelled successfully."
        );
    }

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n====================================");
            System.out.println("    MOVIE TICKET BOOKING");
            System.out.println("=====================================");

            System.out.println("1. Show Available / Booked Seats");
            System.out.println("2. Book Ticket");
            System.out.println("3. Show Booked Tickets");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    showSeats();
                    break;

                case 2:
                    bookTicket();
                    break;

                case 3:
                    showBookedSeats();
                    break;

                case 4:
                    cancelTicket();
                    break;

                case 5:
                    System.out.println("Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}