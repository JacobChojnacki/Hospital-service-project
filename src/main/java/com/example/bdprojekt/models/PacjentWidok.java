package com.example.bdprojekt.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PacjentWidok {
    private StringProperty pesel;
    private StringProperty typSzczepienia;
    private StringProperty producent;
    private StringProperty termin;
    private StringProperty godzina;
    private StringProperty zrealizowano;

    public PacjentWidok(StringProperty pesel) {
        this.pesel = new SimpleStringProperty();
        this.typSzczepienia = new SimpleStringProperty();
        this.producent = new SimpleStringProperty();
        this.termin = new SimpleStringProperty();
        this.godzina = new SimpleStringProperty();
        this.zrealizowano = new SimpleStringProperty();
    }

    public String getPesel() {
        return pesel.get();
    }

    public StringProperty peselProperty() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel.set(pesel);
    }

    public String getTypSzczepienia() {
        return typSzczepienia.get();
    }

    public StringProperty typSzczepieniaProperty() {
        return typSzczepienia;
    }

    public void setTypSzczepienia(String typSzczepienia) {
        this.typSzczepienia.set(typSzczepienia);
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

    public String getTermin() {
        return termin.get();
    }

    public StringProperty terminProperty() {
        return termin;
    }

    public void setTermin(String termin) {
        this.termin.set(termin);
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

    public String getZrealizowano() {
        return zrealizowano.get();
    }

    public StringProperty zrealizowanoProperty() {
        return zrealizowano;
    }

    public void setZrealizowano(String zrealizowano) {
        this.zrealizowano.set(zrealizowano);
    }
}
