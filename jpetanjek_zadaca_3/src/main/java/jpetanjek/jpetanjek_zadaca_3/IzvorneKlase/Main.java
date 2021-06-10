package jpetanjek.jpetanjek_zadaca_3.IzvorneKlase;

import jpetanjek.jpetanjek_zadaca_3.Builder.ConcreteBuilder;
import jpetanjek.jpetanjek_zadaca_3.Builder.TvrtkaBuilder;
import jpetanjek.jpetanjek_zadaca_3.Builder.TvrtkaDirector;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpetanjek.jpetanjek_zadaca_3.Bridge.ConcreteImplementator.IzvoditeljAktivnosti;
import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.ConcreteCreator.AktivnostDatotekaCreator;
import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.ConcreteCreator.CjenikDatotekaCreator;
import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.ConcreteCreator.KapacitetiDatotekaCreator;
import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.ConcreteCreator.LokacijeDatotekaCreator;
import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.ConcreteCreator.OsobaDatotekaCreator;
import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.ConcreteCreator.TvrtkaDatotekaCreator;
import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.ConcreteCreator.VozilaDatotekaCreator;
import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.Creator.DatotekaCreator;
import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.Product.DatotekaProduct;
import jpetanjek.jpetanjek_zadaca_3.MVC.Controller;
import jpetanjek.jpetanjek_zadaca_3.MVC.InteraktivniPogled;
import jpetanjek.jpetanjek_zadaca_3.MVC.IzlazPogled;
import jpetanjek.jpetanjek_zadaca_3.MVC.SkupniPogled;
import jpetanjek.jpetanjek_zadaca_3.Singleton.NacinRada;
import jpetanjek.jpetanjek_zadaca_3.Singleton.Tvrtka;
import jpetanjek.jpetanjek_zadaca_3.Singleton.VirtualnoVrijeme;

/**
 *
 * @author Josip Petanjek
 */
public class Main {

    private static String datotekaVozila;
    private static String datotekaLokacije;
    private static String datotekaCjenik;
    private static String datotekaKapaciteti;
    private static String datotekaOsobe;
    private static String datotekaTvrtke;
    private static String datotekaAktivnosti;

    //dz 3 modifikacija
    private static String datotekaIzlaz;

    /**
     * Izvršni dio programa, prema unosima obavljaju se aktivnosti
     *
     * @param args -v DZ_1_vozila.txt -l DZ_1_lokacije.txt -c DZ_1_cjenik.txt -k
     * DZ_1_lokacije_kapaciteti.txt -o DZ_1_osobe.txt -t „2020-10-16 08:00:00“
     * -s DZ_1_aktivnosti.txt
     */
    public static void main(String[] args) {
        if (IspravniArgumentiDatoteke(args)) {
            UcitajDatoteke();
            if (NacinRada.getInstanca().getNacin().equals("interaktivno")) {
                InteraktivniNacinRada();
            } else if (NacinRada.getInstanca().getNacin().equals("slijedno")) {
                SlijedniNacinRada();
            } else if (NacinRada.getInstanca().getNacin().equals("izlaz")) {
                IzlazNacinRada();
            }
        } else {
            ZavrsiRad("Neispravni argumenti!");
        }
    }

    /**
     * Provjeravanje ispravnosti formata unesenih argumenata
     *
     * @param args argumenti s kojima je inicijaliziran program
     * @return true ako su ispravnog formata, false ako su krivog formata
     */
    public static boolean IspravniArgumentiDatoteke(String[] args) {
        
        PostaviNacinRada("interaktivno");
        
        InteraktivniPogled pogled = new InteraktivniPogled();
        Controller controller = new Controller("", pogled);
        
        Tvrtka.getInstanca().setController(controller);

        //Modificirati ovaj else za konfiguracije
        //provjeri da li je .txt
        if (args[0].contains(".txt")) {

            List<String> output = new ArrayList<>();
            String linija = "";

            try ( BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8))) {
                while ((linija = br2.readLine()) != null) {

                    try {

                        output.add(linija);
                    } catch (Exception e) {
                        Tvrtka.getInstanca().getController().setError(linija);
                        Tvrtka.getInstanca().getController().setError("Zapis konfiguracije neispravan, nije moguće stvoriti objekt! Iznimka: " + e.getMessage());
                    }
                }
            } catch (Exception e) {
                Tvrtka.getInstanca().getController().setError("Dogodila se greška prilikom čitanja datoteke konfiguracije " + e.getMessage());
                System.exit(-1);
            }

