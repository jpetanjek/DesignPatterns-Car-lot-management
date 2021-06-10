/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.Decorator;

import java.util.Date;
import jpetanjek.jpetanjek_zadaca_3.Decorator.Component.CompositeInterface;
import jpetanjek.jpetanjek_zadaca_3.Decorator.Component.CompositeInterface;

/**
 *
 * @author Joc
 */
public abstract class CompositeDecorator implements CompositeInterface {

    @Override
    public void printStruktura() {
    }

    ;
    
    @Override
    public void printLokacije() {
    }

    ;
    
    @Override
    public void printNajam(Date dateOd, Date dateDo) {
    }

    ;
    
    @Override
    public void printZarada(Date dateOd, Date dateDo) {
    }

    ;
    @Override
    public  void printNajamZarada(Date datumOd, Date datumDo){};

    @Override
    public  void printRacun(Date datumOd, Date datumDo){};

}
