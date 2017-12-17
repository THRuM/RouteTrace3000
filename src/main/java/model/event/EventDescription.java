package model.event;


//Poszczególne rodzaje zdarzeń
public enum EventDescription {

    DRIVE("Jazda"), TOWNING("Holowanie"), FERRY_TRANSPORT("Transport promem"),
    STOP_ON_ENGINE("Postój na włączonym silniku"), UNLOADING("Rozładunek"), SALTING_WORK("Praca solarki"),
    COOLER_TEMP("Pomiar temperatury w chłodni");

    EventDescription() {
        this.description = "Nie określono";
    }

    EventDescription(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}
