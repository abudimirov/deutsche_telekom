package cabinet.controller;

import cabinet.dao.ProcedureDAO;
import cabinet.model.Procedure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProcedureController {
    private ProcedureDAO procedureDAO;

    @Autowired
    public void setProcedureDAO(ProcedureDAO procedureDAO) {
        this.procedureDAO = procedureDAO;
    }

    @RequestMapping(value = "/procedures", method = RequestMethod.GET)
    public ModelAndView allProcedures(@RequestParam(defaultValue = "1") int page) {
        List<Procedure> procedures = procedureDAO.allProcedures(page);
        int proceduresCount = procedureDAO.proceduresCount();
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
        procedureDAO.edit(procedure);
        return modelAndView;
    }

    @RequestMapping(value = "/procedures/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        Procedure procedure = procedureDAO.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editProcedure");
        modelAndView.addObject("procedure", procedureDAO.getById(id));
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
        procedureDAO.add(procedure);
        return modelAndView;
    }

    @RequestMapping(value="/procedures/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteProcedure(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/procedures");
        Procedure procedure = procedureDAO.getById(id);
        procedureDAO.delete(procedure);
        return modelAndView;
    }

    @RequestMapping(value = "/procedures/patient/{id}", method = RequestMethod.GET)
    public ModelAndView patientProcedures(@PathVariable("id") int id) {
        List<Procedure> procedures = procedureDAO.proceduresByPatient(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("filteredProcedures");
        modelAndView.addObject("proceduresList", procedures);
        return modelAndView;
    }

    @RequestMapping(value = "/procedures/date/{date}", method = RequestMethod.GET)
    public ModelAndView proceduresByDate(@PathVariable("date") String date) {
        List<Procedure> procedures = procedureDAO.proceduresByDate(date);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("filteredProcedures");
        modelAndView.addObject("proceduresList", procedures);
        return modelAndView;
    }

    @RequestMapping(value = "/procedures/nexthour", method = RequestMethod.GET)
    public ModelAndView proceduresForNextHour() {
        List<Procedure> procedures = procedureDAO.proceduresForNextHour();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("filteredProcedures");
        modelAndView.addObject("proceduresList", procedures);
        return modelAndView;
    }
}
