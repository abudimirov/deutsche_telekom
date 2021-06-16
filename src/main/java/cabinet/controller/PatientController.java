package cabinet.controller;

import cabinet.model.dto.AlertDTO;
import cabinet.model.dto.EventDTO;
import cabinet.model.dto.PatientDTO;
import cabinet.service.AlertService;
import cabinet.service.EventService;
import cabinet.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;
import java.util.List;

@Controller
public class PatientController {
    private PatientService patientService;
    private EventService eventService;
    private AlertService alertService;

    private static final String HOMEPAGE_REDIRECT = "redirect:/patients/";


    @Autowired
    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setAlertService(AlertService alertService) {this.alertService = alertService; }
    /**
     * Controller for patients list with pagination.
     * @param page - page num
     * @return modelAndView
     */
    @GetMapping(path = "/patients")
    public ModelAndView allPatients(@RequestParam(defaultValue = "1") int page) {
        List<PatientDTO> patients = patientService.allPatients(page);
        List<AlertDTO> alerts = alertService.getAllAlerts();
        int patientsCount = patientService.patientsCount();
        int patientsInTreatmentCount = patientService.patientsInTreatmentCount();
        int pagesCount = (patientsCount + 9)/10;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("patients");
        modelAndView.addObject("page", page);
        modelAndView.addObject("patientsList", patients);
        modelAndView.addObject("patientCount", patientsCount);
        modelAndView.addObject("patientsInTreatmentCount", patientsInTreatmentCount);
        modelAndView.addObject("alerts", alerts);
        modelAndView.addObject("pagesCount", pagesCount);
        return modelAndView;
    }

    /**
     * Controller for sending patient to the service
     * @param patient
     * @return
     */
    @PostMapping(path = "/patients/edit")
    public ModelAndView editPatient(@Valid @ModelAttribute("patient") PatientDTO patient, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPatient");
        if (result.hasErrors()){
            modelAndView.addObject("errors", result);
        } else {
            patientService.edit(patient);
            modelAndView.addObject("message", "patient was edited successfully");
        }

        return modelAndView;
    }

    /**
     * Controller to view particular patient by its ID
     *
     * @param id
     * @return modelAndView
     */
    @GetMapping(path = "/patients/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") int id) {
        PatientDTO patient = patientService.getById(id);
        List<EventDTO> events = eventService.getEventsByPatient(patient);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPatient");
        modelAndView.addObject("patient", patient);
        modelAndView.addObject("eventsList", events);
        return modelAndView;
    }

    /**
     * Controller for getting a view for adding new patient
     *
     * @return modelAndView
     */
    @GetMapping(path = "/patients/add")
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addPatient");
        return modelAndView;
    }

    /**
     * Controller for adding new patient to service
     *
     * @param patient
     * @return
     */
    @PostMapping(path = "/patients/add")
    public ModelAndView addPatient(@Valid @ModelAttribute("patient") PatientDTO patient, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addPatient");
        if (result.hasErrors()){
            modelAndView.addObject("errors", result);
        } else {
            patientService.add(patient);
            modelAndView.addObject("message", "patient was successfully added");
        }

        return modelAndView;
    }

    /**
     * Controller for discharging patient by his ID
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/patients/discharge/{id}")
    public ModelAndView dischargePatient(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        String path = "redirect:/patients/edit/" + id;
        modelAndView.setViewName(path);
        PatientDTO patient = patientService.getById(id);
        patientService.discharge(patient);
        return modelAndView;
    }
}
