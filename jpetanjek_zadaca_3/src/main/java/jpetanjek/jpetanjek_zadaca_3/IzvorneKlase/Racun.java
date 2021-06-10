/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.IzvorneKlase;

import java.util.Date;

/**
 *
 * @author Joc
 */
public class Racun {
    int id;
    int idLokacijaNajma;
    int idLokacijaVracanja;
    int idVrsteVozila;
    Date datumNajma;
    Date datumVracanja;
    double zarada;
    String tekst;
    Osoba osoba;
    boolean placeni;

    public boolean isPlaceni() {
        return placeni;
    }

    public void setPlaceni(boolean placeni) {
        this.placeni = placeni;
    }

    public Date getDatumNajma() {
        return datumNajma;
    }

    public void setDatumNajma(Date datumNajma) {
        this.datumNajma = datumNajma;
    }

    public Date getDatumVracanja() {
        return datumVracanja;
    }

    public void setDatumVracanja(Date datumVracanja) {
        this.datumVracanja = datumVracanja;
    }
    

    public int getIdLokacijaNajma() {
        return idLokacijaNajma;
    }

    public void setIdLokacijaNajma(int idLokacijaNajma) {
        this.idLokacijaNajma = idLokacijaNajma;
    }

    public int getIdLokacijaVracanja() {
        return idLokacijaVracanja;
    }

    public void setIdLokacijaVracanja(int idLokacijaVracanja) {
        this.idLokacijaVracanja = idLokacijaVracanja;
    }

    public int getIdVrsteVozila() {
        return idVrsteVozila;
    }

    public void setIdVrsteVozila(int idVrsteVozila) {
        this.idVrsteVozila = idVrsteVozila;
    }

    

    public double getZarada() {
        return zarada;
    }

    public void setZarada(double zarada) {
        this.zarada = zarada;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
