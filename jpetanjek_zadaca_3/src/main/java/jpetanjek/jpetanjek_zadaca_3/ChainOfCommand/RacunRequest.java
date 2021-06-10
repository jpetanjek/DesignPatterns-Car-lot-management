/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.ChainOfCommand;

import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Racun;

/**
 *
 * @author Joc
 */
public class RacunRequest {
    private int nesto;
    private Racun racun;

    public int getNesto() {
        return nesto;
    }

    public void setNesto(int nesto) {
        this.nesto = nesto;
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }
    
    
}
