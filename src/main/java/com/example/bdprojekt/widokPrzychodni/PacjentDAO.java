package com.example.bdprojekt.widokPrzychodni;

import com.example.bdprojekt.Connector.DbUtill;
import com.example.bdprojekt.models.Pacjent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PacjentDAO {
    private DbUtill dbUtill;

    public PacjentDAO(DbUtill dbUtill) {
        this.dbUtill = dbUtill;
    }
    private ObservableList<Pacjent> pacjenciLista(ResultSet resultSet) throws SQLException{
        ObservableList<Pacjent> pacjenci = FXCollections.observableArrayList();

        while (resultSet.next()){
            Pacjent pacjent = new Pacjent();
            pacjent.setID_p(Integer.parseInt(resultSet.getString("ID_p")));
            pacjent.setNazwisko(resultSet.getString("nazwisko"));
            pacjent.setPesel(resultSet.getString("pesel"));
            pacjent.setNumer_telefonu(resultSet.getString("numer_telefonu"));
//            pacjent.setTyp(resultSet.getString("typ")); zle
            pacjent.setTermin(resultSet.getString("termin"));
            pacjent.setGodzina(resultSet.getString("godzina"));
            pacjent.setZrealizowano(resultSet.getString("zrealizowano"));
            pacjenci.add(pacjent);
        }
        return pacjenci;
    }
    public ObservableList<Pacjent> pokazPacjentow() throws SQLException,ClassNotFoundException {
        String selectStmt = "SELECT * FROM punkt_szczepien.widokpunktu;";

        try {
            ResultSet resultSet = dbUtill.dbExecuteQuery(selectStmt);
            ObservableList<Pacjent> pacjenci = pacjenciLista(resultSet);
            return pacjenci;
        }catch (SQLException e) {
            throw e;
        }
    }
    public ObservableList<Pacjent> szukajPacjenta(String n) throws SQLException, ClassNotFoundException{
        String selectStmt = "SELECT * FROM punkt_szczepien.widokpunktu WHERE pesel LIKE '%" + n + "%';";

        try{
            ResultSet resultSet = dbUtill.dbExecuteQuery(selectStmt);
            ObservableList<Pacjent> pacjenci = pacjenciLista(resultSet);
            return pacjenci;
        }catch (SQLException e) {
            throw e;
        }
    }
    public void usunPacjenta(String pesel) throws SQLException, ClassNotFoundException {
        StringBuilder sb = new StringBuilder("DELETE FROM punkt_szczepien.pacjenci WHERE pesel = '");
        sb.append(pesel);
        sb.append("';");
        String insertStmt = sb.toString();

        try {
            dbUtill.dbExecuteUpdate(insertStmt);
        }catch (SQLException e) {
            throw e;
        }
    }
}
