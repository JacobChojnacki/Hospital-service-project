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
    private PasswordField PasswordTextField;

    @FXML
    private TextField PeselTextField;

    @FXML
    private TextField firstnameTextField;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private Button rCancelButton;

    @FXML
    private Button registerButton;

    @FXML
    private Label registerMessageLabel;

    @FXML
    private TextField telefonTextField1;

    public void cancelButtonAction(ActionEvent e) {
        Stage stage = (Stage) rCancelButton.getScene().getWindow();
        stage.close();
    }
    public void registerButtonAction(ActionEvent e) {
        registerInterface();
    }

    public void registerInterface(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String pesel = PeselTextField.getText();
        String telefon = telefonTextField1.getText();
        String haslo = PasswordTextField.getText();

        String insertFields = "INSERT into punkt_szczepien.pacjenci(imie, nazwisko, pesel, numer_telefonu, haslo) VALUES ('";
        String insertValues = firstname + "','" + lastname + "','" + pesel + "','" + telefon + "','" + haslo + "')";
        String insertToRegister = insertFields + insertValues;

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);

            registerMessageLabel.setText("User has been registered");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


}
