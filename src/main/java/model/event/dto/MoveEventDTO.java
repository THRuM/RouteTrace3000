package model.event.dto;

import model.event.Address;
import model.event.Point;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class MoveEventDTO extends EventDTO {
    //Link do Graphoppera wygenerowany przez leaflet routing machine
    @NotNull
    @NotEmpty(message = "{error.noRoute}")
    private String gpLink;

    @NotNull
    @Valid
    private Address endAddress;

    @NotNull
    private Point endPoint;

    public MoveEventDTO(String carId) {
        super(carId);
    }

    public MoveEventDTO() {
    }

    public String getGpLink() {
        return gpLink;
    }

    public void setGpLink(String gpLink) {
        this.gpLink = gpLink;
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
