package cabinet.controller;

import cabinet.dao.EventDAO;
import cabinet.model.Event;
import cabinet.model.dto.ProcedureDTO;
import cabinet.service.EventService;
import cabinet.service.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventController {

    private EventService eventService;

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(path = "/events/cancel/{id}")
    public ModelAndView cancelEvent(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPatient");
        Event event = eventService.getById(id);
        eventService.cancel(event);
        return modelAndView;
    }
}
