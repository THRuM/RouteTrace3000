package model.event;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 27.07.17.
 * Klasa zdarzenia np Jazdy, posiada listę punktów jazdy
 */

@Entity
@DiscriminatorValue("MOVE")
public class MoveEvent extends Event {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "eventId", foreignKey = @ForeignKey(name = "event_point_FK"))
    private List<Point> path = new ArrayList<Point>();

    public MoveEvent() {
        setType(EventType.MOVE);
    }

    public List<Point> getPath() {
        return path;
    }

    public void setPath(List<Point> path) {
        this.path = path;
    }

    //Zwraca tablicę tablic z wartościami lat,lng używanych jako waypoins do wyświetlania trasy przez leaflet
    @JsonIgnore
    public JsonArray getPathAsJsonArray() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder singleRowBuilder = Json.createArrayBuilder();

        for (Point p : path) {
            singleRowBuilder.add(p.getLongitud());
            singleRowBuilder.add(p.getLatitud());

            arrayBuilder.add(singleRowBuilder.build());
            singleRowBuilder = Json.createArrayBuilder();
        }

        return arrayBuilder.build();
    }
}
