package com.example.bdprojekt.zapisy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

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
    private ChoiceBox<?> godzinaBox;

    @FXML
    private ChoiceBox<?> producentBox;

    @FXML
    private ChoiceBox<?> rodzajSzczepionkiBox;

    @FXML
    private Button zapiszButton;

    @FXML
    void anuluButtonClick(ActionEvent event) {

    }

    @FXML
    void zapiszButtonClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert anulujButton != null : "fx:id=\"anulujButton\" was not injected: check your FXML file 'zapisy.fxml'.";
        assert dataWizytyCalendar != null : "fx:id=\"dataWizytyCalendar\" was not injected: check your FXML file 'zapisy.fxml'.";
        assert godzinaBox != null : "fx:id=\"godzinaBox\" was not injected: check your FXML file 'zapisy.fxml'.";
        assert producentBox != null : "fx:id=\"producentBox\" was not injected: check your FXML file 'zapisy.fxml'.";
        assert rodzajSzczepionkiBox != null : "fx:id=\"rodzajSzczepionkiBox\" was not injected: check your FXML file 'zapisy.fxml'.";
        assert zapiszButton != null : "fx:id=\"zapiszButton\" was not injected: check your FXML file 'zapisy.fxml'.";

    }

}
