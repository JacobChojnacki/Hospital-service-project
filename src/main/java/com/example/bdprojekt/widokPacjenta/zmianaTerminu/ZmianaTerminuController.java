package com.example.bdprojekt.widokPacjenta.zmianaTerminu;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.bdprojekt.Connector.DbUtill;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ZmianaTerminuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button anulujButton;

    @FXML
    private TextField godzinaEDX;

    @FXML
    private Button potwierdzButton;

    @FXML
    private TextField terminEDX;

    private DbUtill dbUtill;

    @FXML
    void anulujButtonAction(ActionEvent event) throws SQLException {
        Stage stage = (Stage) anulujButton.getScene().getWindow();
        dbUtill.dbDisconnect();
        stage.close();
    }

    @FXML
    void potwierdzButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        usunZapis(terminEDX.getText(),godzinaEDX.getText());
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        assert anulujButton != null : "fx:id=\"anulujButton\" was not injected: check your FXML file 'widokPacjentaUsunTermin.fxml'.";
        assert godzinaEDX != null : "fx:id=\"godzinaEDX\" was not injected: check your FXML file 'widokPacjentaUsunTermin.fxml'.";
        assert potwierdzButton != null : "fx:id=\"potwierdzButton\" was not injected: check your FXML file 'widokPacjentaUsunTermin.fxml'.";
        assert terminEDX != null : "fx:id=\"terminEDX\" was not injected: check your FXML file 'widokPacjentaUsunTermin.fxml'.";
        dbUtill = new DbUtill();
        dbUtill.dbConnect();
    }

    public void usunZapis(String termin, String godzina) throws SQLException, ClassNotFoundException{
        StringBuilder update = new StringBuilder("UPDATE punkt_szczepien.wizyty SET pesel = null, ID_szcz = null WHERE termin = '");
        update.append(termin);
        update.append("' and godzina = '");
        update.append(godzina);
        update.append("';");
        String insertUpdate = update.toString();

        try {
            dbUtill.dbExecuteUpdate(insertUpdate);
        }catch (SQLException e) {
            throw e;
        }

    }
}
