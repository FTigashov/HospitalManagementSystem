package com.example.qualifiedwork.doctorAccount;

import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class DoctorInfoController {



    private StartApp startApp;

    public void setStartApp(StartApp startApp) { this.startApp = startApp; }
    @FXML
    void doctorLogOut(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение действия");
        alert.setHeaderText("Выход из учетной записи");
        alert.setContentText("Вы уверены, что хотите выйти?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            startApp.switchToChoiceScene();
        } else { return; }
    }

    @FXML
    void doctorOpenInfo(MouseEvent event) {
        startApp.switchToDoctorInfoScene();
    }

    @FXML
    void doctorOpenMenu(MouseEvent event) {
        startApp.switchToDoctorMainMenuScene();
    }

    @FXML
    void doctorOpenProfile(MouseEvent event) {
        startApp.switchToDoctorProfileScene();
    }
}
