package cabinet.service;

import cabinet.dao.EventDAO;
import cabinet.dao.ProcedureDAO;
import cabinet.model.Event;
import cabinet.model.Patient;
import cabinet.model.Procedure;
import cabinet.model.dto.EventDTO;
import cabinet.model.dto.PatientDTO;
import cabinet.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    private EventDAO eventDAO;
    private ProcedureDAO procedureDAO;

    @Autowired
    public void setEventDAO(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Autowired
    public void setProcedureDAO(ProcedureDAO procedureDAO) {
        this.procedureDAO = procedureDAO;
    }

    /**
     * Method to get event from the DB by its ID
     *
     * @param id
     * @return EventDTO
     */
    @Transactional(readOnly = true)
    public EventDTO getById(int id) {
        Event event = eventDAO.getById(id);
        return DtoUtils.convertToDto(event, EventDTO.class);
    }

    /**
     * Method to get all events from the DB by patient
     *
     * @param patientDTO
     * @return List<EventDTO>
     */
    @Transactional(readOnly = true)
    public List<EventDTO> getEventsByPatient(PatientDTO patientDTO) {
        Patient patient = DtoUtils.convertToEntity(Patient.class, patientDTO);
        List<EventDTO> events = new ArrayList<>();
        for (Event event : eventDAO.getEventsByPatient(patient)) {
            events.add(DtoUtils.convertToDto(event, EventDTO.class));
        }
        return events;
    }

    /**
     * Method to edit an existing event in the DB
     *
     * @param eventDTO
     */
    @Transactional
    public void edit(EventDTO eventDTO) {
        Event event = DtoUtils.convertToEntity(Event.class, eventDTO);
        eventDAO.edit(event);
    }

    /**
     * Method to cancel an existing event in the DB and set status to 'cancelled'
     * for all procedures that are schedueld within this event
     *
     * @param eventDTO
     */
    @Transactional
    public void cancel(EventDTO eventDTO) {
        eventDTO.setStatus("cancelled");
        List<Procedure> procedures = eventDTO.getProcedures();
        for (Procedure procedure : procedures) {
            if (procedure.getStatus().equals("scheduled")) {
                procedure.setStatus("cancelled");
                procedureDAO.edit(procedure);
            }
        }
        eventDAO.edit(DtoUtils.convertToEntity(Event.class, eventDTO));
    }
}
