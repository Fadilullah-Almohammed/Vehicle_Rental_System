package Vehicle_Rental_System;

import java.util.Scanner;
import java.time.LocalDate;

public class Car extends Vehicle {
    // Private attribute
    private String transmissionType;

    // Constructor

    Car() {
        super("", "", "", "", 0, 0, 0, "", "", false, false);
    }
    
    Car(
            String vehicleID,
            String name,
            String model,
            String fuelType,
            int year,
            double price,
            int noSeats,
            String make,
            String color,
            String transmissionType,
            boolean hasInsurance,
            boolean isAvailable
        ) {
        super(vehicleID, name, model, fuelType, year, price, noSeats, make,
                color, hasInsurance, isAvailable); // Call to parent class constructor
        this.transmissionType = transmissionType;
    }

    // Getter for transmissionType
    public String getTransmissionType() {
        return transmissionType;
    }
    
    // Setter for transmissionType
    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }
    
    // Prompt user to add car details (For admins!)
    public void requestCarDetails() {
        
        Scanner scanner = new Scanner(System.in);
        LocalDate dateNow = LocalDate.now();
        
        // Ask for car name
        System.out.print("\n - Enter car name: ");
        super.name = scanner.nextLine();
        
        // Ask for car model
        System.out.print("\n - Enter car model: ");
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
        

        // Ask for car year
        int year = 0;
        boolean validYear = false;

        do {
            System.out.print("\n - Enter car year: ");
            String yearInput = scanner.nextLine();
            
            if (yearInput.matches("^\\d{4}$")) {
                year = Integer.parseInt(yearInput);
                
                // Assuming 2024 is the latest year.
                if (year > 1900 && year <= (dateNow.getYear())) { 

                    super.year = year;
                    validYear = true;
                }
                else {
                    System.out.print("\n\n  ! -- Invalid year. Please enter a year between 1900 and " + (dateNow.getYear()) + " -- !\n");
                    validYear = false;
                }
            }
            else {
                validYear = false;
                System.out.print("\n\n  ! -- Invalid input. Please enter a correct number of year -- !\n");
            }
            
        } while (!validYear);
        

        // Ask for car price
        double price = 0;
        boolean validPrice = false;
        
        do {
            System.out.print("\n - Enter car price: ");
            String priceInput = scanner.nextLine();
            
            if (priceInput.matches("^\\d+(\\.\\d{1,2})?$")) {
                price = Double.parseDouble(priceInput);  // Parse price as a double
                
                if (price > 0) {
                    super.price = price;
                    validPrice = true;
                } 
                else {
                    System.out.print("\n\n  ! -- Invalid price. Please enter a price greater than 0 -- !\n");
                    validPrice = false;
                }
            }
            else {
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
            if (seatsInput.matches("\\d+")) { // Ensure it's a valid number
                noSeats = Integer.parseInt(seatsInput);
                if (noSeats >= 1 && noSeats <= 7) {  // Assuming a valid range of 1 to 7 seats
                    super.noSeats = noSeats;
                    validNoSeats = true;
                } else {
                    System.out.print("\n\n  ! -- Invalid number of seats. Please enter a number between 1 and 7 -- !\n");
                    validNoSeats = false;
                }
            } else {
                System.out.print("\n\n  ! -- Invalid input. Please enter a valid number for seats -- !\n");
                validNoSeats = false;
            }
        } while (!validNoSeats);
        
        
        // Ask for car make
        System.out.print("\n - Enter car make: ");
        super.make = scanner.nextLine();
        
        // Ask for car color
        System.out.print("\n - Enter car color: ");
        super.color = scanner.nextLine();


        // Ask for transmission type
        String[] validTransmissions = {"Automatic", "Manual"};
        boolean validTransmission = false;
        do {
            System.out.print("\n - Enter transmission type (Automatic, Manual): ");
            this.transmissionType = scanner.nextLine();
            
            for (String trans : validTransmissions) {
                if (this.transmissionType.equalsIgnoreCase(trans)) {
                    validTransmission = true;
                    break;
                }
            }
            
            if (!validTransmission) {
                System.out.print("\n\n  ! -- Invalid transmission type. Please enter a valid transmission type (Automatic, Manual) -- !\n");
            }
        } while (!validTransmission);

        // Ask for car insurance status
        boolean validInsurance = false;
        do {
            System.out.print("\n - Does the car have insurance? (yes/no): ");
            String insuranceInput = scanner.nextLine();
            if (insuranceInput.equalsIgnoreCase("yes")) {
                super.hasInsurance = true;
                validInsurance = true;
            } else if (insuranceInput.equalsIgnoreCase("no")) {
                super.hasInsurance = false;
                validInsurance = true;
            } else {
                System.out.print("\n\n  ! -- Invalid input. Please enter 'yes' or 'no' for insurance -- !\n");
            }
        } while (!validInsurance);

        // Ask for car availability status
        boolean validAvailability = false;
        do {
            System.out.print("\n - Is the car available? (yes/no): ");
            String availabilityInput = scanner.nextLine();
            if (availabilityInput.equalsIgnoreCase("yes")) {
                super.isAvailable = true;
                validAvailability = true;
            } else if (availabilityInput.equalsIgnoreCase("no")) {
                super.isAvailable = false;
                validAvailability = true;
            } else {
                System.out.println("  ! -- Invalid input. Please enter 'yes' or 'no' for availability -- !\n");
            }
        } while (!validAvailability);



        System.out.print(
            "\n - Car details entered:" + "\n" +
            "\n" + "    - Make: " + super.make  + "\n" +
            "    - Model: " + super.model + "\n" +
            "    - Year: " + super.year + "\n" +
            "    -Color : " + super.color  + "\n" +
            "    - Price: $" + super.price  + "\n" +
            "    - Name: " + super.name  + "\n" +
            "    - Fuel Type: " + super.fuelType  + "\n" +
            "    - Seats: " + super.noSeats  + "\n" +
            "    - Transmission: " + this.transmissionType  + "\n" +
            "    - Insurance: " + (super.hasInsurance ? "Yes" : "No") + "\n" +
            "    - Available: " + (super.hasInsurance ? "Yes" : "No")
        );

    }



}
