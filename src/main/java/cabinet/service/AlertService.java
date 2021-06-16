package cabinet.service;

import cabinet.dao.AlertDAO;
import cabinet.model.Alert;
import cabinet.model.dto.AlertDTO;
import cabinet.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlertService {
    private AlertDAO alertDAO;

    @Autowired
    public void setAlertDAO(AlertDAO alertDAO) {
        this.alertDAO = alertDAO;
    }

    @Transactional(readOnly = true)
    public List<AlertDTO> getAllAlerts() {
        List<AlertDTO> alerts = new ArrayList<>();
        for (Alert alert : alertDAO.getAllAlerts()) {
            alerts.add(DtoUtils.convertToDto(alert, AlertDTO.class));
        }
        return alerts;
    }

    @Transactional
    public void add(AlertDTO alertDTO) {
        Alert alert = DtoUtils.convertToEntity(Alert.class, alertDTO);
        alertDAO.add(alert);
    }


}
