package com.example.bdprojekt.statystyki;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import com.example.bdprojekt.Connector.DbUtill;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StatystykiController {

    ArrayList<String> x = new ArrayList<String>();
    ArrayList<Double> y =new ArrayList<Double>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField miesiacEDX;

    @FXML
    private Button potwierdzButton;

    @FXML
    private Button resetujButton;

    @FXML
    private BarChart<?, ?> statystykaBar;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Button zamknijButton;

    DbUtill dbUtill;

    @FXML
    void potwierdzButtonClicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        statystyka();
    }

    @FXML
    void resetujButtonClicked(ActionEvent event) {
        Collections.singleton(statystykaBar.getData().setAll());
    }

    @FXML
    void zamknijButtonClicked(ActionEvent event) throws SQLException {
        Stage stage = (Stage) zamknijButton.getScene().getWindow();
        dbUtill.dbDisconnect();
        stage.close();
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        assert miesiacEDX != null : "fx:id=\"miesiacEDX\" was not injected: check your FXML file 'statystyki.fxml'.";
        assert potwierdzButton != null : "fx:id=\"potwierdzButton\" was not injected: check your FXML file 'statystyki.fxml'.";
        assert resetujButton != null : "fx:id=\"resetujButton\" was not injected: check your FXML file 'statystyki.fxml'.";
        assert zamknijButton != null : "fx:id=\"zamknijButton\" was not injected: check your FXML file 'statystyki.fxml'.";

        dbUtill = new DbUtill();
        dbUtill.dbConnect();
    }

    public void statystyka() throws SQLException, ClassNotFoundException {
        String stmt = "SELECT ID_szcz, count(ID_szcz) as liczba FROM punkt_szczepien.zrealizowane_szczepienia GROUP BY ID_szcz;";
        ResultSet rst = dbUtill.dbExecuteQuery(stmt);


        while (rst.next()){
            x.add(rst.getString("ID_szcz"));
            y.add(rst.getDouble("liczba"));
        }
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        XYChart.Series dataSeries1 = new XYChart.Series();

        for (int i = 0; i < x.size(); i++){
            dataSeries1.getData().add(new XYChart.Data(x.get(i), y.get(i)));
        }
        statystykaBar.getData().add(dataSeries1);

    }

}
