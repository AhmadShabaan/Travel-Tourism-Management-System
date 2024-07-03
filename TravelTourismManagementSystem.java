import java.io.*;
import java.util.*;

public class TravelTourismManagementSystem {
    public static final String USERS = "users.txt";
    
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Travel and Tourism Management System");
        boolean condition = true;
        do {
            System.out.println();
            System.out.println("1. Login");
            System.out.println("2. Create New Account");
            System.out.println("3. Exit Application.");
            System.out.println();
            System.out.print("Please choose an option (1 or 2 or 3): ");

        
        try {
            int option = input.nextInt();
            input.nextLine(); // Consume newline
            
            switch(option) {
                case 1:
                    login(input);
                    break;
                case 2:
                    createAccount(input);
                    break;
                case 3:
                    System.out.println("Exiting Program");
                    condition = false;
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
        catch (Exception e) {
            System.out.print("Invalid Input Enter Number Between 1-3");
        }
        
        } while(condition == true);

        
    }


    public static void login(Scanner input) {
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();

        if (validateLogin(username, password)) {
            System.out.println("Login successful! Welcome, " + username);
            displayDashboard(input);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    public static void createAccount(Scanner input) {
        System.out.print("Enter a new username: ");
        String username = input.nextLine();
        System.out.print("Enter a new password: ");
        String password = input.nextLine();

        if (saveNewUser(username, password)) {
            System.out.println("Account created successfully! You can now log in.");
        } else {
            System.out.println("Username already exists. Please choose a different username.");
        }
    }


    public static boolean validateLogin(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails[0].equals(username) && userDetails[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        return false;
    }

    public static boolean saveNewUser(String username, String password) {
        if (userExists(username)) {
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS, true))) {
            writer.write(username + "," + password);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean userExists(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        return false;
    }
    

    public static void displayDashboard(Scanner input) {
        while (true) {
            System.out.println("\nDashboard:");
            System.out.println("1. Personal Details");
            System.out.println("2. View Destinations");
            System.out.println("3. Book a Tour Package");
            System.out.println("4. Book a Hotel");
            System.out.println("5. Get Destination Recommendations");
            System.out.println("6. Logout");
            System.out.print("Please choose an option: ");

            int choice = input.nextInt();
            input.nextLine();

            switch(choice) {
                case 1:
                    personalDetails(input);
                    break;
                case 2:
                    destinations(input);
                    break;
                case 3:
                    tourismPackages(input);
                    break;
                case 4:
                    hotelBooking(input);
                    break;
                case 5:
                    destinationRecommendation(input);
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void personalDetails(Scanner input) {

            while (true) {
                System.out.println("\nPersonal Details Menu:");
                System.out.println("1. Add Personal Details");
                System.out.println("2. Update Personal Details");
                System.out.println("3. View Personal Details");
                System.out.println("4. Go Back");
                System.out.print("Please select an option: ");
                int option = input.nextInt();
                input.nextLine();  // Consume the newline character

                switch (option) {
                    case 1:
                        addPersonalDetails(input);
                        break;
                    case 2:
                        updatePersonalDetails(input);
                        break;
                    case 3:
                        viewPersonalDetails();
                        break;
                    case 4:
                        System.out.println("Returning to the main menu.");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    public static final String PERSONAL_DETAILS_FILE = "personal_details.txt";
    public static void addPersonalDetails(Scanner input) {
        System.out.println("\nAdd Personal Details");

        System.out.print("Name: ");
        String name = input.nextLine();
        System.out.print("Passport Number: ");
        String passportNumber = input.nextLine();
        System.out.print("Contact Number: ");
        String contactNumber = input.nextLine();
        System.out.print("Gender: ");
        String gender = input.nextLine();
        System.out.print("Country: ");
        String country = input.nextLine();
        System.out.print("Address: ");
        String address = input.nextLine();
        System.out.print("Email: ");
        String email = input.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PERSONAL_DETAILS_FILE))) {
            writer.write(name + "\n");
            writer.write(passportNumber + "\n");
            writer.write(contactNumber + "\n");
            writer.write(gender + "\n");
            writer.write(country + "\n");
            writer.write(address + "\n");
            writer.write(email + "\n");
            System.out.println("Personal details added successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while adding personal details.");
            e.printStackTrace();
        }
    }

    public static void viewPersonalDetails() {
        System.out.println("\nView Personal Details");

        try (BufferedReader reader = new BufferedReader(new FileReader(PERSONAL_DETAILS_FILE))) {
            String name = reader.readLine();
            String passportNumber = reader.readLine();
            String contactNumber = reader.readLine();
            String gender = reader.readLine();
            String country = reader.readLine();
            String address = reader.readLine();
            String email = reader.readLine();

            if (name == null) {
                System.out.println("No personal details found.");
            } else {
                System.out.println("Name: " + name);
                System.out.println("Passport Number: " + passportNumber);
                System.out.println("Contact Number: " + contactNumber);
                System.out.println("Gender: " + gender);
                System.out.println("Country: " + country);
                System.out.println("Address: " + address);
                System.out.println("Email: " + email);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No personal details found.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading personal details.");
            e.printStackTrace();
        }
    }
    public static void updatePersonalDetails(Scanner input) {
        String[] details = new String[7];

        // Read existing details
        try (BufferedReader reader = new BufferedReader(new FileReader(PERSONAL_DETAILS_FILE))) {
            for (int i = 0; i < details.length; i++) {
                details[i] = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("No existing details found, starting with empty details.");
            for (int i = 0; i < details.length; i++) {
                details[i] = "";
            }
        }

        System.out.println("\nUpdate Personal Details");

        details[0] = promptForUpdate(input, "Name", details[0]);
        details[1] = promptForUpdate(input, "Passport Number", details[1]);
        details[2] = promptForUpdate(input, "Contact Number", details[2]);
        details[3] = promptForUpdate(input, "Gender", details[3]);
        details[4] = promptForUpdate(input, "Country", details[4]);
        details[5] = promptForUpdate(input, "Address", details[5]);
        details[6] = promptForUpdate(input, "Email", details[6]);

        // Write updated details
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PERSONAL_DETAILS_FILE))) {
            for (String detail : details) {
                writer.write(detail + "\n");
            }
            System.out.println("Personal details updated successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while updating personal details.");
            e.printStackTrace();
        }
    }

    public static String promptForUpdate(Scanner input, String field, String currentValue) {
        System.out.print(field + " [" + currentValue + "]: ");
        String newValue = input.nextLine().trim();
        return newValue.isEmpty() ? currentValue : newValue;
    }

    public static void destinations(Scanner input) {
        System.out.println("\nTravel and Tourism Destination Guide");
        System.out.println("1. Show All Destinations");
        System.out.println("2. Go Back");
        System.out.print("Please select an option: ");
        int option = input.nextInt();
        input.nextLine();  // Consume the newline character

        switch (option) {
            case 1:
                showDestinations();
                break;
            case 2:
                System.out.println("Returning to the main menu.");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
    public static void showDestinations() {
        System.out.println("\nDestinations and their Famous Qualities:");

        System.out.println("1. England");
        System.out.println("- Famous for: Big Ben, Buckingham Palace, London Eye, Stonehenge");
        System.out.println("- Qualities: Historical sites, Rich culture, Beautiful countryside\n");

        System.out.println("2. Australia");
        System.out.println("- Famous for: Sydney Opera House, Great Barrier Reef, Kangaroo Island");
        System.out.println("- Qualities: Stunning beaches, Unique wildlife, Friendly people\n");

        System.out.println("3. Scotland");
        System.out.println("- Famous for: Edinburgh Castle, Loch Ness, Scottish Highlands");
        System.out.println("- Qualities: Beautiful landscapes, Rich history, Whisky\n");

        System.out.println("4. Switzerland");
        System.out.println("- Famous for: Swiss Alps, Lake Geneva, Zurich, Interlaken");
        System.out.println("- Qualities: Scenic beauty, Ski resorts, Chocolates\n");

        System.out.println("5. Indonesia");
        System.out.println("- Famous for: Bali, Borobudur Temple, Komodo National Park");
        System.out.println("- Qualities: Tropical climate, Diverse culture, Beautiful islands\n");

        System.out.println("6. Maldives");
        System.out.println("- Famous for: Coral reefs, Luxury resorts, Underwater villas");
        System.out.println("- Qualities: Crystal clear waters, Marine life, Romantic getaways\n");

        System.out.println("7. Canada");
        System.out.println("- Famous for: Niagara Falls, Banff National Park, CN Tower");
        System.out.println("- Qualities: Natural beauty, Friendly locals, Winter sports\n");

        System.out.println("8. France");
        System.out.println("- Famous for: Eiffel Tower, Louvre Museum, Notre-Dame Cathedral");
        System.out.println("- Qualities: Rich culture, Gourmet cuisine, Fashion\n");

        System.out.println("9. Italy");
        System.out.println("- Famous for: Colosseum, Venice Canals, Leaning Tower of Pisa");
        System.out.println("- Qualities: Historical landmarks, Art and architecture, Delicious food\n");

        System.out.println("10. Brazil");
        System.out.println("- Famous for: Christ the Redeemer, Amazon Rainforest, Copacabana Beach");
        System.out.println("- Qualities: Vibrant culture, Carnivals, Football\n");

        System.out.println("11. Germany");
        System.out.println("- Famous for: Brandenburg Gate, Neuschwanstein Castle, Oktoberfest");
        System.out.println("- Qualities: Rich history, Engineering prowess, Beer\n");

        System.out.println("12. Costa Rica");
        System.out.println("- Famous for: Rainforests, Volcanoes, Beautiful beaches");
        System.out.println("- Qualities: Biodiversity, Adventure tourism, Ecotourism\n");

        System.out.println("13. America");
        System.out.println("- Famous for: Statue of Liberty, Grand Canyon, Hollywood");
        System.out.println("- Qualities: Diverse landscapes, Cultural diversity, Innovation\n");

        System.out.println("14. Japan");
        System.out.println("- Famous for: Mount Fuji, Tokyo Tower, Kyoto Temples");
        System.out.println("- Qualities: Technological advancement, Rich traditions, Cherry blossoms\n");

        System.out.println("15. Russia");
        System.out.println("- Famous for: Red Square, Saint Basil's Cathedral, Trans-Siberian Railway");
        System.out.println("- Qualities: Vast landscapes, Historical architecture, Ballet\n");
    }


    public static double bronzeDiscount = 0, silverDiscount = 0, goldDiscount = 0, premiumDiscount = 0;


    public static void tourismPackages(Scanner input) {
        while (true) {
            System.out.println("\nTourism Packages Menu:");
            System.out.println("1. Book a Package");
            System.out.println("2. View Booked Packages");
            System.out.println("3. Delete a Booked Package");
            System.out.println("4. Play Discount Game");
            System.out.println("5. Go Back");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    bookPackage(input);
                    break;
                case 2:
                    viewBookedPackages();
                    break;
                case 3:
                    deleteBookedPackage(input);
                    break;
                case 4:
                    playDiscountGame(input);
                    break;
                case 5:
                    System.out.println("Returning to the main menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }
    public static void bookPackage(Scanner input) {
        String[] locations = {"England", "Australia", "Scotland", "Switzerland", "Indonesia", "Maldives", "Canada", "France", "Italy", "Brazil", "Germany", "Costa Rica", "America", "Japan", "Russia"};
        int[] distances = {6000, 10400, 6200, 5800, 5500, 4300, 1000, 6200, 6200, 2700, 2200, 1800, 12500, 2000, 5100};

        String[] packages = {"Bronze Package(Includes only journey fee and accommodations)", "Silver Package(Includes journey fees, accommodation and medical support)", "Gold Package(Includes journey fees, accommodation and amusement park visits with medical support)", "Premium Package(Includes business class Flights, accommodation, food items, amusement park visits with medical support and free concert tickets)"};
        int[] basePrices = {5000, 10000, 20000, 30000};

        System.out.println("Available Locations:");
        for (int i = 0; i < locations.length; i++) {
            System.out.println((i + 1) + ". " + locations[i]);
        }

        System.out.print("Enter the number of the location you want to visit: ");
        int locationIndex = input.nextInt() - 1;

        if (locationIndex < 0 || locationIndex >= locations.length) {
            System.out.println("Invalid location selected.");
            return;
        }

        System.out.println("Available Packages:");
        for (int i = 0; i < packages.length; i++) {
            int price = basePrices[i] + (distances[locationIndex] / 100 * (i + 1) * 10);
            double discount = 0;
            switch (i) {
                case 0:
                    discount = bronzeDiscount;
                    break;
                case 1:
                    discount = silverDiscount;
                    break;
                case 2:
                    discount = goldDiscount;
                    break;
                case 3:
                    discount = premiumDiscount;
                    break;
            }
            int discountedPrice = (int) (price * (1 - discount));
            System.out.println((i + 1) + ". " + packages[i] + " - Original Price: " + price + " Dollars, Discounted Price: " + discountedPrice + " Dollars");
        }

        System.out.print("Enter the number of the package you want to select: ");
        int packageIndex = input.nextInt() - 1;

        if (packageIndex < 0 || packageIndex >= packages.length) {
            System.out.println("Invalid package selected.");
            return;
        }

        int originalPrice = basePrices[packageIndex] + (distances[locationIndex] / 100 * (packageIndex + 1) * 10);
        double discount = 0;
        switch (packageIndex) {
            case 0:
                discount = bronzeDiscount;
                break;
            case 1:
                discount = silverDiscount;
                break;
            case 2:
                discount = goldDiscount;
                break;
            case 3:
                discount = premiumDiscount;
                break;
        }
        int discountedPrice = (int) (originalPrice * (1 - discount));
        String selectedPackage = packages[packageIndex] + " to " + locations[locationIndex] + " - Price: " + discountedPrice + " Dollars (Original Price: " + originalPrice + " Dollars)";

        try (FileWriter writer = new FileWriter("selected_packages.txt", true)) {
            writer.write(selectedPackage + "\n");
            System.out.println("Package selected and added to booked packages: " + selectedPackage);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
    public static void viewBookedPackages() {
        System.out.println("Booked Packages:");
        try (BufferedReader reader = new BufferedReader(new FileReader("selected_packages.txt"))) {
            String line;
            int index = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println(index++ + ". " + line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
    public static void deleteBookedPackage(Scanner input) {
        List<String> bookedPackages = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("selected_packages.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                bookedPackages.add(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
            return;
        }

        if (bookedPackages.isEmpty()) {
            System.out.println("No booked packages to delete.");
            return;
        }

        System.out.println("Booked Packages:");
        for (int i = 0; i < bookedPackages.size(); i++) {
            System.out.println((i + 1) + ". " + bookedPackages.get(i));
        }

        System.out.print("Enter the number of the package you want to delete: ");
        int deleteIndex = input.nextInt() - 1;

        if (deleteIndex < 0 || deleteIndex >= bookedPackages.size()) {
            System.out.println("Invalid package selected.");
            return;
        }


        bookedPackages.remove(deleteIndex);


        try (FileWriter writer = new FileWriter("selected_packages.txt")) {
            for (String pkg : bookedPackages) {
                writer.write(pkg + "\n");
            }
            System.out.println("Package deleted successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }


    public static void playDiscountGame(Scanner input) {
        int points = playTicTacToe(input);
        System.out.println("You earned " + points + " points!");

        if (points >= 100) {
            bronzeDiscount = 0.30;
            silverDiscount = 0.25;
            goldDiscount = 0.20;
            premiumDiscount = 0.15;
        } else if (points >= 50) {
            bronzeDiscount = 0.20;
            silverDiscount = 0.15;
            goldDiscount = 0.10;
            premiumDiscount = 0.05;
        }
    }

    public static int playTicTacToe(Scanner input) {
        char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        char player = 'X';
        int moves = 0;

        while (true) {
            printBoard(board);
            if (player == 'X') {
                System.out.println("Player's turn (X)");
                System.out.print("Enter row (1-3) and column (1-3): ");
                int row = input.nextInt() - 1;
                int col = input.nextInt() - 1;
                if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ') {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }
                board[row][col] = player;
            } else {
                System.out.println("Computer's turn (O)");
                int[] move = getComputerMove(board);
                board[move[0]][move[1]] = player;
            }

            moves++;
            if (checkWin(board, player)) {
                printBoard(board);
                System.out.println("Player " + player + " wins!");
                return player == 'X' ? 50 : 0;
            }
            if (moves == 9) {
                printBoard(board);
                System.out.println("It's a draw!");
                return 25;
            }
            player = (player == 'X') ? 'O' : 'X';
        }
    }

    
    public static void printBoard(char[][] board) {
        System.out.println("  1    2    3");
        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + "  ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("  -----------");
        }
    }

    public static boolean checkWin(char[][] board, char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true;
        return false;
    }

    public static int[] getComputerMove(char[][] board) {
        Random rand = new Random();
        int row, col;
        do {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
        } while (board[row][col] != ' ');
        return new int[]{row, col};
    }


    public static void hotelBooking(Scanner input) {
        while (true) {
            System.out.println("\nTravel And Tourism Management System");
            System.out.println("1. Book Hotels");
            System.out.println("2. View Booked Hotels");
            System.out.println("3. Back");
            System.out.print("Please select an option: ");
            int option = input.nextInt();
            input.nextLine();  // Consume the newline character

            switch (option) {
                case 1:
                    bookHotel(input);
                    break;
                case 2:
                    viewBookedHotels();
                    break;
                case 3:
                    System.out.println("Thank you for using the Travel And Tourism Management System. Goodbye!");
                    return;
                    
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static final String BOOKING_FILE = "hotel_bookings.txt";
public static final String[] COUNTRIES = {
    "England", "Australia", "Scotland", "Switzerland", "Indonesia", "Maldives", "Canada", "France", "Italy",
    "Brazil", "Germany", "Costa Rica", "America", "Japan", "Russia"
};

public static void bookHotel(Scanner input) {
    System.out.println("\nBook Hotels");

    // Display countries
    System.out.println("Available Countries:");
    for (int i = 0; i < COUNTRIES.length; i++) {
        System.out.println((i + 1) + ". " + COUNTRIES[i]);
    }

    // Get user input for country
    System.out.print("Enter the number of the country you want to visit: ");
    int countryIndex = input.nextInt() - 1;
    input.nextLine();  // Consume the newline character

    if (countryIndex < 0 || countryIndex >= COUNTRIES.length) {
        System.out.println("Invalid country selected.");
        return;
    }

    // Display hotels
    String[] hotels = {
        "3 Star Hotel: " + COUNTRIES[countryIndex] + " Inn - Price per day: $100",
        "5 Star Hotel: " + COUNTRIES[countryIndex] + " Grand - Price per day: $200",
        "7 Star Hotel: " + COUNTRIES[countryIndex] + " Palace - Price per day: $500"
    };

    System.out.println("Available Hotels in " + COUNTRIES[countryIndex] + ":");
    for (int i = 0; i < hotels.length; i++) {
        System.out.println((i + 1) + ". " + hotels[i]);
    }

    // Get user input for hotel
    System.out.print("Enter the number of the hotel you want to select: ");
    int hotelIndex = input.nextInt() - 1;
    input.nextLine();  // Consume the newline character

    if (hotelIndex < 0 || hotelIndex >= hotels.length) {
        System.out.println("Invalid hotel selected.");
        return;
    }

    // Get user input for number of rooms and days
    System.out.print("Enter the number of rooms to book: ");
    int rooms = input.nextInt();
    System.out.print("Enter the number of days to stay: ");
    int days = input.nextInt();

    int pricePerDay = 0;
    switch (hotelIndex) {
        case 0:
            pricePerDay = 100;
            break;
        case 1:
            pricePerDay = 200;
            break;
        case 2:
            pricePerDay = 500;
            break;
    }

    int totalCost = rooms * days * pricePerDay;
    System.out.println("Total cost: $" + totalCost);

    // Confirm booking
    System.out.print("Do you want to confirm the booking? (yes/no): ");
    input.nextLine();  // Consume the newline character
    String confirm = input.nextLine();

    if (confirm.equalsIgnoreCase("yes")) {
        String bookingDetails = "Country: " + COUNTRIES[countryIndex] + ", Hotel: " + hotels[hotelIndex] + 
                                ", Rooms: " + rooms + ", Days: " + days + ", Total Cost: $" + totalCost;
        saveBooking(bookingDetails);
        System.out.println("Booking confirmed: " + bookingDetails);
    } else {
        System.out.println("Booking canceled.");
    }
}

public static void saveBooking(String bookingDetails) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKING_FILE, true))) {
        writer.write(bookingDetails + "\n");
    } catch (IOException e) {
        System.out.println("An error occurred while saving the booking.");
        e.printStackTrace();
    }
}

public static void viewBookedHotels() {
    System.out.println("\nView Booked Hotels");
    try (BufferedReader reader = new BufferedReader(new FileReader(BOOKING_FILE))) {
        String booking;
        int count = 0;
        while ((booking = reader.readLine()) != null) {
            System.out.println(++count + ". " + booking);
        }
        if (count == 0) {
            System.out.println("No bookings found.");
        }
    } catch (IOException e) {
        System.out.println("An error occurred while reading the bookings.");
        e.printStackTrace();
    }
}

public static void destinationRecommendation(Scanner input) {

    System.out.println("Welcome to the Travel Recommendation System!");
    System.out.println("Please answer the following questions to get a travel destination recommendation.\n");

    // Question 1
    System.out.println("1. What type of landscape do you prefer for your vacation?");
    System.out.println("a) Mountains");
    System.out.println("b) Beach/Sea");
    System.out.println("c) Forest");
    System.out.println("d) City/Urban");
    char landscapeChoice = input.next().charAt(0);

    switch (landscapeChoice) {
        case 'a':
            recommendMountainDestination(input);
            return;
        case 'b':
            recommendBeachDestination(input);
            return;
        case 'c':
            recommendForestDestination(input);
            return;
        case 'd':
            recommendCityDestination(input);
            return;
        default:
            System.out.println("Invalid choice. Please restart the program and enter a valid option.");
            break;
    }

    input.close();
}


public static void recommendMountainDestination(Scanner input) {
    // Question 2 for Mountains
    System.out.println("\n2. What kind of mountain activities do you enjoy?");
    System.out.println("a) Hiking");
    System.out.println("b) Skiing/Snowboarding");
    System.out.println("c) Sightseeing");
    System.out.println("d) Relaxing in a cabin");
    char mountainActivity = input.next().charAt(0);

    // Question 3 for Mountains
    System.out.println("\n3. Do you prefer cold or mild weather?");
    System.out.println("a) Cold");
    System.out.println("b) Mild");
    char mountainWeather = input.next().charAt(0);

    // Question 4 for Mountains
    System.out.println("\n4. How do you prefer to spend your evenings?");
    System.out.println("a) By a campfire");
    System.out.println("b) At a local restaurant");
    System.out.println("c) At a spa");
    System.out.println("d) Stargazing");
    char mountainEvening = input.next().charAt(0);

    // Question 5 for Mountains
    System.out.println("\n5. What kind of accommodation do you prefer?");
    System.out.println("a) Camping");
    System.out.println("b) Mountain lodge");
    System.out.println("c) Hotel");
    System.out.println("d) Cabin");
    char mountainAccommodation = input.next().charAt(0);

    // Recommendation logic for Mountains
    if (mountainActivity == 'a' && mountainWeather == 'a' && mountainEvening == 'd' && mountainAccommodation == 'd') {
        System.out.println("\nRecommended Destination: Swiss Alps, Switzerland");
    } else if (mountainActivity == 'b' && mountainWeather == 'a' && mountainEvening == 'b' && mountainAccommodation == 'c') {
        System.out.println("\nRecommended Destination: Aspen, USA");
    } else if (mountainActivity == 'c' && mountainWeather == 'b' && mountainEvening == 'c' && mountainAccommodation == 'b') {
        System.out.println("\nRecommended Destination: Blue Mountains, Australia");
    } else if (mountainActivity == 'd' && mountainWeather == 'b' && mountainEvening == 'a' && mountainAccommodation == 'a') {
        System.out.println("\nRecommended Destination: Smoky Mountains, USA");
    } else {
        System.out.println("\nRecommended Destination: Rocky Mountains, Canada");
    }
}

public static void recommendBeachDestination(Scanner input) {
    // Question 2 for Beach/Sea
    System.out.println("\n2. What kind of beach activities do you enjoy?");
    System.out.println("a) Swimming");
    System.out.println("b) Scuba Diving/Snorkeling");
    System.out.println("c) Sunbathing");
    System.out.println("d) Beach sports (volleyball, surfing, etc.)");
    char beachActivity = input.next().charAt(0);

    // Question 3 for Beach/Sea
    System.out.println("\n3. Do you prefer crowded beaches or secluded ones?");
    System.out.println("a) Crowded");
    System.out.println("b) Secluded");
    char beachType = input.next().charAt(0);

    // Question 4 for Beach/Sea
    System.out.println("\n4. What time of year do you prefer to visit the beach?");
    System.out.println("a) Summer");
    System.out.println("b) Winter");
    System.out.println("c) Spring");
    System.out.println("d) Autumn");
    char beachSeason = input.next().charAt(0);

    // Question 5 for Beach/Sea
    System.out.println("\n5. What type of accommodation do you prefer?");
    System.out.println("a) Beach resort");
    System.out.println("b) Hotel");
    System.out.println("c) Beach bungalow");
    System.out.println("d) Vacation rental");
    char beachAccommodation = input.next().charAt(0);

    // Recommendation logic for Beach/Sea
    if (beachActivity == 'a' && beachType == 'a' && beachSeason == 'a' && beachAccommodation == 'a') {
        System.out.println("\nRecommended Destination: Miami Beach, USA");
    } else if (beachActivity == 'b' && beachType == 'b' && beachSeason == 'c' && beachAccommodation == 'c') {
        System.out.println("\nRecommended Destination: Great Barrier Reef, Australia");
    } else if (beachActivity == 'c' && beachType == 'b' && beachSeason == 'd' && beachAccommodation == 'd') {
        System.out.println("\nRecommended Destination: Maldives");
    } else if (beachActivity == 'd' && beachType == 'a' && beachSeason == 'a' && beachAccommodation == 'b') {
        System.out.println("\nRecommended Destination: Bondi Beach, Australia");
    } else {
        System.out.println("\nRecommended Destination: Bali, Indonesia");
    }
}

public static void recommendForestDestination(Scanner input) {
    // Question 2 for Forest
    System.out.println("\n2. What kind of forest activities do you enjoy?");
    System.out.println("a) Hiking");
    System.out.println("b) Wildlife Watching");
    System.out.println("c) Camping");
    System.out.println("d) Photography");
    char forestActivity = input.next().charAt(0);

    // Question 3 for Forest
    System.out.println("\n3. Do you prefer tropical or temperate forests?");
    System.out.println("a) Tropical");
    System.out.println("b) Temperate");
    char forestType = input.next().charAt(0);

    // Question 4 for Forest
    System.out.println("\n4. How do you prefer to explore the forest?");
    System.out.println("a) Guided tours");
    System.out.println("b) Self-guided hikes");
    System.out.println("c) Cycling");
    System.out.println("d) Canoeing/Kayaking");
    char forestExploration = input.next().charAt(0);

    // Question 5 for Forest
    System.out.println("\n5. What kind of accommodation do you prefer?");
    System.out.println("a) Camping");
    System.out.println("b) Eco-lodge");
    System.out.println("c) Cabin");
    System.out.println("d) Treehouse");
    char forestAccommodation = input.next().charAt(0);

    // Recommendation logic for Forest
    if (forestActivity == 'a' && forestType == 'a' && forestExploration == 'a' && forestAccommodation == 'b') {
        System.out.println("\nRecommended Destination: Amazon Rainforest, Brazil");
    } else if (forestActivity == 'b' && forestType == 'b' && forestExploration == 'b' && forestAccommodation == 'c') {
        System.out.println("\nRecommended Destination: Yellowstone National Park, USA");
    } else if (forestActivity == 'c' && forestType == 'b' && forestExploration == 'd' && forestAccommodation == 'a') {
        System.out.println("\nRecommended Destination: Black Forest, Germany");
    } else if (forestActivity == 'd' && forestType == 'a' && forestExploration == 'a' && forestAccommodation == 'd') {
        System.out.println("\nRecommended Destination: Costa Rica Rainforest");
    } else {
        System.out.println("\nRecommended Destination: Banff National Park, Canada");
    }
}

public static void recommendCityDestination(Scanner input) {
    // Question 2 for City/Urban
    System.out.println("\n2. What kind of urban activities do you enjoy?");
    System.out.println("a) Sightseeing/Historical sites");
    System.out.println("b) Shopping");
    System.out.println("c) Nightlife");
    System.out.println("d) Cultural experiences (museums, theaters, etc.)");
    char cityActivity = input.next().charAt(0);

    // Question 3 for City/Urban
    System.out.println("\n3. Do you prefer modern or historical cities?");
    System.out.println("a) Modern");
    System.out.println("b) Historical");
    char cityType = input.next().charAt(0);

    // Question 4 for City/Urban
    System.out.println("\n4. What type of cuisine do you prefer?");
    System.out.println("a) Local street food");
    System.out.println("b) Fine dining");
    System.out.println("c) International cuisine");
    System.out.println("d) Traditional local dishes");
    char cityCuisine = input.next().charAt(0);

    // Question 5 for City/Urban
    System.out.println("\n5. How do you prefer to get around the city?");
    System.out.println("a) Public transportation");
    System.out.println("b) Walking");
    System.out.println("c) Biking");
    System.out.println("d) Taxi/Rideshare");
    char cityTransportation = input.next().charAt(0);

    // Recommendation logic for City/Urban
    if (cityActivity == 'a' && cityType == 'b' && cityCuisine == 'd' && cityTransportation == 'b') {
        System.out.println("\nRecommended Destination: Rome, Italy");
    } else if (cityActivity == 'b' && cityType == 'a' && cityCuisine == 'b' && cityTransportation == 'a') {
        System.out.println("\nRecommended Destination: Tokyo, Japan");
    } else if (cityActivity == 'c' && cityType == 'a' && cityCuisine == 'c' && cityTransportation == 'd') {
        System.out.println("\nRecommended Destination: New York City, USA");
    } else if (cityActivity == 'd' && cityType == 'b' && cityCuisine == 'a' && cityTransportation == 'c') {
        System.out.println("\nRecommended Destination: Paris, France");
    } else {
        System.out.println("\nRecommended Destination: London, UK");
    }

}


}