            //System.out.println(output.contains(".txt"));
            if (sadrziPolje(output, "vozila") && sadrziPolje(output, "lokacije")
                    && sadrziPolje(output, "cjenik")
                    && sadrziPolje(output, "kapaciteti")
                    && sadrziPolje(output, "osobe")
                    && sadrziPolje(output, "vrijeme")
                    && sadrziPolje(output, "struktura")) {
                //dohvati lokacije
                //datotekaVozila = args[argumenti.indexOf("-v") + 1];

                datotekaVozila = putanjaKonfiguracije("vozila", output);
                datotekaLokacije = putanjaKonfiguracije("lokacije", output);
                datotekaCjenik = putanjaKonfiguracije("cjenik", output);
                datotekaKapaciteti = putanjaKonfiguracije("kapaciteti", output);
                datotekaOsobe = putanjaKonfiguracije("osobe", output);
                datotekaTvrtke = putanjaKonfiguracije("struktura", output);

                PostaviVirtualnoVrijeme(putanjaKonfiguracije("vrijeme", output));

                if (sadrziPolje(output, "izlaz")) {
                    datotekaIzlaz = putanjaKonfiguracije("izlaz", output);
                    Tvrtka.getInstanca().setDatotekaIzlaz(datotekaIzlaz);
                } else {
                    datotekaIzlaz = "";
                }

                if (sadrziPolje(output, "aktivnosti")) {
                    datotekaAktivnosti = putanjaKonfiguracije("aktivnosti", output);
                    PostaviNacinRada("slijedno");
                    if(sadrziPolje(output, "izlaz")){
                        PostaviNacinRada("izlaz");
                    }
                } else {
                    PostaviNacinRada("interaktivno");
                }

                //System.out.println(datotekaVozila + " " + datotekaLokacije + " " + datotekaCjenik + " " + datotekaKapaciteti + " " + datotekaOsobe + " " + datotekaTvrtke + " " + datotekaAktivnosti);
                Tvrtka.getInstanca().setDt(30);
                Tvrtka.getInstanca().setDc(5);
                Tvrtka.getInstanca().setDd(2);

                if (sadrziPolje(output, "tekst")) {
                    Tvrtka.getInstanca().setDt(Integer.parseInt(putanjaKonfiguracije("tekst", output).trim()));
                }

                if (sadrziPolje(output, "cijeli")) {
                    Tvrtka.getInstanca().setDc(Integer.parseInt(putanjaKonfiguracije("cijeli", output).trim()));
                }
                if (sadrziPolje(output, "decimala")) {
                    Tvrtka.getInstanca().setDd(Integer.parseInt(putanjaKonfiguracije("decimala", output).trim()));
                }

                //cjenik.setPo_satu(Double.parseDouble(cjenikLinija[2].trim().replace(",", ".")));
                if (sadrziPolje(output, "dugovanje")) {
                    Tvrtka.getInstanca().setDugovanje(Double.parseDouble(putanjaKonfiguracije("dugovanje", output).trim().replace(",", ".")));
                    //System.out.println(Tvrtka.getInstanca().getDugovanje());
                }

                //System.out.println(Tvrtka.getInstanca().getDt() + " " + Tvrtka.getInstanca().getDc() + " " + Tvrtka.getInstanca().getDd());
                return true;

            }

        } else {
            Tvrtka.getInstanca().getController().setError("Neispravan format");
            return false;
        }

        Tvrtka.getInstanca().getController().setError("Neispravan format");
        return false;

    }

    private static boolean sadrziPolje(List<String> polje, String sadrzi) {
        boolean returnme = false;
        for (String string : polje) {
            if (string.contains(sadrzi)) {
                return true;
            }
        }
        return returnme;
    }

    private static String putanjaKonfiguracije(String pronaci, List<String> polje) {
        String returnme = "";

        for (String string : polje) {
            if (string.contains(pronaci)) {
                return string.substring(pronaci.length() + 1, string.length());
            }
        }

        return returnme;
    }

    /**
     * Funkcija za pronalazenje potpune apsolutne putanje do txt datoteke
     *
     * @param indeks Indeks oznake datoteke
     * @param polje Polje Stringova unosa
     * @return Putanja do datoteke
     */
    private static String pronadiPutanju(int indeks, String[] polje) {
        String returnme = "";
        int indeksTxt = 0;
        for (int i = indeks; i < polje.length; i++) {
            if (polje[i].contains(".txt")) {
                indeksTxt = i;
                break;
            }
        }
        for (int i = indeks; i <= indeksTxt; i++) {
            returnme += polje[i] + " ";
        }
        returnme = returnme.trim();
        //System.out.println("Putanja: " + returnme);
        return returnme;
    }

    /**
     * Funkcija za postavljanje virtualnog vremena (Singleton)
     *
     * @param args virtualno vrijeme u string obliku
     */
    private static void PostaviVirtualnoVrijeme(String args) {
        VirtualnoVrijeme.getInstanca().setVrijeme(args);
    }

    /**
     * Funkcija za postavljanje nacina rada (Singleton)
     *
     * @param args Nacin rada u string obliku
     */
    private static void PostaviNacinRada(String args) {
        NacinRada.getInstanca().setNacin(args);
    }

    /**
     * Funkcija za ucitavanje datoteka, te stvaranje objekata pročitanih iz
     * datoteka sa Factory Method uzorkom
     */
    public static void UcitajDatoteke() {

        //DatotekaCreator
        DatotekaCreator vozilaDatotekaCreator = new VozilaDatotekaCreator();
        DatotekaCreator osobaDatotekaCreator = new OsobaDatotekaCreator();
        DatotekaCreator lokacijaDatotekaCreator = new LokacijeDatotekaCreator();
        DatotekaCreator cjenikDatotekaCreator = new CjenikDatotekaCreator();
        DatotekaCreator kapacitetiDatotekaCreator = new KapacitetiDatotekaCreator();

        DatotekaCreator tvrtkaDatotekaCreator = new TvrtkaDatotekaCreator();

        //DatotekaProduct
        DatotekaProduct vozilaProduct = vozilaDatotekaCreator.makeProduct();
        DatotekaProduct osobaProduct = osobaDatotekaCreator.makeProduct();
        DatotekaProduct lokacijaProduct = lokacijaDatotekaCreator.makeProduct();
        DatotekaProduct cjenikProduct = cjenikDatotekaCreator.makeProduct();
        DatotekaProduct kapacitetiProduct = kapacitetiDatotekaCreator.makeProduct();

        DatotekaProduct tvrtkaProduct = tvrtkaDatotekaCreator.makeProduct();

        Tvrtka.getInstanca().setSvaVozila((List<Vozilo>) vozilaProduct.getConcreteProducts(datotekaVozila));
        Tvrtka.getInstanca().setSveOsobe((List<Osoba>) osobaProduct.getConcreteProducts(datotekaOsobe));
        Tvrtka.getInstanca().setSveLokacije((List<Lokacija>) lokacijaProduct.getConcreteProducts(datotekaLokacije));
        Tvrtka.getInstanca().setSviCjenici((List<Cjenik>) cjenikProduct.getConcreteProducts(datotekaCjenik));
        Tvrtka.getInstanca().setSviKapaciteti((List<Lokacije_kapaciteti>) kapacitetiProduct.getConcreteProducts(datotekaKapaciteti));

        //Builder za Composite - Strukturu tvrtke
        TvrtkaBuilder builder = new ConcreteBuilder();
        TvrtkaDirector direktor = new TvrtkaDirector(builder);
        Tvrtka.getInstanca().setIzvornaOrganizacijskaJedinica(direktor.construct((List<OrgJedModel>) tvrtkaProduct.getConcreteProducts(datotekaTvrtke)));

        if (NacinRada.getInstanca().getNacin().equals("slijedno")||NacinRada.getInstanca().getNacin().equals("izlaz")) {
            DatotekaCreator aktivnostDatotekaCreator = new AktivnostDatotekaCreator();
            DatotekaProduct aktivnostProduct = aktivnostDatotekaCreator.makeProduct();
            Tvrtka.getInstanca().setSveAktivnosti((List<Aktivnost>) aktivnostProduct.getConcreteProducts(datotekaAktivnosti));
        }

    }

    /**
     * Funkcija za prekidanje rada programa
     *
     * @param arg Poruka za ispis, razlog prekidanja programa
     */
    private static void ZavrsiRad(String arg) {
        Tvrtka.getInstanca().getController().setModel(arg);
        System.exit(0);
    }

    /**
     * Funkcija za izvođenje aktivnosti na interaktivan način - korištenjem
     * Bridge uzorka dizajna
     */
    private static void InteraktivniNacinRada() {
        PostaviNacinRada("interaktivno");
        boolean kraj = false;
        
        InteraktivniPogled pogled = new InteraktivniPogled();
        Controller controller = new Controller("", pogled);
        
        Tvrtka.getInstanca().setController(controller);

        while (!kraj) {
            try {
                Tvrtka.getInstanca().getController().setModel("Unesite aktivnost: ");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

                //String unos = br.readLine();
                String unos = "";
                boolean kraj2 = false;
                while (!kraj2) {
                    unos = unos + (char) br.read();
                    //System.out.println("Pocetni unos: " +unos);
                    if (unos.contains("\n") || unos.contains("\r\n")) {
                        unos = unos.replace("\n", "").replace("\r", "");
                        kraj2 = true;
                    }
                }

                try {
                    Aktivnost aktivnost = Aktivnost.AktivnostFromString(unos);
                    IzvoditeljAktivnosti izvoditeljAktivnosti = new IzvoditeljAktivnosti(aktivnost);
                    izvoditeljAktivnosti.izvediAktivnost();
                } catch (Exception e) {
                    Tvrtka.getInstanca().getController().setModel("Zapis aktivnosti neispravan: " + unos + " - nije moguće stvoriti objekt! Iznimka: " + e.getMessage());
                }

            } catch (IOException ex) {
                Tvrtka.getInstanca().getController().setError(ex.getMessage());
            }

        }

    }

    /**
     * Funkcija za izvođenje aktivnosti na slijedni način - korištenjem Bridge
     * uzorka dizajna
     */
    public static void SlijedniNacinRada() {
        PostaviNacinRada("slijedno");
        
        SkupniPogled pogled = new SkupniPogled();
        Controller controller = new Controller("", pogled);
        
        Tvrtka.getInstanca().setController(controller);
        
        for (Aktivnost a : Tvrtka.getSveAktivnosti()) {
            //izvedi
            try {
                IzvoditeljAktivnosti izvoditeljAktivnosti = new IzvoditeljAktivnosti(a);
                izvoditeljAktivnosti.izvediAktivnost();
            } catch (Exception e) {
                Tvrtka.getInstanca().getController().setError("Zapis aktivnosti neispravan: " + a + " - nije moguće stvoriti objekt! Iznimka: " + e.getMessage());
            }
        }
        InteraktivniNacinRada();
    }

    private static void IzlazNacinRada() {
        PostaviNacinRada("izlaz");
        
        IzlazPogled pogled = new IzlazPogled();
        Controller controller = new Controller("", pogled);
        
        Tvrtka.getInstanca().setController(controller);
        
        for (Aktivnost a : Tvrtka.getSveAktivnosti()) {
            //izvedi
            try {
                IzvoditeljAktivnosti izvoditeljAktivnosti = new IzvoditeljAktivnosti(a);
                izvoditeljAktivnosti.izvediAktivnost();
            } catch (Exception e) {
                Tvrtka.getInstanca().getController().setError("Zapis aktivnosti neispravan: " + a + " - nije moguće stvoriti objekt! Iznimka: " + e.getMessage());
            }
        }
        InteraktivniNacinRada();
    }

}
