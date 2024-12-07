package Vehicle_Rental_System;

import java.time.LocalDate;
import java.util.Scanner;

public class MotorCycle extends Vehicle {
    // Private attributes specific to Motorcycle
    private String handleBarType; // Type of handlebar
    private boolean licenseRequired; // Whether a license is required to ride

    // Constructor

    public MotorCycle() {
        super("", "", "", "", 0, 0, 0, "", "", false, false);
    }

    public MotorCycle(String vehicleID, String name, String model, String fuelType, 
                      int year, double price, int noSeats, String make, 
                      String color, String handleBarType, 
                      boolean licenseRequired, boolean isAvailable) {
        // Call the constructor of the parent Vehicle class
        super(vehicleID, name, model, fuelType, year, price, noSeats, make, color, true, isAvailable);
        this.handleBarType = handleBarType;
        this.licenseRequired = licenseRequired;
    }

    // Getter and Setter for handleBarType
    public String getHandleBar() {
        return handleBarType;
    }

    public void setHandleBar(String handleBar) {
        this.handleBarType = handleBar;
    }

    // Getter and Setter for licenseRequired
    public boolean isLicenseRequired() {
        return licenseRequired;
    }

    public void setLicenseRequired(boolean isRequired) {
        this.licenseRequired = isRequired;
    }

    // Overriding the method to include additional information for Motorcycle
    @Override
    public void getVehicleInfo() {
        super.getVehicleInfo(); // Call the parent method to get common info
        System.out.println("Handle Bar Type: " + handleBarType);
        System.out.println("License Required: " + licenseRequired);
    }

    public void requestMotorCycleDetails() {
        Scanner scanner = new Scanner(System.in);
        LocalDate dateNow = LocalDate.now();
    
        // Ask for motorcycle name
        System.out.print("\n - Enter motorcycle name: ");
        super.name = scanner.nextLine();
    
        // Ask for motorcycle model
        System.out.print("\n - Enter motorcycle model: ");
        super.model = scanner.nextLine();
    
        // Ask for fuel type
        String[] validFuelTypes = {"Gasoline", "Diesel", "Electric", "Hybrid"};
        boolean validFuelType = false;
        do {
            System.out.print("\n - Enter fuel type (Gasoline, Diesel, Electric, Hybrid): ");
            super.fuelType = scanner.nextLine();
    
            // Check if input fuelType is valid
            for (String fuel : validFuelTypes) {
                if (super.fuelType.equalsIgnoreCase(fuel)) {
                    validFuelType = true;
                    break;
                }
            }
    
            if (!validFuelType) {
                System.out.print("\n\n  ! -- Invalid fuel type. Please enter a valid fuel type (Gasoline, Diesel, Electric, Hybrid) -- !\n");
            }
        } while (!validFuelType);
    
        // Ask for motorcycle year
        int year = 0;
        boolean validYear = false;
        do {
            System.out.print("\n - Enter motorcycle year: ");
            String yearInput = scanner.nextLine();
    
            if (yearInput.matches("^\\d{4}$")) {
                year = Integer.parseInt(yearInput);
                if (year > 1900 && year <= dateNow.getYear()) {
                    super.year = year;
                    validYear = true;
                } else {
                    System.out.print("\n\n  ! -- Invalid year. Please enter a year between 1900 and " + (dateNow.getYear()) + " -- !\n");
                    validYear = false;
                }
            } else {
                validYear = false;
                System.out.print("\n\n  ! -- Invalid input. Please enter a correct year number -- !\n");
            }
        } while (!validYear);
    
        // Ask for motorcycle price
        double price = 0;
        boolean validPrice = false;
        do {
            System.out.print("\n - Enter motorcycle price: ");
            String priceInput = scanner.nextLine();
    
            if (priceInput.matches("^\\d+(\\.\\d{1,2})?$")) {
                price = Double.parseDouble(priceInput);
                if (price > 0) {
                    super.price = price;
                    validPrice = true;
                } else {
                    System.out.print("\n\n  ! -- Invalid price. Please enter a price greater than 0 -- !\n");
                    validPrice = false;
                }
            } else {
                System.out.print("\n\n  ! -- Invalid input. Please enter a valid price -- !\n");
                validPrice = false;
            }
        } while (!validPrice);
    
        // Ask for number of seats
        int noSeats = 0;
        boolean validNoSeats = false;
        do {
            System.out.print("\n - Enter the number of seats: ");
            String seatsInput = scanner.nextLine();
            if (seatsInput.matches("\\d+")) {
                noSeats = Integer.parseInt(seatsInput);
                if (noSeats >= 1 && noSeats <= 2) { // Assuming a valid range of 1 to 2 seats for motorcycles
                    super.noSeats = noSeats;
                    validNoSeats = true;
                } else {
                    System.out.print("\n\n  ! -- Invalid number of seats. Please enter a number between 1 and 2 -- !\n");
                    validNoSeats = false;
                }
            } else {
                System.out.print("\n\n  ! -- Invalid input. Please enter a valid number for seats -- !\n");
                validNoSeats = false;
            }
        } while (!validNoSeats);
    
        // Ask for handlebar type
        System.out.print("\n - Enter handlebar type (e.g., Standard, Sport, Cruiser): ");
        this.handleBarType = scanner.nextLine();
    
        // Ask if license is required
        boolean validLicense = false;
        do {
            System.out.print("\n - Does the motorcycle require a license? (yes/no): ");
            String licenseInput = scanner.nextLine();
            if (licenseInput.equalsIgnoreCase("yes")) {
                this.licenseRequired = true;
                validLicense = true;
            } else if (licenseInput.equalsIgnoreCase("no")) {
                this.licenseRequired = false;
                validLicense = true;
            } else {
                System.out.print("\n\n  ! -- Invalid input. Please enter 'yes' or 'no' for license requirement -- !\n");
            }
        } while (!validLicense);
    
        // Ask for motorcycle make
        System.out.print("\n - Enter motorcycle make: ");
        super.make = scanner.nextLine();  // Added prompt for make
    
        // Ask for motorcycle color
        System.out.print("\n - Enter motorcycle color: ");
        super.color = scanner.nextLine();  // Added prompt for color
    
        // Ask for insurance status (yes/no)
        System.out.print("\n - Does the motorcycle have insurance? (yes/no): ");
        String insuranceInput = scanner.nextLine().toLowerCase();
        if (insuranceInput.equals("yes")) {
            super.hasInsurance = true;
        } else if (insuranceInput.equals("no")) {
            super.hasInsurance = false;
        } else {
            System.out.println("\n  ! -- Invalid input. Please enter 'yes' or 'no' for insurance -- !");
        }
    
        // Ask for availability status (yes/no)
        System.out.print("\n - Is the motorcycle available? (yes/no): ");
        String availabilityInput = scanner.nextLine().toLowerCase();
        if (availabilityInput.equals("yes")) {
            super.isAvailable = true;
        } else if (availabilityInput.equals("no")) {
            super.isAvailable = false;
        } else {
            System.out.println("\n  ! -- Invalid input. Please enter 'yes' or 'no' for availability -- !");
        }
    
        // Display entered details
        System.out.println("\n - Motorcycle details entered:");
        getVehicleInfo();
    }

}
