package cabinet.controller;

import cabinet.model.Procedure;
import cabinet.model.dto.PatientDTO;
import cabinet.model.dto.ProcedureDTO;
import cabinet.service.PatientService;
import cabinet.service.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
public class ProcedureController {
    private ProcedureService procedureService;
    private PatientService patientService;


    @Autowired
    public void setProcedureService(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @Autowired
    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }


    @RequestMapping(value = "/procedures", method = RequestMethod.GET)
    public ModelAndView allProcedures(@RequestParam(defaultValue = "1") int page) {
        List<ProcedureDTO> procedures = procedureService.allProcedures(page);
        int proceduresCount = procedureService.proceduresCount();
        int pagesCount = (proceduresCount + 9)/10;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("procedures");
        modelAndView.addObject("page", page);
        modelAndView.addObject("proceduresList", procedures);
        modelAndView.addObject("proceduresCount", proceduresCount);
        modelAndView.addObject("pagesCount", pagesCount);
        return modelAndView;
    }

   @RequestMapping(value = "/procedures/edit", method = RequestMethod.POST)
    public ModelAndView editProcedure(@ModelAttribute("procedure") ProcedureDTO procedure) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/procedures");
        procedureService.edit(procedure);
        return modelAndView;
    }

    @RequestMapping(value = "/procedures/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        ProcedureDTO procedure = procedureService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editProcedure");
        modelAndView.addObject("procedure", procedure);
        return modelAndView;
    }

    @RequestMapping(value = "/procedures/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addProcedure");
        return modelAndView;
    }

    @RequestMapping(value = "/procedures/add", method = RequestMethod.POST)
    public ModelAndView addProcedure(@ModelAttribute("procedure") ProcedureDTO procedure) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/procedures");
        procedureService.add(procedure);
        return modelAndView;
    }

    @RequestMapping(value="/procedures/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteProcedure(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/procedures");
        ProcedureDTO procedure = procedureService.getById(id);
        procedureService.delete(procedure);
        return modelAndView;
    }


    @RequestMapping(value = "/procedures/patient/{id}", method = RequestMethod.GET)
    public ModelAndView patientProcedures(@PathVariable("id") int id) {
        PatientDTO patient = patientService.getById(id);
        Set<Procedure> procedures = patient.getProcedures();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("filteredProcedures");
        modelAndView.addObject("proceduresList", procedures);
        return modelAndView;
    }

    @RequestMapping(value = "/procedures/date/{date}", method = RequestMethod.GET)
    public ModelAndView proceduresByDate(@PathVariable("date") String date) {
        List<ProcedureDTO> procedures = procedureService.proceduresByDate(date);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("filteredProcedures");
        modelAndView.addObject("proceduresList", procedures);
        return modelAndView;
    }

    @RequestMapping(value = "/procedures/nexthour", method = RequestMethod.GET)
    public ModelAndView proceduresForNextHour() {
        List<ProcedureDTO> procedures = procedureService.proceduresForNextHour();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("filteredProcedures");
        modelAndView.addObject("proceduresList", procedures);
        return modelAndView;
    }
}
