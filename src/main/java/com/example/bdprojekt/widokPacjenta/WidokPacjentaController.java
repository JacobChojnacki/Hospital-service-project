package com.example.bdprojekt.widokPacjenta;

import java.sql.*;
import com.example.bdprojekt.Connector.DbUtill;
import com.example.bdprojekt.Main;
import com.example.bdprojekt.models.PacjentWidok;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class WidokPacjentaController {

    @FXML
    private TableView<PacjentWidok> pacjentWidok;

    @FXML
    private TableColumn<PacjentWidok, String> godzinaColumn;

    @FXML
    private TableColumn<PacjentWidok, String> producentColumn;

    @FXML
    private TableColumn<PacjentWidok, String> terminColumn;

    @FXML
    private TableColumn<PacjentWidok, String> typSzczepieniaColumn;

    @FXML
    private Label uzytkownikLabel;

    @FXML
    private Button zakonczButton;

    @FXML
    private Button zapisButton;

    private String n;

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    @FXML
    private TableColumn<PacjentWidok, String> zrealizowanoColumn;

    public void setUzytkownikLabel(String labelText){
        uzytkownikLabel.setText(labelText);
    }

    DbUtill dbUtill = new DbUtill();
//    ObservableList<PacjentWidok> pacjentWidokLista = FXCollections.observableArrayList();
    private PacjentWidokDAO pacjentWidokDAO;

    @FXML
    void zakonczButtonAction(ActionEvent event) throws SQLException {
        Stage stage = (Stage) zakonczButton.getScene().getWindow();
        dbUtill.dbDisconnect();
        stage.close();
    }

    @FXML
    void zapiszButtonAction(ActionEvent event) {
        createZapisy();
    }


    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        assert godzinaColumn != null : "fx:id=\"godzinaColumn\" was not injected: check your FXML file 'widokPacjenta.fxml'.";
        assert producentColumn != null : "fx:id=\"producentColumn\" was not injected: check your FXML file 'widokPacjenta.fxml'.";
        assert terminColumn != null : "fx:id=\"terminColumn\" was not injected: check your FXML file 'widokPacjenta.fxml'.";
        assert typSzczepieniaColumn != null : "fx:id=\"typColumn\" was not injected: check your FXML file 'widokPacjenta.fxml'.";
        assert uzytkownikLabel != null : "fx:id=\"uzytkownikLabel\" was not injected: check your FXML file 'widokPacjenta.fxml'.";
        assert zakonczButton != null : "fx:id=\"zakonczButton\" was not injected: check your FXML file 'widokPacjenta.fxml'.";
        assert zapisButton != null : "fx:id=\"zapisButton\" was not injected: check your FXML file 'widokPacjenta.fxml'.";
        assert zrealizowanoColumn != null : "fx:id=\"zrealizowanoColumn\" was not injected: check your FXML file 'widokPacjenta.fxml'.";

        dbUtill = new DbUtill();
        dbUtill.dbConnect();
        pacjentWidokDAO = new PacjentWidokDAO(dbUtill);
    }

    public void createZapisy() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("zapisy.fxml"));
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

    private void zaladujDane() {
        typSzczepieniaColumn.setCellValueFactory(new PropertyValueFactory<>("choroba"));
        godzinaColumn.setCellValueFactory(new PropertyValueFactory<>("godzina"));
        terminColumn.setCellValueFactory(new PropertyValueFactory<>("termin"));
        zrealizowanoColumn.setCellValueFactory(new PropertyValueFactory<>("zrealizowano"));
        producentColumn.setCellValueFactory(new PropertyValueFactory<>("producent"));
    }
    public void bazaDanychPacjenta(ObservableList<PacjentWidok> danePacjenta) {
        pacjentWidok.setItems(danePacjenta);
    }

    public void wyswietleniaInformacjiPacjenta(String n) throws SQLException, ClassNotFoundException{
        try {
            pacjentWidok.getItems().clear();
            ObservableList<PacjentWidok> pacjentWidok = pacjentWidokDAO.pokazDanePacjentaKonkretnego(n);
            bazaDanychPacjenta(pacjentWidok);
            zaladujDane();
        }catch (SQLException e) {
            throw e;
        }
    }
}

