package cabinet.controller;

import cabinet.dao.PatientDAO;
import cabinet.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Controller
public class PatientController {
    private PatientDAO patientDAO;

    @Autowired
    public void setPatientDAO(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
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

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editPatient(@ModelAttribute("patient") Patient patient) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        patientDAO.edit(patient);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        Patient patient = patientDAO.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("patient", patientDAO.getById(id));
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addPatient(@ModelAttribute("patient") Patient patient) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        patientDAO.add(patient);
        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deletePatient(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        Patient patient = patientDAO.getById(id);
        patientDAO.delete(patient);
        return modelAndView;
    }
}
