/*
 * 
 *  Group: 5M07
 *  Group Section: 7
 * 
 *  Students Team:
 *  - Fadhlallah Almohammed 2230006097"
    - Abdulrahman Bushihab  2230004493"
    - Omar Alsubgh          2210002182"
    - Ali Alhashim          2230004042"
    - Abdulrahman Alakkas   2230006008"
 * 
 * You can login as admin directly for test purposes:
 *  - email: ali@gmail.com
 *  - password: Ali11!!1
 *  - authorization code: authorize12!@
 * 
 * You can login as cutomer directly for test purposes:
 *  - email: moh@gmail.com
 *  - password: Moham12!@
 * 
 */




package Vehicle_Rental_System;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class VehicleManager {


    private static final int MAX_VEHICLES = 100;
    
    //Top-Level Domain
    private static final String TLD = "com";
    private static final String CURRENCY = "Saudi Riyal";

    private static LocalDate dateNow = LocalDate.now();
    private static Vehicle[] vehicles = new Vehicle[MAX_VEHICLES];
    private static ArrayList<User> users = new ArrayList<User>();
    private static User currentUser;

    private static int noVehicles;
    private static int vehicleReserved;

    
    VehicleManager() {

    }

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //----------------------------- FOR TEST PURPOSES -----------------------------//
        User admin1 = new Admin();
        admin1.registerUser("Ali", "Mohammed", "Hasan", "0559645334", "ali@gmail.com", "Ali11!!1", "1", 19);
        VehicleManager.users.add(admin1);

        User customer1 = new Customer();
        customer1.registerUser("Mohammed", "Ahmed", "Hasan", "0596884732", "moh@gmail.com", "Moham12!@", "1", 30);
        VehicleManager.users.add(customer1);

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
        VehicleManager.vehicles[Integer.parseInt(car1.getVehicleID()) - 1] = car1;
        VehicleManager.noVehicles++;


        

        //----------------------------- FOR TEST PURPOSES -----------------------------//
        
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

                                    String firstName = null, secondName, lastName, phoneNumber, email, newPassword, cnofirmPassword, adminID = "";
                                    int age = 0;
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
                                        String ageToCheck;
                                        boolean hasLetter = false;

                                        System.out.print("\n\n - Age: ");
                                        ageToCheck = scanner.nextLine();
                                        for (char digit: ageToCheck.toCharArray()) {
                                            
                                            if (!Character.isDigit(digit)) {
                                                hasLetter = true;
                                                break;
                                            }
                                        }

                                        if (hasLetter) {
                                            isValid = false;
                                            System.out.print("\n\n  ! -- age must be digits only with no space -- !");
                                        }
                                        else {
                                            isValid = true;
                                            age = Integer.parseInt(ageToCheck);
                                        }
                                    } while (!isValid);

                                    boolean isAuthenticated = false;
                                    // authentication verification
                                    System.out.print("\n\n - Authentication code: ");
                                    
                                    isAuthenticated = ((Admin) newAdmin).isAuthorized(scanner.nextLine());
                                    
                                    if (!isAuthenticated) {
                                        System.out.print("\n\n  ! -- Sorry, you are not authorized to log in -- !");
                                    }
                                    else {
                                        
                                        // check if user available in users arrayList. If not, user will be registered and returned to ADMIN LOGIN menu
                                        boolean registerValid = true;

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

                                    isAuthenticated = false;
                                    System.out.print("\n\n - Authentication code: ");
                                    
                                    isAuthenticated = Admin.isAuthorized(scanner.nextLine());
                                    
                                    if (!isAuthenticated) {
                                        System.out.print("\n\n  ! -- Sorry, you are not authorized to log in -- !");
                                    }
                                    else {

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
                                int age = 0;
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
                                    String ageToCheck;
                                    boolean hasLetter = false;

                                    System.out.print("\n\n - Age: ");
                                    ageToCheck = scanner.nextLine();
                                    for (char digit: ageToCheck.toCharArray()) {
                                        
                                        if (!Character.isDigit(digit)) {
                                            hasLetter = true;
                                            break;
                                        }
                                    }

                                    if (hasLetter) {
                                        isValid = false;
                                        System.out.print("\n\n  ! -- age must be digits only with no space -- !");
                                    }
                                    else {
                                        isValid = true;
                                        age = Integer.parseInt(ageToCheck);
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
                    
                System.out.print(
                    "\n\n  ---- THANK YOU FOR USING VEHICLE RENTAL SYSTEM ----" + "\n" +
                    "\n" +
                    "   - GROUP: 5M07" + "\n" +
                    "   - GROUP SECTION: 7" + "\n" +
                    "   - STUDENTS TEAM:" + "\n\n" +
                    "       - Fadhlallah Almohammed 2230006097" + "\n" +
                    "       - Abdulrahman Bushihab  2230004493" + "\n" +
                    "       - Omar Alsubgh          2210002182" + "\n" +
                    "       - Ali Alhashim          2230004042" + "\n" +
                    "       - Abdulrahman Alakkas   2230006008" + "\n" +
                    "\n" +
                    "\n" +
                    "    ---- GOOD BYE ----"
                );
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
                    "0 - Log out" + "\n" +
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
                        } else if (type.equals("van")) {
                            Van newVan = new Van();
                            newVan.requestVanDetails();
                            addVehicle(newVan);
                            break;

                        } else if (type.equals("motorcycle")) {
                            MotorCycle newMotorCycle = new MotorCycle();
                            newMotorCycle.requestMotorCycleDetails();
                            addVehicle(newMotorCycle);
                            break;
                        } else {
                            System.out.print("\n\n  ! -- Invalid vehicle type. Please try again. -- !");
                            validType = false;
                        }
                    } while (!validType);
                    break;

                    case 3:
                        System.out.println("\n\n  ---- UPDATE VEHICLE ----");
                    
                        validType = true;
                        do {
                            System.out.print("\n\n - Enter vehicle type (Car, Van, Motorcycle): ");
                            String type = scanner.nextLine().toLowerCase();
                    
                            if (type.equals("car")) {
                                printAvailableCars();
                                System.out.print("\n\n - Enter the ID of the vehicle you wish to update: ");
                                String vehicleIdInput = scanner.nextLine();
                    
                                Vehicle vehicleToBook = VehicleManager.getVehicleByID(vehicleIdInput);
                    
                                if (vehicleToBook != null && vehicleToBook.isAvailable) {  
                                    Car carToUpdate = (Car) vehicleToBook;
                                    carToUpdate.requestCarDetails();
                    
                                    int index = searchVehicleIndex(vehicleToBook);
                                    if (index >= 0) {
                                        VehicleManager.vehicles[index] = carToUpdate;
                                    }
                                } else {
                                    System.out.print("\n\n  ! -- Car is not available or does not exist. -- !");
                                }
                    
                                break;
                    
                            } else if (type.equals("van")) {
                                printAvailabeVans();
                                System.out.print("\n\n - Enter the ID of the vehicle you wish to update: ");
                                String vehicleIdInput = scanner.nextLine();
                    
                                Vehicle vehicleToBook = VehicleManager.getVehicleByID(vehicleIdInput);
                    
                                if (vehicleToBook != null && vehicleToBook.isAvailable) {  
                                    Van vanToUpdate = (Van) vehicleToBook;
                                    vanToUpdate.requestVanDetails();
                    
                                    int index = searchVehicleIndex(vehicleToBook);
                                    if (index >= 0) {
                                        VehicleManager.vehicles[index] = vanToUpdate;
                                    }
                                } else {
                                    System.out.print("\n\n  ! -- Van is not available or does not exist. -- !");
                                }
                    
                                break;
                    
                            } else if (type.equals("motorcycle")) {
                                printAvailableMotorCycles();
                                System.out.print("\n\n - Enter the ID of the motorcycle you wish to update: ");
                                String vehicleIdInput = scanner.nextLine();
                    
                                Vehicle vehicleToBook = VehicleManager.getVehicleByID(vehicleIdInput);
                    
                                if (vehicleToBook != null && vehicleToBook.isAvailable) {  
                                    MotorCycle motorcycleToUpdate = (MotorCycle) vehicleToBook;
                                    motorcycleToUpdate.requestMotorCycleDetails();
                    
                                    int index = searchVehicleIndex(vehicleToBook);
                                    if (index >= 0) {
                                        VehicleManager.vehicles[index] = motorcycleToUpdate;
                                    }
                                } else {
                                    System.out.print("\n\n  ! -- Motorcycle is not available or does not exist. -- !");
                                }
                    
                                break;
                    
                            } else {
                                System.out.print("\n\n  ! -- Invalid vehicle type. Please try again. -- !");
                                validType = false;
                            }
                        } while (!validType);
                        break;
                    case 4:
                        System.out.println("\n\n  ---- REMOVE VEHICLE ----");
                        
                        validType = false;
                        do {
                            System.out.print("\n\n - Enter vehicle type (Car, Van, Motorcycle): ");
                            String type = scanner.nextLine().toLowerCase();
    
                            if (type.equals("car")) {
                                
                                validType = true;

                                printAvailableCars();
                

                            }
                            else if (type.equals("van")) {

                                validType = true;
                                printAvailabeVans();

                            }
                            else if (type.equals("motorcycle")) {

                                validType = true;
                                printAvailableMotorCycles();

                            }
                            else {
                                System.out.print("\n\n  ! -- Invalid vehicle type. Please try again. -- !");
                                validType = false;
                            }
                        } while (!validType);
                        
                        System.out.print("\n\n - Enter the ID of the vehicle you wish to remove: ");
                        String vehicleIdInput = scanner.nextLine();
                    
                        // Get the vehicle by ID
                        Vehicle vehicleToRemove = VehicleManager.getVehicleByID(vehicleIdInput);

                        deleteVehicle(vehicleToRemove);

                        break;
        
                    case 5:
                        System.out.println("\n\n  ---- VIEW ALL BOOKINGS ----");
                        // Show all bookings
                        break;
        
                    case 0:

                        System.out.println("\n\n Logging out...");
                        break;
        
                    default:
                        System.out.println("\n  ! -- Invalid choice. Please select a valid menu option -- !");
                        break;
                }
        
            } while (adminChoice != 0);
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
        
                Customer customer = (Customer) VehicleManager.currentUser;

                switch (customerChoice) {
                    case 1:
                        // View available vehicles
                        printAvailableVehicles();
                        break;
        
                    case 2:
                    System.out.println("\n\n  ---- BOOK A VEHICLE ----");

                    // Step 1: Prompt for the vehicle type (Car, Van, or Motorcycle)
                    String vehicleType = "";
                    boolean validType = false;
                    boolean areAvailable = true;
                    
                    do {

                        System.out.print("\n - Please choose a vehicle type (Car, Van, Motorcycle): ");
                        vehicleType = scanner.nextLine().toLowerCase();
                
                        if (vehicleType.equals("car")) {
                            
                            validType = true;
                            
                            // Display the list of available cars
                            printAvailableCars();

                            if (getAvailableVehicles() == null) {
                                areAvailable = false;
                            }

                        } 
                        else if (vehicleType.equals("van")) {
                            validType = true;

                            // Display the list of available vans
                            printAvailabeVans();

                            if (getAvailableVehicles() == null) {
                                areAvailable = false;
                            }

                        }
                        else if (vehicleType.equals("motorcycle")) {
                            validType = true;

                            // Display the list of available motorcycles
                            System.out.println("\n  ---- AVAILABLE MOTORCYCLES ----");
                            printAvailableMotorCycles();

                            if (getAvailableVehicles() == null) {
                                areAvailable = false;
                            }

                        }
                        else {
                            System.out.println("\n  ! -- Invalid vehicle type. Please try again -- !");
                        }
                    } while (!validType);
        
                    if (!areAvailable) {
                        break;
                    }
                    // Ask for the vehicle ID to book

                    System.out.print("\n\n - Enter the ID of the vehicle you wish to book: ");
                    String vehicleIdInput = scanner.nextLine();
                
                    // Get the vehicle by ID
                    Vehicle vehicleToBook = VehicleManager.getVehicleByID(vehicleIdInput);
                    
                    // Check if the vehicle is available and proceed with booking
                    if (vehicleToBook != null && vehicleToBook.isAvailable) {

                        String startDateCheck, endDateCheck, month, day;
                        int startMonth = 0, startDay = 0, endMonth = 0, endDay = 0;
                        int year = 0;

                        year = VehicleManager.dateNow.getYear();
                        
                        boolean isValid = false;

                        // start Date prompt and error handling
                        do {

                            boolean hasLetter = false;

                            System.out.print("\n\n - Enter the booking start date in the format: MM-dd with spaces (e.g. 2 4 | e.g. 11 30): ");
                            startDateCheck = scanner.nextLine();

                            if (startDateCheck.split(" ").length < 2) {
                                System.out.print("\n\n  ! -- Invalid input -- !");
                                continue;
                            }


                            month = startDateCheck.split(" ")[0];
                            day = startDateCheck.split(" ")[1];

                            for (char digit: month.toCharArray()) {
                                
                                if (!Character.isDigit(digit)) {
                                    hasLetter = true;
                                    break;
                                }
                            }

                            for (char digit: day.toCharArray()) {

                                if (!Character.isDigit(digit)) {
                                    hasLetter = true;
                                    break;
                                }
                            }

                            if (hasLetter) {
                                isValid = false;
                                System.out.print("\n\n  ! -- Please enter numbers -- !");
                            }
                            else {

                                startMonth = Integer.parseInt(month);
                                startDay = Integer.parseInt(day);

                                if ((startMonth > 0 && startMonth < 13) && (startDay > 0 && startDay < 32)) {
                                    isValid = true;
                                }
                                else {
                                    System.out.print("\n\n  ! -- Date format is incorrct -- !");
                                    isValid = false;
                                }
                            }

                        } while (!isValid);


                        // end Date prompt and error handling
                        do {
                            boolean hasLetter = false;

                            System.out.print("\n\n - Enter the booking end date in the format: MM-dd with spaces (e.g. 2 4 | e.g. 11 30): ");
                            endDateCheck = scanner.nextLine();

                            month = endDateCheck.split(" ")[0];
                            day = endDateCheck.split(" ")[1];

                            for (char digit: month.toCharArray()) {
                                
                                if (!Character.isDigit(digit)) {
                                    hasLetter = true;
                                    break;
                                }
                            }

                            for (char digit: day.toCharArray()) {

                                if (!Character.isDigit(digit)) {
                                }
                            }

                            if (hasLetter) {
                                isValid = false;
                                System.out.print("\n\n  ! -- Please enter numbers -- !");
                            }
                            else {

                                endMonth = Integer.parseInt(month);
                                endDay = Integer.parseInt(day);

                                if ((endMonth > 0 && endMonth < 13) && (endDay > 0 && endDay < 32)) {
                                    isValid = true;
                                }
                                else {
                                    System.out.print("\n\n  ! -- Date format is incorrct -- !");
                                    isValid = false;
                                }
                            }

                        } while (!isValid);
                        LocalDate startDate = LocalDate.of(year, startMonth, startDay);
                        LocalDate endDate = LocalDate.of(year, endMonth, endDay);
                
                        // Call bookVehicle to complete the booking process
                        if (VehicleManager.bookVehicle(vehicleToBook, startDate, endDate, (Customer) VehicleManager.currentUser)) {

                            System.out.print("\n\n  ---- Vehicle Info ----");
                            vehicleToBook.getVehicleInfo();
                            System.out.print("\n\n -- Booked succesfully --\n\n");

                        }
                        else {

                            System.out.println("Failed to book the vehicle. It might already be booked or unavailable.");
                        }
                    } else {

                        System.out.println("\n\n  ! -- Invalid vehicle ID or vehicle is not available -- !");
                    }
                    break;
        
                    case 3: // Cancel Booking
                        if (customer.bookings.isEmpty()) {
                            System.out.println("\n\n  ! -- You have no bookings to cancel -- !");
                            break;
                        }

                        System.out.println("\n\n  ---- Your Bookings ----");
                        for (Booking booking : customer.bookings) {
                            booking.displayBookingDetails();
                        }

                        System.out.print("\n\n - Enter the Vehicle ID to cancel your booking:");
                        String vehicleIdToCancel = scanner.nextLine();

                        boolean bookingFound = false;

                        // Use a standard for loop to safely remove bookings
                        for (int i = 0; i < customer.bookings.size(); i++) {
                            Booking booking = customer.bookings.get(i);
                            if (booking.vehicle.getVehicleID().equals(vehicleIdToCancel)) {
                                customer.bookings.remove(i); // Remove the booking directly
                                booking.vehicle.isAvailable = true; // Mark vehicle as available
                                System.out.println("\n\n  -- Booking successfully canceled --");
                                bookingFound = true;
                                break; // Exit loop after successful cancellation
                            }
                        }

                        if (!bookingFound) {
                            System.out.println("\n\n  ! -- No booking found with the specified Vehicle ID -- !");
                        }
                        break;


                    case 4: // Display All Bookings
                
                    System.out.println("Your Bookings:");



                    if (customer.bookings.isEmpty()) {
                        System.out.print("\n\n  ! -- You don't have any bookings yet -- !");
                        break;
                    }
                    else {
                        for (Booking booking: customer.bookings) {

                        
                            booking.displayBookingDetails();
                        }
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

        VehicleManager.noVehicles++;
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
            System.out.println("\n\n  -- There is no vehicle with the name: " + vehicleToRemove.name);
        }
        else {
            for (int i = indexToRemove; i < (VehicleManager.noVehicles); i++) {
                VehicleManager.vehicles[i] = VehicleManager.vehicles[i + 1];
            }
        }
    }

