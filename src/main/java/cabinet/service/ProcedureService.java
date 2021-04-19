package cabinet.service;

import cabinet.model.Procedure;

import java.util.List;

public interface ProcedureService {
    List<Procedure> allProcedures(int page);
    void add(Procedure procedure);
    void delete(Procedure procedure);
    void edit(Procedure procedure);
    Procedure getById(int id);

    int proceduresCount();
}
