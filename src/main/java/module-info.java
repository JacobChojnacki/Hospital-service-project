module com.example.bdprojekt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires mysql.connector.java;

    exports com.example.bdprojekt;
    opens com.example.bdprojekt.Connector;
    opens com.example.bdprojekt.login;
    opens com.example.bdprojekt.register;
}