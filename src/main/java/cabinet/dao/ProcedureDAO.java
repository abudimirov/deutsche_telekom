package cabinet.dao;

import cabinet.model.Procedure;

import java.util.List;

public interface ProcedureDAO {
    List<Procedure> allProcedures(int page);
    void add(Procedure procedure);
    void delete(Procedure procedure);
    void edit(Procedure procedure);
    Procedure getById(int id);
    List<Procedure> proceduresByPatient(int id);
    List<Procedure>proceduresByDate(String date);
    int proceduresCount();
}
