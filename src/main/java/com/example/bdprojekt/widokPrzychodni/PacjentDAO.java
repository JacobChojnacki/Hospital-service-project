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
            pacjent.setNazwisko(resultSet.getString("nazwisko"));
            pacjent.setPesel(resultSet.getString("pesel"));
            pacjent.setNumer_telefonu(resultSet.getString("numer_telefonu"));
            pacjent.setTyp(resultSet.getString("ID_szcz"));
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
    public ObservableList<Pacjent> szukajPacjenta(String n,String x, String y) throws SQLException, ClassNotFoundException{
        String selectStmt = "SELECT * FROM punkt_szczepien.widokpunktu WHERE pesel LIKE '%" + n + "%';";

        try{
            ResultSet resultSet = dbUtill.dbExecuteQuery(selectStmt);
            ObservableList<Pacjent> pacjenci = pacjenciLista(resultSet);
            return pacjenci;
        }catch (SQLException e) {
            throw e;
        }
    }
    public void realizacjaPacjenta(String termin, String ID_szcz, String pesel) throws SQLException, ClassNotFoundException {
        StringBuilder sb = new StringBuilder("UPDATE punkt_szczepien.wizyty set zrealizowano = 'tak' WHERE pesel = ");
        sb.append(pesel);
        sb.append(" and termin = '");
        sb.append(termin);
        sb.append("' and ID_szcz = '");
        sb.append(ID_szcz);
        sb.append("';");
        String insertStmt = sb.toString();

        try {
            dbUtill.dbExecuteUpdate(insertStmt);
        }catch (SQLException e) {
            throw e;
        }
    }
}
