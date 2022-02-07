package com.example.bdprojekt.zapisy;

import com.example.bdprojekt.Connector.DbUtill;

import java.sql.SQLException;

public class WizytyDAO {
    private final DbUtill dbUtill;

    public WizytyDAO(DbUtill dbUtill) {
        this.dbUtill = dbUtill;
    }

    public void dodajZapis(String pesel, String ID_szcz, String termin, String godzina) throws SQLException, ClassNotFoundException{
        StringBuilder update = new StringBuilder("UPDATE punkt_szczepien.wizyty SET pesel = '");
        update.append(pesel);
        update.append("',");
        update.append("ID_szcz = '");
        update.append(ID_szcz);
        update.append("'WHERE termin = '");
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
