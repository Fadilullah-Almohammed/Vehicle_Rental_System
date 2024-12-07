package Vehicle_Rental_System;

import java.time.LocalDate;
import java.util.Scanner;

public class Van extends Vehicle {
    // Private attributes specific to Van
    private double cargoSpaceFt; // Cargo space in feet
    private double maxLoadKg;    // Maximum load in kilograms

    // Constructor

    public Van() {
        super("", "", "", "", 0, 0, 0, "", "", false, false);
    }

    public Van(String vehicleID, String name, String model, String fuelType, 
               int year, double price, int noSeats, String make, 
               String color, boolean hasInsurance, boolean isAvailable,
               double cargoSpaceFt, double maxLoadKg) {
        // Call the constructor of the parent Vehicle class
        super(vehicleID, name, model, fuelType, year, price, noSeats, make, color, hasInsurance, isAvailable);
        this.cargoSpaceFt = cargoSpaceFt;
        this.maxLoadKg = maxLoadKg;
    }

    // Getters and Setters for cargo space and max load
    public double getCargoSpace() {
        return cargoSpaceFt;
    }

    public void setCargoSpace(double cargoSpaceFt) {
        this.cargoSpaceFt = cargoSpaceFt;
    }

    public double getMaxLoad() {
        return maxLoadKg;
    }

    public void setMaxLoad(double maxLoadKg) {
        this.maxLoadKg = maxLoadKg;
    }

    // Overriding the method to include additional information for Van
    @Override
    public void getVehicleInfo() {
        super.getVehicleInfo(); // Call the parent method to get common info
        System.out.println("Cargo Space: " + cargoSpaceFt + " ft");
        System.out.println("Max Load: " + maxLoadKg + " kg");
    }

    public void requestVanDetails() {
        Scanner scanner = new Scanner(System.in);
        LocalDate dateNow = LocalDate.now();
    
        // Ask for van name
        System.out.print("\n - Enter van name: ");
        super.name = scanner.nextLine();
    
        // Ask for van model
        System.out.print("\n - Enter van model: ");
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
    
        // Ask for van year
        int year = 0;
        boolean validYear = false;
        do {
            System.out.print("\n - Enter van year: ");
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
    
        // Ask for van price
        double price = 0;
        boolean validPrice = false;
        do {
            System.out.print("\n - Enter van price: ");
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
                if (noSeats >= 1 && noSeats <= 15) {
                    super.noSeats = noSeats;
                    validNoSeats = true;
                } else {
                    System.out.print("\n\n  ! -- Invalid number of seats. Please enter a number between 1 and 15 -- !\n");
                    validNoSeats = false;
                }
            } else {
                System.out.print("\n\n  ! -- Invalid input. Please enter a valid number for seats -- !\n");
                validNoSeats = false;
            }
        } while (!validNoSeats);
    
        // Ask for cargo space and max load
        System.out.print("\n - Enter cargo space (in cubic feet): ");
        this.cargoSpaceFt = scanner.nextDouble();
    
        System.out.print("\n - Enter max load (in kilograms): ");
        this.maxLoadKg = scanner.nextDouble();
    
        // Consume the newline character left by nextDouble()
        scanner.nextLine();
    
        // Ask for van make
        System.out.print("\n - Enter van make: ");
        super.make = scanner.nextLine();  // Added prompt for make
    
        // Ask for van color
        System.out.print("\n - Enter van color: ");
        super.color = scanner.nextLine();  // Added prompt for color
    
        // Ask for insurance status (yes/no)
        System.out.print("\n - Does the van have insurance? (yes/no): ");
        String insuranceInput = scanner.nextLine().toLowerCase();
        if (insuranceInput.equals("yes")) {
            super.hasInsurance = true;
        } else if (insuranceInput.equals("no")) {
            super.hasInsurance = false;
        } else {
            System.out.println("\n  ! -- Invalid input. Please enter 'yes' or 'no' for insurance -- !");
        }
    
        // Ask for availability status (yes/no)
        System.out.print("\n - Is the van available? (yes/no): ");
        String availabilityInput = scanner.nextLine().toLowerCase();
        if (availabilityInput.equals("yes")) {
            super.isAvailable = true;
        } else if (availabilityInput.equals("no")) {
            super.isAvailable = false;
        } else {
            System.out.println("\n  ! -- Invalid input. Please enter 'yes' or 'no' for availability -- !");
        }
    
        // Display entered details
        System.out.println("\n - Van details entered:");
        getVehicleInfo();
    }


}
