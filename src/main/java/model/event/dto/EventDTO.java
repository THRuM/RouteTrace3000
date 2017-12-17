package model.event.dto;

import model.event.Address;
import model.event.EventDescription;
import model.event.Point;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by user on 29.07.17.
 * Data Transfer Object dla wydarzeń wszystkie możliwe dane które mogą zostać przekazane dla wydarzenia
 * przez warstwę widoku
 */

public class EventDTO {

    @NotNull(message = "{error.noCarId}")
    private String carId;

    @DateTimeFormat(pattern = "dd/MM/yy HH:mm")
    @NotNull(message = "{error.startDate}")
    private Date startDate;

    @DateTimeFormat(pattern = "dd/MM/yy HH:mm")
    @NotNull(message = "{error.endDate}")
    private Date endDate;

    @NotNull(message = "{error.startPoint}")
    private Point startPoint;

    @NotNull(message = "{error.description}")
    private EventDescription description;

    @Valid
    @NotNull(message = "{startAddress}")
    private Address startAddress;

    public EventDTO() {
    }

    public EventDTO(String carId) {
        this.carId = carId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public EventDescription getDescription() {
        return description;
    }

    public void setDescription(EventDescription description) {
        this.description = description;
    }

    public Address getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(Address startAddress) {
        this.startAddress = startAddress;
    }

}
