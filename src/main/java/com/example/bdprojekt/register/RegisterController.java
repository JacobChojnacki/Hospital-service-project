package com.example.bdprojekt.register;

import com.example.bdprojekt.Connector.DatabaseConnection;
import com.example.bdprojekt.Connector.DbUtill;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

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
    public void zarejestrujButtonAction(ActionEvent e) throws ParseException {
        zarejestrujInterface();
    }

    public void zarejestrujInterface() throws ParseException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();



        String imie = imieTextField.getText();
        String nazwisko = nazwiskoTextField.getText();
        String pesel = peselTextField.getText();
        String telefon = telefonTextField.getText();
        String haslo = hasloTextField.getText();
        if(getDate(pesel) >= 18) {
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
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Blad rejestracji");
            alert.setContentText("Nie spelniasz wymogow wiekowych. Musisz miec przynajmniej 18 lat.");
            alert.showAndWait();
        }


    }

    private static long getDate(String PESEL) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        int r = 1900;
        int d = Integer.parseInt(Character.toString(PESEL.charAt(4))) * 10
                + Integer.parseInt(Character.toString(PESEL.charAt(5)));
        int m = Integer.parseInt(Character.toString(PESEL.charAt(2))) * 10
                + Integer.parseInt(Character.toString(PESEL.charAt(3)));
        if ((m - 20) >= 1 && (m - 20) <= 12)
            r = 2000;
        if ((m - 40) >= 1 && (m - 40) <= 12)
            r = 2100;
        if ((m - 60) >= 1 && (m - 60) <= 12)
            r = 2200;
        if ((m - 80) >= 1 && (m - 80) <= 12)
            r = 1800;
        if (r == 2000)
            m -= 20;
        if (r == 2100)
            m -= 40;
        if (r == 2200)
            m -= 60;
        if (r == 1800)
            m -= 80;
        r += Integer.parseInt(Character.toString(PESEL.charAt(0))) * 10
                + Integer.parseInt(Character.toString(PESEL.charAt(1)));
        String s = r + "-" + m + "-" + d;

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(s);

        String wiek = new SimpleDateFormat("yyyy-MM-dd").format(date);

        LocalDate birthDate = LocalDate.parse(wiek);

        LocalDate end = LocalDate.now();
        long years = ChronoUnit.YEARS.between(birthDate, end);
        return years;
    }
}
