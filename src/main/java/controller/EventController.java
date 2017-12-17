package controller;

import model.event.*;
import model.event.dto.EventDTO;
import model.event.dto.MoveEventDTO;
import model.event.dto.SpecificEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.EventService;

import javax.validation.Valid;

/**
 * Created by user on 27.07.17.
 * Kontroler do obsługi funkcjonalości związanych z wydarzeniami
 */

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "allEvents", method = RequestMethod.GET)
    public String allEvents(Model model) {
        model.addAttribute("events", eventService.getAllEvents());

        return "allEvents";
    }

    @RequestMapping(value = "addEvent/{type}/{carId}", method = RequestMethod.GET)
    public String addEvent(Model model, @PathVariable("type") String eventTypeVariable, @PathVariable("carId") String carIdVariable) {
        EventType eventType = EventType.valueOf(eventTypeVariable);

        //Domyślny widokiem jest widok zdarzenia WORK
        String viewName = "addWorkEvent";

        model.addAttribute("eventDescriptions", EventDescription.values());

        switch (eventType) {
            case MOVE:
                model.addAttribute("eventDTO", new MoveEventDTO(carIdVariable));
                viewName = "addMoveEvent";
                break;
            case SPECIFIC:
                model.addAttribute("eventDTO", new SpecificEventDTO(carIdVariable));
                viewName = "addSpecificEvent";
                break;
            case WORK:
                viewName = "addWorkEvent";
                model.addAttribute("eventDTO", new EventDTO(carIdVariable));
        }

        return viewName;
    }

    @RequestMapping(value = "addMoveEvent", method = RequestMethod.POST)
    public String addMoveEvent(@ModelAttribute("eventDTO") @Valid MoveEventDTO eventDTO, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("eventDescriptions", EventDescription.values());
            return "addMoveEvent";
        }

        eventService.addMoveEvent(eventDTO);

        return "redirect:/allEvents";
    }

    @RequestMapping(value = "addWorkEvent", method = RequestMethod.POST)
    public String addWorkEvent(@ModelAttribute("eventDTO") @Valid EventDTO eventDTO, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("eventDescriptions", EventDescription.values());
            return "addWorkEvent";
        }

        eventService.addWorkEvent(eventDTO);

        return "redirect:/allEvents";
    }

    @RequestMapping(value = "addSpecificEvent", method = RequestMethod.POST)
    public String addSpecificEvent(@ModelAttribute("eventDTO") @Valid SpecificEventDTO eventDTO, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("eventDescriptions", EventDescription.values());
            return "addSpecificEvent";
        }

        eventService.addSpecificEvent(eventDTO);

        return "redirect:/allEvents";
    }

    @RequestMapping(value = "showEvent/{eventId}", method = RequestMethod.GET)
    public String showEvent(@PathVariable("eventId") String eventIdVariable, Model model) {

        Event event = eventService.getEventById(Integer.parseInt(eventIdVariable));
        model.addAttribute("event", event);

        //Domyślny widokiem jest widok zdarzenia WORK
        String viewName = "showWorkEvent";

        if (event instanceof MoveEvent) {
            viewName = "showMoveEvent";
        } else if (event instanceof SpecificEvent) {
            viewName = "showSpecificEvent";
        } else {
            viewName = "showWorkEvent";
        }

        return viewName;
    }

    @RequestMapping(value = "deleteEvent/{eventId}", method = RequestMethod.GET)
    public String deleteEvent(@PathVariable("eventId") String eventId) {

        eventService.deleteEventById(Integer.parseInt(eventId));

        return "redirect:/allEvents";
    }
}
