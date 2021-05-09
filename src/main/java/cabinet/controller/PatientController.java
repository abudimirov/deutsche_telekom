package cabinet.controller;

import cabinet.model.dto.PatientDTO;
import cabinet.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Controller
public class PatientController {
    private PatientService patientService;

    private static final String HOMEPAGE_REDIRECT = "redirect:/patients/";


    @Autowired
    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Controller for patients list with pagination.
     * @param page - page num
     * @return modelAndView
     */
    @GetMapping(path = "/patients")
    public ModelAndView allPatients(@RequestParam(defaultValue = "1") int page) {
        List<PatientDTO> patients = patientService.allPatients(page);
        int patientsCount = patientService.patientsCount();
        int pagesCount = (patientsCount + 9)/10;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("patients");
        modelAndView.addObject("page", page);
        modelAndView.addObject("patientsList", patients);
        modelAndView.addObject("patientCount", patientsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        return modelAndView;
    }

    /**
     * Controller for sending patient to the service
     * @param patient
     * @return
     */
    @PostMapping(path = "/patients/edit")
    public ModelAndView editPatient(@ModelAttribute("patient") PatientDTO patient) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(HOMEPAGE_REDIRECT);
        patientService.edit(patient);
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
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPatient");
        modelAndView.addObject("patient", patient);
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
        modelAndView.setViewName("editPatient");
        return modelAndView;
    }

    /**
     * Controller for adding new patient to service
     *
     * @param patient
     * @return
     */
    @PostMapping(path = "/patients/add")
    public ModelAndView addPatient(@ModelAttribute("patient") PatientDTO patient) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(HOMEPAGE_REDIRECT);
        patientService.add(patient);
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
        modelAndView.setViewName(HOMEPAGE_REDIRECT);
        PatientDTO patient = patientService.getById(id);
        patientService.discharge(patient);
        return modelAndView;
    }
}
