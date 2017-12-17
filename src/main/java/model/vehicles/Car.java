package model.vehicles;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Klasa reprezentująca samochód
 */
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "car_id")
    private Integer id;

    @NotEmpty(message = "{error.car.make}")
    @Column(name = "car_make")
    private String make;

    @NotEmpty(message = "{error.car.model}")
    @Column(name = "car_model")
    private String model;

    @NotEmpty(message = "{error.car.reg}")
    @Column(name = "car_registration", unique = true, length = 120)
    private String registrationNumber;

    @NotNull
    @Column(name = "car_type")
    @Enumerated(EnumType.STRING)
    private VehicleType type;

    public Car(String make, String model, String registrationNumber, VehicleType type) {
        this.make = make;
        this.model = model;
        this.type = type;
        this.registrationNumber = registrationNumber;
    }

    public Car() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}
