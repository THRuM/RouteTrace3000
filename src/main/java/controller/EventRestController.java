package controller;

import model.event.Event;
import model.event.dto.EventDTO;
import model.event.dto.MoveEventDTO;
import model.event.dto.SpecificEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.EventService;

/**
 * Kontroler REST
 */
@RestController
@RequestMapping("rest")
public class EventRestController {


    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/event/{eventId}", method = RequestMethod.GET, produces = "application/json")
    public Event getEventAsJSON(@PathVariable("eventId") String eventId) {
        return eventService.getEventById(Integer.parseInt(eventId));

    }

    @RequestMapping(value = "/event/{eventId}", method = RequestMethod.GET, produces = "application/xml")
    public Event getEventAsXml(@PathVariable("eventId") String eventId) {
        return eventService.getEventById(Integer.parseInt(eventId));
    }

    @RequestMapping(value = "/event/addMoveEvent", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Integer addMoveEvent(@RequestBody MoveEventDTO moveEventDTO) {
        return eventService.addMoveEvent(moveEventDTO);
    }

    @RequestMapping(value = "/event/addSpecificEvent", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Integer addSpecificEvent(@RequestBody SpecificEventDTO specificEventDTO) {
        return eventService.addSpecificEvent(specificEventDTO);
    }

    @RequestMapping(value = "/event/addWorkEvent", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Integer addWorkEvent(@RequestBody EventDTO eventDTO) {
        return eventService.addWorkEvent(eventDTO);
    }

    @RequestMapping(value = "/event/delete/{eventId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteEvent(@PathVariable("eventId") Integer eventId) {
        eventService.deleteEventById(eventId);
    }

    @RequestMapping(value = "/event/download/{eventId}", method = RequestMethod.GET, produces = {"application/xml"})
    public ResponseEntity<Event> getEventAsFile(@PathVariable("eventId") String eventId) {
        Event event = eventService.getEventById(Integer.parseInt(eventId));

        HttpHeaders responseHeaders = new HttpHeaders();

        responseHeaders.add("content-disposition", "attachment; filename=Event" + eventId + ".xml");
        responseHeaders.add("Content-Type", "application/xml");

        return new ResponseEntity<Event>(event, responseHeaders, HttpStatus.OK);
    }
}
