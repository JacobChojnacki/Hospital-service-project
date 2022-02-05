package com.example.bdprojekt.login;



import com.example.bdprojekt.Connector.DatabaseConnection;
import com.example.bdprojekt.Connector.DbUtill;
import com.example.bdprojekt.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LoginController {

    @FXML
    private RadioButton pracownikRadioButton;

    @FXML
    private RadioButton pacjentRadioButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private Button registerButton;

    private String username;


    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    DbUtill dbUtill = new DbUtill();

    /**
     * Zwraca boolean w zależnie od tego czy udalo sie zalogowac czy nie
     * @param e - przechwytywane zdarzenie polegajace na kliknieciu przycisku Login
     */
    public void LoginButtonOnAction(ActionEvent e) throws SQLException, ClassNotFoundException {


        if (usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false) {
            setUsername(usernameTextField.getText());
            validateLogin();
        } else {
            loginMessageLabel.setText("Please enter username and password");
        }
    }

    /**
     * Metoda zamykajaca nasza aplikacje
     * @param e - przechwytywane zdarzenie polegajace na kliknieciu przycisku cancel
     */
    public void cancelButtonAction(ActionEvent e) throws SQLException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        dbUtill.dbDisconnect();
        stage.close();
    }

    public void registerButtonAction(ActionEvent e){
        createAccountStage();
    }

    /**
     * Metoda weryfikujaca poprawnosc danych logowania
     */

    public void validateLogin() throws SQLException, ClassNotFoundException {

        Connection connectDB = dbUtill.dbConnect();

        String verifyLogin = "select count(1) from punkt_szczepien.pacjenci where pesel = '" + usernameTextField.getText() + "' and haslo = '" + passwordPasswordField.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if(pacjentRadioButton.isSelected()){
                    if (queryResult.getInt(1) == 1) {
                        createWidokPacjenta();
                    }else {
                        loginMessageLabel.setText("Invalid Login. Please try again");
                    }
                }else if(pracownikRadioButton.isSelected()){
                    if (usernameTextField.getText().equals("przychodnia") && passwordPasswordField.getText().equals("54321")){
                        createWidokPrzychodni();
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Blad logowania");
                    alert.setContentText("Prosze o zaznaczenie jako kto chce sie Pan zalogować.");
                    alert.showAndWait();
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda otwierajaca okno z rejestracja do bazy danych
     */
    public void createAccountStage(){
        try{
            FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("register.fxml"));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.DECORATED);
            Scene scene = new Scene(fxmlLoader.load(), 520, 400);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void createWidokPacjenta(){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("widokPacjenta.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception error) {
            error.printStackTrace();
            error.getCause();
        }
    }
    public void createWidokPrzychodni(){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("widokPrzychodni.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void initialize() {
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'login.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'login.fxml'.";
        assert loginMessageLabel != null : "fx:id=\"loginMessageLabel\" was not injected: check your FXML file 'login.fxml'.";
        assert pacjentRadioButton != null : "fx:id=\"pacjentRadioButton\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordPasswordField != null : "fx:id=\"passwordPasswordField\" was not injected: check your FXML file 'login.fxml'.";
        assert pracownikRadioButton != null : "fx:id=\"pracownikRadioButton\" was not injected: check your FXML file 'login.fxml'.";
        assert registerButton != null : "fx:id=\"registerButton\" was not injected: check your FXML file 'login.fxml'.";
        assert usernameTextField != null : "fx:id=\"usernameTextField\" was not injected: check your FXML file 'login.fxml'.";

        ToggleGroup radioGroup = new ToggleGroup();
        pacjentRadioButton.setToggleGroup(radioGroup);
        pracownikRadioButton.setToggleGroup(radioGroup);
    }

}
