package cabinet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GeneralController {

    /**
     * Controller for main page of application
     *
     * @return modelAndView
     */
    @GetMapping("/")
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    /**
     * In case of forbidden access return custom jsp /error/403
     *
     * @return modelAndView
     */
    @GetMapping("/403")
    public ModelAndView error403() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/403");
        return modelAndView;
    }
}
