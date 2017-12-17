package model.event;

import javax.persistence.*;

/**
 * Created by user on 27.07.17.
 * Klasa reprezentująca punkt na mapie
 */
@Entity
//Nie waliduję punktów - są pobierane na podstawie adresu
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id")
    private Integer id;

    @Column(name = "po_latitud")
    private double latitud;

    @Column(name = "po_longitud")
    private double longitud;

    @Column(name = "eventId")
    private Integer eventId;

    public Point() {
    }

    public Point(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        return latitud + "," + longitud;
    }
}
