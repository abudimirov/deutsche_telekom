package cabinet.controller;

import cabinet.model.Event;
import cabinet.model.dto.EventDTO;
import cabinet.model.dto.ProcedureDTO;
import cabinet.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        modelAndView.setViewName("patients");
        EventDTO eventDTO = eventService.getById(id);
        eventService.cancel(eventDTO);
        return modelAndView;
    }

}
