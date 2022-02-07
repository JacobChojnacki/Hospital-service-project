package com.example.bdprojekt.zapisy;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.example.bdprojekt.Connector.DbUtill;
import com.example.bdprojekt.models.Wizyty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ZapisyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button anulujButton;

    @FXML
    private ComboBox godzinaBox;

    @FXML
    private ComboBox producentBox;

    @FXML
    private ComboBox rodzajBox;

    @FXML
    private ComboBox dataWizytyBox;

    @FXML
    private Button zapiszButton;

    private DbUtill dbUtill;
    private WizytyDAO wizytyDao;

    @FXML
    void confirmRodzajButtonAction(ActionEvent event) {
        loadProducent();
    }

    @FXML
    void confirmDateButtonAction(ActionEvent event) {
        loadGodzina();
    }

    @FXML
    void anulujButtonClick(ActionEvent event) throws SQLException {
        Stage stage = (Stage) anulujButton.getScene().getWindow();
        dbUtill.dbDisconnect();
        stage.close();
    }

    @FXML
    void zapiszButtonClick(ActionEvent event) throws SQLException, ClassNotFoundException{
        try{
            if(rodzajBox.getValue()!=null && producentBox.getValue()!=null && godzinaBox.getValue()!=null && dataWizytyBox.getValue() != null){
                wizytyDao.dodajZapis("99011003939", creatorID(),dataWizytyBox.getValue().toString(),godzinaBox.getValue().toString());
//                wizytyDao.dodajZapis("99011003939","DURANT", "2010.10.20","00:00:13");
            }
        }catch (SQLException e){
            throw e;
        }
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        assert anulujButton != null : "fx:id=\"anulujButton\" was not injected: check your FXML file 'zapisy.fxml'.";
        assert dataWizytyBox != null : "fx:id=\"dataWizytyBox\" was not injected: check your FXML file 'zapisy.fxml'.";
        assert godzinaBox != null : "fx:id=\"godzinaBox\" was not injected: check your FXML file 'zapisy.fxml'.";
        assert producentBox != null : "fx:id=\"producentBox\" was not injected: check your FXML file 'zapisy.fxml'.";
        assert rodzajBox != null : "fx:id=\"rodzajBox\" was not injected: check your FXML file 'zapisy.fxml'.";
        assert zapiszButton != null : "fx:id=\"zapiszButton\" was not injected: check your FXML file 'zapisy.fxml'.";
        dbUtill = new DbUtill();
        dbUtill.dbConnect();
        wizytyDao = new WizytyDAO(dbUtill);
        loadRodzaj();
        loadDataWizyty();
    }
    private void loadRodzaj(){
        String selectStmt = "SELECT choroba FROM punkt_szczepien.szczepienia;";
        try {
            ResultSet resultSet = dbUtill.dbExecuteQuery(selectStmt);

            ObservableList choroba = FXCollections.observableArrayList();
            while (resultSet.next()) {
                choroba.add(resultSet.getString("choroba"));
            }
            rodzajBox.setItems((ObservableList<Wizyty>) choroba
                    .stream()
                    .distinct()
                    .collect(Collectors.toCollection(FXCollections::observableArrayList)));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void loadProducent(){
        String selectStmt = "SELECT producent FROM punkt_szczepien.szczepienia WHERE choroba = '" + rodzajBox.getValue() + "';";
        try {
            ResultSet resultSet = dbUtill.dbExecuteQuery(selectStmt);

            ObservableList producent = FXCollections.observableArrayList();
            while (resultSet.next()) {
                producent.add(resultSet.getString("producent"));
            }
            producentBox.setItems((ObservableList<Wizyty>) producent.stream()
                    .distinct()
                    .collect(Collectors.toCollection(FXCollections::observableArrayList)));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void loadDataWizyty(){
        String selectStmt = "SELECT termin FROM punkt_szczepien.wizyty;";
        try {
            ResultSet resultSet = dbUtill.dbExecuteQuery(selectStmt);

            ObservableList dataWizyty = FXCollections.observableArrayList();
            while (resultSet.next()) {
                dataWizyty.add(resultSet.getString("termin"));
            }
            dataWizytyBox.setItems((ObservableList<Wizyty>) dataWizyty.stream()
                    .distinct()
                    .sorted()
                    .collect(Collectors.toCollection(FXCollections::observableArrayList)));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void loadGodzina(){
        String selectStmt = "SELECT godzina FROM punkt_szczepien.wizyty WHERE termin = '" + dataWizytyBox.getValue() + "' and pesel is null;";
        try {
            ResultSet resultSet = dbUtill.dbExecuteQuery(selectStmt);

            ObservableList godzinaWizyty = FXCollections.observableArrayList();
            while (resultSet.next()) {
                godzinaWizyty.add(resultSet.getString("godzina"));
            }
            godzinaBox.setItems((ObservableList<Wizyty>) godzinaWizyty.stream()
                    .distinct()
                    .sorted()
                    .collect(Collectors.toCollection((FXCollections::observableArrayList))));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String creatorID(){
        StringBuilder idCreator = new StringBuilder();
        idCreator.append(rodzajBox.getValue().toString().substring(0,3).toUpperCase());
        idCreator.append(producentBox.getValue().toString().substring(0,3).toUpperCase());
        return idCreator.toString();
    }
}
