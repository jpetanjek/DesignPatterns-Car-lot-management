package jpetanjek.jpetanjek_zadaca_3.Bridge.RefinedAbstraction;

import jpetanjek.jpetanjek_zadaca_3.Bridge.Implementor.IAktivnost;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Aktivnost;
import jpetanjek.jpetanjek_zadaca_3.Singleton.Tvrtka;
import jpetanjek.jpetanjek_zadaca_3.Singleton.VirtualnoVrijeme;

/**
 *
 * @author Josip Petanjek
 */
public class KrajPrograma extends Aktivnost implements IAktivnost {

    @Override
    public void izvedi() throws Exception {
        super.izvedi();
        if (VirtualnoVrijeme.ispravnost(vrijeme)) {
            Tvrtka.getInstanca().getController().setModel("U " + this.vrijeme + " program završava s radom.");
            VirtualnoVrijeme.setVrijeme(this.vrijeme);
            System.exit(0);
        } else {
            throw new Exception("Vrijeme akcije je prije trenutnog virtualnog vremena, nije moguće provesti akciju.");
        }

    }

}
