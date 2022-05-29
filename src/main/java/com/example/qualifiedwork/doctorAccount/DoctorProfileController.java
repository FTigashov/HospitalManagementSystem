package com.example.qualifiedwork.doctorAccount;

import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class DoctorProfileController {
    @FXML
    private Label birthDateLabel;

    @FXML
    private Button doctorInfoBtn;

    @FXML
    private Button doctorLogOutBtn;

    @FXML
    private Button doctorMenuMainBtn;

    @FXML
    private Button doctorProfileBtn;

    @FXML
    private Label emplDateLabel;

    @FXML
    private Label fatherNameLabel;

    @FXML
    private Label loginLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label responsStatusLabel;

    @FXML
    private Label secondNameLabel;

    private StartApp startApp;

    public void setStartApp(StartApp startApp) { this.startApp = startApp; }

    public void setDoctorInfo(String getSecondName, String getName,
                              String getFatherName, String getBirthDate,
                              String getEmplDate, String getResponsStatus,
                              String getLogin, String getPassword) {

        secondNameLabel.setText(getSecondName);
        nameLabel.setText(getName);
        fatherNameLabel.setText(getFatherName);
        birthDateLabel.setText(getBirthDate);
        emplDateLabel.setText(getEmplDate);
        responsStatusLabel.setText(getResponsStatus);
        loginLabel.setText(getLogin);
        passwordLabel.setText(getPassword);
    }

    @FXML
    void doctorLogOut(MouseEvent event) {
        startApp.switchToChoiceScene();
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
