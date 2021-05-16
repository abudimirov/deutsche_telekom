package cabinet.controller;

import cabinet.model.Procedure;
import cabinet.model.dto.PatientDTO;
import cabinet.model.dto.ProcedureDTO;
import cabinet.service.PatientService;
import cabinet.service.ProcedureService;
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
import java.util.Set;

@Controller
public class ProcedureController {
    private ProcedureService procedureService;
    private PatientService patientService;

    private static final String PROCEDURES_REDIRECT = "redirect:/procedures";
    private static final String PATIENTS_REDIRECT = "redirect:/patients";
    private static final String PROCEDURES_LIST = "proceduresList";
    private static final String PROCEDURES_FILTERED = "filteredProcedures";

    @Autowired
    public void setProcedureService(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @Autowired
    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(path = "/procedures")
    public ModelAndView allProcedures(@RequestParam(defaultValue = "1") int page) {
        List<ProcedureDTO> procedures = procedureService.allProcedures(page);
        int proceduresCount = procedureService.proceduresCount();
        int pagesCount = (proceduresCount + 9)/10;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("procedures");
        modelAndView.addObject("page", page);
        modelAndView.addObject(PROCEDURES_LIST, procedures);
        modelAndView.addObject("proceduresCount", proceduresCount);
        modelAndView.addObject("pagesCount", pagesCount);
        return modelAndView;
    }

    @PostMapping(path = "/procedures/edit")
    public ModelAndView editProcedure(@ModelAttribute("procedure") ProcedureDTO procedure) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editProcedure");
        procedureService.edit(procedure);
        return modelAndView;
    }

    @GetMapping(path = "/procedures/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") int id) {
        ProcedureDTO procedure = procedureService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editProcedure");
        modelAndView.addObject("procedure", procedure);
        return modelAndView;
    }

    @GetMapping(path = "/procedures/add")
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<PatientDTO> patients = patientService.allPatients();
        modelAndView.setViewName("addProcedure");
        modelAndView.addObject("patients", patients);
        return modelAndView;
    }

    @PostMapping(path = "/procedures/add")
    public ModelAndView addProcedure(@Valid @ModelAttribute("procedure") ProcedureDTO procedure, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()){
            modelAndView.setViewName("addProcedure");
            modelAndView.addObject("errors", result);
        } else {
            modelAndView.setViewName(PATIENTS_REDIRECT);
            procedureService.add(procedure);
        }

        return modelAndView;
    }

    @GetMapping(path = "/procedures/delete/{id}")
    public ModelAndView deleteProcedure(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PROCEDURES_REDIRECT);
        ProcedureDTO procedure = procedureService.getById(id);
        procedureService.delete(procedure);
        return modelAndView;
    }


    @GetMapping(path = "/procedures/patient/{id}")
    public ModelAndView patientProcedures(@PathVariable("id") int id) {
        PatientDTO patient = patientService.getById(id);
        Set<Procedure> procedures = patient.getProcedures();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PROCEDURES_FILTERED);
        modelAndView.addObject(PROCEDURES_LIST, procedures);
        return modelAndView;
    }

    @GetMapping(path = "/procedures/date/{date}")
    public ModelAndView proceduresByDate(@PathVariable("date") String date) {
        List<ProcedureDTO> procedures = procedureService.proceduresByDate(date);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PROCEDURES_FILTERED);
        modelAndView.addObject(PROCEDURES_LIST, procedures);
        return modelAndView;
    }

    @GetMapping(path = "/procedures/nexthour")
    public ModelAndView proceduresForNextHour() {
        List<ProcedureDTO> procedures = procedureService.proceduresForNextHour();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PROCEDURES_FILTERED);
        modelAndView.addObject(PROCEDURES_LIST, procedures);
        return modelAndView;
    }
}
