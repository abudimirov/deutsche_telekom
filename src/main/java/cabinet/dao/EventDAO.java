package cabinet.dao;

import cabinet.model.Event;
import cabinet.model.Patient;

import java.util.List;

public interface EventDAO {
    List<Event> allEvents();
    List<Event> getEventsByPatient(Patient patient);
    void add(Event event);
    void delete(Event event);
    void edit(Event event);
    Event getById(int id);

}
