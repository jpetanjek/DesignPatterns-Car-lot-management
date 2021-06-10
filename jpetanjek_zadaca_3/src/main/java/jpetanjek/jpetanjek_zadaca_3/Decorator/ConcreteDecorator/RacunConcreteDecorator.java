/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.Decorator.ConcreteDecorator;

import jpetanjek.jpetanjek_zadaca_3.Decorator.CompositeDecorator;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.FormatiranjeTablica;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Osoba;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Racun;
import jpetanjek.jpetanjek_zadaca_3.Singleton.Tvrtka;

/**
 *
 * @author Joc
 */
public class RacunConcreteDecorator extends CompositeDecorator {
    
    private Racun instanca;
    
    private int dt;
    private int dc;
    private int dd;
    
    public RacunConcreteDecorator(Racun instanca) {
        this.instanca = instanca;
        dt=Tvrtka.getInstanca().getDt();
        dc=Tvrtka.getInstanca().getDc();
        dd=Tvrtka.getInstanca().getDd();
    }
    
    @Override
    public void printStruktura() {
        Tvrtka.getInstanca().getController().setModel(String.format(String.format("%" + (dc+dt+dt) + "s", "-").replace(" ", "-")));

            Tvrtka.getInstanca().getController().setModel((
                FormatiranjeTablica.formatiraj(instanca.getId()))
                +(
                FormatiranjeTablica.formatiraj(instanca.getZarada()))
                +(
                FormatiranjeTablica.formatiraj(instanca.getDatumVracanja().toString()))
                +(
                FormatiranjeTablica.formatiraj(Boolean.toString(instanca.isPlaceni())))
                +(
                FormatiranjeTablica.formatiraj(instanca.getIdVrsteVozila()))
                +(
                FormatiranjeTablica.formatiraj(instanca.getIdLokacijaNajma())
                ));
        
        
        Tvrtka.getInstanca().getController().setModel(String.format(String.format("%" + (dc+dt+dt) + "s", "-").replace(" ", "-")));
    };
}