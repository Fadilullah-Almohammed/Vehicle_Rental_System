package Vehicle_Rental_System;

import java.util.ArrayList;
import java.util.Scanner;

public class VehicleManager {


    private static final int MAX_VEHICLES = 100;
    
    //Top-Level Domain
    private static final String TLD = "com";
    
    private static Vehicle[] vehicles = new Vehicle[MAX_VEHICLES];
    private static ArrayList<User> users = new ArrayList<User>();
    private static User currentUser;

    private static int noVehicles;
    private static int vehicleReserved;

    
    VehicleManager() {

    }

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

                //------------------------------------------------------------------------------- FOR TEST PURPOSES
                User admin1 = new Admin();
                admin1.registerUser("Ali", "Mohammed", "Hasan", "0559645334", "ali@gmail.com", "Ali11!!", "1", 19);
                VehicleManager.users.add(admin1);
        
                Vehicle car1 = new Car(
                    "1",
                    "2022 Toyota Camry",
                    "Camry",
                    "Gasoline",
                    2022,
                    200,
                    5,
                    "Toyota",
                    "Silver",
                    "Automatic",
                    true,
                    true
                );
                VehicleManager.vehicles[Integer.parseInt(car1.getVehicleID())] = car1;

                //------------------------------------------------------------------------------- FOR TEST PURPOSES
        
        System.out.println(
            "\n\n  ----  WELCOME TO VEHICLE RENTAL SYSTEM  ----  \n\n"
        );

        int userChoice = 0;
        boolean userFound = false;

