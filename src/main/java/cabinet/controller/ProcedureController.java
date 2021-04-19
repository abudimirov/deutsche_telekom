package cabinet.controller;

import cabinet.model.Patient;
import cabinet.model.Procedure;
import cabinet.service.PatientService;
import cabinet.service.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProcedureController {
    private ProcedureService procedureService;
    private PatientService patientService;

    @Autowired
    public void setProcedureService(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @RequestMapping(value = "/procedures", method = RequestMethod.GET)
    public ModelAndView allProcedures(@RequestParam(defaultValue = "1") int page) {
        List<Procedure> procedures = procedureService.allProcedures(page);
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
    public ModelAndView editProcedure(@ModelAttribute("procedure") Procedure procedure) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/procedures");
        procedureService.edit(procedure);
        return modelAndView;
    }

    @RequestMapping(value = "/procedures/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        Procedure procedure = procedureService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editProcedure");
        modelAndView.addObject("procedure", procedureService.getById(id));
        return modelAndView;
    }

    @RequestMapping(value = "/procedures/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editProcedure");
        return modelAndView;
    }

    @RequestMapping(value = "/procedures/add", method = RequestMethod.POST)
    public ModelAndView addProcedure(@ModelAttribute("procedure") Procedure procedure) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/procedures");
        procedureService.add(procedure);
        return modelAndView;
    }

    @RequestMapping(value="/procedures/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteProcedure(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/procedures");
        Procedure procedure = procedureService.getById(id);
        procedureService.delete(procedure);
        return modelAndView;
    }
}
