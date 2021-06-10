package jpetanjek.jpetanjek_zadaca_3.Bridge.RefinedAbstraction;

import jpetanjek.jpetanjek_zadaca_3.Bridge.Implementor.IAktivnost;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Aktivnost;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Lokacija;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Lokacije_kapaciteti;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Osoba;
import jpetanjek.jpetanjek_zadaca_3.Singleton.Tvrtka;
import jpetanjek.jpetanjek_zadaca_3.Singleton.VirtualnoVrijeme;

/**
 *
 * @author Josip Petanjek
 */
public class PregledRaspolozivihVozila extends Aktivnost implements IAktivnost {

    private int id_korisnika;
    private int id_lokacije;
    private int id_vrste_vozila;

    @Override
    public void izvedi() throws Exception {
        super.izvedi();
        Osoba osoba = Tvrtka.getInstanca().getOsobaById(id_korisnika);
        Lokacija lokacija = Tvrtka.getInstanca().getLokacijaById(id_lokacije);

        if (VirtualnoVrijeme.ispravnost(vrijeme)) {
            if (lokacija != null) {
                if (osoba != null) {
                    if (Tvrtka.getInstanca().getVoziloById(id_vrste_vozila) != null) {
                        String staroVrijeme = VirtualnoVrijeme.getInstanca().getVrijeme();
                        VirtualnoVrijeme.getInstanca().setVrijeme(this.vrijeme);
                        Lokacije_kapaciteti kapacitet = Tvrtka.getInstanca().getKapacitetByVoziloLokacija(id_vrste_vozila, lokacija.getId());
                        if (kapacitet != null) {

                            Tvrtka.getInstanca().getController().setModel("U " + vrijeme + " korisnik " + osoba.getImePrezime()
                                    + " traži na lokaciji " + lokacija.getNaziv() + " broj (" + kapacitet.getRaspolozivi() + ") "
                                    + "raspoloživih " + Tvrtka.getInstanca().getVoziloById(id_vrste_vozila).getNaziv() + "."
                            );

                        } else {
                            VirtualnoVrijeme.getInstanca().reverseVrijeme(staroVrijeme);
                            throw new Exception("Ne postoji ovakvo vozilo na ovoj lokacije.");
                        }
                    } else {
                        throw new Exception("Ne postoji vozilo sa ovim id.");
                    }
                } else {
                    throw new Exception("Ne postoji osoba sa ovim id.");
                }
            } else {
                throw new Exception("Ne postoji lokacija sa ovim id.");
            }
        } else {
            throw new Exception("Vrijeme akcije je prije trenutnog virtualnog vremena, nije moguće provesti akciju.");
        }

    }

    public int getId_korisnika() {
        return id_korisnika;
    }

    public void setId_korisnika(int id_korisnika) {
        this.id_korisnika = id_korisnika;
    }

    public int getId_lokacije() {
        return id_lokacije;
    }

    public void setId_lokacije(int id_lokacije) {
        this.id_lokacije = id_lokacije;
    }

    public int getId_vrste_vozila() {
        return id_vrste_vozila;
    }

    public void setId_vrste_vozila(int id_vrste_vozila) {
        this.id_vrste_vozila = id_vrste_vozila;
    }

}