        // LOGIN PORTAL
        do {

            System.out.print(
                "\n\n  ----  LOGIN PORTAL  ----  " + "\n" +
                "\n" +
                "1 - For admins only" + "\n" +
                "2 - For customers" + "\n" +
                "0 - Exit" + "\n" +
                "\n" +
                "Enter your choice: "
            );
            userChoice = scanner.nextInt();
            scanner.nextLine(); // clear buffer


            
            switch (userChoice) {
             
                case 1:

                     // ADMIN LOGIN
                    int adminChoice = 0;
                        do {
                            
                            System.out.print(
                                "\n\n  ----  ADMIN LOGIN  ----  "+ "\n" +
                                "\n" +
                                "1 - Admin Register"+ "\n" +
                                "2 - Admin Login"+ "\n" +
                                "0 - Go back to Login Portal"+ "\n" +
                                "\n" +
                                "Enter your choice: "
                            );
                            adminChoice = scanner.nextInt();
                            scanner.nextLine(); // clear buffer

                            

                            switch (adminChoice) {
                                
                                case 1:
                                    // admin registeration
                                    User newAdmin = new Admin();

                                    String firstName = null, secondName, lastName, phoneNumber, email, newPassword, cnofirmPassword;
                                    int age;
                                    boolean isValid = false;

                                    // first name
                                    do {

                                        System.out.print("\n\n  - First name: ");

                                        firstName = scanner.nextLine();
                                        isValid = validateName(firstName);

                                        if (!isValid) {
                                            System.out.print("\n\n  ! -- The name can not be empty and can not have any digit or space-- !");
                                        }

                                    } while (!isValid);

                                    // second name
                                    do {
                                        System.out.print("\n\n  - Second name: ");
                                        secondName = scanner.nextLine();

                                        isValid = validateName(secondName);

                                        if (!isValid) {
                                            System.out.print("\n\n  ! -- The name can not be empty and can not have any digit or space-- !");
                                        }

                                    } while (!isValid);

                                    // last name
                                    do {
                                        System.out.print("\n\n  - Last name: ");
                                        lastName = scanner.nextLine();

                                        isValid = validateName(lastName);

                                        if (!isValid) {
                                            System.out.print("\n\n  ! -- The name can not be empty and can not have any digit or space -- !");
                                        }

                                    } while (!isValid);

                                    // phone number
                                    do {

                                        boolean hasLetter = false;

                                        System.out.print("\n\n - Phone number: ");
                                        phoneNumber = scanner.nextLine();

                                        for (char digit: phoneNumber.toCharArray()) {
                                            
                                            if (!Character.isDigit(digit)) {
                                                hasLetter = true;
                                                break;
                                            }
                                        }

                                        if (hasLetter) {
                                            isValid = false;
                                            System.out.print("\n\n  ! -- Phone number must be digits only with no space -- !");
                                        }
                                        else {
                                            isValid = true;
                                        }
                                        

                                    } while (!isValid);

                                    // email
                                    do {
                                        System.out.print("\n\n - Email: ");
                                        email = scanner.next();
                                        scanner.nextLine(); // clear buffer

                                        isValid = validateEmail(email);

                                        if (!isValid) {
                                            System.out.print("\n\n  ! -- Email is invalid. Please enter email correctly -- !");
                                        }

                                    } while (!isValid);

                                    // new password
                                    do {

                                        System.out.print(
                                            "\n\n  ---- Password Rules ----" + "\n" +
                                            "\n" +
                                            " - Must be at least 8 characters length" + "\n" +
                                            " - Must have at least 2 digits" + "\n" +
                                            " - Must have at least 3 letters" + "\n" +
                                            " - At least 1 letter uppercase and 1 letter lowercase" + "\n" +
                                            " - Must have at least 2 special characters from the following: !@#$%^&*()-_=+[]{}|;:'\\\",.<>?/ " + "\n"
                                        );

                                        System.out.print("\n\n - New password: ");
                                        newPassword = scanner.nextLine();

                                        isValid = validatePassword(newPassword);

                                        if (!isValid) {
                                            System.out.print("\n\n  ! -- Password is invalid. Please check the rules provided -- !");
                                            continue;
                                        }

                                        // confirm password
                                        System.out.print("\n\n - Confirm password: ");
                                        cnofirmPassword = scanner.nextLine();
                                        
                                        if (newPassword.equals(cnofirmPassword)) {
                                            isValid = true;
                                        }
                                        else {
                                            System.out.print("\n\n  ! -- There is no match between the two passwords -- !");
                                            isValid = false;
                                        }
                                        
                                    } while (!isValid);

                                    // age
                                    do {

                                        System.out.print("\n\n - Age: ");
                                        age = scanner.nextInt();
                                        
                                        if (age > 18 && age < 65) {
                                            isValid = true;
                                        }
                                        else {
                                            System.out.print("\n\n  ! -- Sorry, age must be between 18 and 65 to register -- !");
                                            isValid = false;
                                        }

                                    } while (!isValid);

                                    // check if user available in users arrayList. If not, user will be registered and returned to ADMIN LOGIN menu
                                    boolean registerValid = true;
                                    String adminID = "0";

                                    if (VehicleManager.users != null) {
                                        for (int i = 0; i < VehicleManager.users.size(); i++) {
                                            
                                            if (VehicleManager.users.get(i) instanceof Admin) {

                                                // phone number stored before?
                                                if (VehicleManager.users.get(i).getPhoneNumber() == phoneNumber) {
                                                    registerValid = false;
                                                    System.out.print("\n\n  ! -- Phone number you entered appears to be in our system. Please try registering again with another phone number or login -- !");
                                                    break;
                                                }
                                                // email stored before?
                                                else if (VehicleManager.users.get(i).getEmail() == email) {
                                                    registerValid = false;
                                                    System.out.print("\n\n  ! -- Email address you entered appears to be in our system. Please try registering again with another email address or login -- !");
                                                    break;
                                                } 
                                            }
                                            

                                            adminID = String.valueOf(VehicleManager.users.size() - 1);
                                        }
                                    }

                                    if (registerValid) {

                                        newAdmin.registerUser(firstName, secondName, lastName, phoneNumber, email, newPassword, adminID, age);
                                        VehicleManager.users.add(newAdmin);

                                        System.out.print("\n\n ---- You have successfully registered. Now, you can login to enter to your account ----");
                                    }

                                    break;
                
                                case 2:
                                    // admin login
                                    String adminEmail = "";

                                    // email
                                    do {
                                        System.out.print("\n\n - Email: ");
                                        adminEmail = scanner.next();
                                        scanner.nextLine(); // clear buffer

                                        isValid = validateEmail(adminEmail);

                                        if (!isValid) {
                                            System.out.print("\n\n  ! -- Email is invalid. Please enter email correctly -- !");
                                        }

                                    } while (!isValid);

                                    // password
                                    String adminPassword = "";
                                    System.out.print("\n\n - password: ");
                                    adminPassword = scanner.nextLine();
                                
                                    userFound = false;

                                    User adminUser = null;
                                    for (User user: VehicleManager.users) {

                                        if (user instanceof Admin) {
                                            
                                            // check email
                                            if (user.getEmail().equals(adminEmail)) {
                                                adminUser = user;
                                                userFound = true;
                                                break;
                                            }
                                        }
                                    }

                                    if (userFound) {

                                        // check password
                                        if (adminUser.getPassword().equals(adminPassword)) {
                                            VehicleManager.setCurrentUser(adminUser);
                                        }
                                        else {
                                            System.out.print("\n\n  ! -- Password is incorrect -- !");
                                        }                                        
                                    }
                                    else {
                                        System.out.print("\n\n  ! -- Email is not available in our system -- !");
                                    }
                                
                                    break;

                                case 0:
                                    break;
                                
                                default:
                                    System.out.print("\n\n  ! -- Please enter a correct number from the menu -- !");
                                    break;
                                }

                        } while (adminChoice != 0 && VehicleManager.getCurrentUser() == null);
                    
                    break;

                case 2:
                
                    // CUSTOMER LOGIN
                    int customerChoice = 0;
                    do {
                        
                        System.out.print(
                            "\n\n  ----  CUSTOMER LOGIN  ----  "+ "\n" +
                            "\n" +
                            "1 - Customer Register"+ "\n" +
                            "2 - Customer Login"+ "\n" +
                            "0 - Go back to Login Portal"+ "\n" +
                            "\n" +
                            "Enter your choice: "
                        );
                        customerChoice = scanner.nextInt();
                        scanner.nextLine(); // clear buffer

                        
                        switch (customerChoice) {
                            
                            case 1:
                                // customer registeration
                                User newCustomer = new Customer();

                                String firstName, secondName, lastName, phoneNumber, email, newPassword, cnofirmPassword;
                                int age;
                                boolean isValid = false;

                                // first name
                                do {

                                    System.out.print("\n\n  - First name: ");

                                    firstName = scanner.nextLine();
                                    isValid = validateName(firstName);

                                    if (!isValid) {
                                        System.out.print("\n\n  ! -- The name can not be empty and can not have any digit or space-- !");
                                    }

                                } while (!isValid);

                                // second name
                                do {
                                    System.out.print("\n\n  - Second name: ");
                                    secondName = scanner.nextLine();

                                    isValid = validateName(secondName);

                                    if (!isValid) {
                                        System.out.print("\n\n  ! -- The name can not be empty and can not have any digit or space-- !");
                                    }

                                } while (!isValid);

                                // last name
                                do {
                                    System.out.print("\n\n  - Last name: ");
                                    lastName = scanner.nextLine();

                                    isValid = validateName(lastName);

                                    if (!isValid) {
                                        System.out.print("\n\n  ! -- The name can not be empty and can not have any digit or space -- !");
                                    }

                                } while (!isValid);

                                // phone number
                                do {

                                    boolean hasLetter = false;

                                    System.out.print("\n\n - Phone number: ");
                                    phoneNumber = scanner.nextLine();

                                    for (char digit: phoneNumber.toCharArray()) {
                                        
                                        if (!Character.isDigit(digit)) {
                                            hasLetter = true;
                                            break;
                                        }
                                    }

                                    if (hasLetter) {
                                        isValid = false;
                                        System.out.print("\n\n  ! -- Phone number must be digits only with no space -- !");
                                    }
                                    else {
                                        isValid = true;
                                    }
                                    

                                } while (!isValid);

                                // email
                                do {
                                    System.out.print("\n\n - Email: ");
                                    email = scanner.next();
                                    scanner.nextLine(); // clear buffer

                                    isValid = validateEmail(email);

                                    if (!isValid) {
                                        System.out.print("\n\n  ! -- Email is invalid. Please enter email correctly -- !");
                                    }

                                } while (!isValid);

                                // new password
                                do {

                                    System.out.print(
                                        "\n\n  ---- Password Rules ----" + "\n" +
                                        "\n" +
                                        " - Must be at least 8 characters length" + "\n" +
                                        " - Must have at least 2 digits" + "\n" +
                                        " - Must have at least 3 letters" + "\n" +
                                        " - At least 1 letter uppercase and 1 letter lowercase" + "\n" +
                                        " - Must have at least 2 special characters from the following: !@#$%^&*()-_=+[]{}|;:'\\\",.<>?/ " + "\n"
                                    );

                                    System.out.print("\n\n - New password: ");
                                    newPassword = scanner.nextLine();

                                    isValid = validatePassword(newPassword);

                                    if (!isValid) {
                                        System.out.print("\n\n  ! -- Password is invalid. Please check the rules provided -- !");
                                        continue;
                                    }

                                    // confirm password
                                    System.out.print("\n\n - Confirm password: ");
                                    cnofirmPassword = scanner.nextLine();
                                    
                                    if (newPassword.equals(cnofirmPassword)) {
                                        isValid = true;
                                    }
                                    else {
                                        System.out.print("\n\n  ! -- There is no match between the two passwords -- !");
                                        isValid = false;
                                    }
                                    
                                } while (!isValid);

                                // age
                                do {

                                    System.out.print("\n\n - Age: ");
                                    age = scanner.nextInt();
                                    
                                    if (age > 18 && age < 100) {
                                        isValid = true;
                                    }
                                    else {
                                        System.out.print("\n\n  ! -- Sorry, age must be between 18 and 100 to register -- !");
                                        isValid = false;
                                    }

                                } while (!isValid);

                                // check if user available in users arrayList. If not, user will be registered and returned to CUSTOMER LOGIN menu
                                boolean registerValid = true;
                                String customerID = "0";

                                if (VehicleManager.users != null) {
                                    for (int i = 0; i < VehicleManager.users.size(); i++) {
                                        
                                        if (VehicleManager.users.get(i) instanceof Customer) {

                                            // phone number stored before?
                                            if (VehicleManager.users.get(i).getPhoneNumber() == phoneNumber) {
                                                registerValid = false;
                                                System.out.print("\n\n  ! -- Phone number you entered appears to be in our system. Please try registering again with another phone number or login -- !");
                                                break;
                                            }
                                            // email stored before?
                                            else if (VehicleManager.users.get(i).getEmail() == email) {
                                                registerValid = false;
                                                System.out.print("\n\n  ! -- Email address you entered appears to be in our system. Please try registering again with another email address or login -- !");
                                                break;
                                            } 
                                        }
                                        

                                        customerID = String.valueOf(VehicleManager.users.size() - 1);
                                    }
                                }

                                if (registerValid) {

                                    newCustomer.registerUser(firstName, secondName, lastName, phoneNumber, email, newPassword, customerID, age);
                                    VehicleManager.users.add(newCustomer);

                                    System.out.print("\n\n ---- You have successfully registered. Now, you can login to enter to your account ----");
                                }

                                break;

                            // customer login
                            case 2:
                                // customer login
                                String customerEmail = "";

                                // email
                                do {
                                    System.out.print("\n\n - Email: ");
                                    customerEmail = scanner.next();
                                    scanner.nextLine(); // clear buffer

                                    isValid = validateEmail(customerEmail);

                                    if (!isValid) {
                                        System.out.print("\n\n  ! -- Email is invalid. Please enter email correctly -- !");
                                    }

                                } while (!isValid);

                                // password
                                String customerPassword = "";
                                System.out.print("\n\n - password: ");
                                customerPassword = scanner.nextLine();
                            
                                userFound = false;

                                User customerUser = null;
                                for (User user: VehicleManager.users) {

                                    if (user instanceof Customer) {
                                        
                                        // check email
                                        if (user.getEmail().equals(customerEmail)) {
                                            customerUser = user;
                                            userFound = true;
                                            break;
                                        }
                                    }
                                }

                                if (userFound) {

                                    // check password
                                    if (customerUser.getPassword().equals(customerPassword)) {
                                        VehicleManager.setCurrentUser(customerUser);
                                    }
                                    else {
                                        System.out.print("\n\n  ! -- Password is incorrect -- !");
                                    }                                        
                                }
                                else {
                                    System.out.print("\n\n  ! -- Email is not available in our system -- !");
                                }
                                
                                break;

                            case 0:
                                break;
                            
                            default:
                                System.out.print("\n\n  ! -- Please enter a correct number from the menu -- !");
                                break;
                            }

                    } while (customerChoice != 0 && VehicleManager.getCurrentUser() == null);
                    
                    break;

                case 0:
                    
                    System.out.print("\n\n BYE BYE --------------------");
            }
            

        } while (userChoice != 0 && VehicleManager.getCurrentUser() == null);

