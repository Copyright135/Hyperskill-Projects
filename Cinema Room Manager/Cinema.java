package cinema;

import java.util.Scanner;
import java.util.Arrays;

public class Cinema {
    private static Scanner scan;
    private static char[][] cinema;
    private static int openSeats;
    private static int currentIncome;
    
	public static void main(String[] args) {
        scan = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scan.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scan.nextInt();
        openSeats = rows * seats;
        
        cinema = new char[rows][seats];
        
        for (char[] row : cinema) {
            Arrays.fill(row, 'S');
        }
        
        displayMenu();
    }
    
    public static void displayMenu() {
        int selection;
        do {
            System.out.println();
            System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            selection = scan.nextInt();
            System.out.println();
            switch (selection) {
                case 1:
                    printCinema();
                    break;
                case 2:
                    purchaseTicket();
                    break;
                case 3:
                    printStatistics();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid operation.");
            }
        } while (selection != 0);
    }
    
    public static void purchaseTicket() {
        int row;
        int seat;
        while (true) {
            System.out.println("Enter a row number:");
            row = scan.nextInt();
            System.out.println("Enter a seat number in that row:");
            seat = scan.nextInt();
            System.out.println();
            
            if (row > cinema.length || seat > cinema[0].length) {
                System.out.println("Wrong input!");
            } else if (cinema[row - 1][seat - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                break;
            }
            System.out.println();
        }
        
        int price;
        
        if (cinema.length * cinema[0].length > 60) {
            if (row < Math.ceil((double) cinema.length / 2)) {
                price = 10;
            } else {
                price = 8;
            }
        } else {
            price = 10;
        }
        System.out.println("Ticket price: $" + price);
        cinema[row - 1][seat - 1] = 'B';
        openSeats--;
        currentIncome += price;
    }
    
    public static void printCinema() {
        System.out.println("Cinema:");
        for (int i = 0; i <= cinema[0].length; i++) {
            if (i == 0) {
                System.out.print("  ");
            } else {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        
        for (int i = 0; i < cinema.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < cinema[i].length; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
	}
	
	public static void printStatistics() {
	    int purchasedSeats = cinema.length * cinema[0].length - openSeats;
	    System.out.println("Number of purchased tickets: " + purchasedSeats);
	    System.out.println("Percentage: " + getPercentage(purchasedSeats));
	    System.out.println("Current income: $" + currentIncome);
	    System.out.println("Total income: $" + calculateTotalIncome());
	}
	
	public static String getPercentage(int purchasedSeats) {
	    return String.format("%.2f", (double) purchasedSeats / (cinema.length * cinema[0].length) * 100) + "%";
	}
    
    public static int calculateTotalIncome() {
        int total;
        int rows = cinema.length;
        int seats = cinema[0].length;
        if (rows * seats > 60) {
            int premiumRows = rows / 2;
            total = premiumRows * seats * 10 + (rows - premiumRows) * seats * 8;
        } else {
            total = rows * seats * 10;
        }
        return total;
    }
}
