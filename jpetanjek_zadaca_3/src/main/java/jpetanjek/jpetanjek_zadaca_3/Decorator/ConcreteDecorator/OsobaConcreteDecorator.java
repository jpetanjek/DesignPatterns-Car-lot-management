/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.Decorator.ConcreteDecorator;

import java.util.Date;
import jpetanjek.jpetanjek_zadaca_3.Composite.OrganizacijskaJedinica;
import jpetanjek.jpetanjek_zadaca_3.Composite.OrganizacijskaJedinicaComposite;
import jpetanjek.jpetanjek_zadaca_3.Decorator.CompositeDecorator;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.FormatiranjeTablica;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Osoba;
import jpetanjek.jpetanjek_zadaca_3.Singleton.Tvrtka;

/**
 *
 * @author Joc
 */
public class OsobaConcreteDecorator extends CompositeDecorator {
    
    private Osoba instanca;
    
    private int dt;
    private int dc;
    private int dd;
    
    public OsobaConcreteDecorator(Osoba instanca) {
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
                FormatiranjeTablica.formatiraj(instanca.getImePrezime()))
                +(
                FormatiranjeTablica.formatiraj(instanca.getDugovanjeSuma()))
                +(
                FormatiranjeTablica.formatiraj(instanca.getZadnjiNajamVozila())
                ));
        
        
        Tvrtka.getInstanca().getController().setModel(String.format(String.format("%" + (dc+dt+dt) + "s", "-").replace(" ", "-")));
    };
}
