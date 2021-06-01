package cabinet.config;

import cabinet.model.Patient;
import cabinet.model.dto.ProcedureDTO;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class ProcedureSerializer implements JsonSerializer<ProcedureDTO> {
    @Override
    public JsonElement serialize(ProcedureDTO procedureDTO, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.add("id", new JsonPrimitive(procedureDTO.getId()));
        result.add("title", new JsonPrimitive(procedureDTO.getTitle()));
        result.add("time", new JsonPrimitive(procedureDTO.getTime()));
        Patient patient = procedureDTO.getPatient();
        if (patient != null) {
            result.add("patient_name", new JsonPrimitive(patient.getName()));
            result.add("patient_surname", new JsonPrimitive(patient.getSurname()));
        }
        result.add("status", new JsonPrimitive(procedureDTO.getStatus()));

        return result;
    }
}
