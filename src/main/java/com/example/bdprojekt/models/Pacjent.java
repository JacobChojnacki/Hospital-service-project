package com.example.bdprojekt.models;

import javafx.beans.property.*;

public class Pacjent {
    StringProperty nazwisko;
    StringProperty pesel;
    StringProperty numer_telefonu;
    StringProperty typ;
    StringProperty termin;
    StringProperty godzina;
    StringProperty zrealizowano;

    public Pacjent() {
        this.nazwisko = new SimpleStringProperty();
        this.pesel = new SimpleStringProperty();
        this.numer_telefonu = new SimpleStringProperty();
        this.typ = new SimpleStringProperty();
        this.termin = new SimpleStringProperty();
        this.godzina = new SimpleStringProperty();
        this.zrealizowano = new SimpleStringProperty();
    }

    public String getNazwisko() {
        return nazwisko.get();
    }

    public StringProperty nazwiskoProperty() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko.set(nazwisko);
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

    public String getNumer_telefonu() {
        return numer_telefonu.get();
    }

    public StringProperty numer_telefonuProperty() {
        return numer_telefonu;
    }

    public void setNumer_telefonu(String numer_telefonu) {
        this.numer_telefonu.set(numer_telefonu);
    }

    public String getTyp() {
        return typ.get();
    }

    public StringProperty typProperty() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ.set(typ);
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
