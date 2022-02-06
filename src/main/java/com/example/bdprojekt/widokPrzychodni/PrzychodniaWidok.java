package com.example.bdprojekt.widokPrzychodni;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import com.example.bdprojekt.Connector.DbUtill;
import com.example.bdprojekt.Main;
import com.example.bdprojekt.models.Pacjent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PrzychodniaWidok {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Pacjent, String> godzina;

    @FXML
    private TableColumn<Pacjent, Integer> id;

    @FXML
    private TableColumn<Pacjent, String> nazwisko;

    @FXML
    private TableColumn<Pacjent, String> numerTelefonu;

    @FXML
    private TableColumn<Pacjent, String> pesel;

    @FXML
    private TextField peselEDX;

    @FXML
    private TableColumn<Pacjent, String> realizacja;

    @FXML
    private Button statystykiButton;

    @FXML
    private TableView pacjenciTable;

    @FXML
    private TableColumn<Pacjent, String> termin;

    @FXML
    private TableColumn<Pacjent, String> typ;

    @FXML
    private Button usunButton;

    @FXML
    private Label uzytkownikLabel;

    @FXML
    private Button wyszukajButton;

    @FXML
    private Button zarejestrujButton;

    @FXML
    private Button szczepieniaButton;

    Connection connection = null;
    ResultSet resultSet = null;
    ObservableList<Pacjent> pacjenciLista = FXCollections.observableArrayList();
    private DbUtill dbUtill;
    private PacjentDAO pacjentDAO;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        assert godzina != null : "fx:id=\"godzina\" was not injected: check your FXML file 'widokPrzychodni.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'widokPrzychodni.fxml'.";
        assert nazwisko != null : "fx:id=\"nazwisko\" was not injected: check your FXML file 'widokPrzychodni.fxml'.";
        assert numerTelefonu != null : "fx:id=\"numerTelefonu\" was not injected: check your FXML file 'widokPrzychodni.fxml'.";
        assert pesel != null : "fx:id=\"pesel\" was not injected: check your FXML file 'widokPrzychodni.fxml'.";
        assert peselEDX != null : "fx:id=\"peselEDX\" was not injected: check your FXML file 'widokPrzychodni.fxml'.";
        assert realizacja != null : "fx:id=\"realizacja\" was not injected: check your FXML file 'widokPrzychodni.fxml'.";
        assert statystykiButton != null : "fx:id=\"statystykiButton\" was not injected: check your FXML file 'widokPrzychodni.fxml'.";
        assert termin != null : "fx:id=\"termin\" was not injected: check your FXML file 'widokPrzychodni.fxml'.";
        assert typ != null : "fx:id=\"typ\" was not injected: check your FXML file 'widokPrzychodni.fxml'.";
        assert usunButton != null : "fx:id=\"usunButton\" was not injected: check your FXML file 'widokPrzychodni.fxml'.";
        assert uzytkownikLabel != null : "fx:id=\"uzytkownikLabel\" was not injected: check your FXML file 'widokPrzychodni.fxml'.";
        assert wyszukajButton != null : "fx:id=\"wyszukajButton\" was not injected: check your FXML file 'widokPrzychodni.fxml'.";
        assert zarejestrujButton != null : "fx:id=\"zarejestrujButton\" was not injected: check your FXML file 'widokPrzychodni.fxml'.";

        dbUtill = new DbUtill();
        dbUtill.dbConnect();
        pacjentDAO = new PacjentDAO(dbUtill);
    }

    private void odswiezanieTabeli(){

    }

    @FXML
    private void szczepieniaButtonClick(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("szczepienia.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void wyszukajButtonClick(ActionEvent event) throws SQLException, ClassNotFoundException{
        try {
            if(peselEDX.getText() != null) {
                pacjenciTable.getItems().clear();
                ObservableList<Pacjent> pacjenciData = pacjentDAO.szukajPacjenta(peselEDX.getText());
                bazaPacjentow(pacjenciData);
                zaladujDane();
            }
        }catch (SQLException e) {
            throw e;
        }
    }

    @FXML
    private void pacjenciButtonClick(ActionEvent event) throws SQLException, ClassNotFoundException{
        try {

            pacjenciTable.getItems().clear();
            ObservableList<Pacjent> pacjenciData = pacjentDAO.pokazPacjentow();
            bazaPacjentow(pacjenciData);
            zaladujDane();

        } catch (SQLException e) {
            throw e;
        }
    }

    @FXML
    private void zarejestrujButtonClick(ActionEvent e) {

    }

    @FXML
    private void usunButtonClick(ActionEvent event) throws SQLException, ClassNotFoundException{
        try {
            if(peselEDX.getText() != null){
                pacjentDAO.usunPacjenta(peselEDX.getText());
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    @FXML
    private void statystykiButtonClick(ActionEvent e) {

    }

    private void bazaPacjentow(ObservableList<Pacjent> pacjentData) {
        pacjenciTable.setItems(pacjentData);
    }

    private void zaladujDane() {
        id.setCellValueFactory(new PropertyValueFactory<>("ID_p"));
        nazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        pesel.setCellValueFactory(new PropertyValueFactory<>("pesel"));
        numerTelefonu.setCellValueFactory(new PropertyValueFactory<>("numer_telefonu"));
        typ.setCellValueFactory(new PropertyValueFactory<>("typ"));
        termin.setCellValueFactory(new PropertyValueFactory<>("termin"));
        godzina.setCellValueFactory(new PropertyValueFactory<>("godzina"));
        realizacja.setCellValueFactory(new PropertyValueFactory<>("zrealizowano"));
    }
}
