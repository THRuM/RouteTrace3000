package service;

import model.event.Event;
import model.event.MoveEvent;
import model.event.Point;
import model.event.SpecificEvent;
import model.event.dto.EventDTO;
import model.event.dto.MoveEventDTO;
import model.event.dto.SpecificEventDTO;
import model.repository.EventRepository;
import model.vehicles.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.json.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by user on 29.07.17.
 * Serwis obsługujący dodawanie/usuwanie zdarzeń
 */

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CarService carService;

    public Integer addMoveEvent(MoveEventDTO eventDTO) {
        //Pobranie JSONa parsowanie i zapis do bazy danych
        MoveEvent event = new MoveEvent();

        Car car = carService.getCarById(Integer.parseInt(eventDTO.getCarId()));

        event.setCar(car);
        event.setDescription(eventDTO.getDescription());
        event.setStartAddress(eventDTO.getStartAddress());
        event.setEndAddress(eventDTO.getEndAddress());

        event.setStartPointLatitud(eventDTO.getStartPoint().getLatitud());
        event.setStartPointLongitud(eventDTO.getStartPoint().getLongitud());
        event.setEndPointLatitud(eventDTO.getEndPoint().getLatitud());
        event.setEndPointLongitud(eventDTO.getEndPoint().getLongitud());

//        event.setStartPoint(eventDTO.getStartPoint());
//        event.setEndPoint(eventDTO.getEndPoint());
        event.setStartDate(eventDTO.getStartDate());
        event.setEndDate(eventDTO.getEndDate());

        //Konwersja pobranego JSONa na listę punktów do zapisania do bazy
        //Pobieramy bez instrukcji nie-kodowane punkty
        try {
            List<Point> points = decodeGraphhopperLink(eventDTO.getGpLink());
            event.setPath(points);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return eventRepository.save(event).getId();
    }

    public Integer addWorkEvent(EventDTO eventDTO) {
        Event event = new Event();

        Car car = carService.getCarById(Integer.parseInt(eventDTO.getCarId()));

        event.setCar(car);
        event.setDescription(eventDTO.getDescription());
        event.setStartAddress(eventDTO.getStartAddress());

        event.setStartPointLatitud(eventDTO.getStartPoint().getLatitud());
        event.setStartPointLongitud(eventDTO.getStartPoint().getLongitud());

//        event.setStartPoint(eventDTO.getStartPoint());
        event.setStartDate(eventDTO.getStartDate());
        event.setEndDate(eventDTO.getEndDate());

        return eventRepository.save(event).getId();
    }

    public Integer addSpecificEvent(SpecificEventDTO eventDTO) {
        SpecificEvent event = new SpecificEvent();

        Car car = carService.getCarById(Integer.parseInt(eventDTO.getCarId()));

        event.setCar(car);
        event.setDescription(eventDTO.getDescription());
        event.setStartAddress(eventDTO.getStartAddress());
        event.setEndAddress(eventDTO.getEndAddress());

        event.setStartPointLatitud(eventDTO.getStartPoint().getLatitud());
        event.setStartPointLongitud(eventDTO.getStartPoint().getLongitud());
        event.setEndPointLatitud(eventDTO.getEndPoint().getLatitud());
        event.setEndPointLongitud(eventDTO.getEndPoint().getLongitud());

//        event.setStartPoint(eventDTO.getStartPoint());
//        event.setEndPoint(eventDTO.getEndPoint());
        event.setStartDate(eventDTO.getStartDate());
        event.setEndDate(eventDTO.getEndDate());

        HashMap<String, String> spMap = decodeStringToMap(eventDTO.getSpecificValues());

        event.setSpecificValuesMap(spMap);

        return eventRepository.save(event).getId();
    }

    public void deleteEventById(Integer id) {
        eventRepository.delete(getEventById(id));
    }

    public Event getEventById(Integer id) {
        return eventRepository.findOne(id);
    }

    ;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getAllEventsByCarId(Integer carId) {
        return eventRepository.findAllByCarId(carId);
    }

    public void deleteEventsByCarId(Integer carId) {
        eventRepository.removeAllByCarId(carId);
    }

    private List<Point> decodeGraphhopperLink(String graphhopperLink) throws IOException {
        List<Point> points = new ArrayList<Point>();

        //Przygotowanie linku - intrukcję nie będą trzymane w bazie, punkty mają być nie kodowane
        graphhopperLink = graphhopperLink.replace("instructions=true", "instructions=false&points_encoded=false");

        URL url = new URL(graphhopperLink);

        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

        //Użycie JsonObject z pakietu javax.json
        JsonReader jsonReader = Json.createReader(in);

        JsonObject object = jsonReader.readObject();

        jsonReader.close();

        //Pobranie koordynatów z JSONa
        //Paths->points->coordinates
        //Pobranie obiektów ścieżek
        JsonArray paths = object.getJsonArray("paths");
        //Pobranie pierwszej ścieżki
        JsonObject firstPath = paths.getJsonObject(0);
        //Pobranie punktów dla ścieżki
        JsonObject pointsForPath = firstPath.getJsonObject("points");
        //Pobranie coordynatów dla punktów
        JsonArray coordinatesForPath = pointsForPath.getJsonArray("coordinates");

        for (JsonArray singleCoordinate : coordinatesForPath.getValuesAs(JsonArray.class)) {
            JsonNumber latitud = singleCoordinate.getJsonNumber(0);
            JsonNumber longitud = singleCoordinate.getJsonNumber(1);

            Point point = new Point(latitud.doubleValue(), longitud.doubleValue());
            points.add(point);
        }

        return points;
    }

    //Dekoduje String w formacie klucz:wartość;klucz:wartość; na mapę
    private HashMap<String, String> decodeStringToMap(String specificString) {

        HashMap<String, String> specificMap = new HashMap<String, String>();

        StringTokenizer stringTokenizer = new StringTokenizer(specificString, ";");

        String singleElement;
        String keyValuePair[];

        while (stringTokenizer.hasMoreElements()) {
            singleElement = (String) stringTokenizer.nextElement();

            keyValuePair = singleElement.split(":");

            specificMap.put(keyValuePair[0], keyValuePair[1]);
        }

        return specificMap;
    }
}
