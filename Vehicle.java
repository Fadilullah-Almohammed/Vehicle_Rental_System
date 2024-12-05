package Vehicle_Rental_System;

abstract public class Vehicle {
    // Public attributes
    public String name;
    public String model;
    public String fuelType;
    public boolean hasInsurance;
    public boolean isAvailable;

    // Protected attributes
    protected String vehicleID;
    protected String make;
    protected int year;
    protected double price;
    protected int noSeats;
    protected String color;

    // Constructor
    Vehicle(String vehicleID, String name, String model, String fuelType, 
                   int year, double price, int noSeats, String make, 
                   String color, boolean hasInsurance, boolean isAvailable) {
        this.vehicleID = vehicleID;
        this.name = name;
        this.model = model;
        this.fuelType = fuelType;
        this.year = year;
        this.price = price;
        this.noSeats = noSeats;
        this.make = make;
        this.color = color;
        this.hasInsurance = hasInsurance;
        this.isAvailable = isAvailable;
    }

    // Getters and Setters
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    public int getNoSeats() {
        return noSeats;
    }

    public void setNoSeats(int noSeats) {
        this.noSeats = noSeats;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }
    
    public String getVehicleID() {
        return vehicleID;
    }

    // Method to get all vehicle information
    public void getVehicleInfo() {
        System.out.println("Vehicle ID: " + vehicleID);
        System.out.println("Name: " + name);
        System.out.println("Model: " + model);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Year: " + year);
        System.out.println("Price: " + price);
        System.out.println("Number of Seats: " + noSeats);
        System.out.println("Make: " + make);
        System.out.println("Color: " + color);
        System.out.println("Has Insurance: " + hasInsurance);
        System.out.println("Is Available: " + isAvailable);
    }
}