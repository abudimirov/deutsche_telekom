package cabinet.model.dto;

import cabinet.model.Patient;
import cabinet.model.Procedure;
import cabinet.utils.ProcedureSorter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class EventDTO implements DTOEntity {
    private int id;
    private String title;
    private String start_date;
    private String end_date;
    private String status;
    private Set<Procedure> procedures;
    private Patient patient;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Procedure> getProcedures() {
        ArrayList<Procedure> proceduresList = new ArrayList<>(procedures);
        Collections.sort(proceduresList, new ProcedureSorter());
        return proceduresList;
    }

    public void setProcedures(Set<Procedure> procedures) {
        this.procedures = procedures;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
