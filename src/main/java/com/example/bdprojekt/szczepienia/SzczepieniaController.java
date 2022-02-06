package com.example.bdprojekt.szczepienia;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.bdprojekt.Connector.DbUtill;
import com.example.bdprojekt.models.Szczepionka;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SzczepieniaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Szczepionka, String> Choroba;

    @FXML
    private TableColumn<Szczepionka, Integer> ID_szcz;

    @FXML
    private TableColumn<Szczepionka, String> Producent;

    @FXML
    private TextField chorobaEDX;

    @FXML
    private Button dodajButton;

    @FXML
    private Button pokazButton;

    @FXML
    private TextField producentEDX;

    @FXML
    private TableView<Szczepionka> szczepieniaTabela;

    @FXML
    private Button znajdzButton;

    private DbUtill dbUtill;
    private SzczepionkaDAO szczepionkaDAO;

    @FXML
    void dodajButtonClick(ActionEvent event) throws SQLException,ClassNotFoundException {
        try {
            if(chorobaEDX.getText() != null && producentEDX.getText() != null){
                szczepionkaDAO.dodajSzczepionke(chorobaEDX.getText(),producentEDX.getText());
            }
        }catch (SQLException e){
            throw e;
        }
    }

    @FXML
    void pokazButtonClick(ActionEvent event) throws SQLException,ClassNotFoundException{
        try {
            szczepieniaTabela.getItems().clear();
            ObservableList<Szczepionka> szczepionki = szczepionkaDAO.pokazSzczepionki();
            bazaSzczepionek(szczepionki);
            wczytajDane();
        }catch (SQLException e){
            throw e;
        }
    }

    @FXML
    void znajdzButtonClick(ActionEvent event) throws SQLException, ClassNotFoundException{
        try {
            if(chorobaEDX.getText() != null){
                szczepieniaTabela.getItems().clear();
                ObservableList<Szczepionka> szczepionkiData = szczepionkaDAO.szukajSzczepionki(chorobaEDX.getText());
                bazaSzczepionek(szczepionkiData);
                wczytajDane();
            }
        }catch (SQLException e){
            throw e;
        }
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        assert Choroba != null : "fx:id=\"Choroba\" was not injected: check your FXML file 'szczepienia.fxml'.";
        assert ID_szcz != null : "fx:id=\"ID\" was not injected: check your FXML file 'szczepienia.fxml'.";
        assert Producent != null : "fx:id=\"Producent\" was not injected: check your FXML file 'szczepienia.fxml'.";
        assert chorobaEDX != null : "fx:id=\"chorobaEDX\" was not injected: check your FXML file 'szczepienia.fxml'.";
        assert dodajButton != null : "fx:id=\"dodajButton\" was not injected: check your FXML file 'szczepienia.fxml'.";
        assert pokazButton != null : "fx:id=\"pokazButton\" was not injected: check your FXML file 'szczepienia.fxml'.";
        assert producentEDX != null : "fx:id=\"producentEDX\" was not injected: check your FXML file 'szczepienia.fxml'.";
        assert szczepieniaTabela != null : "fx:id=\"szczepieniaTabela\" was not injected: check your FXML file 'szczepienia.fxml'.";
        assert znajdzButton != null : "fx:id=\"znajdzButton\" was not injected: check your FXML file 'szczepienia.fxml'.";
        dbUtill = new DbUtill();
        dbUtill.dbConnect();
        szczepionkaDAO = new SzczepionkaDAO(dbUtill);
    }
    private void bazaSzczepionek(ObservableList<Szczepionka> szczepionkaData){
        szczepieniaTabela.setItems(szczepionkaData);
    }
    private void wczytajDane() {
        ID_szcz.setCellValueFactory(new PropertyValueFactory<>("ID_szcz"));
        Producent.setCellValueFactory(new PropertyValueFactory<>("Producent"));
        Choroba.setCellValueFactory(new PropertyValueFactory<>("Choroba"));
    }
}

