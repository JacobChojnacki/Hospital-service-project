package com.example.bdprojekt.models;

import javafx.beans.property.*;

public class Szczepionka {
    private StringProperty choroba;
    private StringProperty producent;
    private IntegerProperty ID_szcz;

    public Szczepionka() {
        this.ID_szcz = new SimpleIntegerProperty();
        this.choroba = new SimpleStringProperty();
        this.producent = new SimpleStringProperty();
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

    public int getID_szcz() {
        return ID_szcz.get();
    }

    public IntegerProperty ID_szczProperty() {
        return ID_szcz;
    }

    public void setID_szcz(int ID_szcz) {
        this.ID_szcz.set(ID_szcz);
    }
}
