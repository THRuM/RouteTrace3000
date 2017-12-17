package model.event;

import model.vehicles.Car;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by user on 27.07.17.
 * Podstawowa klasa zdarzenia, posiada jedno miejsce/adres (występuje w jednym miejscu)
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Event", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("EVENT")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ev_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "car_id", foreignKey = @ForeignKey(name = "car_id_FK"))
    private Car car;

    @Column(name = "ev_startDate")
    private Date startDate;

    @Column(name = "ev_endDate")
    private Date endDate;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "ev_startPoint")
//    private Point startPoint;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "ev_endPoint")
//    private Point endPoint;

    @Column(name = "startPointLatitud")
    private Double startPointLatitud;

    @Column(name = "startPointLongitud")
    private Double startPointLongitud;

    @Column(name = "endPointLatitud")
    private Double endPointLatitud;

    @Column(name = "endPointLongitud")
    private Double endPointLongitud;

    @Column(name = "ev_type")
    @Enumerated(EnumType.STRING)
    private EventType type = EventType.WORK;

    @Column(name = "ev_description")
    @Enumerated(EnumType.STRING)
    private EventDescription description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "startAddress_id", foreignKey = @ForeignKey(name = "startAddress_id_FK"))
    private Address startAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endAddress_id", foreignKey = @ForeignKey(name = "endAddress_id_FK"))
    private Address endAddress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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

//    public Point getStartPoint() {
//        return startPoint;
//    }
//
//    public void setStartPoint(Point startPoint) {
//        this.startPoint = startPoint;
//    }
//
//    public Point getEndPoint() {
//        return endPoint;
//    }
//
//    public void setEndPoint(Point endPoint) {
//        this.endPoint = endPoint;
//    }


    public Double getStartPointLatitud() {
        return startPointLatitud;
    }

    public void setStartPointLatitud(Double startPointLatitud) {
        this.startPointLatitud = startPointLatitud;
    }

    public Double getStartPointLongitud() {
        return startPointLongitud;
    }

    public void setStartPointLongitud(Double startPointLongitud) {
        this.startPointLongitud = startPointLongitud;
    }

    public Double getEndPointLatitud() {
        return endPointLatitud;
    }

    public void setEndPointLatitud(Double endPointLatitud) {
        this.endPointLatitud = endPointLatitud;
    }

    public Double getEndPointLongitud() {
        return endPointLongitud;
    }

    public void setEndPointLongitud(Double endPointLongitud) {
        this.endPointLongitud = endPointLongitud;
    }

    public EventType getType() {
        return type;
    }

    protected void setType(EventType type) {
        this.type = type;
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

    public Address getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(Address endAddress) {
        this.endAddress = endAddress;
    }
}
