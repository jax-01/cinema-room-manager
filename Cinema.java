import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
//        System.out.println("Cinema:");
//        System.out.println("  1 2 3 4 5 6 7 8");
//        System.out.println("1 S S S S S S S S");
//        System.out.println("2 S S S S S S S S");
//        System.out.println("3 S S S S S S S S");
//        System.out.println("4 S S S S S S S S");
//        System.out.println("5 S S S S S S S S");
//        System.out.println("6 S S S S S S S S");
//        System.out.println("7 S S S S S S S S");

        Scanner sc = new Scanner(System.in);
        int totalIncome;
        int totalSeats;
        int price;

        /*
         * Reads two positive integer numbers from user: the number of rows
         * and the number of seats in each row. The ticket price will be determined
         * by the inputted numbers.
         */
        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = sc.nextInt();
        char[][] seatsArray = new char[rows][seats];
        totalSeats = rows * seats;

        for (int i = 0; i < seatsArray.length; i++) {
            for (int j = 0; j < seatsArray[i].length; j++) {
                seatsArray[i][j] = 'S';
            }
        }

        /*
         * If totalSeats <= 60, then the price of each ticket is 10 dollars.
         * Else if totalSeats > 60, the tickets are 10 dollars for the front
         * half of the rows and 8 dollars for the back half. Rows can be odd,
         * e.g. 9 rows. For this instance, the first half is the first 4 rows,
         * and the second half is the other 5 rows.
         *
         * The equivalent code for the conditions are shown below.
         */
        if (totalSeats <= 60) {
            price = 10;
            totalIncome = totalSeats * price;
        } else {
            int firstHalf = rows / 2;
            int firstHalfIncome = firstHalf * seats * 10;
            int secondHalf = rows - firstHalf;
            int secondHalfIncome = secondHalf * seats * 8;
            totalIncome = firstHalfIncome + secondHalfIncome;
        }
        System.out.println("Total income:\n" + "$" + totalIncome);
    }
}