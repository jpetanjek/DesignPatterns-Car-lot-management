package jpetanjek.jpetanjek_zadaca_3.Bridge.RefinedAbstraction;

import jpetanjek.jpetanjek_zadaca_3.Bridge.Implementor.IAktivnost;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Aktivnost;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Lokacija;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Lokacije_kapaciteti;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Osoba;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Vozilo;
import jpetanjek.jpetanjek_zadaca_3.Singleton.Tvrtka;
import jpetanjek.jpetanjek_zadaca_3.Singleton.VirtualnoVrijeme;
import jpetanjek.jpetanjek_zadaca_3.State.Unajmljeno;

/**
 *
 * @author Josip Petanjek
 */
public class NajamVozila extends Aktivnost implements IAktivnost {

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
                    //Provjeriti!!!!!!!!!!!
                    if ((osoba.getUgovor() == 1 && (osoba.getDugovanje() <= Tvrtka.getInstanca().getDugovanje())) || osoba.getUgovor() == 0) {
                        if (Tvrtka.getInstanca().getVoziloById(id_vrste_vozila) != null) {
                            Lokacije_kapaciteti kapacitet = Tvrtka.getInstanca().getKapacitetByVoziloLokacija(id_vrste_vozila, lokacija.getId());
                            if (kapacitet != null) {
                                if (kapacitet.raspolozivi != 0) {
                                    if (osoba.getAktivniNajam(id_vrste_vozila) == null) {
                                        String staroVrijeme = VirtualnoVrijeme.getInstanca().getVrijeme();
                                        VirtualnoVrijeme.getInstanca().setVrijeme(this.vrijeme);
                                        Vozilo vozilo = Lokacije_kapaciteti.dohvatiVoziloSaBaterijom(kapacitet.getVozila());
                                        if (vozilo != null) {

                                            Tvrtka.getInstanca().getController().setModel("U " + vrijeme + " korisnik " + osoba.getImePrezime()
                                                    + " traži na lokaciji " + lokacija.getNaziv() + " najam "
                                                    + vozilo.naziv + "."
                                            );

                                            //Postavi broj unajmljivanja
                                            //Zadaca 2 modifikacija
                                            vozilo.setBroj_unajmljivanja(vozilo.broj_unajmljivanja++);
                                            //Postavi lokaciju unajmljivanja
                                            vozilo.setIdLokacijaNajma(lokacija.getId());

                                            //Postavi vrijeme vozilu
                                            vozilo.setVrijemeUnajmljivanjaVracanja(vrijeme);

                                            //Postavi stanje vozila
                                            vozilo.setState(new Unajmljeno());

                                            //Dodaj vozilo osobi
                                            osoba.setAktivniNajam(vozilo);
                                            //DZ3 modifikacija
                                            osoba.setZadnjiNajamVozila(vrijeme);
                                            //System.out.println("Zadnji najam:  " + osoba.getZadnjiNajamVozila());
                                            //Obrisi ga sa kapaciteta i smanji za jedan
                                            kapacitet.obrisiVozilo(vozilo);
                                            //Postavi virtualno vrijeme

                                        } else {
                                            VirtualnoVrijeme.getInstanca().reverseVrijeme(staroVrijeme);
                                            throw new Exception("Nema vozila sa punom baterijom");
                                        }
                                    } else {
                                        throw new Exception("Osoba ima aktivan najam ove vrste vozila.");
                                    }
                                } else {
                                    throw new Exception("Nema raspoloživih kapaciteta.");
                                }
                            } else {
                                throw new Exception("Nije moguće unajmiti ovakvo vozilo sa ove lokacije.");
                            }
                        } else {
                            throw new Exception("Ne postoji vozilo sa ovim id.");
                        }
                    } else {
                        throw new Exception("Osoba koja ima ugovor premasila je dugovanje. " + osoba.getImePrezime() + " dugovanje je: " + osoba.getDugovanje() + " a dopusteno dugovanje je: " + Tvrtka.getInstanca().getDugovanje());
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
