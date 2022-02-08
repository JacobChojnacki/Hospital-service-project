package com.example.bdprojekt.zapisy;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.example.bdprojekt.Connector.DbUtill;
import com.example.bdprojekt.models.Wizyty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ZapisyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField uzytkownikEDX;

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

    private ArrayList<Integer> cell = new ArrayList<Integer>();

    public void setUzytkownikEDX(String labelText) {
        uzytkownikEDX.setText(labelText);
    }

    private DbUtill dbUtill;
    private WizytyDAO wizytyDao;

    @FXML
    void confirmRodzajButtonAction(ActionEvent event) {
        loadProducent();
    }

    @FXML
    void confirmDateButtonAction(ActionEvent event) {
        loadGodzina();
        System.out.println(dataWizytyBox.getValue());
    }

    @FXML
    void anulujButtonClick(ActionEvent event) throws SQLException {
        Stage stage = (Stage) anulujButton.getScene().getWindow();
        dbUtill.dbDisconnect();
        stage.close();
    }

    @FXML
    void zapiszButtonClick(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (rodzajBox.getValue() != null && producentBox.getValue() != null && godzinaBox.getValue() != null && dataWizytyBox.getValue() != null) {
            if(chorobaType(uzytkownikEDX.getText())==null) {
                wizytyDao.dodajZapis(uzytkownikEDX.getText(), creatorID(), dataWizytyBox.getValue().toString(), godzinaBox.getValue().toString());
            }else{
                if(chorobaType(uzytkownikEDX.getText()).substring(0,3).equals(rodzajBox.getValue().toString().substring(0,3))){
                    if (veryficationDate(dataWizytyBox.getValue().toString(), uzytkownikEDX.getText()) > 364 || veryficationDate(dataWizytyBox.getValue().toString(), uzytkownikEDX.getText())==-1000){
                        wizytyDao.dodajZapis(uzytkownikEDX.getText(), creatorID(), dataWizytyBox.getValue().toString(), godzinaBox.getValue().toString());
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Bład rejestracji");
                        alert.setContentText("Okres pomiedzy szczepieniami tego samego typu musi byc wiekszy niz 364 dni");
                        alert.showAndWait();
                    }
                }else {
                    if (veryficationDate(dataWizytyBox.getValue().toString(), uzytkownikEDX.getText()) > 20 || veryficationDate(dataWizytyBox.getValue().toString(), uzytkownikEDX.getText())==-1000){
                        wizytyDao.dodajZapis(uzytkownikEDX.getText(), creatorID(), dataWizytyBox.getValue().toString(), godzinaBox.getValue().toString());
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Bład rejestracji");
                        alert.setContentText("Okres od ostatniego szczepienia musi byc wiekszy niz 21 dni");
                        alert.showAndWait();
                    }
                }
            }
        }
    }

//    @FXML
//    void zapiszButtonClick(ActionEvent event) throws SQLException, ClassNotFoundException {
//        if (rodzajBox.getValue() != null && producentBox.getValue() != null && godzinaBox.getValue() != null && dataWizytyBox.getValue() != null) {
////            if(veryficationVaccineDate(uzytkownikEDX.getText(), creatorID(),dataWizytyBox.getValue().toString()) > 364 || veryficationVaccineDate(uzytkownikEDX.getText(), creatorID(),dataWizytyBox.getValue().toString())==0) {
//            if (veryficationDate(dataWizytyBox.getValue().toString(), uzytkownikEDX.getText()) > 20 || veryficationDate(dataWizytyBox.getValue().toString(), uzytkownikEDX.getText())==-1000) {
//                wizytyDao.dodajZapis(uzytkownikEDX.getText(), creatorID(), dataWizytyBox.getValue().toString(), godzinaBox.getValue().toString());
//            } else {
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.setTitle("Bład rejestracji");
//                alert.setContentText("Okres od ostatniego szczepienia musi byc wiekszy niz 21 dni");
//                alert.showAndWait();
//            }
//        } else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Bład rejestracji");
//            alert.setContentText("Okres pomiedzy szczepieniami tego samego typu musi byc wiekszy niz 364 dni");
//            alert.showAndWait();
//        }
//    }


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

    private void loadRodzaj() {
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

    private void loadProducent() {
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

    private void loadDataWizyty() {
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

    private void loadGodzina() {
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

    private String creatorID() {
        StringBuilder idCreator = new StringBuilder();
        idCreator.append(rodzajBox.getValue().toString().substring(0, 3).toUpperCase());
        idCreator.append(producentBox.getValue().toString().substring(0, 3).toUpperCase());
        return idCreator.toString();
    }

    private int veryficationDate(String termin, String pesel) throws SQLException, ClassNotFoundException {

        String liczbaDNI = "SELECT datediff('" + termin + "',(SELECT termin FROM punkt_szczepien.wizyty WHERE pesel = " + pesel +
                " order by termin DESC LIMIT 1)) as roznica FROM punkt_szczepien.wizyty WHERE pesel = " + pesel +
                " order by termin DESC LIMIT 1;";

        ResultSet resultSet = dbUtill.dbExecuteQuery(liczbaDNI);
        int roznica = -1000;
        while (resultSet.next()) {
            roznica = resultSet.getInt("roznica");
        }
        return roznica;
    }

//    private String veryficationVaccineDate(String pesel, String ID_szcz, String termin) throws SQLException, ClassNotFoundException {
//        String liczbaDniTenSamTyp = "SELECT datediff('" + termin + "'),(SELECT termin FROM punkt_szczepien.wizyty WHERE pesel = " + pesel +
//                " AND ID_szcz = '" + ID_szcz + "' order by termin DESC LIMIT 1)) as roznica_typ FROM punkt_szczepien.wizyty WHERE pesel =  " + pesel +
//                " order by termin DESC LIMIT 1;";
//        ResultSet resultSet = dbUtill.dbExecuteQuery(liczbaDniTenSamTyp);
//        String roznica = null;
//        while (resultSet.next()){
//            roznica = resultSet.getInt("roznica_typ");
//        }
//        return roznica;
//    }
    private String chorobaType(String pesel) throws SQLException, ClassNotFoundException {
        String stmt = "SELECT ID_szcz FROM punkt_szczepien.wizyty WHERE pesel = '" + pesel + "' ORDER BY TERMIN DESC LIMIT 1;";
        ResultSet resultSet = dbUtill.dbExecuteQuery(stmt);
        String xd = null;
        while (resultSet.next()){
            xd = resultSet.getString("ID_szcz");
        }
        return xd;
    }

}
