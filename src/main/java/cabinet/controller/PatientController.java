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

    private static final String HOMEPAGE_REDIRECT = "redirect:/";


    @Autowired
    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(path = "/")
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

    @PostMapping(path = "/edit")
    public ModelAndView editPatient(@ModelAttribute("patient") PatientDTO patient) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(HOMEPAGE_REDIRECT);
        patientService.edit(patient);
        return modelAndView;
    }


    @GetMapping(path = "/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") int id) {
        PatientDTO patient = patientService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPatient");
        modelAndView.addObject("patient", patient);
        return modelAndView;
    }

    @GetMapping(path = "/add")
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPatient");
        return modelAndView;
    }

    @PostMapping(path = "/add")
    public ModelAndView addPatient(@ModelAttribute("patient") PatientDTO patient) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(HOMEPAGE_REDIRECT);
        patientService.add(patient);
        return modelAndView;
    }

    @GetMapping(path = "/discharge/{id}")
    public ModelAndView dischargePatient(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(HOMEPAGE_REDIRECT);
        PatientDTO patient = patientService.getById(id);
        patientService.discharge(patient);
        return modelAndView;
    }
}
