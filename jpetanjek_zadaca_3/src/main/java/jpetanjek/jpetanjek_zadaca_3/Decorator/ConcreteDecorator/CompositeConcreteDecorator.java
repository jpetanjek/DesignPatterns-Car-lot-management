/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.Decorator.ConcreteDecorator;

import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.FormatiranjeTablica;
import jpetanjek.jpetanjek_zadaca_3.Composite.OrganizacijskaJedinica;
import jpetanjek.jpetanjek_zadaca_3.Composite.OrganizacijskaJedinicaComposite;
import java.util.Date;
import java.util.List;
import jpetanjek.jpetanjek_zadaca_3.Decorator.CompositeDecorator;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Lokacija;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Lokacije_kapaciteti;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Racun;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Vozilo;
import jpetanjek.jpetanjek_zadaca_3.Facade.FacadeSustavPronalaska;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Osoba;
import jpetanjek.jpetanjek_zadaca_3.Singleton.Tvrtka;

/**
 *
 * @author Joc
 */
public class CompositeConcreteDecorator extends CompositeDecorator {

    private OrganizacijskaJedinicaComposite instanca;
    private int dt;
    private int dc;
    private int dd;

    public CompositeConcreteDecorator(OrganizacijskaJedinicaComposite instanca) {
        this.instanca = instanca;
        dt=Tvrtka.getInstanca().getDt();
        dc=Tvrtka.getInstanca().getDc();
        dd=Tvrtka.getInstanca().getDd();
    }

    @Override
    public void printStruktura() {
        Tvrtka.getInstanca().getController().setModel(String.format(String.format("%" + (dc+dt+dt) + "s", "-").replace(" ", "-") ));

        
        Tvrtka.getInstanca().getController().setModel(String.format(
                FormatiranjeTablica.formatiraj(instanca.getId())
                +(
                FormatiranjeTablica.formatiraj(instanca.getNaziv()))
                +(
                FormatiranjeTablica.formatiraj(FormatiranjeTablica.getLokacijeOrganizacijeLinija((OrganizacijskaJedinica) instanca)))));
        
        
        Tvrtka.getInstanca().getController().setModel(String.format(String.format("%" + (dc+dt+dt) + "s", "-").replace(" ", "-") ));

        for (OrganizacijskaJedinica organizacijskaJedinica : instanca.listaOrgJed) {
            Tvrtka.getInstanca().getController().setModel(String.format(
                    FormatiranjeTablica.formatiraj(organizacijskaJedinica.getId()))+(
                    FormatiranjeTablica.formatiraj(organizacijskaJedinica.getNaziv()))+(
                    FormatiranjeTablica.formatiraj(FormatiranjeTablica.getLokacijeOrganizacijeLinija(organizacijskaJedinica))));
        }

        Tvrtka.getInstanca().getController().setModel(String.format(String.format("%" + (dc+dt+dt) + "s", "-").replace(" ", "-") ));

    }

    private int mjestaKumulativno;
    private int raspolozivoKumulativno;
    private int neraspKumulativno;

    //Refactor - svaki u svoji 
    @Override
    public void printLokacije() {
        mjestaKumulativno = 0;
        raspolozivoKumulativno = 0;
        neraspKumulativno = 0;

        lokacijeProcedura(instanca);

        for (OrganizacijskaJedinica organizacijskaJedinica : instanca.listaOrgJed) {
            lokacijeProcedura(organizacijskaJedinica);
        }
        Tvrtka.getInstanca().getController().setModel(String.format(
                FormatiranjeTablica.formatiraj("Kumulativno za " + instanca.getNaziv() + ":"))+(
                FormatiranjeTablica.formatiraj(mjestaKumulativno))+(
                FormatiranjeTablica.formatiraj(raspolozivoKumulativno))+(
                FormatiranjeTablica.formatiraj(neraspKumulativno)));

        Tvrtka.getInstanca().getController().setModel(String.format(String.format("%" + (dc+dt+dc+dc+dc+dc) + "s", "-").replace(" ", "-") ));
    }

