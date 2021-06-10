/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.Bridge.RefinedAbstraction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jpetanjek.jpetanjek_zadaca_3.Bridge.Implementor.IAktivnost;
import jpetanjek.jpetanjek_zadaca_3.ChainOfCommand.RacunRequest;
import jpetanjek.jpetanjek_zadaca_3.Decorator.ConcreteDecorator.RacunConcreteDecorator;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Aktivnost;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.FormatiranjeTablica;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Osoba;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Racun;
import jpetanjek.jpetanjek_zadaca_3.Singleton.Tvrtka;

/**
 *
 * @author Joc
 */
public class PlacanjeDugovanjaKorisnika extends Aktivnost implements IAktivnost {

    private int id_korisnika;
    private double iznos;

    public int getId_korisnika() {
        return id_korisnika;
    }

    public void setId_korisnika(int id_korisnika) {
        this.id_korisnika = id_korisnika;
    }

    @Override
    public void izvedi() throws Exception {
        super.izvedi();
        double inicijalniIznos = iznos;

        Osoba osoba = Tvrtka.getInstanca().getOsobaById(id_korisnika);
        if (osoba != null) {
            if (osoba.getUgovor() == 1) {

                RacunRequest request = new RacunRequest();
                request.setNesto(id_korisnika);
                List<Racun> racuniKorisnika = Tvrtka.getInstanca().getRacunHandler().izvedi(2, request).getListaRacuna();
                List<Racun> nemogucePlatiti = new ArrayList();
                List<Racun> placeni = new ArrayList();
                for (Racun racun : racuniKorisnika) {
                    if (!racun.isPlaceni()) {
                        if (racun.getZarada() <= iznos) {
                            iznos -= racun.getZarada();
                            racun.setPlaceni(true);
                            placeni.add(racun);
                            Double preostalo = osoba.getDugovanje();
                            osoba.setDugovanje(preostalo - racun.getZarada());
                        } else {
                            nemogucePlatiti.add(racun);
                        }
                    }
                }

                if (placeni.size() > 0) {
                    Tvrtka.getInstanca().getController().setModel(String.format("|%-" + Tvrtka.getInstanca().getDt() + "s|",
                            FormatiranjeTablica.formatiraj("PLACENI RACUNI")));
                    for (Racun racun : placeni) {
                        RacunConcreteDecorator printer = new RacunConcreteDecorator(racun);
                        printer.printStruktura();
                    }
                }

                String ispis = "Korisnik " + osoba.imePrezime + " je platio " + inicijalniIznos + " za svoje dugovanje. ";
                int i = 0;

                if (placeni.size() > 0) {
                    ispis += "Podmireni su ";
                    for (Racun racun : placeni) {
                        ispis += "racun " + racun.getId() + " s iznosom od " + racun.getZarada() + " kn";
                        if (i + 1 < placeni.size()) {
                            ispis += " i ";
                        }
                        i++;
                    }
                    ispis += ". ";
                }
                if (nemogucePlatiti.size() > 0) {
                    i = 0;
                    for (Racun racun : nemogucePlatiti) {
                        ispis += "Racun " + racun.getId() + " nije podmiren jer ima iznos " + racun.getZarada() + " kn";
                        if (i + 1 < nemogucePlatiti.size()) {
                            ispis += " i ";
                        }
                        i++;
                    }
                }

                if (placeni.size() > 0) {
                    i = 0;
                    ispis += ". Nakon podmirenja racuna ";
                    for (Racun racun : placeni) {
                        ispis += racun.getId();
                        if (i + 1 < placeni.size()) {
                            ispis += ",";
                        }
                        i++;
                    }
                }

                ispis += " ostalo je " + iznos + " kn za ostale racune";
                if (nemogucePlatiti.size() > 0) {
                    ispis += " , a to nije dovoljno za racun ";
                    i = 0;
                    for (Racun racun : nemogucePlatiti) {
                        ispis += racun.getId();
                        if (i + 1 < nemogucePlatiti.size()) {
                            ispis += ",";
                        }
                        i++;
                    }
                }
                ispis += ". Korisniku je vraceno " + iznos + " kn.";

                Tvrtka.getInstanca().getController().setModel(ispis);
            } else {
                throw new Exception("Osoba nema ugovor, dakle nema dugovanja");
            }
        } else {
            throw new Exception("Ne postoji osoba sa ovim id.");
        }
        //provjeri da li korisnik postoji
        //dohvati racune korisnika
        //filtriraj po datumu - oba ukljucena
        //ispis ne placenih
        //ispis placenih
        //id racuna, iznos, datum, placen/neplacen, vrsta vozila, lokacija unajmljivanja

    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

}
