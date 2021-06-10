/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.Decorator.Component;

import java.util.Date;


/**
 *
 * @author Joc
 */
public interface CompositeInterface {

    public abstract void printStruktura();
    
    public abstract void printLokacije();
    
    public abstract void printNajam(Date datumOd, Date datumDo);
    
    public abstract void printZarada(Date datumOd, Date datumDo);
    
    public abstract void printNajamZarada(Date datumOd, Date datumDo);
    
    public abstract void printRacun(Date datumOd, Date datumDo);
     

}
