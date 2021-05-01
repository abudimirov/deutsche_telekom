package cabinet.service;

import cabinet.dao.ProcedureDAO;
import cabinet.model.Procedure;
import cabinet.model.dto.ProcedureDTO;
import cabinet.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Autowired
    public void setProcedureDAO(ProcedureDAO procedureDAO) {
        this.procedureDAO = procedureDAO;
    }

    @Transactional
    public List<ProcedureDTO> allProcedures(int page) {
        List<ProcedureDTO> procedures = new ArrayList<>();
        for (Procedure procedure : procedureDAO.allProcedures(page)) {
            procedures.add((ProcedureDTO) new DtoUtils().convertToDto(procedure, new ProcedureDTO()));
        }
        return procedures;
    }

    @Transactional
    public void add(ProcedureDTO procedureDTO) {
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(procedureDTO.getStartDate(), dtf);
        LocalDate endDate = LocalDate.parse(procedureDTO.getEndDate(), dtf);
        List<LocalDate> dateRange = getDatesBetween(startDate, endDate);

        for(LocalDate date : dateRange) {
            Calendar c = Calendar.getInstance();
            c.setTime(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            String dayOfWeek = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
            List<String> weeklyPattern = Arrays.asList(procedureDTO.getWeeklyPattern());

            if(weeklyPattern.contains(dayOfWeek)) {
                Procedure procedure = (Procedure) new DtoUtils().convertToEntity(new Procedure(), procedureDTO);
                procedure.setDate(date.toString());

                switch (procedureDTO.getDailyPattern()) {
                    case "1":
                        procedure.setTime("10:00");
                        procedureDAO.add(procedure);
                        break;
                    case "2":
                        procedure.setTime("10:00");
                        procedureDAO.add(procedure);
                        procedure.setTime("22:00");
                        procedureDAO.add(procedure);
                        break;
                    case "3":
                        procedure.setTime("10:00");
                        procedureDAO.add(procedure);
                        procedure.setTime("15:00");
                        procedureDAO.add(procedure);
                        procedure.setTime("22:00");
                        procedureDAO.add(procedure);
                        break;
                    default:
                        System.err.println("no case for that time pattern");
                        break;
                }
            }
        }
    }

    // НЕ УДАЛЯТЬ
    @Transactional
    public void delete(ProcedureDTO procedureDTO) {
        Procedure procedure = (Procedure) new DtoUtils().convertToEntity(new Procedure(), procedureDTO);
        procedureDAO.delete(procedure);
    }

    @Transactional
    public void edit(ProcedureDTO procedureDTO) {
        Procedure procedure = (Procedure) new DtoUtils().convertToEntity(new Procedure(), procedureDTO);
        procedureDAO.edit(procedure);
    }

    @Transactional
    public ProcedureDTO getById(int id) {
        Procedure procedure = procedureDAO.getById(id);
        return (ProcedureDTO) new DtoUtils().convertToDto(procedure, new ProcedureDTO());
    }

    @Transactional
    public List<ProcedureDTO> proceduresByPatient(int id) {
        List<ProcedureDTO> procedures = new ArrayList<>();
        for (Procedure procedure : procedureDAO.proceduresByPatient(id)) {
            procedures.add((ProcedureDTO) new DtoUtils().convertToDto(procedure, new ProcedureDTO()));
        }
        return procedures;
    }

    @Transactional
    public List<ProcedureDTO> proceduresByDate(String date) {
        List<ProcedureDTO> procedures = new ArrayList<>();
        for (Procedure procedure : procedureDAO.proceduresByDate(date)) {
            procedures.add((ProcedureDTO) new DtoUtils().convertToDto(procedure, new ProcedureDTO()));
        }
        return procedures;
    }

    @Transactional
    public List<ProcedureDTO> proceduresForNextHour() {
        List<ProcedureDTO> procedures = new ArrayList<>();
        for (Procedure procedure : procedureDAO.proceduresForNextHour()) {
            procedures.add((ProcedureDTO) new DtoUtils().convertToDto(procedure, new ProcedureDTO()));
        }
        return procedures;
    }

    @Transactional
    public int proceduresCount() {
        return procedureDAO.proceduresCount();
    }

    public List<LocalDate> getDatesBetween(
            LocalDate startDate, LocalDate endDate) {

        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> startDate.plusDays(i))
                .collect(Collectors.toList());
    }
}
