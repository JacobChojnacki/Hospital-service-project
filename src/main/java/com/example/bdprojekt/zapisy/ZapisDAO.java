package com.example.bdprojekt.zapisy;

import com.example.bdprojekt.Connector.DbUtill;
import com.example.bdprojekt.models.Szczepionka;

import java.sql.SQLException;

public class ZapisDAO {
    private DbUtill dbUtill;

    public ZapisDAO(DbUtill dbUtill) {
        this.dbUtill = dbUtill;
    }

    public void dodajZapis(String termin, String godzina, Szczepionka choroba, Szczepionka producent) throws SQLException, ClassNotFoundException{
        StringBuilder data = new StringBuilder("INSERT INTO punkt_szczepien.wizyty(termin,godzina) VALUES('");
        StringBuilder szczepionka = new StringBuilder("INSERT INTO punkt_szczepien.szczepienia(choroba, producent) VALUES('");
        data.append(termin);
        data.append("','");
        data.append(godzina);
        data.append("');");
        String insertStmtData = data.toString();
        szczepionka.append(choroba);
        szczepionka.append("','");
        szczepionka.append(producent);
        szczepionka.append("');");
        String insertStmtSzczepionka = szczepionka.toString();
        try {
            dbUtill.dbExecuteUpdate(insertStmtData);
            dbUtill.dbExecuteUpdate(insertStmtSzczepionka);
        } catch (SQLException e){
            throw e;
        }
    }
}
