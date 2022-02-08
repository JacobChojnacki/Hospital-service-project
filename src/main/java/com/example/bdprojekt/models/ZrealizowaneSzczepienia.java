package com.example.bdprojekt.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ZrealizowaneSzczepienia {
    private StringProperty pesel;
    private StringProperty ID_szcz;
    private StringProperty ID_wizyty;
    private StringProperty termin;

    public ZrealizowaneSzczepienia() {
        this.pesel = new SimpleStringProperty();
        this.ID_szcz = new SimpleStringProperty();
        this.ID_wizyty = new SimpleStringProperty();
        this.termin = new SimpleStringProperty();
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

    public String getID_szcz() {
        return ID_szcz.get();
    }

    public StringProperty ID_szczProperty() {
        return ID_szcz;
    }

    public void setID_szcz(String ID_szcz) {
        this.ID_szcz.set(ID_szcz);
    }

    public String getID_wizyty() {
        return ID_wizyty.get();
    }

    public StringProperty ID_wizytyProperty() {
        return ID_wizyty;
    }

    public void setID_wizyty(String ID_wizyty) {
        this.ID_wizyty.set(ID_wizyty);
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
}
