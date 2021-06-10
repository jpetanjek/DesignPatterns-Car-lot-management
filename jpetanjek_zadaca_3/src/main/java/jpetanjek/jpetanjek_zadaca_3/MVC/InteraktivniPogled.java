/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.MVC;

import java.io.PrintStream;

/**
 *
 * @author Joc
 */
public class InteraktivniPogled implements Pogled {
    @Override
    public void ispis(String unos){
        System.out.println(unos);
    }
    
    @Override
    public String toString() {
        return "Interaktivni Pogled";
    }

    @Override
    public void ispisError(String error) {
        ispis("\033[0;31m"+error);
    }
    
}
