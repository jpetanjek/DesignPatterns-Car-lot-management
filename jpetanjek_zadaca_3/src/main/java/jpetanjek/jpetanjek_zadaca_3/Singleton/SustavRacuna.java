/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.Singleton;

import jpetanjek.jpetanjek_zadaca_3.Facade.Pronalazitelj;
import java.util.ArrayList;
import java.util.List;
import jpetanjek.jpetanjek_zadaca_3.ChainOfCommand.RacunRequest;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Lokacija;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Racun;

/**
 *
 * @author Joc
 */
public class SustavRacuna implements Pronalazitelj<List<Racun>>{
    List<Racun> listaSvihRacuna = new ArrayList<>();
    int idRacuna=0;

    public List<Racun> getListaSvihRacuna() {
        return listaSvihRacuna;
    }

    public void setListaSvihRacuna(List<Racun> listaSvihRacuna) {
        this.listaSvihRacuna = listaSvihRacuna;
    }
    
    public void dodajRacun(Racun racun){
        racun.setId(idRacuna++);
        
        //Zovi CHAIN!!!!! umjesto add
        RacunRequest request= new RacunRequest();
        request.setRacun(racun);
        Tvrtka.getInstanca().getRacunHandler().izvedi(1,request);
        //listaSvihRacuna.add(racun);
        //System.out.println("!!!!DODAN RACUN!!! "+ racun.getIdLokacijaNajma() + " " + racun.getTekst());
    }
    
    private static volatile SustavRacuna Instanca;

    private SustavRacuna() {
    }
    
    public static SustavRacuna getInstanca() {
        if (Instanca == null) {
            synchronized ((SustavRacuna.class)) {
                Instanca = new SustavRacuna();
            }
        }
        return Instanca;
    }

    public static void setInstanca(SustavRacuna Instanca) {
        SustavRacuna.Instanca = Instanca;
    }

    @Override
    public List<Racun> pronadi(int idLokacije) {
        //ZOVI CHAIN!!!
        List<Racun> returnme = new ArrayList<>();
        RacunRequest request= new RacunRequest();
        request.setNesto(idLokacije);
        returnme=Tvrtka.getInstanca().getRacunHandler().izvedi(3,request).getListaRacuna();
        return returnme;
        /*
        List<Racun> returnme = new ArrayList<>();
        for (Racun racun : listaSvihRacuna) {
            //System.out.println("Lokacija najma na racunu: " + racun.getIdLokacijaNajma() + " trazi se: " + idLokacije);
            if(racun.getIdLokacijaNajma()==idLokacije){
                //System.out.println("vracam: " + racun.getId());
                returnme.add(racun);
            }
        }
        return returnme;
        */
    }
    
    
 
    
}
