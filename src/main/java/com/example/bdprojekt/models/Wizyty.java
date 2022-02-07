package com.example.bdprojekt.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Wizyty extends Szczepionka {

    private StringProperty ID_szcz;
    private StringProperty termin;
    private StringProperty godzina;


    public Wizyty() {
        this.ID_szcz = new SimpleStringProperty();
        this.termin = new SimpleStringProperty();
        this.godzina = new SimpleStringProperty();
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

    @Override
    public String getID_szcz() {
        return ID_szcz.get();
    }

    @Override
    public StringProperty ID_szczProperty() {
        return ID_szcz;
    }

    public void setID_szcz(String ID_szcz) {
        this.ID_szcz.set(ID_szcz);
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
