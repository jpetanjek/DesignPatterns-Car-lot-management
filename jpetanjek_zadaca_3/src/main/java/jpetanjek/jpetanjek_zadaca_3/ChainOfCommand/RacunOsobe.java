/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.ChainOfCommand;

import java.util.ArrayList;
import java.util.List;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Racun;
import jpetanjek.jpetanjek_zadaca_3.Singleton.SustavRacuna;

/**
 *
 * @author Joc
 */
public class RacunOsobe extends RacunHandler {

    public RacunOsobe(int level) {
        this.level=level;
    }
    
    @Override
    protected RacunResponse protokol(RacunRequest nesto) {
        RacunResponse returnme = new RacunResponse();
        returnme.setListaRacuna(new ArrayList<>());
        for (Racun racun : SustavRacuna.getInstanca().getListaSvihRacuna()) {
            //System.out.println("Lokacija najma na racunu: " + racun.getIdLokacijaNajma() + " trazi se: " + idLokacije);
            if(racun.getOsoba().getId()==nesto.getNesto()){
                //System.out.println("vracam: " + racun.getId());
                List<Racun> dodaj= returnme.getListaRacuna();
                dodaj.add(racun);
                returnme.setListaRacuna(dodaj);
            }
        }
        
        return returnme;
    }
    
}
