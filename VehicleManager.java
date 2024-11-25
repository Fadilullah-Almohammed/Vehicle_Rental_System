package Vehicle_Rental_System;





public class VehicleManager {


    private static final String AUTHORIZE_CODE = "@3#dg$%32FR5#Dhj524^#$%l;jdftgq";
    private static final int MAX_VEHICLES = 100;
    
    private Vehicle[] vehicles;
    private int noVehicles;
    private int vehicleReserved;
    private int check;


    VehicleManager() {
        this.vehicles = new Vehicle[VehicleManager.MAX_VEHICLES];
    }

    public static void main(String[] args) {
        
        
    }

    public void addVehicle(Vehicle newVehicle) {
        
        this.vehicles[this.noVehicles] = newVehicle; 
    }

    public void updateVehicle(Vehicle oldVehicle, Vehicle newVehicle) {

        for (int i = 0; i < this.noVehicles; i++) {
            
            if (vehicles[i] == oldVehicle) {
                vehicles[i] = newVehicle;

                System.out.println("  -- Updated Successfuly --");
            }

            System.out.println("Couldn't found a vehicle with name: " + oldVehicle.name);
        }
    }

    public void deleteVehicle(Vehicle vehicleToRemove) {

        int indexToRemove = this.searchVehicleIndex(vehicleToRemove);

        if (indexToRemove < 0) {
            System.out.println("  -- There is no vehicle with the name: " + vehicleToRemove.name);
        }
        else {
            for (int i = indexToRemove; i < (this.noVehicles - 1); i++) {
                this.vehicles[i] = this.vehicles[i + 1];
            }
        }
    }

    public boolean isAvailable(Vehicle vehicle) {

        int vehicleIndex = searchVehicleIndex(vehicle);

        if (vehicleIndex < 0) {
            return false;
        }
        else {
            if (this.vehicles[vehicleIndex].isAvailable) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    


    // if not found: returns -1
    public int searchVehicleIndex(Vehicle vehicle) {

        for (int i = 0; i < this.noVehicles; i++) {
            
            if (this.vehicles[i] == vehicle) {
                return i;
            }
        }

        return -1;
    }



    public boolean isAuthorized(boolean isLoggedIn, String authorizeCode) {

        return true;
    }

}