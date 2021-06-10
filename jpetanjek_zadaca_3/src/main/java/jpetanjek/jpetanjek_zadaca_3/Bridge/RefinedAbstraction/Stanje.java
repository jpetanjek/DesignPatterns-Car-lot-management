/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.Bridge.RefinedAbstraction;

import jpetanjek.jpetanjek_zadaca_3.Composite.OrganizacijskaJedinica;
import jpetanjek.jpetanjek_zadaca_3.Composite.OrganizacijskaJedinicaComposite;
import jpetanjek.jpetanjek_zadaca_3.Iterator.Iterator;
import jpetanjek.jpetanjek_zadaca_3.Iterator.IteratorRepository;
import jpetanjek.jpetanjek_zadaca_3.Bridge.Implementor.IAktivnost;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.FormatiranjeTablica;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Aktivnost;
import jpetanjek.jpetanjek_zadaca_3.Singleton.Tvrtka;

/**
 *
 * @author Joc
 */
public class Stanje extends Aktivnost implements IAktivnost {

    private boolean struktura;

    public boolean isStruktura() {
        return struktura;
    }

    public void setStruktura(boolean struktura) {
        this.struktura = struktura;
    }

    public boolean isStanje() {
        return stanje;
    }

    public void setStanje(boolean stanje) {
        this.stanje = stanje;
    }
    private boolean stanje;

    @Override
    public void izvedi() throws Exception {
        super.izvedi();

        if (id==-1||Tvrtka.getInstanca().daLiPostojiOrganizacijskaJedinica(id)) {
            if (struktura && !stanje) {
                Tvrtka.getInstanca().getController().setModel(String.format("|%-" + Tvrtka.getInstanca().getDt() + "s|",
                        FormatiranjeTablica.formatiraj("ISPIS STRUKTURE")));
                IteratorRepository ir2 = new IteratorRepository(Tvrtka.getInstanca().getIzvornaOrganizacijskaJedinica());
                Iterator i2 = ir2.getIterator();
                if (id == -1) {
                    while (i2.hasNext()) {
                        OrganizacijskaJedinicaComposite oi = (OrganizacijskaJedinicaComposite) i2.getNext();
                        oi.printStruktura();
                    }
                } else {
                    ir2 = new IteratorRepository(Tvrtka.getInstanca().getIzvornaOrganizacijskaJedinica());
                    i2 = ir2.getIterator();
                    while (i2.hasNext()) {
                        OrganizacijskaJedinicaComposite oi2 = (OrganizacijskaJedinicaComposite) i2.getNext();
                        if (oi2.getId() == id) {
                            IteratorRepository ir3 = new IteratorRepository(oi2);
                            Iterator i3 = ir3.getIterator();
                            while (i3.hasNext()) {
                                OrganizacijskaJedinicaComposite oi3 = (OrganizacijskaJedinicaComposite) i3.getNext();
                                oi3.printStruktura();
                            }
                        }

                    }
                }
            }

            if (stanje) {
                Tvrtka.getInstanca().getController().setModel(String.format("|%-" + Tvrtka.getInstanca().getDt() + "s|",
                        FormatiranjeTablica.formatiraj("ISPIS STANJA")));
                IteratorRepository ir2 = new IteratorRepository(Tvrtka.getInstanca().getIzvornaOrganizacijskaJedinica());
                Iterator i2 = ir2.getIterator();
                if (id == -1) {
                    while (i2.hasNext()) {
                        OrganizacijskaJedinicaComposite oi = (OrganizacijskaJedinicaComposite) i2.getNext();
                        oi.printLokacije();
                    }
                } else {
                    ir2 = new IteratorRepository(Tvrtka.getInstanca().getIzvornaOrganizacijskaJedinica());
                    i2 = ir2.getIterator();
                    while (i2.hasNext()) {
                        OrganizacijskaJedinicaComposite oi2 = (OrganizacijskaJedinicaComposite) i2.getNext();
                        if (oi2.getId() == id) {
                            IteratorRepository ir3 = new IteratorRepository(oi2);
                            Iterator i3 = ir3.getIterator();
                            //oi2.printLokacije();
                            while (i3.hasNext()) {
                                OrganizacijskaJedinicaComposite oi3 = (OrganizacijskaJedinicaComposite) i3.getNext();
                                oi3.printLokacije();
                            }
                            break;
                        }
                    }
                }
            }
        } else {
            throw new Exception("Ne postoji organizacijska jedinica sa ovim id.");
        }

    }
}
