package com.example.qualifiedwork.patientAccount;

import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class PatientProfileController {
    @FXML
    private Button patientInfoBtn;

    @FXML
    private Button patientLogOutBtn;

    @FXML
    private Button patientMenuMainBtn;

    @FXML
    private Button patientProfileBtn;

    @FXML
    void patientLogOut(MouseEvent event) {
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
    void patientOpenInfoPage(MouseEvent event) {

    }

    @FXML
    void patientOpenMainPage(MouseEvent event) {

    }

    private StartApp startApp;

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }
}
