package com.example.bdprojekt.widokPacjenta;

import com.example.bdprojekt.Connector.DbUtill;
import com.example.bdprojekt.models.PacjentWidok;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PacjentWidokDAO {
    private DbUtill dbUtill;

    public PacjentWidokDAO(DbUtill dbUtill) {
        this.dbUtill = dbUtill;
    }
    private ObservableList<PacjentWidok> pacjentWidokLista(ResultSet resultSet) throws SQLException{
        ObservableList<PacjentWidok> danePacjenta = FXCollections.observableArrayList();

        while(resultSet.next()){
            PacjentWidok pacjentWidok = new PacjentWidok();
            pacjentWidok.setChoroba(resultSet.getString("choroba"));
            pacjentWidok.setProducent(resultSet.getString("producent"));
            pacjentWidok.setTermin(resultSet.getString("termin"));
            pacjentWidok.setGodzina(resultSet.getString("godzina"));
            pacjentWidok.setZrealizowano(resultSet.getString("zrealizowano"));
            danePacjenta.add(pacjentWidok);
        }
        return danePacjenta;
    }
    public ObservableList<PacjentWidok> pokazDanePacjenta() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM punkt_szczepien.widokpacjenta;";

        try {
            ResultSet resultSet = dbUtill.dbExecuteQuery(selectStmt);
            ObservableList<PacjentWidok> danePacjenta = pacjentWidokLista(resultSet);
            return danePacjenta;
        } catch (SQLException e) {
            throw e;
        }
    }
    public ObservableList<PacjentWidok> pokazDanePacjentaKonkretnego(String n) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM punkt_szczepien.widokpacjenta WHERE pesel like '%" + n + "%';";

        try {
            ResultSet resultSet = dbUtill.dbExecuteQuery(selectStmt);
            ObservableList<PacjentWidok> daneKontretnegoPacjenta = pacjentWidokLista(resultSet);
            return daneKontretnegoPacjenta;
        } catch (SQLException e) {
            throw e;
        }
    }
}
