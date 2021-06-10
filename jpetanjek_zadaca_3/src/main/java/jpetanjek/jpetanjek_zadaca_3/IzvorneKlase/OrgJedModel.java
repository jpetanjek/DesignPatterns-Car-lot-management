/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.IzvorneKlase;

import java.util.List;

/**
 *
 * @author Joc
 */
public class OrgJedModel {
    int id;
    String naziv;
    int nadredeni;
    List<Integer> lokacije;
    
    boolean izvorni;

    public boolean isIzvorni() {
        return izvorni;
    }

    public void setIzvorni(boolean izvorni) {
        this.izvorni = izvorni;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getNadredeni() {
        return nadredeni;
    }

    public void setNadredeni(int nadredeni) {
        this.nadredeni = nadredeni;
    }

    public List<Integer> getLokacije() {
        return lokacije;
    }

    public void setLokacije(List<Integer> lokacije) {
        this.lokacije = lokacije;
    }
}
