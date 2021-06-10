/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.MVC;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpetanjek.jpetanjek_zadaca_3.Singleton.Tvrtka;

/**
 *
 * @author Joc
 */
public class IzlazPogled implements Pogled {
    @Override
    public void ispis(String unos){
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(Tvrtka.getInstanca().getDatotekaIzlaz(),true),true);
            printWriter.write(unos+System.getProperty("line.separator"));
            printWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(IzlazPogled.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String toString() {
        return "Izlaz Pogled";
    }

    @Override
    public void ispisError(String error) {
        ispis(error);
        String linijaGreske="";
        for (int i = 0; i < error.length(); i++) {
            linijaGreske+="x";
        }
        ispis(linijaGreske);
    }
    
}
