package com.example.bdprojekt.zapisy;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.example.bdprojekt.Connector.DbUtill;
import com.example.bdprojekt.models.Szczepionka;
import com.example.bdprojekt.szczepienia.SzczepionkaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class ZapisyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button anulujButton;

    @FXML
    private DatePicker dataWizytyCalendar;

    @FXML
    private ComboBox<?> godzinaBox;

    @FXML
    private ComboBox<Szczepionka> producentBox;

    @FXML
    private ComboBox<Szczepionka> rodzajBox;

    @FXML
    private Button zapiszButton;

    private DbUtill dbUtill;
    private SzczepionkaDAO szczepionkaDAO;

    @FXML
    void anulujButtonClick(ActionEvent event) throws SQLException {
        Stage stage = (Stage) anulujButton.getScene().getWindow();
        dbUtill.dbDisconnect();
        stage.close();
    }

    @FXML
    void zapiszButtonClick(ActionEvent event) {

    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        assert anulujButton != null : "fx:id=\"anulujButton\" was not injected: check your FXML file 'zapisy.fxml'.";
        assert dataWizytyCalendar != null : "fx:id=\"dataWizytyCalendar\" was not injected: check your FXML file 'zapisy.fxml'.";
        assert godzinaBox != null : "fx:id=\"godzinaBox\" was not injected: check your FXML file 'zapisy.fxml'.";
        assert producentBox != null : "fx:id=\"producentBox\" was not injected: check your FXML file 'zapisy.fxml'.";
        assert rodzajBox != null : "fx:id=\"rodzajBox\" was not injected: check your FXML file 'zapisy.fxml'.";
        assert zapiszButton != null : "fx:id=\"zapiszButton\" was not injected: check your FXML file 'zapisy.fxml'.";
        dbUtill = new DbUtill();
        dbUtill.dbConnect();
        szczepionkaDAO = new SzczepionkaDAO(dbUtill);
        loadRodzaj();
        loadProducent();
    }
    private void loadRodzaj(){
        String selectStmt = "SELECT choroba FROM punkt_szczepien.szczepienia;";
        try {
            ResultSet resultSet = dbUtill.dbExecuteQuery(selectStmt);

            ObservableList choroba = FXCollections.observableArrayList();
            while (resultSet.next()) {
                choroba.add(resultSet.getString("choroba"));
            }
            rodzajBox.setItems(choroba);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void loadProducent(){
        String selectStmt = "SELECT producent FROM punkt_szczepien.szczepienia;";
        try {
            ResultSet resultSet = dbUtill.dbExecuteQuery(selectStmt);

            ObservableList producent = FXCollections.observableArrayList();
            while (resultSet.next()) {
                producent.add(resultSet.getString("producent"));
            }
            producentBox.setItems(producent);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