public static void deleteBooking(Customer customer, Booking bookingToDelete) {

    if (!customer.bookings.contains(bookingToDelete)) {
        System.out.println("\n\n  ! -- The booking is not available -- !");
    } else {
        customer.bookings.remove(bookingToDelete);
        System.out.println("\n\n  -- Booking successfully canceled --");
        bookingToDelete.vehicle.isAvailable = true; // Mark vehicle as available again
    }
}


    public static boolean isAvailable(Vehicle vehicle) {

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
        
        if (VehicleManager.vehicles == null) {
            return null;
        }

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
    
    public static void printAvailableCars() {

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
    }
    
    
    public static void printAvailabeVans() {

        Vehicle[] availableVehicles = getAvailableVehicles();

        int counter = 0;

        if (availableVehicles == null) {
            System.out.print("\n\n  ! -- There are no available vehicles at this time -- !");
            return;
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

    }
    
    public static void printAvailableMotorCycles() {

        Vehicle[] availableVehicles = getAvailableVehicles();

        int counter = 0;

        if (availableVehicles == null) {
            System.out.print("\n\n  ! -- There are no available vehicles at this time -- !");
            return;
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

    public static Vehicle getVehicleByID(String id) {

        Vehicle[] availableVehicles = getAvailableVehicles();

        if (availableVehicles == null) {
            System.out.print("\n\n -- no available vehicles --");
            return null;
        }

        for (Vehicle vehicle: availableVehicles) {
            
            if (vehicle.getVehicleID() == "") {
                return null;
            }
            if (vehicle.getVehicleID().equals(id)) {
                return vehicle;
            }
        }

        return null;

        

    }

    public static boolean bookVehicle(Vehicle vehicle, LocalDate startDate, LocalDate endDate, Customer currentUser) {

        // Check if vehicle is available
        if (!vehicle.isAvailable) {

            System.out.print("\n\n  ! -- The vehicle is unavailable -- !");
            return false;
        }



        Booking newBooking = new Booking(vehicle, VehicleManager.dateNow, startDate, endDate, vehicle.getPrice(), VehicleManager.CURRENCY, VehicleManager.dateNow);

        currentUser.bookings.add(newBooking);

        vehicle.isAvailable = false;
    
        return true;
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


            // // Customer menu
            // System.out.print("\n\n\n  ---- WELCOME " + VehicleManager.currentUser.getUserName() + " ----");
            // do {
            //     System.out.print(
            //         "\n\n  ---- CUSTOMER MAIN MENU ----" + "\n" +
            //         "\n" +
            //         "1 - View available vehicles" + "\n" +
            //         "2 - Book a vehicle" + "\n" +
            //         "3 - Cancel a booking" + "\n" +
            //         "4 - View my bookings" + "\n" +
            //         "0 - Log out" + "\n" +
            //         "\n" +
            //         "Enter your choice: "
            //     );
        
            //     String customerChoiceInput = scanner.nextLine(); // Handle invalid input
            //     if (customerChoiceInput.matches("\\d")) {
            //         customerChoice = Integer.parseInt(customerChoiceInput);
            //     } else {
            //         System.out.println("\n  ! -- Invalid input. Please enter a valid number. -- !");
            //         continue;
            //     }
        
        //         switch (customerChoice) {
        //             case 1:
        //                 // View available vehicles
        //                 printAvailableVehicles();
        //                 break;
        
        //             case 2:
        //             System.out.println("\n\n  ---- BOOK A VEHICLE ----");

        //             // Step 1: Prompt for the vehicle type (Car, Van, or Motorcycle)
        //             String vehicleType = "";
        //             boolean validType = false;
                
        //             // Loop until a valid type is selected
        //             do {

        //                 System.out.print("Please choose a vehicle type (Car, Van, Motorcycle): ");
        //                 vehicleType = scanner.nextLine().toLowerCase();
                
        //                 if (vehicleType.equals("car")) {
                            
        //                     validType = true;
                            
        //                     // Display the list of available cars
        //                     printAvailableCars();

        //                 } 
        //                 else if (vehicleType.equals("van")) {
        //                     validType = true;

        //                     // Display the list of available vans
        //                     printAvailabeVans();

        //                 }
        //                 else if (vehicleType.equals("motorcycle")) {
        //                     validType = true;

        //                     // Display the list of available motorcycles
        //                     System.out.println("\n  ---- AVAILABLE MOTORCYCLES ----");
        //                     printAvailableMotorCycles();
        //                 } else {
        //                     System.out.println("\n  ! -- Invalid vehicle type. Please try again -- !");
        //                 }
        //             } while (!validType);
                
        //             // Ask for the vehicle ID to book

        //             System.out.print("Enter the ID of the vehicle you wish to book: ");
        //             String vehicleIdInput = scanner.nextLine();
                
        //             // Get the vehicle by ID
        //             Vehicle vehicleToBook = VehicleManager.getVehicleByID(vehicleIdInput);
                    
        //             // Check if the vehicle is available and proceed with booking
        //             if (vehicleToBook != null && vehicleToBook.isAvailable) {

        //                 System.out.print(" - Enter the booking start date (YYYY-MM-DD): ");
        //                 String startDate = scanner.nextLine();
        //                 System.out.print(" - Enter the booking end date (YYYY-MM-DD): ");
        //                 String endDate = scanner.nextLine();
                
        //                 // Call bookVehicle to complete the booking process
        //                 if (VehicleManager.bookVehicle(vehicleToBook, startDate, endDate, (Customer) VehicleManager.currentUser)) {
        //                     System.out.println("Vehicle booked successfully!");
        //                 } else {
        //                     System.out.println("Failed to book the vehicle. It might already be booked or unavailable.");
        //                 }
        //             } else {
        //                 System.out.println("Invalid vehicle ID or vehicle is not available.");
        //             }
        //             break;
        
        //             case 3:
        //                 System.out.println("\n\n  ---- CANCEL A BOOKING ----");
        
        //                 // Show the list of customer bookings to cancel
        //                 System.out.println("Fetching your bookings...");
        //                 List<Booking> bookings = VehicleManager.getCustomerBookings((Customer) VehicleManager.currentUser);
        //                 if (bookings.isEmpty()) {
        //                     System.out.println("No bookings found.");
        //                 } else {
        //                     // List all bookings with options to cancel
        //                     for (int i = 0; i < bookings.size(); i++) {
        //                         System.out.println((i + 1) + ". " + bookings.get(i).toString());
        //                     }
        //                     System.out.print("Enter the booking number you want to cancel: ");
        //                     String cancelChoiceInput = scanner.nextLine();
        //                     if (cancelChoiceInput.matches("\\d")) {
        //                         int cancelChoice = Integer.parseInt(cancelChoiceInput);
        //                         if (cancelChoice > 0 && cancelChoice <= bookings.size()) {
        //                             Booking bookingToCancel = bookings.get(cancelChoice - 1);
        //                             if (cancelBooking(bookingToCancel)) {
        //                                 System.out.println("Booking canceled successfully.");
        //                             } else {
        //                                 System.out.println("Failed to cancel the booking. Try again.");
        //                             }
        //                         } else {
        //                             System.out.println("Invalid choice.");
        //                         }
        //                     } else {
        //                         System.out.println("Invalid input.");
        //                     }
        //                 }
        //                 break;
        
        //             case 4:
        //                 System.out.println("\n\n  ---- VIEW MY BOOKINGS ----");
        
        //                 // Show the list of customer bookings
        //                 System.out.println("Fetching your bookings...");
        //                 List<Booking> myBookings = VehicleManager.getCustomerBookings((Customer) VehicleManager.currentUser);
        //                 if (myBookings.isEmpty()) {
        //                     System.out.println("No bookings found.");
        //                 } else {
        //                     for (Booking booking : myBookings) {
        //                         System.out.println(booking.toString());
        //                     }
        //                 }
        //                 break;
        
        //             case 0:
        //                 System.out.println("\n\n Logging out...");
        //                 break;
        
        //             default:
        //                 System.out.println("\n  ! -- Invalid choice. Please select a valid menu option. -- !");
        //         }
        
        //     } while (customerChoice != 0);
        // }
        
