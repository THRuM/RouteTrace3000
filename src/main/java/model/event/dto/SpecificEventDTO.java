package model.event.dto;

import model.event.Address;
import model.event.Point;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class SpecificEventDTO extends EventDTO {

    @NotNull
    private String specificValues;

    @NotNull
    @Valid
    private Address endAddress;

    @NotNull
    private Point endPoint;

    public SpecificEventDTO(String carId) {
        super(carId);
    }

    public SpecificEventDTO() {
    }

    public String getSpecificValues() {
        return specificValues;
    }

    public void setSpecificValues(String specificValues) {
        this.specificValues = specificValues;
    }

    public Address getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(Address endAddress) {
        this.endAddress = endAddress;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }
}
