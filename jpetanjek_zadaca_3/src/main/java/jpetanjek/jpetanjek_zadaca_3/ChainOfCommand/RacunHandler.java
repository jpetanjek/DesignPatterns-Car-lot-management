/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.ChainOfCommand;

import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Osoba;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Racun;

/**
 *
 * @author Joc
 */
public abstract class RacunHandler {
    public static int EVIDENCIJA=1;
    public static int OSOBE=2;
    public static int LOKACIJA=3;
    
    protected int level;
    
    protected RacunHandler nextLogger;
    
    public void setNextLogger(RacunHandler nextLogger){
        this.nextLogger=nextLogger;
    }
    
    public RacunResponse izvedi(int level, RacunRequest nesto){
        if(this.level==level){
           return protokol(nesto);
        }
        if(nextLogger!= null){
            return nextLogger.izvedi(level, nesto);
        }
        return null;
    }
    
    abstract protected RacunResponse protokol(RacunRequest nesto);
    
}