    private void lokacijeProcedura(OrganizacijskaJedinica organizacijskaJedinica) {
        Tvrtka.getInstanca().getController().setModel(String.format(String.format("%" + (dc+dt+dc+dc+dc+dc) + "s", "-").replace(" ", "-") ));

        Tvrtka.getInstanca().getController().setModel(String.format(
                FormatiranjeTablica.formatiraj(organizacijskaJedinica.getId()))+(
                FormatiranjeTablica.formatiraj(organizacijskaJedinica.getNaziv()))+(
                FormatiranjeTablica.formatiraj(FormatiranjeTablica.getLokacijeOrganizacijeLinija((OrganizacijskaJedinica) organizacijskaJedinica))));

        if (!organizacijskaJedinica.DohvatiLok().isEmpty()) {
            Tvrtka.getInstanca().getController().setModel(String.format(
                    FormatiranjeTablica.formatirajNaslovInt("Id lok"))+(
                    FormatiranjeTablica.formatiraj("Naziv"))+(
                    FormatiranjeTablica.formatirajNaslovInt("Vrsta vozila"))+(
                    FormatiranjeTablica.formatirajNaslovInt("Broj mjesta"))+(
                    FormatiranjeTablica.formatirajNaslovInt("Raspolozivo"))+(
                    FormatiranjeTablica.formatirajNaslovInt("Ne raspolozivo")));
        }

        for (Lokacija lokacija : organizacijskaJedinica.DohvatiLok()) {
            for (Vozilo vozilo : Tvrtka.getSvaVozila()) {
                if (Tvrtka.getKapacitetByVoziloLokacija(vozilo.idVrsteVozila, lokacija.getId()) != null) {
                    Lokacije_kapaciteti lokKap = new Lokacije_kapaciteti();
                    lokKap = Tvrtka.getKapacitetByVoziloLokacija(vozilo.idVrsteVozila, lokacija.getId());

                    mjestaKumulativno += lokKap.getBroj_mjesta();
                    raspolozivoKumulativno += lokKap.getRaspolozivi();
                    neraspKumulativno += lokKap.getNeraspolozivi();

                    Tvrtka.getInstanca().getController().setModel(String.format(
                            FormatiranjeTablica.formatiraj( lokacija.getId()))+(
                            FormatiranjeTablica.formatiraj(lokacija.getNaziv()))+(
                            FormatiranjeTablica.formatiraj(lokKap.getId_vrste_vozila()))+(
                            FormatiranjeTablica.formatiraj(lokKap.getBroj_mjesta()))+(
                            FormatiranjeTablica.formatiraj(lokKap.getRaspolozivi()))+(
                            FormatiranjeTablica.formatiraj(lokKap.getNeraspolozivi())));
                }
            }

        }
    }

    private int brojNajmovaKumulativno;
    private int brojDanaKumulativno;

    @Override
    public void printNajam(Date datumOd, Date datumDo) {
        brojNajmovaKumulativno = 0;
        brojDanaKumulativno = 0;

        najamProcedura(instanca, datumOd, datumDo);

        for (OrganizacijskaJedinica organizacijskaJedinica : instanca.listaOrgJed) {
            najamProcedura(organizacijskaJedinica, datumOd, datumDo);
        }

        Tvrtka.getInstanca().getController().setModel(String.format(
                FormatiranjeTablica.formatiraj("Kumulativno za " + instanca.getNaziv() + ":"))+(
                FormatiranjeTablica.formatiraj((brojNajmovaKumulativno)))+(
                FormatiranjeTablica.formatiraj((brojDanaKumulativno))));

        Tvrtka.getInstanca().getController().setModel(String.format(String.format("%" + (dc+dt+dc+dc+dc) + "s", "-").replace(" ", "-") ));
    }

