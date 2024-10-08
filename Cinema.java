import java.util.Scanner;

public class Cinema {

    private static int numberOfTickets = 0;
    private static int currentIncome = 0;
    private static int totalIncome = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int rows = getNumOfRows();
        int seats = getNumOfSeats();
        char[][] seatsArray = new char[rows][seats];
        int totalSeats = getTotalSeats(rows, seats);
        initializeSeats(seatsArray);

        int choice;
        do {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    printSeats(seatsArray);
                    break;
                case 2:
                    buyTicket(seatsArray, rows, seats, totalSeats);
                    break;
                case 3:
                    showStatistics(totalSeats);
                    break;
                default:
                    break;
            }
        } while (choice >= 1 && choice <= 3);
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

    public static void buyTicket(char[][] seatsArray, int rows, int seats, int totalSeats) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter row choice: ");
        int rowChoice = sc.nextInt();

        System.out.print("Enter seat choice: ");
        int seatChoice = sc.nextInt();

        if (rowChoice > rows || seatChoice > seats) {
            System.out.println("Wrong input!");
        } else {
            rowChoice--;
            seatChoice--;
            if (isSeatAvailable(seatsArray, rowChoice, seatChoice)) {
                seatsArray[rowChoice][seatChoice] = 'B';
                calculatePrice(rowChoice, rows, seats, totalSeats);
                numberOfTickets++;
            } else {
                System.out.println("That ticket has already been purchased!");
            }
        }
    }

    public static boolean isSeatAvailable(char[][] seatsArray, int rowChoice, int seatChoice) {
        return seatsArray[rowChoice][seatChoice] == 'S';
    }

    public static void calculatePrice(int rowChoice, int rows, int seats, int totalSeats) {
        /*
         * If totalSeats <= 60, then the price of each ticket is 10 dollars.
         * Else if totalSeats > 60, the tickets are 10 dollars for the front
         * half of the rows and 8 dollars for the back half. Rows can be odd,
         * e.g. 9 rows. For this instance, the first half is the first 4 rows,
         * and the second half is the other 5 rows.
         *
         * The equivalent code for the conditions are shown below.
         */
        int price;
        if (totalSeats <= 60) {
            price = 10;
            totalIncome = totalSeats * price;
        } else {
            int firstHalf = rows / 2;
            int secondHalf = rows - firstHalf;
            price = rowChoice <= firstHalf ? 10: 8;

            int firstHalfIncome = firstHalf * seats * 10;
            int secondHalfIncome = secondHalf * seats * 8;
            totalIncome = firstHalfIncome + secondHalfIncome;
        }
        currentIncome += price;
        System.out.println("Ticket price: $" + price);
    }

    public static void showStatistics(int totalSeats) {
        System.out.printf("Number of purchased tickets: %d \n", numberOfTickets);
        System.out.printf("Percentage: %.2f \n", numberOfTickets/(double)totalSeats * 100);
        System.out.printf("Current income: $%d \n", currentIncome);
        System.out.printf("Total income: $%d \n", totalIncome);
    }
}