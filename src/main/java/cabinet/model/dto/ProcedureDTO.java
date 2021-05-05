package cabinet.model.dto;

import cabinet.model.Patient;

public class ProcedureDTO implements DTOEntity {
    private int id;
    private int patient_id;
    private Patient patient;
    private String title;
    private String date;
    private String time;
    private String startDate;
    private String endDate;
    private String dailyPattern;
    private String[] weeklyPattern;
    private String status;
    private boolean updated;


    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDailyPattern() {
        return dailyPattern;
    }

    public void setDailyPattern(String dailyPattern) {
        this.dailyPattern = dailyPattern;
    }

    public String[] getWeeklyPattern() {
        return weeklyPattern;
    }

    public void setWeeklyPattern(String[] weeklyPattern) {
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
}
