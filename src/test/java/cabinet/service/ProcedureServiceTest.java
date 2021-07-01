package cabinet.service;

import cabinet.dao.AlertDAO;
import cabinet.dao.EventDAO;
import cabinet.dao.ProcedureDAOImpl;
import cabinet.model.Event;
import cabinet.model.Patient;
import cabinet.model.Procedure;
import cabinet.model.dto.ProcedureDTO;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jms.core.JmsTemplate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ProcedureServiceTest {

  // Mock procedureService, other services will injected in it
  // because we use @RunWith(MockitoJUnitRunner.class), it will inject everything in procedureService

  @InjectMocks
  private ProcedureService procedureService;

  // Make mock of all services to not get an NPE just in case
  @Mock
  private ProcedureDAOImpl procedureDAO;
  @Mock
  private JmsTemplate jmsTemplate;
  @Mock
  private EventDAO eventDAO;
  @Mock
  private AlertDAO alertDAO;

  @Test
  public void testProceduresByPatient() {
    final ArrayList<Procedure> procedures = new ArrayList<>();
    Patient patient = new Patient();
    Event event = new Event();
    procedures.add(new Procedure(444, patient, "title", "2021-05-19","10:00:00", "status", "procedure", 0, event, "comment"));
    when(procedureDAO.proceduresByPatient(anyInt())).thenReturn(procedures); // it's an emulation of DB response. Method with any id will return list "procedures"
    final List<ProcedureDTO> proceduresDtos = procedureService.proceduresByPatient(666); // execute method for not existing patient
    verify(procedureDAO).proceduresByPatient(anyInt()); // Check if procedureDAO.proceduresByPatient was called inside method
    assertEquals(procedures.size(), proceduresDtos.size()); // Check count of object that were converted
  }

  // Check if method returns the correct count of days between two dates
  @Test
  public void testGetDatesBetween() {
    LocalDate startDate = LocalDate.of(1914, 7, 28);
    LocalDate endDate = LocalDate.of(1914, 8, 13);
    int expectedDates = 17;
    final List<LocalDate> datesBetween = procedureService.getDatesBetween(startDate, endDate);
    assertEquals(expectedDates, datesBetween.size());
  }

  @Test
  public void testAdd() {
    final ProcedureDTO procedureDTO = new ProcedureDTO(); //Make procedure object and fill it
    procedureDTO.setId(10);
    procedureDTO.setDates("10/10/2020-10/18/2020");
    procedureDTO.setTitle("some title");
    procedureDTO.setStatus("some status");
    procedureDTO.setPatient(mock(Patient.class));
    final ArrayList<String> strings = new ArrayList<>();
    strings.add("7");
    procedureDTO.setWeeklyPattern(strings);
    procedureDTO.setDailyPattern("3");
    procedureService.add(procedureDTO); // call add method
    verify(jmsTemplate).convertAndSend(anyString()); // check if message is sent to queue
    verify(procedureDAO, times(6)).add(any(Procedure.class)); // check that saving process of a procedure is called N times
  }
}