    private void najamProcedura(OrganizacijskaJedinica instanca1, Date datumOd, Date datumDo) {
        Tvrtka.getInstanca().getController().setModel(String.format(String.format("%" + (dc+dt+dc+dc+dc) + "s", "-").replace(" ", "-") ));

        Tvrtka.getInstanca().getController().setModel(String.format(
                FormatiranjeTablica.formatiraj( instanca1.getId()))+(
                FormatiranjeTablica.formatiraj(instanca1.getNaziv()))+( FormatiranjeTablica.formatiraj((""))));

        if (!instanca1.DohvatiLok().isEmpty()) {
            Tvrtka.getInstanca().getController().setModel(String.format(
                    FormatiranjeTablica.formatirajNaslovInt("Id lok "))+(
                    FormatiranjeTablica.formatiraj("Naziv"))+(
                    FormatiranjeTablica.formatirajNaslovInt("Vrsta vozila"))+(
                    FormatiranjeTablica.formatirajNaslovInt("Najmova"))+(
                    FormatiranjeTablica.formatirajNaslovInt("Sati")));
        }
        for (Lokacija lokacija : instanca1.DohvatiLok()) {
            for (Vozilo vozilo : Tvrtka.getSvaVozila()) {
                if (Tvrtka.getKapacitetByVoziloLokacija(vozilo.idVrsteVozila, lokacija.getId()) != null) {
                    Lokacije_kapaciteti lokKap = new Lokacije_kapaciteti();

                    List<Racun> racuniLokacije = FacadeSustavPronalaska.getInstanca().PronadiRacuneLokacije(lokacija.getId());
                    int brojNajmova = 0;
                    int brojDana = 0;

                    for (Racun racun : racuniLokacije) {
                        //vrsta vozila, datumOd, datumDo
                        //System.out.println("datumOd after " + racun.getDatumNajma().after(datumOd) + " datumOd equal " + racun.getDatumNajma().equals(datumOd) + " datumDo before " + racun.getDatumNajma().before(datumDo) + " datumDo equals " + racun.getDatumNajma().equals(datumOd));
                        if (racun.getIdVrsteVozila() == vozilo.getIdVrsteVozila() && (racun.getDatumNajma().after(datumOd) || racun.getDatumNajma().equals(datumOd)) && (racun.getDatumNajma().before(datumDo) || racun.getDatumNajma().equals(datumOd))) {
                            brojNajmova++;
                            brojDana += (racun.getDatumNajma().getTime() - racun.getDatumVracanja().getTime()) / 1000 / 60 / 60;
                        }
                    }

                    brojNajmovaKumulativno += brojNajmova;
                    brojDanaKumulativno += brojDana;

                    lokKap = Tvrtka.getKapacitetByVoziloLokacija(vozilo.idVrsteVozila, lokacija.getId());
                    Tvrtka.getInstanca().getController().setModel(String.format(
                            FormatiranjeTablica.formatiraj(lokacija.getId()))+(
                            FormatiranjeTablica.formatiraj(lokacija.getNaziv()))+(
                            FormatiranjeTablica.formatiraj((lokKap.getId_vrste_vozila())))+(
                            FormatiranjeTablica.formatiraj((brojNajmova)))+(
                            FormatiranjeTablica.formatiraj((brojDana))));
                }
            }

        }
    }

    private double zaradaKumulativno;

    @Override
    public void printZarada(Date datumOd, Date datumDo) {
        zaradaKumulativno = 0;

        zaradaProcedura(instanca, datumOd, datumDo);

        for (OrganizacijskaJedinica organizacijskaJedinica : instanca.listaOrgJed) {
            zaradaProcedura(organizacijskaJedinica, datumOd, datumDo);
        }

        Tvrtka.getInstanca().getController().setModel(String.format(
                FormatiranjeTablica.formatiraj("Kumulativno za " + instanca.getNaziv() + ":"))+(
                FormatiranjeTablica.formatiraj((zaradaKumulativno))));

        Tvrtka.getInstanca().getController().setModel(String.format(String.format("%" + (dc+dt+dc+dc) + "s", "-").replace(" ", "-") ));

    }

