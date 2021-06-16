package cabinet.dao;

import cabinet.model.Alert;

import java.util.List;

public interface AlertDAO {
    List<Alert> getAllAlerts();
    void add(Alert alert);
}
