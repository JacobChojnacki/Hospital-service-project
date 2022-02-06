package com.example.bdprojekt.szczepienia;

import com.example.bdprojekt.Connector.DbUtill;
import com.example.bdprojekt.models.Szczepionka;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SzczepionkaDAO {
    private DbUtill dbUtill;

    public SzczepionkaDAO(DbUtill dbUtill) {
        this.dbUtill = dbUtill;
    }
    private ObservableList<Szczepionka> szczepionkiLista(ResultSet resultSet) throws SQLException{
        ObservableList<Szczepionka> szczepionki = FXCollections.observableArrayList();

        while (resultSet.next()){
            Szczepionka szczepionka = new Szczepionka();
            szczepionka.setID_szcz(resultSet.getString("ID_szcz"));
            szczepionka.setChoroba(resultSet.getString("Choroba"));
            szczepionka.setProducent(resultSet.getString("Producent"));
            szczepionki.add(szczepionka);
        }
        return szczepionki;
    }
    public ObservableList<Szczepionka> szukajSzczepionki(String n) throws SQLException, ClassNotFoundException{

        String selectStmt = "SELECT * FROM punkt_szczepien.szczepienia WHERE choroba LIKE '%" + n + "%';";

        try {
            ResultSet resultSet = dbUtill.dbExecuteQuery(selectStmt);
            ObservableList<Szczepionka> szczepionki = szczepionkiLista(resultSet);
            return szczepionki;
        } catch (SQLException e) {
            throw e;
        }
    }
    public ObservableList<Szczepionka> pokazSzczepionki() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM punkt_szczepien.szczepienia;";

        try {
            ResultSet resultSet = dbUtill.dbExecuteQuery(selectStmt);
            ObservableList<Szczepionka> szczepionki = szczepionkiLista(resultSet);
            return szczepionki;
        }catch (SQLException e) {
            throw e;
        }
    }
    public void dodajSzczepionke(String choroba,String producent) throws SQLException,ClassNotFoundException {
        StringBuilder sb = new StringBuilder("INSERT INTO punkt_szczepien.szczepienia(choroba, producent) VALUES('");
        sb.append(choroba);
        sb.append("','");
        sb.append(producent);
        sb.append("');");
        String insertStmt = sb.toString();

        try {
            dbUtill.dbExecuteUpdate(insertStmt);
        }catch (SQLException e) {
            throw e;
        }
    }
}
