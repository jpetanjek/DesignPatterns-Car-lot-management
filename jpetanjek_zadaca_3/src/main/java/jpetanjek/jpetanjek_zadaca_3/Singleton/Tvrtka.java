package jpetanjek.jpetanjek_zadaca_3.Singleton;

import jpetanjek.jpetanjek_zadaca_3.Composite.OrganizacijskaJedinicaComposite;
import jpetanjek.jpetanjek_zadaca_3.Facade.Pronalazitelj;
import java.util.ArrayList;
import java.util.List;
import jpetanjek.jpetanjek_zadaca_3.ChainOfCommand.RacunEvidencija;
import jpetanjek.jpetanjek_zadaca_3.ChainOfCommand.RacunHandler;
import jpetanjek.jpetanjek_zadaca_3.ChainOfCommand.RacunLokacije;
import jpetanjek.jpetanjek_zadaca_3.ChainOfCommand.RacunOsobe;
import jpetanjek.jpetanjek_zadaca_3.Iterator.Iterator;
import jpetanjek.jpetanjek_zadaca_3.Iterator.IteratorRepository;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Aktivnost;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Cjenik;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Lokacija;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Lokacije_kapaciteti;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Osoba;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Vozilo;
import jpetanjek.jpetanjek_zadaca_3.MVC.Controller;

/**
 *
 * @author Joc
 */
public class Tvrtka implements Pronalazitelj {

    private static volatile Tvrtka Instanca;

    private static List<Osoba> sveOsobe;
    private static List<Vozilo> svaVozila;
    private static List<Lokacija> sveLokacije;
    private static List<Cjenik> sviCjenici;
    private static List<Aktivnost> sveAktivnosti;
    private static List<Lokacije_kapaciteti> sviKapaciteti;
    private static int dt;
    private static int dc;
    private static int dd;

    //Dz 3 chain
    private static RacunHandler Chain;
    private static double dugovanje;
    
    //Dz 3 MVC
    private static Controller controller;
    private static String datotekaIzlaz;

    public static String getDatotekaIzlaz() {
        return datotekaIzlaz;
    }

    public static void setDatotekaIzlaz(String datotekaIzlaz) {
        Tvrtka.datotekaIzlaz = datotekaIzlaz;
    }

    public static Controller getController() {
        return controller;
    }

    public static void setController(Controller controller) {
        Tvrtka.controller = controller;
    }
    
    

    public static double getDugovanje() {
        return dugovanje;
    }

    public static void setDugovanje(double dugovanje) {
        Tvrtka.dugovanje = dugovanje;
    }

    public static RacunHandler getRacunHandler() {
        Chain = new RacunEvidencija(RacunHandler.EVIDENCIJA);
        RacunHandler osoba = new RacunOsobe(RacunHandler.OSOBE);
        RacunHandler lokacija = new RacunLokacije(RacunHandler.LOKACIJA);

        Chain.setNextLogger(osoba);
        osoba.setNextLogger(lokacija);

        return Chain;
    }

    public static int getDt() {
        return dt;
    }

    public static void setDt(int dt) {
        Tvrtka.dt = dt;
    }

    public static int getDc() {
        return dc;
    }

    public static void setDc(int dc) {
        Tvrtka.dc = dc;
    }

    public static int getDd() {
        return dd;
    }

    public static void setDd(int dd) {
        Tvrtka.dd = dd;
    }

    private static OrganizacijskaJedinicaComposite izvornaOrganizacijskaJedinica;

    public static OrganizacijskaJedinicaComposite getIzvornaOrganizacijskaJedinica() {
        return izvornaOrganizacijskaJedinica;
    }

    public static void setIzvornaOrganizacijskaJedinica(OrganizacijskaJedinicaComposite izvornaOrganizacijskaJedinica) {
        Tvrtka.izvornaOrganizacijskaJedinica = izvornaOrganizacijskaJedinica;
    }

    public static boolean daLiPostojiOrganizacijskaJedinica(int idOrgJed) {
        boolean returnme = false;

        IteratorRepository ir2 = new IteratorRepository(Tvrtka.getInstanca().getIzvornaOrganizacijskaJedinica());
        Iterator i2 = ir2.getIterator();
        while (i2.hasNext()) {
            OrganizacijskaJedinicaComposite oi = (OrganizacijskaJedinicaComposite) i2.getNext();
            if (oi.getId() == idOrgJed) {
                returnme = true;
            }
        }
        return returnme;
    }

