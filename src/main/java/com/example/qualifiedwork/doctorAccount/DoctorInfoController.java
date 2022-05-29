package com.example.qualifiedwork.doctorAccount;

import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class DoctorInfoController {



    private StartApp startApp;

    public void setStartApp(StartApp startApp) { this.startApp = startApp; }
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
