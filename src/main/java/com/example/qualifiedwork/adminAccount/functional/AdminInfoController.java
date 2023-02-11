package com.example.qualifiedwork.adminAccount.functional;

import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class AdminInfoController {
    @FXML
    private Button adminInfoBtn;

    @FXML
    private Button adminLogOutBtn;

    @FXML
    private Button adminMenuMainBtn;

    @FXML
    private Button adminProfileBtn;

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
    void openAdminInfo(MouseEvent event) {
        startApp.switchToAdminInfoScene();
    }

    @FXML
    void openAdminMenu(MouseEvent event) {
        startApp.switchToAdminMainMenuScene();
    }

    @FXML
    void openAdminProfile(MouseEvent event) {
        startApp.switchToAdminProfileScene();
    }

    private StartApp startApp;

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }
}
