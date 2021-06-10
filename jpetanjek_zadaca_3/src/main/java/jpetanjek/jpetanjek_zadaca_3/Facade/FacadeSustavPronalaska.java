/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.Facade;

import java.util.List;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Lokacija;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Racun;
import jpetanjek.jpetanjek_zadaca_3.Singleton.SustavRacuna;
import jpetanjek.jpetanjek_zadaca_3.Singleton.Tvrtka;

/**
 *
 * @author Joc
 */
public class FacadeSustavPronalaska {
    private static volatile FacadeSustavPronalaska Instanca;

    private FacadeSustavPronalaska() {
    }
    
    public static FacadeSustavPronalaska getInstanca() {
        if (Instanca == null) {
            synchronized ((FacadeSustavPronalaska.class)) {
                Instanca = new FacadeSustavPronalaska();
            }
        }
        return Instanca;
    }

    public static void setInstanca(FacadeSustavPronalaska Instanca) {
        FacadeSustavPronalaska.Instanca = Instanca;
    }
    
    public static List<Racun> PronadiRacuneLokacije(int idLokacije){
        return SustavRacuna.getInstanca().pronadi(idLokacije);
    }
    
    public static Lokacija PronadiLokacijuRacuna(int idLokacije){
        return Tvrtka.getInstanca().pronadi(idLokacije);
    }
}
