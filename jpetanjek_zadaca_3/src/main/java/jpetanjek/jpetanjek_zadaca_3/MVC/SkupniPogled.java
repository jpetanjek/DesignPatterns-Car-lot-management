/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.MVC;

/**
 *
 * @author Joc
 */
public class SkupniPogled implements Pogled {
    @Override
    public void ispis(String unos){
        System.out.println(unos);
    }

    @Override
    public String toString() {
        return "Skupni Pogled";
    }

    @Override
    public void ispisError(String error) {
        ispis("\033[0;31m"+error);
    }
    
}