package Vehicle_Rental_System;

import java.time.LocalDate;

public class Booking {
    // Attributes
    public Vehicle vehicle;
    private LocalDate bookingDate;
    private LocalDate fromDate;
    private LocalDate toDate;
    private int rentPeriodDays; 
    public boolean isBookingApproved;
    public int noBookings;
    private double totalAmount;
    private String currency;

    // Constructor
    public Booking(Vehicle vehicle, LocalDate bookingDate, LocalDate fromDate, LocalDate toDate, double totalAmount, String currency, LocalDate paymentDate) {
        this.totalAmount = totalAmount;
        this.currency = currency;
        this.vehicle = vehicle;
        this.bookingDate = bookingDate;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.rentPeriodDays = 0; 
        this.isBookingApproved = false;
    }

    // Setters and Getters
    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setFromToDate(LocalDate fromDate, LocalDate toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setRentPeriod(int days) {
        this.rentPeriodDays = days;
    }

    public int getRentPeriod() {
        return rentPeriodDays;
    }

    public void setTotalAmount(double amount) {
        this.totalAmount = amount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }
    // Business Logic
    public void createBooking(Vehicle vehicle, LocalDate bookingDate, LocalDate fromDate, LocalDate toDate) {
        this.vehicle = vehicle;
        this.bookingDate = bookingDate;
        this.fromDate = fromDate;
        this.toDate = toDate;
        calculateRentPeriod();  // Calculate the rent period manually
    }

    public void cancelBooking(Vehicle vehicle) {
        if (this.vehicle == vehicle) {
            this.vehicle = null;
            this.bookingDate = null;
            this.fromDate = null;
            this.toDate = null;
        }
    }

    public void confirmBooking() {
        System.out.println("Booking confirmed for vehicle: " + vehicle);
    }

    // Manual calculation of the rent period in days
    private void calculateRentPeriod() {
        if (fromDate != null && toDate != null) {
            
            // Manually calculate the rent period in days by subtracting dates
            rentPeriodDays = (int) (toDate.toEpochDay() - fromDate.toEpochDay());
        }
    }

    public void displayBookingDetails() {
        System.out.print("\n\nVehicle ID: " + vehicle.getVehicleID());
        System.out.println("Vehicle: " + vehicle.name);
        System.out.println("Booking Date: " + bookingDate);
        System.out.println("From: " + fromDate);
        System.out.println(" To: " + toDate);
        System.out.println("Total Amount: " + getTotalAmount() + " " + getCurrency());
        System.out.println("\n"); 
    }
}
