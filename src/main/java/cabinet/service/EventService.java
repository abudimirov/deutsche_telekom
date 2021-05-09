package cabinet.service;

import cabinet.dao.EventDAO;
import cabinet.dao.ProcedureDAO;
import cabinet.model.Event;
import cabinet.model.Patient;
import cabinet.model.Procedure;
import cabinet.model.dto.PatientDTO;
import cabinet.model.dto.ProcedureDTO;
import cabinet.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Event getById(int id) {
        Event event = eventDAO.getById(id);
        return event;
    }

    @Transactional
    public List<Event> getEventsByPatient(PatientDTO patientDTO) {
        Patient patient = (Patient) DtoUtils.convertToEntity(new Patient(), patientDTO);
        return eventDAO.getEventsByPatient(patient);
    }

    @Transactional
    public void cancel(Event event) {
        event.setStatus("cancelled");
        Set<Procedure> procedures = event.getProcedures();
        for (Procedure procedure : procedures) {
            if (procedure.getStatus().equals("scheduled")) {
                procedure.setStatus("cancelled");
                procedureDAO.edit(procedure);
            }
        }
        eventDAO.edit(event);
    }
}
