package model.event;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;
import java.util.HashMap;

/**
 * Created by user on 27.07.17.
 * Klasa reprezentująca specyficzne zdarzenie posiada mapę z dodatkowymi wartościami
 */
@Entity
@DiscriminatorValue("SPECIFIC")
public class SpecificEvent extends Event {
    @Lob
    @Column(name = "sp_SpecificValuesMap")
    private HashMap<String, String> specificValuesMap;

    public SpecificEvent() {
        setType(EventType.SPECIFIC);
    }

    public HashMap<String, String> getSpecificValuesMap() {
        return specificValuesMap;
    }

    public void setSpecificValuesMap(HashMap<String, String> specificValuesMap) {
        this.specificValuesMap = specificValuesMap;
    }
}
