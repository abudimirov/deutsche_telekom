package cabinet.service;

import cabinet.dao.PatientDAO;
import cabinet.dao.ProcedureDAO;
import cabinet.model.Patient;
import cabinet.model.Procedure;
import cabinet.model.dto.ProcedureDTO;
import cabinet.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProcedureService {
    private ProcedureDAO procedureDAO;
    private PatientDAO patientDAO;

    @Autowired
    public void setProcedureDAO(ProcedureDAO procedureDAO) {
        this.procedureDAO = procedureDAO;
    }

    @Autowired
    public void setPatientDAO(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    /**
     * Service for getting all procedures. Gets a list of procedures entities from DAO and converts to list of DTOs
     *
     * @return list of DTO procedures
     */
    @Transactional(readOnly = true)
    public List<ProcedureDTO> allProcedures() {
        List<ProcedureDTO> procedures = new ArrayList<>();
        for (Procedure procedure : procedureDAO.allProcedures()) {
            procedures.add((ProcedureDTO) DtoUtils.convertToDto(procedure, new ProcedureDTO()));
        }
        return procedures;
    }
    /**
     * Service for getting all procedures with pagination.
     * Gets a list of procedures entities from DAO and converts to list of DTOs
     *
     * @param page - num of page
     * @return list of DTO procedures
     */
    @Transactional(readOnly = true)
    public List<ProcedureDTO> allProcedures(int page) {
        List<ProcedureDTO> procedures = new ArrayList<>();
        for (Procedure procedure : procedureDAO.allProcedures(page)) {
            procedures.add((ProcedureDTO) DtoUtils.convertToDto(procedure, new ProcedureDTO()));
        }
        return procedures;
    }

    /**
     * Service gets a procedure DTO which can have start date of prescripted procedures and
     * end date, weekly pattern and daily pattern. It has to create every procedure one by one by its
     * pattern and add to the DB.
     *
     * @param procedureDTO
     */
    @Transactional
    public void add(ProcedureDTO procedureDTO) {
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(procedureDTO.getStartDate(), dtf);
        LocalDate endDate = LocalDate.parse(procedureDTO.getEndDate(), dtf);
        List<LocalDate> dateRange = getDatesBetween(startDate, endDate);

        /**
         * If "Replace" is checked in View (isUpdated = true) we set all procedures for
         * patient to cancelled and add new ones.
         * If "Replace" is false we simply add new procedures.
         * */

        if (procedureDTO.isUpdated()) {
            Patient patient = patientDAO.getById(procedureDTO.getPatient().getId());
            patientDAO.cancelProcedures(patient);

        }
        for (LocalDate date : dateRange) {
            Calendar c = Calendar.getInstance();
            c.setTime(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            String dayOfWeek = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
            List<String> weeklyPattern = Arrays.asList(procedureDTO.getWeeklyPattern());

            if (weeklyPattern.contains(dayOfWeek)) {

                Procedure morningProcedure = (Procedure) DtoUtils.convertToEntity(new Procedure(), procedureDTO);
                morningProcedure.setDate(date.toString());
                morningProcedure.setTime("10:00");

                Procedure middayProcedure = (Procedure) DtoUtils.convertToEntity(new Procedure(), procedureDTO);
                middayProcedure.setDate(date.toString());
                middayProcedure.setTime("15:00");

                Procedure eveningProcedure = (Procedure) DtoUtils.convertToEntity(new Procedure(), procedureDTO);
                eveningProcedure.setDate(date.toString());
                eveningProcedure.setTime("22:00");

                switch (procedureDTO.getDailyPattern()) {
                    case "1":
                        procedureDAO.add(morningProcedure);
                        break;
                    case "2":
                        procedureDAO.add(morningProcedure);
                        procedureDAO.add(eveningProcedure);
                        break;
                    case "3":
                        procedureDAO.add(morningProcedure);
                        procedureDAO.add(middayProcedure);
                        procedureDAO.add(eveningProcedure);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * Method for delete of the specified procedure.
     * @param procedureDTO
     */
    @Transactional
    public void delete(ProcedureDTO procedureDTO) {
        Procedure procedure = (Procedure) DtoUtils.convertToEntity(new Procedure(), procedureDTO);
        procedureDAO.delete(procedure);
    }

    /**
     * Method converts DTO to an entity and updates it in the DB
     * @param procedureDTO
     */
    @Transactional
    public void edit(ProcedureDTO procedureDTO) {
        Procedure procedure = (Procedure) DtoUtils.convertToEntity(new Procedure(), procedureDTO);
        procedureDAO.edit(procedure);
    }

    /**
     * Method gets entity of procedure by its ID in the DB, converts to DTO and return it
     * @param id
     * @return procedureDTO
     */
    @Transactional
    public ProcedureDTO getById(int id) {
        Procedure procedure = procedureDAO.getById(id);
        return (ProcedureDTO) DtoUtils.convertToDto(procedure, new ProcedureDTO());
    }

    /**
     * Method gets all procedure entities by patient ID, converts to list of DTO's and returns it
     * @param id - patient ID
     * @return list of procedureDTO
     */
    @Transactional(readOnly = true)
    public List<ProcedureDTO> proceduresByPatient(int id) {
        List<ProcedureDTO> procedures = new ArrayList<>();
        for (Procedure procedure : procedureDAO.proceduresByPatient(id)) {
            procedures.add((ProcedureDTO) DtoUtils.convertToDto(procedure, new ProcedureDTO()));
        }
        return procedures;
    }

    /**
     * Method returns list of proceduresDTO by specified date
     *
     * @param date
     * @return list of procedureDTO
     */
    @Transactional(readOnly = true)
    public List<ProcedureDTO> proceduresByDate(String date) {
        List<ProcedureDTO> procedures = new ArrayList<>();
        for (Procedure procedure : procedureDAO.proceduresByDate(date)) {
            procedures.add((ProcedureDTO) DtoUtils.convertToDto(procedure, new ProcedureDTO()));
        }
        return procedures;
    }

    /**
     * Method returns a list of procedures DTO for the next hour
     * @return
     */
    @Transactional(readOnly = true)
    public List<ProcedureDTO> proceduresForNextHour() {
        List<ProcedureDTO> procedures = new ArrayList<>();
        for (Procedure procedure : procedureDAO.proceduresForNextHour()) {
            procedures.add((ProcedureDTO) DtoUtils.convertToDto(procedure, new ProcedureDTO()));
        }
        return procedures;
    }

    @Transactional(readOnly = true)
    public int proceduresCount() {
        return procedureDAO.proceduresCount();
    }

    /**
     * Additional method for getting a range of dates between specified dates
     *
     * @param startDate
     * @param endDate
     * @return list of dates
     */
    public List<LocalDate> getDatesBetween(
            LocalDate startDate, LocalDate endDate) {

        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> startDate.plusDays(i))
                .collect(Collectors.toList());
    }
}
