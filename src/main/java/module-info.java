module com.example.bdprojekt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires mysql.connector.java;
    requires java.sql.rowset;

    exports com.example.bdprojekt;
    opens com.example.bdprojekt.Connector;
    opens com.example.bdprojekt.login;
    opens com.example.bdprojekt.models;
    opens com.example.bdprojekt.register;
    opens com.example.bdprojekt.szczepienia;
    opens com.example.bdprojekt.terminy;
    opens com.example.bdprojekt.widokPacjenta;
    opens com.example.bdprojekt.widokPrzychodni;
    opens com.example.bdprojekt.zapisy;
}