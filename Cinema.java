import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
//        int choice;
        int rows = getNumOfRows();
        int seats = getNumOfSeats();
        char[][] seatsArray = new char[rows][seats];
        int totalSeats = getTotalSeats(rows, seats);
        initializeSeats(seatsArray);

        int choice;
        do {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    printSeats(seatsArray);
                    break;
                case 2:
                    buyTicket(seatsArray, rows, totalSeats);
                    break;
                default:
                    break;
            }
        } while (choice == 1 || choice == 2);
    }

    public static int getNumOfRows() {
        System.out.println("Enter the number of rows:");
        return new Scanner(System.in).nextInt();
    }

    public static int getNumOfSeats() {
        System.out.println("Enter the number of seats:");
        return new Scanner(System.in).nextInt();
    }

    public static int getTotalSeats(int rows, int seats) {
        return rows * seats;
    }

    public static void initializeSeats(char[][] seatsArray) {
        for (int i = 0; i < seatsArray.length; i++) {
            for (int j = 0; j < seatsArray[i].length; j++) {
                seatsArray[i][j] = 'S';
            }
        }
    }

    public static void printSeats(char[][] seatsArray) {
        System.out.println("Cinema seats:");
        for (int i = 0; i < seatsArray.length; i++) {
            for (int j = 0; j < seatsArray[i].length; j++) {
                System.out.print(seatsArray[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void buyTicket(char[][] seatsArray, int rows, int totalSeats) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter row choice: ");
        int rowChoice = sc.nextInt();

        System.out.print("Enter seat choice: ");
        int seatChoice = sc.nextInt();

        seatsArray[rowChoice - 1][seatChoice - 1] = 'B';
        calculatePrice(rowChoice, rows, totalSeats);
    }

    public static boolean isSeatAvailable(char[][] seatsArray, int row, int seat) {
        return seatsArray[row][seat] == 'S';
    }

    public static void calculatePrice(int rowChoice, int rows, int totalSeats) {
        /*
         * If totalSeats <= 60, then the price of each ticket is 10 dollars.
         * Else if totalSeats > 60, the tickets are 10 dollars for the front
         * half of the rows and 8 dollars for the back half. Rows can be odd,
         * e.g. 9 rows. For this instance, the first half is the first 4 rows,
         * and the second half is the other 5 rows.
         *
         * The equivalent code for the conditions are shown below.
         */
        int price = 0;
        if (totalSeats <= 60) {
            price = 10;
            // totalIncome = totalSeats * price;
        } else {
            int firstHalf = rows / 2;
            price = rowChoice <= firstHalf ? 10: 8;

            // int firstHalfIncome = firstHalf * seats * 10;
            // int secondHalfIncome = secondHalf * seats * 8;
            // totalIncome = firstHalfIncome + secondHalfIncome;
        }
        System.out.println("Ticket price: $" + price);
    }
}