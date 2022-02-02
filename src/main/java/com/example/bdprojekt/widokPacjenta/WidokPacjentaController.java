package com.example.bdprojekt.widokPacjenta;

import java.sql.Time;
import java.util.Date;
import com.example.bdprojekt.Connector.DbUtill;
import com.example.bdprojekt.Main;
import javafx.collections.FXCollections;
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
    private TableColumn<PacjentWidok, Time> godzinaColumn;

    @FXML
    private TableColumn<PacjentWidok, String> producentColumn;

    @FXML
    private TableColumn<PacjentWidok, Date> terminColumn;

    @FXML
    private TableColumn<PacjentWidok, String> typSzczepieniaColumn;

    @FXML
    private Label uzytkownikLabel;

    @FXML
    private Button zakonczButton;

    @FXML
    private Button zapisButton;

    @FXML
    private TableColumn<?, ?> zrealizowanoColumn;

    private DbUtill dbUtill;
    @FXML
    void zakonczButtonAction(ActionEvent event) {
        Stage stage = (Stage) zakonczButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void zapiszButtonAction(ActionEvent event) {
        createZapisy();
    }

    @FXML
    void initialize() {
        assert godzinaColumn != null : "fx:id=\"godzinaColumn\" was not injected: check your FXML file 'widokPacjenta.fxml'.";
        assert producentColumn != null : "fx:id=\"producentColumn\" was not injected: check your FXML file 'widokPacjenta.fxml'.";
        assert terminColumn != null : "fx:id=\"terminColumn\" was not injected: check your FXML file 'widokPacjenta.fxml'.";
        assert typSzczepieniaColumn != null : "fx:id=\"typColumn\" was not injected: check your FXML file 'widokPacjenta.fxml'.";
        assert uzytkownikLabel != null : "fx:id=\"uzytkownikLabel\" was not injected: check your FXML file 'widokPacjenta.fxml'.";
        assert zakonczButton != null : "fx:id=\"zakonczButton\" was not injected: check your FXML file 'widokPacjenta.fxml'.";
        assert zapisButton != null : "fx:id=\"zapisButton\" was not injected: check your FXML file 'widokPacjenta.fxml'.";
        assert zrealizowanoColumn != null : "fx:id=\"zrealizowanoColumn\" was not injected: check your FXML file 'widokPacjenta.fxml'.";
        zaladujDane();
    }

    public void createZapisy(){
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
    private void zaladujDane(){

        ObservableList<PacjentWidok> pacjentWidoksLista = FXCollections.observableArrayList();

        typSzczepieniaColumn.setCellValueFactory(new PropertyValueFactory<>("choroba"));
        producentColumn.setCellValueFactory(new PropertyValueFactory<>("producent"));
        terminColumn.setCellValueFactory(new PropertyValueFactory<>("termin"));
        godzinaColumn.setCellValueFactory(new PropertyValueFactory<>("godzina"));
        zrealizowanoColumn.setCellValueFactory(new PropertyValueFactory<>("zrealizowano"));

    }

}
