package model.repository;

import model.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 29.07.17.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findAllByCarId(Integer carId);

    void removeAllByCarId(Integer carId);
}
