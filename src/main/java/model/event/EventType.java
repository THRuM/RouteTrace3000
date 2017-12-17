package model.event;

/**
 * Created by user on 27.07.17.
 * Typy zdarzeń - używane w kontrolerze oraz do reprezentacji na stronie
 */
public enum EventType {

    MOVE("Ruch pojazdu"), WORK("Praca bez przemieszczania"), SPECIFIC("Specyficzny");

    String description;

    EventType(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
