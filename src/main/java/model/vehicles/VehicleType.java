package model.vehicles;

/**
 * Możliwe rodzaje samochodów
 */
public enum VehicleType {

    CAR("Osobowy"), TRUCK("Ciężarowy");

    private String description;

    VehicleType(String description) {
        this.description = description;
        ;
    }

    public String getDescription() {
        return description;
    }
}
