/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.ChainOfCommand;

import java.util.List;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Racun;
import jpetanjek.jpetanjek_zadaca_3.Singleton.SustavRacuna;

/**
 *
 * @author Joc
 */
public class RacunEvidencija extends RacunHandler{

    public RacunEvidencija(int level) {
        this.level=level;
    }

    @Override
    protected RacunResponse protokol(RacunRequest nesto) {
        List<Racun> listaSvihRacuna = SustavRacuna.getInstanca().getListaSvihRacuna();
        //System.out.println(SustavRacuna.getInstanca().getListaSvihRacuna().size());
        listaSvihRacuna.add(nesto.getRacun());
        SustavRacuna.getInstanca().setListaSvihRacuna(listaSvihRacuna);
        //System.out.println(SustavRacuna.getInstanca().getListaSvihRacuna().size());
        //System.out.println("Dodan racun");
        return new RacunResponse();
    }
    
}
