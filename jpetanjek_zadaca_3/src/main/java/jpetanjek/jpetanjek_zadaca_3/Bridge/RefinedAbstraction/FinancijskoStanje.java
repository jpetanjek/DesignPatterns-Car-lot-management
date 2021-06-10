/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.Bridge.RefinedAbstraction;

import java.util.ArrayList;
import java.util.List;
import jpetanjek.jpetanjek_zadaca_3.Bridge.Implementor.IAktivnost;
import jpetanjek.jpetanjek_zadaca_3.ChainOfCommand.RacunRequest;
import jpetanjek.jpetanjek_zadaca_3.Decorator.ConcreteDecorator.CompositeConcreteDecorator;
import jpetanjek.jpetanjek_zadaca_3.Decorator.ConcreteDecorator.OsobaConcreteDecorator;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Aktivnost;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.FormatiranjeTablica;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Osoba;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Racun;
import jpetanjek.jpetanjek_zadaca_3.Singleton.SustavRacuna;
import jpetanjek.jpetanjek_zadaca_3.Singleton.Tvrtka;

/**
 *
 * @author Joc
 */
public class FinancijskoStanje extends Aktivnost implements IAktivnost{
    
    @Override
    public void izvedi() throws Exception{
        super.izvedi();
        Tvrtka.getInstanca().getController().setModel(String.format("|%-"+Tvrtka.getInstanca().getDt()+"s|",
                FormatiranjeTablica.formatiraj("ISPIS FINANCIJSKO STANJE")));
        
        Tvrtka.getInstanca().getController().setModel(String.format(
                    FormatiranjeTablica.formatirajNaslovInt("Id osobe"))+(
                    FormatiranjeTablica.formatiraj("Ime i prezime"))+(
                    FormatiranjeTablica.formatirajNaslovDouble("Dugovanje"))+(
                    FormatiranjeTablica.formatiraj("Datum zadnjeg najma")));
        
        for (Osoba osoba : Tvrtka.getInstanca().getSveOsobe()) {
            if(!osoba.getZadnjiNajamVozila().equals("")){
                OsobaConcreteDecorator printer = new OsobaConcreteDecorator(osoba);
                printer.printStruktura();
            }
        }
    }
    
}