    private void zaradaProcedura(OrganizacijskaJedinica organizacijskaJedinica, Date datumOd, Date datumDo) {
        Tvrtka.getInstanca().getController().setModel(String.format(String.format("%" + (dc+dt+dc+dc) + "s", "-").replace(" ", "-") ));

        Tvrtka.getInstanca().getController().setModel(String.format(
                FormatiranjeTablica.formatiraj(organizacijskaJedinica.getId()))+(
                FormatiranjeTablica.formatiraj(organizacijskaJedinica.getNaziv()))+( FormatiranjeTablica.formatiraj((""))));

        if (!organizacijskaJedinica.DohvatiLok().isEmpty()) {
            Tvrtka.getInstanca().getController().setModel(String.format(
                    FormatiranjeTablica.formatirajNaslovInt("Id lok "))+(
                    FormatiranjeTablica.formatiraj("Naziv"))+(
                    FormatiranjeTablica.formatirajNaslovInt("Vrsta vozila"))+(
                    FormatiranjeTablica.formatirajNaslovDouble("Zarada")));
        }

        for (Lokacija lokacija : organizacijskaJedinica.DohvatiLok()) {
            for (Vozilo vozilo : Tvrtka.getSvaVozila()) {
                if (Tvrtka.getKapacitetByVoziloLokacija(vozilo.idVrsteVozila, lokacija.getId()) != null) {
                    Lokacije_kapaciteti lokKap = new Lokacije_kapaciteti();

                    List<Racun> racuniLokacije = FacadeSustavPronalaska.getInstanca().PronadiRacuneLokacije(lokacija.getId());
                    double zarada = 0;
                    int brojNajmova = 0;
                    int brojDana = 0;

                    for (Racun racun : racuniLokacije) {
                        //vrsta vozila, datumOd, datumDo
                        //System.out.println("datumOd after " + racun.getDatumNajma().after(datumOd) + " datumOd equal " + racun.getDatumNajma().equals(datumOd) + " datumDo before " + racun.getDatumNajma().before(datumDo) + " datumDo equals " + racun.getDatumNajma().equals(datumOd));
                        if (racun.getIdVrsteVozila() == vozilo.getIdVrsteVozila() && (racun.getDatumNajma().after(datumOd) || racun.getDatumNajma().equals(datumOd)) && (racun.getDatumNajma().before(datumDo) || racun.getDatumNajma().equals(datumOd))) {
                            zarada += racun.getZarada();
                            brojNajmova++;
                            brojDana += (racun.getDatumNajma().getTime() - racun.getDatumVracanja().getTime()) / 1000 / 60 / 60;
                        }
                    }

                    brojNajmovaKumulativno += brojNajmova;
                    brojDanaKumulativno += brojDana;
                    zaradaKumulativno += zarada;

                    lokKap = Tvrtka.getKapacitetByVoziloLokacija(vozilo.idVrsteVozila, lokacija.getId());
                    Tvrtka.getInstanca().getController().setModel(String.format(
                            FormatiranjeTablica.formatiraj(lokacija.getId()))+(
                            FormatiranjeTablica.formatiraj(lokacija.getNaziv()))+(
                            FormatiranjeTablica.formatiraj((lokKap.getId_vrste_vozila())))+(
                            FormatiranjeTablica.formatiraj(zarada)));
                }
            }

        }
    }
    
    @Override
    public void printNajamZarada(Date datumOd, Date datumDo){
        zaradaKumulativno = 0;
        brojNajmovaKumulativno = 0;
        brojDanaKumulativno = 0;

        najamZaradaProcedura(instanca, datumOd, datumDo);

        for (OrganizacijskaJedinica organizacijskaJedinica : instanca.listaOrgJed) {
            najamZaradaProcedura(organizacijskaJedinica, datumOd, datumDo);
        }

        Tvrtka.getInstanca().getController().setModel(String.format(
                FormatiranjeTablica.formatiraj("Kumulativno za " + instanca.getNaziv() + ":"))+(
                FormatiranjeTablica.formatiraj((zaradaKumulativno)))+(
                FormatiranjeTablica.formatiraj((brojNajmovaKumulativno)))+(
                FormatiranjeTablica.formatiraj((brojDanaKumulativno))));

        Tvrtka.getInstanca().getController().setModel(String.format(String.format("%" + (dc+dt+dc+dc+dc+dc) + "s", "-").replace(" ", "-") ));
    }

