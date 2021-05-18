package cabinet.model.dto;

import cabinet.model.Event;
import cabinet.model.Patient;

import java.util.List;
import java.util.Objects;

public class ProcedureDTO implements DTOEntity {
    private int id;
    private int patient_id;
    private Event event;
    private Patient patient;
    private String title;
    private String date;
    private String time;
    private String dates;
    private String dailyPattern;
    private List<String> weeklyPattern;
    private String status;
    private String type;
    private int dose;
    private boolean updated;
    private String comment;


    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }


    public String getDailyPattern() {
        return dailyPattern;
    }

    public void setDailyPattern(String dailyPattern) {
        this.dailyPattern = dailyPattern;
    }

    //TO-DO return list instead of []
    public String[] getWeeklyPattern() {
        String[] weeklyPattern = new String[0];
        return this.weeklyPattern.toArray(weeklyPattern);
    }

    public void setWeeklyPattern(List<String> weeklyPattern) {
        this.weeklyPattern = weeklyPattern;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcedureDTO that = (ProcedureDTO) o;
        return id == that.id &&
                patient_id == that.patient_id &&
                dose == that.dose &&
                updated == that.updated &&
                Objects.equals(event, that.event) &&
                Objects.equals(patient, that.patient) &&
                Objects.equals(title, that.title) &&
                Objects.equals(date, that.date) &&
                Objects.equals(time, that.time) &&
                Objects.equals(dailyPattern, that.dailyPattern) &&
                Objects.equals(weeklyPattern, that.weeklyPattern) &&
                Objects.equals(status, that.status) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient_id, event, patient, title, date, time, dailyPattern, weeklyPattern, status, type, dose, updated);
    }
}
