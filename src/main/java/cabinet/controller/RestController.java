package cabinet.controller;

import cabinet.config.ProcedureSerializer;
import cabinet.model.dto.ProcedureDTO;
import cabinet.service.ProcedureService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;


@Controller
@RequestMapping("api")
public class RestController {
    private ProcedureService procedureService;

    @Autowired
    public void setProcedureService(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @GetMapping(value = "/procedures", produces = "application/json")
    public @ResponseBody String getProcedures() {
        Gson gson = new GsonBuilder().registerTypeAdapter(ProcedureDTO.class, new ProcedureSerializer()).create();

        return gson.toJson(procedureService.proceduresByDate(LocalDate.now().toString()));
    }
}
