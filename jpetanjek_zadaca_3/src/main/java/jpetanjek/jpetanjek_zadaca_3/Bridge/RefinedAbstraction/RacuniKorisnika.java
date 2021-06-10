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
import jpetanjek.jpetanjek_zadaca_3.Decorator.ConcreteDecorator.OsobaConcreteDecorator;
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
public class RacuniKorisnika extends Aktivnost implements IAktivnost {

    private int id_korisnika;
    private Date datumOd;
    private Date datumDo;

    public int getId_korisnika() {
        return id_korisnika;
    }

    public void setId_korisnika(int id_korisnika) {
        this.id_korisnika = id_korisnika;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    @Override
    public void izvedi() throws Exception {
        super.izvedi();

        Osoba osoba = Tvrtka.getInstanca().getOsobaById(id_korisnika);
        if (osoba != null) {
            RacunRequest request = new RacunRequest();
            request.setNesto(id_korisnika);
            List<Racun> racuniKorisnika = Tvrtka.getInstanca().getRacunHandler().izvedi(2, request).getListaRacuna();
            List<Racun> filtrirani = new ArrayList();
            if (racuniKorisnika.size() > 0) {

                for (Racun racun : racuniKorisnika) {
                    if ((racun.getDatumVracanja().after(datumOd) || racun.getDatumVracanja().equals(datumOd)) && (racun.getDatumVracanja().before(datumDo) || racun.getDatumVracanja().equals(datumOd))) {
                        filtrirani.add(racun);
                    }
                }
                if (filtrirani.size() > 0) {
                    Tvrtka.getInstanca().getController().setModel(String.format("|%-" + Tvrtka.getInstanca().getDt() + "s|",
                            FormatiranjeTablica.formatiraj("RACUNI KORISNIKA")));
                    
                    Tvrtka.getInstanca().getController().setModel(String.format(
                    FormatiranjeTablica.formatirajNaslovInt("Id racuna"))+(
                    FormatiranjeTablica.formatirajNaslovDouble("Iznos"))+(
                    FormatiranjeTablica.formatiraj("Datum"))+(
                    FormatiranjeTablica.formatiraj("Placeni"))+(
                    FormatiranjeTablica.formatirajNaslovInt("Id vrste vozila"))+(
                    FormatiranjeTablica.formatirajNaslovInt("Id lokacije najma"))
                    );
                    
                    for (Racun racun : filtrirani) {
                        if (!racun.isPlaceni()) {
                            RacunConcreteDecorator printer = new RacunConcreteDecorator(racun);
                            printer.printStruktura();
                        }
                    }
                    for (Racun racun : filtrirani) {
                        if (racun.isPlaceni()) {
                            RacunConcreteDecorator printer = new RacunConcreteDecorator(racun);
                            printer.printStruktura();
                        }
                    }
                } else {
                    throw new Exception("Nema racuna u ovom datumskom razdoblju");
                }
            } else {
                throw new Exception("Ovaj korisnik nema racuna");
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

}
