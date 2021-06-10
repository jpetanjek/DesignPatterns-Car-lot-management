/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.Bridge.RefinedAbstraction;

import java.util.List;
import jpetanjek.jpetanjek_zadaca_3.Bridge.Implementor.IAktivnost;
import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.ConcreteCreator.AktivnostDatotekaCreator;
import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.Creator.DatotekaCreator;
import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.Product.DatotekaProduct;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Aktivnost;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Main;
import jpetanjek.jpetanjek_zadaca_3.Singleton.NacinRada;
import jpetanjek.jpetanjek_zadaca_3.Singleton.Tvrtka;
import jpetanjek.jpetanjek_zadaca_3.Singleton.VirtualnoVrijeme;

/**
 *
 * @author Joc
 */
public class Prijelaz extends Aktivnost implements IAktivnost {

    private String datoteka;
    
    @Override
    public void izvedi() throws Exception {
        super.izvedi();
        //samo aktivnosti ucitati, sve ostalo ostaviti isto
        if(NacinRada.getInstanca().getNacin().equals("interaktivno")){
            NacinRada.getInstanca().setNacin("slijedno");
            DatotekaCreator aktivnostDatotekaCreator = new AktivnostDatotekaCreator();
            DatotekaProduct aktivnostProduct = aktivnostDatotekaCreator.makeProduct();
            Tvrtka.getInstanca().setSveAktivnosti((List<Aktivnost>) aktivnostProduct.getConcreteProducts(datoteka));
            
            Main.SlijedniNacinRada();
        }else{
            throw new Exception("Niste u interaktivnom nacinu rada, mozete samo iz njega prijeci u slijedni.");
        }
    }
    
    public String getDatoteka() {
        return datoteka;
    }

    public void setDatoteka(String datoteka) {
        this.datoteka = datoteka;
    }

}