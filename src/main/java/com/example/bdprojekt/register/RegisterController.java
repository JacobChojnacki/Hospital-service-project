package com.example.bdprojekt.register;

import com.example.bdprojekt.Connector.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.Statement;

public class RegisterController {

    @FXML
    private PasswordField hasloTextField;

    @FXML
    private TextField peselTextField;

    @FXML
    private TextField imieTextField;

    @FXML
    private TextField nazwiskoTextField;

    @FXML
    private Button anulujButton;

    @FXML
    private Button zarejestrujButton;

    @FXML
    private Label zarejestrujMessageLabel;

    @FXML
    private TextField telefonTextField;

    public void anulujButtonAction(ActionEvent e) {
        Stage stage = (Stage) anulujButton.getScene().getWindow();
        stage.close();
    }
    public void zarejestrujButtonAction(ActionEvent e) {
        zarejestrujInterface();
    }

    public void zarejestrujInterface(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String imie = imieTextField.getText();
        String nazwisko = nazwiskoTextField.getText();
        String pesel = peselTextField.getText();
        String telefon = telefonTextField.getText();
        String haslo = hasloTextField.getText();
        String insertFields = "INSERT into punkt_szczepien.pacjenci(imie, nazwisko, pesel, numer_telefonu, haslo) VALUES ('";
        String insertValues = imie + "','" + nazwisko + "','" + pesel + "','" + telefon + "','" + haslo + "')";
        String insertToRegister = insertFields + insertValues;

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);

            zarejestrujMessageLabel.setText("User has been registered");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


}
