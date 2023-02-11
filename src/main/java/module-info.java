module com.example.qualifiedwork {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.qualifiedwork to javafx.fxml;
    opens com.example.qualifiedwork.authenticaton;
    opens com.example.qualifiedwork.patientAccount;
    opens com.example.qualifiedwork.adminAccount;
    opens com.example.qualifiedwork.doctorAccount;

    exports com.example.qualifiedwork;
    exports com.example.qualifiedwork.patientAccount;
    exports com.example.qualifiedwork.authenticaton;
    exports com.example.qualifiedwork.adminAccount;
    exports com.example.qualifiedwork.doctorAccount;
    exports com.example.qualifiedwork.adminAccount.controllers;
    opens com.example.qualifiedwork.adminAccount.controllers;
    exports com.example.qualifiedwork.adminAccount.functional;
    opens com.example.qualifiedwork.adminAccount.functional;
    exports com.example.qualifiedwork.doctorAccount.controllers;
    opens com.example.qualifiedwork.doctorAccount.controllers;
    exports com.example.qualifiedwork.patientAccount.controllers;
    opens com.example.qualifiedwork.patientAccount.controllers;
    exports com.example.qualifiedwork.db_connection;
    opens com.example.qualifiedwork.db_connection to javafx.fxml;
    exports com.example.qualifiedwork.authenticaton.controllers;
    opens com.example.qualifiedwork.authenticaton.controllers;
}