        // if user entered 0, the program terminates here
        if (userChoice == 0) {
            return;
        }


        if (VehicleManager.currentUser instanceof Admin) {

            int adminChoice = 0;
        
            // Admin menu
            System.out.print("\n\n\n  ---- WELCOME " + VehicleManager.currentUser.getUserName().toUpperCase() + " ----");
            do {
                System.out.print(
                    "\n\n  ---- ADMIN MAIN MENU ----" + "\n" +
                    "\n" +
                    "1 - View available vehicles" + "\n" +
                    "2 - Add a vehicle" + "\n" +
                    "3 - Update a vehicle" + "\n" +
                    "4 - Remove a vehicle" + "\n" +
                    "5 - View all current bookings" + "\n" +
                    "6 - Approve/Reject pending bookings" + "\n" +
                    "7 - Log out" + "\n" +
                    "\n" +
                    "Enter your choice: "
                );
        
                String adminChoiceInput = scanner.nextLine(); // Handle invalid input
                if (adminChoiceInput.matches("\\d")) {
                    adminChoice = Integer.parseInt(adminChoiceInput);
                } 
                else {
                    System.out.println("\n  ! -- Invalid input. Please enter a valid number -- !");
                    continue;
                }

        
                switch (adminChoice) {
                    case 1:
                        printAvailableVehicles();
                        break;

                    case 2:
                        System.out.print("\n\n  ---- ADD NEW VEHICLE ----");

                        boolean validType = true;
                        do {
                            System.out.print("\n\n - Enter vehicle type (Car, Van, Motorcycle): ");
                            String type = scanner.nextLine().toLowerCase();
    
                            if (type.equals("car")) {
                                
                                Car newCar = new Car();

                                // prompt user for new car details and assign them
                                newCar.requestCarDetails();
                                addVehicle(newCar);
                                break;
                            }
                            else if (type.equals("van")) {

                            }
                            else if (type.equals("motorcycle")) {

                            }
                            else {
                                System.out.print("\n\n  ! -- Invalid vehicle type. Please try again. -- !");
                                validType = false;
                            }
                        } while (!validType);
                        break;
        
                    case 3:
                        System.out.println("\n\n  ---- UPDATE VEHICLE ----");
                        // Provide vehicle update logic
                        break;
        
                    case 4:
                        System.out.println("\n\n  ---- REMOVE VEHICLE ----");
                        // Provide vehicle removal logic
                        break;
        
                    case 5:
                        System.out.println("\n\n  ---- VIEW ALL BOOKINGS ----");
                        // Show all bookings
                        break;
        
                    case 6:
                        System.out.println("\n\n  ---- APPROVE/REJECT BOOKINGS ----");
                        // Approve or reject bookings
                        break;
        
                    case 7:
                        System.out.println("\n\n Logging out...");
                        break;
        
                    default:
                        System.out.println("\n  ! -- Invalid choice. Please select a valid menu option -- !");
                }
        
            } while (adminChoice != 7);
        }
        

        
        if (VehicleManager.currentUser instanceof Customer) {

            int customerChoice = 0;
        
            // Customer menu
            System.out.print("\n\n\n  ---- WELCOME " + VehicleManager.currentUser.getUserName() + " ----");
            do {
                System.out.print(
                    "\n\n  ---- CUSTOMER MAIN MENU ----" + "\n" +
                    "\n" +
                    "1 - View available vehicles" + "\n" +
                    "2 - Book a vehicle" + "\n" +
                    "3 - Cancel a booking" + "\n" +
                    "4 - View my bookings" + "\n" +
                    "0 - Log out" + "\n" +
                    "\n" +
                    "Enter your choice: "
                );
        
                String customerChoiceInput = scanner.nextLine(); // Handle invalid input
                if (customerChoiceInput.matches("\\d")) {
                    customerChoice = Integer.parseInt(customerChoiceInput);
                } else {
                    System.out.println("\n  ! -- Invalid input. Please enter a valid number. -- !");
                    continue;
                }
        
                switch (customerChoice) {
                case 1:
                    printAvailableVehicles(); // Assuming this method prints all available vehicles
                    break;
            
                case 2:
                    System.out.println("\n\n  ---- BOOK A VEHICLE ----");
                    // Provide booking logic
                    System.out.print("Enter customer ID to book a vehicle: ");
                    int customerIdForBooking = scanner.nextInt();
                    Customer customerForBooking = customerManager.getCustomerById(customerIdForBooking); // Assuming customerManager exists
                    if (customerForBooking != null && customerForBooking.isEligible()) {
                        // Get the available vehicle
                        System.out.print("Enter vehicle ID to book: ");
                        int vehicleIdForBooking = scanner.nextInt();
                        Vehicle vehicleToBook = vehicleManager.getVehicleById(vehicleIdForBooking); // Assuming vehicleManager exists
                        if (vehicleToBook != null) {
                            // Check if the customer can book more vehicles (limit to 3 bookings)
                            if (customerForBooking.noVehiclesRented < Customer.MAX_BOOKINGS) {
                                // Assuming the dates are provided or calculated
                                Date bookingDate = new Date();  // Placeholder
                                Date fromDate = new Date();     // Placeholder
                                Date toDate = new Date();       // Placeholder
                                double totalAmount = 100.0;     // Placeholder
                                String currency = "USD";        // Placeholder
            
                                // Create the booking
                                Booking newBooking = new Booking(vehicleToBook, bookingDate, fromDate, toDate, totalAmount, currency, new Date());
                                customerForBooking.bookings[customerForBooking.noVehiclesRented++] = newBooking;
                                System.out.println("Booking successful!");
                            } else {
                                System.out.println("You have reached the maximum number of bookings.");
                            }
                        } else {
                            System.out.println("Vehicle not found.");
                        }
                    } else {
                        System.out.println("Customer is not eligible to rent a vehicle or customer not found.");
                    }
                    break;
            
                case 3:
                    System.out.println("\n\n  ---- CANCEL A BOOKING ----");
                    // Provide cancel booking logic
                    System.out.print("Enter customer ID to cancel a booking: ");
                    int customerIdForCancel = scanner.nextInt();
                    Customer customerForCancel = customerManager.getCustomerById(customerIdForCancel);
                    if (customerForCancel != null) {
                        System.out.print("Enter booking ID to cancel: ");
                        int bookingIdToCancel = scanner.nextInt();
                        if (bookingIdToCancel >= 0 && bookingIdToCancel < customerForCancel.noVehiclesRented) {
                            // Cancel the booking
                            customerForCancel.bookings[bookingIdToCancel] = null; // Remove booking
                            customerForCancel.noVehiclesRented--; // Decrease rented vehicles count
                            System.out.println("Booking cancelled successfully.");
                        } else {
                            System.out.println("Booking ID not valid.");
                        }
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;
            
                case 4:
                    System.out.println("\n\n  ---- VIEW MY BOOKINGS ----");
                    // Show customer bookings
                    System.out.print("Enter customer ID to view bookings: ");
                    int customerIdForViewBookings = scanner.nextInt();
                    Customer customerForViewBookings = customerManager.getCustomerById(customerIdForViewBookings);
                    if (customerForViewBookings != null) {
                        System.out.println("Customer's Bookings:");
                        if (customerForViewBookings.noVehiclesRented > 0) {
                            for (int i = 0; i < customerForViewBookings.noVehiclesRented; i++) {
                                System.out.println(customerForViewBookings.bookings[i]); // Assuming Booking class has a proper toString() method
                            }
                        } else {
                            System.out.println("No bookings found for this customer.");
                        }
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;
            
                case 0:
                    System.out.println("\n\n Logging out...");
                    break;
            
                default:
                    System.out.println("\n  ! -- Invalid choice. Please select a valid menu option. -- !");
            }

        
            } while (customerChoice != 0);
        }
        



    }

    public static void addVehicle(Vehicle newVehicle) {
        
        String vehicleID = String.valueOf(VehicleManager.noVehicles + 1);
        newVehicle.setVehicleID(vehicleID);
        VehicleManager.vehicles[VehicleManager.noVehicles] = newVehicle; 
    }

    public static void updateVehicle(Vehicle oldVehicle, Vehicle newVehicle) {

        for (int i = 0; i < VehicleManager.noVehicles; i++) {
            
            if (vehicles[i] == oldVehicle) {
                vehicles[i] = newVehicle;

                System.out.println("  -- Updated Successfuly --");
            }

            System.out.println("Couldn't found a vehicle with name: " + oldVehicle.name);
        }
    }

    public static void deleteVehicle(Vehicle vehicleToRemove) {

        int indexToRemove = VehicleManager.searchVehicleIndex(vehicleToRemove);

        if (indexToRemove < 0) {
            System.out.println("  -- There is no vehicle with the name: " + vehicleToRemove.name);
        }
        else {
            for (int i = indexToRemove; i < (VehicleManager.noVehicles - 1); i++) {
                VehicleManager.vehicles[i] = VehicleManager.vehicles[i + 1];
            }
        }
    }

    public boolean isAvailable(Vehicle vehicle) {

        int vehicleIndex = searchVehicleIndex(vehicle);

        if (vehicleIndex < 0) {
            return false;
        }
        else {
            if (VehicleManager.vehicles[vehicleIndex].isAvailable) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    public static Vehicle[] getAvailableVehicles() {

        int counter = 0;
        boolean allNull = true;
        Vehicle[] availableVehicles = new Vehicle[VehicleManager.MAX_VEHICLES - VehicleManager.vehicleReserved];
        
        for (int i = 0; i < VehicleManager.vehicles.length; i++) {

            if (VehicleManager.vehicles[i] != null) {

                if (VehicleManager.vehicles[i].isAvailable) {

                    availableVehicles[counter] = VehicleManager.vehicles[i];
                    counter++;
                }
            }

        }

        // check if all elements are null
        for (Vehicle vehicle: availableVehicles) {

            if (vehicle != null) {
                allNull = false;
                break;
            }
        }

        if (allNull) {
            return null;
        }

        return availableVehicles;
    }

    public static void printAvailableVehicles() {

        Vehicle[] availableVehicles = getAvailableVehicles();

        int counter = 0;

        if (availableVehicles == null) {
            System.out.print("\n\n  ! -- There are no available vehicles at this time -- !");
            return;
        }

        // cars list
        System.out.print("\n\n  ---- Available Cars ----");
        for (int i = 0; i < availableVehicles.length; i++) {

            if (availableVehicles[i] instanceof Car) {

                Car car = (Car) availableVehicles[i];

                System.out.print(
                    "\n\n - " + (counter + 1) + ". " + (car.name) + "\n" +
                    "    - Vehicle ID: " + car.getVehicleID() + "\n" +
                    "    - Make: " + car.getMake() + "\n" +
                    "    - Model: " + car.model + "\n" +
                    "    - Model Year: " + car.getYear() + "\n" +
                    "    - Transmission Type: " + car.getTransmissionType() + "\n" +
                    "    - Fuel Type: " + car.fuelType + "\n" +
                    "    - Color: " + car.getColor() + "\n" +
                    "    - Number of Seats: " + car.getNoSeats() + "\n" +
                    "    - Has insurance: " + (car.hasInsurance ? "Yes": "No") + "\n" +
                    "    - Price: " + car.getPrice()
                );

                counter++;
            }
        }

        counter = 0;
        // vans list
        System.out.print("\n\n  ---- Available Vans ----");
        for (int i = 0; i < availableVehicles.length; i++) {

            if (availableVehicles[i] instanceof Van) {

                Van van = (Van) availableVehicles[i];
                
                System.out.print(
                    "\n\n - " + (counter + 1) + ". " + (van.name) + "\n" +
                    "    - Vehicle ID: " + van.getVehicleID() + "\n" +
                    "    - Make: " + van.getMake() + "\n" +
                    "    - Model: " + van.model + "\n" +
                    "    - Model Year: " + van.getYear() + "\n" +
                    "    - Fuel Type: " + van.fuelType + "\n" +
                    "    - Color: " + van.getColor() + "\n" +
                    "    - Number of Seats: " + van.getNoSeats() + "\n" +
                    "    - Cargo space (cubic feet): " + van.getCargoSpace() + "\n" +
                    "    - Maximum Load (Kg): " + van.getMaxLoad() + "\n" +
                    "    - Has insurance: " + (van.hasInsurance ? "Yes": "No") + "\n" +
                    "    - Price: " + van.getPrice()
                );

                counter++;
            }
        }

        counter = 0;
        // motorcycles list
        System.out.print("\n\n  ---- Available Motorcycles ----");
        for (int i = 0; i < availableVehicles.length; i++) {

            if (availableVehicles[i] instanceof MotorCycle) {

                MotorCycle motorCycle = (MotorCycle) availableVehicles[i];
                
                System.out.print(
                    "\n\n - " + (counter + 1) + ". " + (motorCycle.name) + "\n" +
                    "    - Vehicle ID: " + motorCycle.getVehicleID() + "\n" +
                    "    - Make: " + motorCycle.getMake() + "\n" +
                    "    - Model: " + motorCycle.model + "\n" +
                    "    - Model Year: " + motorCycle.getYear() + "\n" +
                    "    - Type of Handle Bar: " + motorCycle.getHandleBar() + "\n" +
                    "    - Licence Required: " + (motorCycle.isLicenseRequired() ? "Yes": "No") + "\n" +
                    "    - Fuel Type: " + motorCycle.fuelType + "\n" +
                    "    - Color: " + motorCycle.getColor() + "\n" +
                    "    - Number of Seats: " + motorCycle.getNoSeats() + "\n" +
                    "    - Has insurance: " + (motorCycle.hasInsurance ? "Yes": "No") + "\n" +
                    "    - Price: " + motorCycle.getPrice()
                );

                counter++;
            }
        }

    }
    // if not found: returns -1
    public static int searchVehicleIndex(Vehicle vehicle) {

        for (int i = 0; i < VehicleManager.noVehicles; i++) {
            
            if (VehicleManager.vehicles[i] == vehicle) {
                return i;
            }
        }

        return -1;
    }

    // returns false if: name is null or has character that is not a letter
    // returns true: otherwise
    public static boolean validateName(String name) {

        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        
        for (int i = 0; i < name.length(); i++) {
            
            if (!Character.isLetter(name.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /*

    returns false if email doesn't have:
        - "@"
        - "." (dot)
        - domain part or local part has symbols
        - TLD (Top-Level Domain) doesn't equal ".com"

    returns true: otherwise
    */
    public static boolean validateEmail(String email) {

        int atIndex = email.indexOf('@');
        int dotIndex = email.indexOf('.');

        // top-level domain
        String userTLD = email.substring(dotIndex + 1);

        if (atIndex == -1 || atIndex == 0 || atIndex == email.length() - 1) {
            return false;
        }

        if (dotIndex == -1 || dotIndex == 0 || dotIndex == email.length() - 1) {
            return false;
        }

        String localPart = email.substring(0, atIndex);
        String domainPart = email.substring(atIndex + 1,  dotIndex);

        
        if (!userTLD.equals(VehicleManager.TLD)) {
            return false;
        }

        for (char letter: localPart.toCharArray()) {
            if (!Character.isLetterOrDigit(letter)) {
                return false;
            }
        }

        for (char letter: domainPart.toCharArray()) {
            if (!Character.isLetterOrDigit(letter)) {
                return false;
            }
        }

        return true;

    }

    public static boolean validatePassword(String password) {

        if (password == null || password.length() < 8) {
            return false;
        }

        int noUpperCases = 0, noLowerCases = 0, noLetters = 0, noDigits = 0, noSymbols = 0;
        String specialChars = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/"; 

        for (char character: password.toCharArray()) {
            
            if (Character.isUpperCase(character)) {
                noUpperCases += 1;
            }
            else if (Character.isLowerCase(character)) {
                noLowerCases += 1;
            }
            else if (Character.isDigit(character)) {
                noDigits += 1;
            }
            else if (specialChars.indexOf(character) >= 0) {
                noSymbols += 1;
            }
            else {
                return false;
            }
        }

        noLetters = noLowerCases + noUpperCases;

        if (noUpperCases >= 1 && noLowerCases >= 1 && noLetters >= 3 && noDigits >= 2 && noSymbols >= 2) {
            return true;
        }
        else {
            return false;
        }
    }
 
    public static void setCurrentUser(User user) {
        
        user.logInUser();
        VehicleManager.currentUser = user;
    }

    public static User getCurrentUser() {
        return VehicleManager.currentUser;
    }




}




// System.out.print(
//     " ---- Menu ---- " + "\n" +
//     "\n" +
//     "1 - Add new vehicle " + "\n" +
//     "2 - Update a vehicle " + "\n" +
//     "3 - Delete a vehicle " + "\n" +
//     "4 - Search for a vehicle " + "\n" +
//     "5 - Display available vehicles" + "\n" +
//     "6 - Display all vehicles in the lot " + "\n" +
//     "0 - Exit"
// );
