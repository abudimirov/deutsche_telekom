package cabinet.controller;

import cabinet.dao.PatientDAO;
import cabinet.dto.PatientDTO;
import cabinet.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Controller
public class PatientController {
    private PatientDAO patientDAO;

    private static final String HOMEPAGE_REDIRECT = "redirect:/";

    @Autowired
    public void setPatientDAO(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @GetMapping(path = "/")
    public ModelAndView allPatients(@RequestParam(defaultValue = "1") int page) {
        List<Patient> patients = patientDAO.allPatients(page);
        int patientsCount = patientDAO.patientsCount();
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
        Patient persistentPatient = new Patient(patient.getId(),
                patient.getName(),
                patient.getSurname(),
                patient.isCured(),
                patient.getDiagnosis(),
                patient.getInsuranceNum(),
                patient.getDoctor()
        );
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(HOMEPAGE_REDIRECT);
        patientDAO.edit(persistentPatient);
        return modelAndView;
    }


    @GetMapping(path = "/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") int id) {
        Patient patient = patientDAO.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("patient", patient);
        return modelAndView;
    }

    @GetMapping(path = "/add")
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

    @PostMapping(path = "/add")
    public ModelAndView addPatient(@ModelAttribute("patient") PatientDTO patient) {
        Patient persistentPatient = new Patient(patient.getId(),
                patient.getName(),
                patient.getSurname(),
                patient.isCured(),
                patient.getDiagnosis(),
                patient.getInsuranceNum(),
                patient.getDoctor()
        );
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(HOMEPAGE_REDIRECT);
        patientDAO.add(persistentPatient);
        return modelAndView;
    }

    @GetMapping(path = "/delete/{id}")
    public ModelAndView deletePatient(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(HOMEPAGE_REDIRECT);
        Patient patient = patientDAO.getById(id);
        patientDAO.delete(patient);
        return modelAndView;
    }

    @GetMapping(path = "/discharge/{id}")
    public ModelAndView dischargePatient(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(HOMEPAGE_REDIRECT);
        Patient patient = patientDAO.getById(id);
        patientDAO.discharge(patient);
        return modelAndView;
    }
}
