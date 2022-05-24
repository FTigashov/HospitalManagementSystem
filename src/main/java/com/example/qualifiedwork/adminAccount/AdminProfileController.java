package com.example.qualifiedwork.adminAccount;

import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class AdminProfileController {
    @FXML
    private Button adminMenuMainBtn;

    @FXML
    private Button patientInfoBtn;

    @FXML
    private Button patientLogOutBtn;

    @FXML
    private Button patientProfileBtn;

    @FXML
    void adminLogOut(MouseEvent event) {
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
    void adminOpenInfoPage(MouseEvent event) {
        startApp.switchToAdminInfoScene();
    }

    @FXML
    void adminOpenMainPage(MouseEvent event) {
        startApp.switchToAdminMainMenuScene();
    }

    @FXML
    void adminOpenProfile(MouseEvent event) {
        startApp.switchToAdminProfileScene();
    }

    private StartApp startApp;

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }
}
