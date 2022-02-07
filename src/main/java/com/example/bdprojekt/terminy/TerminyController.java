package com.example.bdprojekt.terminy;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.bdprojekt.Connector.DbUtill;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TerminyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button anulujButton;

    @FXML
    private TextField godzinaTerminy;

    @FXML
    private TextField dataTermin;

    @FXML
    private Button zatwierdzButton;

    private DbUtill dbUtill;

    @FXML
    void anulujButtonClick(ActionEvent event) throws SQLException {
        Stage stage = (Stage) anulujButton.getScene().getWindow();
        dbUtill.dbDisconnect();
        stage.close();
    }

    @FXML
    void zatwierdzButtonClick(ActionEvent event) throws SQLException, ClassNotFoundException{
        try {
            if(dataTermin.getText() != null && godzinaTerminy.getText() != null){
                dodajTermin(dataTermin.getText(),godzinaTerminy.getText());
            }
        }catch (SQLException e) {
            throw e;
        }
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        assert anulujButton != null : "fx:id=\"anulujButton\" was not injected: check your FXML file 'terminy.fxml'.";
        assert godzinaTerminy != null : "fx:id=\"godzinaTerminy\" was not injected: check your FXML file 'terminy.fxml'.";
        assert dataTermin != null : "fx:id=\"dataTermin\" was not injected: check your FXML file 'terminy.fxml'.";
        assert zatwierdzButton != null : "fx:id=\"zatwierdzButton\" was not injected: check your FXML file 'terminy.fxml'.";
        dbUtill = new DbUtill();
        dbUtill.dbConnect();
    }

    public void dodajTermin(String termin, String godzina) throws SQLException, ClassNotFoundException {
        StringBuilder sb = new StringBuilder("INSERT INTO punkt_szczepien.wizyty(termin,godzina,zrealizowano) VALUES ('");
        sb.append(termin);
        sb.append("','");
        sb.append(godzina);
        sb.append("','");
        sb.append("nie");
        sb.append("');");
        String insertStmt = sb.toString();

        try {
            dbUtill.dbExecuteUpdate(insertStmt);
        }catch (SQLException e) {
            throw e;
        }
    }
}
