package com.example.bdprojekt.widokPacjenta;

import java.sql.Time;
import java.util.Date;

public class PacjentWidok {
    private String typSzczepienia;
    private String producent;
    private Date termin;
    private Time godzina;
    private String zrealizowano;

    public PacjentWidok(String typSzczepienia, String producent, Date termin, Time godzina, String zrealizowano) {
        this.typSzczepienia = typSzczepienia;
        this.producent = producent;
        this.termin = termin;
        this.godzina = godzina;
        this.zrealizowano = zrealizowano;
    }
}