    private void najamZaradaProcedura(OrganizacijskaJedinica organizacijskaJedinica, Date datumOd, Date datumDo) {
        Tvrtka.getInstanca().getController().setModel(String.format(String.format("%" + (dc+dt+dc+dc+dc+dc) + "s", "-").replace(" ", "-") ));

        Tvrtka.getInstanca().getController().setModel(String.format(
                FormatiranjeTablica.formatiraj( organizacijskaJedinica.getId()))+(
                FormatiranjeTablica.formatiraj(organizacijskaJedinica.getNaziv()))+( FormatiranjeTablica.formatiraj((""))));

        if (!organizacijskaJedinica.DohvatiLok().isEmpty()) {
            Tvrtka.getInstanca().getController().setModel(String.format(
                    FormatiranjeTablica.formatirajNaslovInt("Id lok "))+(
                    FormatiranjeTablica.formatiraj("Naziv"))+(
                    FormatiranjeTablica.formatirajNaslovInt("Vrsta vozila"))+(
                    FormatiranjeTablica.formatirajNaslovDouble("Zarada"))+(
                    FormatiranjeTablica.formatirajNaslovInt("Najmova"))+(
                    FormatiranjeTablica.formatirajNaslovInt("Sati")));
        }

        for (Lokacija lokacija : organizacijskaJedinica.DohvatiLok()) {
            for (Vozilo vozilo : Tvrtka.getSvaVozila()) {
                if (Tvrtka.getKapacitetByVoziloLokacija(vozilo.idVrsteVozila, lokacija.getId()) != null) {
                    Lokacije_kapaciteti lokKap = new Lokacije_kapaciteti();

                    List<Racun> racuniLokacije = FacadeSustavPronalaska.getInstanca().PronadiRacuneLokacije(lokacija.getId());
                    double zarada = 0;
                    int brojNajmova = 0;
                    int brojDana = 0;

                    for (Racun racun : racuniLokacije) {
                        //vrsta vozila, datumOd, datumDo
                        //System.out.println("datumOd after " + racun.getDatumNajma().after(datumOd) + " datumOd equal " + racun.getDatumNajma().equals(datumOd) + " datumDo before " + racun.getDatumNajma().before(datumDo) + " datumDo equals " + racun.getDatumNajma().equals(datumOd));
                        if (racun.getIdVrsteVozila() == vozilo.getIdVrsteVozila() && (racun.getDatumNajma().after(datumOd) || racun.getDatumNajma().equals(datumOd)) && (racun.getDatumNajma().before(datumDo) || racun.getDatumNajma().equals(datumOd))) {
                            brojNajmova++;
                            brojDana += (racun.getDatumNajma().getTime() - racun.getDatumVracanja().getTime()) / 1000 / 60 / 60;
                            zarada += racun.getZarada();
                        }
                    }

                    brojNajmovaKumulativno += brojNajmova;
                    brojDanaKumulativno += brojDana;
                    zaradaKumulativno += zarada;

                    lokKap = Tvrtka.getKapacitetByVoziloLokacija(vozilo.idVrsteVozila, lokacija.getId());
                    Tvrtka.getInstanca().getController().setModel(String.format(
                            FormatiranjeTablica.formatiraj(lokacija.getId()))+(
                            FormatiranjeTablica.formatiraj(lokacija.getNaziv()))+(
                            FormatiranjeTablica.formatiraj((lokKap.getId_vrste_vozila())))+(
                            FormatiranjeTablica.formatiraj((zarada)))+(
                            FormatiranjeTablica.formatiraj((brojNajmova)))+(
                            FormatiranjeTablica.formatiraj((brojDana))));
                }
            }

        }
    }
    
    
    @Override
    public void printRacun(Date datumOd, Date datumDo) {

        racuniProcedura(instanca, datumOd, datumDo);

        for (OrganizacijskaJedinica organizacijskaJedinica : instanca.listaOrgJed) {
            racuniProcedura(organizacijskaJedinica, datumOd, datumDo);
        }

    }

