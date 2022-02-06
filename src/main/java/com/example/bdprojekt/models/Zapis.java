package com.example.bdprojekt.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Zapis {
    StringProperty choroba;
    StringProperty producent;
    StringProperty godzina;
    StringProperty termin;

    public Zapis() {
        this.choroba = new SimpleStringProperty();
        this.producent = new SimpleStringProperty();
        this.godzina = new SimpleStringProperty();
        this.termin = new SimpleStringProperty();
    }

    public String getTermin() {
        return termin.get();
    }

    public StringProperty terminProperty() {
        return termin;
    }

    public void setTermin(String termin) {
        this.termin.set(termin);
    }

    public String getChoroba() {
        return choroba.get();
    }

    public StringProperty chorobaProperty() {
        return choroba;
    }

    public void setChoroba(String choroba) {
        this.choroba.set(choroba);
    }

    public String getProducent() {
        return producent.get();
    }

    public StringProperty producentProperty() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent.set(producent);
    }

    public String getGodzina() {
        return godzina.get();
    }

    public StringProperty godzinaProperty() {
        return godzina;
    }

    public void setGodzina(String godzina) {
        this.godzina.set(godzina);
    }
}