    public static Cjenik getCjenikById(int id) {
        Cjenik returnme = null;
        for (Cjenik cjenik : sviCjenici) {
            if (id == cjenik.id_vrste_vozila) {
                return cjenik;
            }
        }
        return returnme;
    }

    public static Lokacije_kapaciteti getKapacitetByVoziloLokacija(int id_vozila, int id_lokacija) {
        Lokacije_kapaciteti returnme = null;
        for (Lokacije_kapaciteti lokacije_kapaciteti : sviKapaciteti) {
            if (lokacije_kapaciteti.getId_lokacije() == id_lokacija
                    && lokacije_kapaciteti.getId_vrste_vozila() == id_vozila) {
                return lokacije_kapaciteti;
            }
        }
        return returnme;
    }

    public static void dodajVoziloKapacitetu(Vozilo vozilo, Lokacija lokacija) {
        for (Lokacije_kapaciteti lokacije_kapaciteti : sviKapaciteti) {
            if (lokacije_kapaciteti.getId_lokacije() == lokacija.getId()
                    && lokacije_kapaciteti.getId_vrste_vozila() == vozilo.getIdVrsteVozila()) {

                //System.out.println("Velicina " + lokacije_kapaciteti.vozila.size());
                lokacije_kapaciteti.vozila.add(vozilo);

                //System.out.println("Velicina " + lokacije_kapaciteti.vozila.size());
                //lokacije_kapaciteti.raspolozivi++;
            }
        }
    }

    private Tvrtka() {
    }

    public static Osoba getOsobaById(int id) {
        Osoba returnme = null;
        for (Osoba osoba : sveOsobe) {
            if (id == osoba.id) {
                return osoba;
            }
        }
        return returnme;
    }

    public static Lokacija getLokacijaById(int id) {
        Lokacija returnme = null;
        for (Lokacija lokacija : sveLokacije) {
            if (id == lokacija.id) {
                return lokacija;
            }
        }
        return returnme;
    }

    public static Vozilo getVoziloById(int id) {
        Vozilo returnme = null;
        for (Vozilo vozilo : svaVozila) {
            if (id == vozilo.idVrsteVozila) {
                return vozilo;
            }
        }
        return returnme;
    }

    public static Tvrtka getInstanca() {
        if (Instanca == null) {
            synchronized ((Tvrtka.class)) {
                Instanca = new Tvrtka();
                sveOsobe = new ArrayList<>();
            }
        }
        return Instanca;
    }

    public static void setInstanca(Tvrtka Instanca) {
        Tvrtka.Instanca = Instanca;
    }

    public static List<Osoba> getSveOsobe() {
        return sveOsobe;
    }

    public static void setSveOsobe(List<Osoba> sveOsobe) {
        Tvrtka.sveOsobe = sveOsobe;
    }

    public static List<Vozilo> getSvaVozila() {
        return svaVozila;
    }

    public static void setSvaVozila(List<Vozilo> svaVozila) {
        Tvrtka.svaVozila = svaVozila;
    }

    public static List<Lokacija> getSveLokacije() {
        return sveLokacije;
    }

    public static void setSveLokacije(List<Lokacija> sveLokacije) {
        Tvrtka.sveLokacije = sveLokacije;
    }

    public static List<Cjenik> getSviCjenici() {
        return sviCjenici;
    }

    public static void setSviCjenici(List<Cjenik> sviCjenici) {
        Tvrtka.sviCjenici = sviCjenici;
    }

    public static List<Aktivnost> getSveAktivnosti() {
        return sveAktivnosti;
    }

    public static void setSveAktivnosti(List<Aktivnost> sveAktivnosti) {
        Tvrtka.sveAktivnosti = sveAktivnosti;
    }

    public static List<Lokacije_kapaciteti> getSviKapaciteti() {
        return sviKapaciteti;
    }

    public static void setSviKapaciteti(List<Lokacije_kapaciteti> sviKapaciteti) {
        Tvrtka.sviKapaciteti = sviKapaciteti;
    }

    @Override
    public Lokacija pronadi(int idLokacije) {
        Lokacija returnme = new Lokacija();
        for (Lokacija lokacija : sveLokacije) {
            if (lokacija.getId() == idLokacije) {
                return lokacija;
            }
        }
        return returnme;
    }

}
