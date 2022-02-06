package com.example.bdprojekt.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PacjentWidok {
    private StringProperty typSzczepienia;
    private StringProperty producent;
    private StringProperty termin;
    private StringProperty godzina;
    private StringProperty zrealizowano;

    public PacjentWidok() {
        this.typSzczepienia = new SimpleStringProperty();
        this.producent = new SimpleStringProperty();
        this.termin = new SimpleStringProperty();
        this.godzina = new SimpleStringProperty();
        this.zrealizowano = new SimpleStringProperty();
    }
}