    private void racuniProcedura(OrganizacijskaJedinica organizacijskaJedinica, Date datumOd, Date datumDo) {
        Tvrtka.getInstanca().getController().setModel(String.format(String.format("%" + (dc+dt+dc+dc+dt+dt+dc+dt+dt) + "s", "-").replace(" ", "-") ));

        Tvrtka.getInstanca().getController().setModel(String.format(
                FormatiranjeTablica.formatiraj(organizacijskaJedinica.getId())+(
                FormatiranjeTablica.formatiraj(organizacijskaJedinica.getNaziv()))+(FormatiranjeTablica.formatiraj(("")))));

        if (!organizacijskaJedinica.DohvatiLok().isEmpty()) {
            Tvrtka.getInstanca().getController().setModel(String.format(
                    FormatiranjeTablica.formatirajNaslovInt("Id lok najma "))+(
                    FormatiranjeTablica.formatiraj("Naziv"))+(
                    FormatiranjeTablica.formatirajNaslovInt("Vrsta vozila"))+(
                    FormatiranjeTablica.formatirajNaslovInt("Redni broj racuna"))+(
                    FormatiranjeTablica.formatiraj("Datum izdavanja"))+(
                    FormatiranjeTablica.formatiraj("Osoba"))+(
                    FormatiranjeTablica.formatirajNaslovInt("Id lok vracanja"))+(
                    FormatiranjeTablica.formatiraj("Naziv"))+(
                    FormatiranjeTablica.formatiraj("Opis")));
        }

        
        for (Lokacija lokacija : organizacijskaJedinica.DohvatiLok()) {
            for (Vozilo vozilo : Tvrtka.getSvaVozila()) {
                if (Tvrtka.getKapacitetByVoziloLokacija(vozilo.idVrsteVozila, lokacija.getId()) != null) {
                    //Lokacije_kapaciteti lokKap = new Lokacije_kapaciteti();
                    List<Racun> racuniLokacije = FacadeSustavPronalaska.getInstanca().PronadiRacuneLokacije(lokacija.getId());
                    
                    for (Racun racun : racuniLokacije) {
                        //vrsta vozila, datumOd, datumDo
                        //System.out.println("datumOd after " + racun.getDatumNajma().after(datumOd) + " datumOd equal " + racun.getDatumNajma().equals(datumOd) + " datumDo before " + racun.getDatumNajma().before(datumDo) + " datumDo equals " + racun.getDatumNajma().equals(datumOd));
                        //if (racun.getIdVrsteVozila() == vozilo.getIdVrsteVozila() &&
                       if (racun.getIdVrsteVozila() == vozilo.getIdVrsteVozila() &&(racun.getDatumNajma().after(datumOd) || racun.getDatumNajma().equals(datumOd)) && (racun.getDatumNajma().before(datumDo) || racun.getDatumNajma().equals(datumOd))) {
                            Tvrtka.getInstanca().getController().setModel(String.format(
                                    FormatiranjeTablica.formatiraj(FacadeSustavPronalaska.getInstanca().PronadiLokacijuRacuna(racun.getIdLokacijaNajma()).getId()))+(
                                    FormatiranjeTablica.formatiraj(FacadeSustavPronalaska.getInstanca().PronadiLokacijuRacuna(racun.getIdLokacijaNajma()).getNaziv()))+(
                                    FormatiranjeTablica.formatiraj(racun.getIdVrsteVozila()))+(
                                    FormatiranjeTablica.formatiraj(racun.getId()))+(
                                    FormatiranjeTablica.formatiraj("" +racun.getDatumVracanja()))+(
                                    FormatiranjeTablica.formatiraj(racun.getOsoba().getImePrezime()))+(
                                    FormatiranjeTablica.formatiraj(FacadeSustavPronalaska.getInstanca().PronadiLokacijuRacuna(racun.getIdLokacijaVracanja()).getId()))+(
                                    FormatiranjeTablica.formatiraj(FacadeSustavPronalaska.getInstanca().PronadiLokacijuRacuna(racun.getIdLokacijaVracanja()).getNaziv()))+(
                                    FormatiranjeTablica.formatiraj(racun.getTekst())));
                        }
                    }

                }
            }
        }
    }
    
    

    
}
