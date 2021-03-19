package cinema;

import java.util.Scanner;

public class Cinema {
    private static int countS = 0;
    private static final Scanner sc = new Scanner(System.in);
    private static int currentIncome = 0;
    private static int numberSoldTickets = 0;

    public static void main(String[] args) {

        System.out.println("Enter the number of rows:");
        final int row = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        final int seats = sc.nextInt();

        char[][] cinemaRoom = new char[row][seats + 1];
        for (int i = 0; i < row; ++i) {
            for (int j = 1; j < seats + 1; j++) {
                cinemaRoom[i][j] = 'S';
                countS++;
            }
        }
        System.out.println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
        int menu;
        do {
            menu = sc.nextInt();
            switch (menu) {
                case 1:
                    showSeats(cinemaRoom, row, seats);
                    break;
                case 2:
                    buyTicket(cinemaRoom, row, seats);
                    break;
                case 3:
                    statistics(seats);
                    break;
            }
        } while (menu != 0);
    }

    public static void showSeats(char[][] cinemaRoom, int row, int seats) {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i < seats + 1; ++i) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < row; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < seats + 1; j++) {
                System.out.print(cinemaRoom[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
    }

    public static void buyTicket(char[][] cinemaRoom, int row, int seats) {
        do {
            System.out.println("Enter a row number:");
            int rowNumber = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatNumber = sc.nextInt();

            int b = row / 2;
            if (rowNumber < 1 || rowNumber > row || seatNumber < 1 || seatNumber > seats) {
                System.out.println("Wrong input!");
                System.out.println();
            } else if (cinemaRoom[rowNumber - 1][seatNumber] == 'B') {
                System.out.println("That ticket has already been purchased!");
                System.out.println();
            } else if (countS <= 60 && cinemaRoom[rowNumber - 1][seatNumber] != 'B') {
                System.out.println();
                System.out.print("Ticket price: ");
                System.out.println("$" + "10");
                cinemaRoom[rowNumber - 1][seatNumber] = 'B';
                numberSoldTickets += 1;
                currentIncome += 10;
                break;
            } else if (rowNumber <= b && cinemaRoom[rowNumber - 1][seatNumber] != 'B') {
                System.out.println();
                System.out.print("Ticket price: ");
                System.out.println("$" + "10");
                cinemaRoom[rowNumber - 1][seatNumber] = 'B';
                numberSoldTickets += 1;
                currentIncome += 10;
                break;
            } else if (rowNumber > b && cinemaRoom[rowNumber - 1][seatNumber] != 'B') {
                System.out.println();
                System.out.print("Ticket price: ");
                System.out.println("$" + "8");
                cinemaRoom[rowNumber - 1][seatNumber] = 'B';
                numberSoldTickets += 1;
                currentIncome += 8;
                break;
            }

        } while (true);
        System.out.println();
        System.out.println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
    }

    public static void statistics(int seats) {
        int total;
        if (countS <= 60) {
            total = countS * 10;
        } else {
            int frontRows = countS / 2 - ((countS / 2) % seats);
            int backRows = countS - frontRows;
            total = frontRows * 10 + backRows * 8;
        }
        float percentage = numberSoldTickets / (countS / 100F);
        System.out.println("Number of purchased tickets: " + numberSoldTickets);
        System.out.print("Percentage: ");
        System.out.printf("%.2f", percentage);
        System.out.print("%");
        System.out.println();
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + total);
        System.out.println();
        System.out.println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
    }
}
