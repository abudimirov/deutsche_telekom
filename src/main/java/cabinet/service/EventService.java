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
import java.util.Set;

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

    @Transactional(readOnly = true)
    public EventDTO getById(int id) {
        Event event = eventDAO.getById(id);
        return (EventDTO) DtoUtils.convertToDto(event, new EventDTO());
    }

    @Transactional(readOnly = true)
    public List<EventDTO> getEventsByPatient(PatientDTO patientDTO) {
        Patient patient = (Patient) DtoUtils.convertToEntity(new Patient(), patientDTO);
        List<EventDTO> events = new ArrayList<>();
        for (Event event : eventDAO.getEventsByPatient(patient)) {
            events.add((EventDTO) DtoUtils.convertToDto(event, new EventDTO()));
        }
        return events;
    }

    @Transactional
    public void cancel(EventDTO eventDTO) {
        eventDTO.setStatus("cancelled");
        Set<Procedure> procedures = eventDTO.getProcedures();
        for (Procedure procedure : procedures) {
            if (procedure.getStatus().equals("scheduled")) {
                procedure.setStatus("cancelled");
                procedureDAO.edit(procedure);
            }
        }
        eventDAO.edit((Event) DtoUtils.convertToEntity(new Event(), eventDTO));
    }
}
