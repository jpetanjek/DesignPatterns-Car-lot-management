package jpetanjek.jpetanjek_zadaca_3.IzvorneKlase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpetanjek.jpetanjek_zadaca_3.Bridge.Implementor.IAktivnost;
import jpetanjek.jpetanjek_zadaca_3.Bridge.RefinedAbstraction.FinancijskoStanje;
import jpetanjek.jpetanjek_zadaca_3.Bridge.RefinedAbstraction.KrajPrograma;
import jpetanjek.jpetanjek_zadaca_3.Bridge.RefinedAbstraction.NajamVozila;
import jpetanjek.jpetanjek_zadaca_3.Bridge.RefinedAbstraction.PlacanjeDugovanjaKorisnika;
import jpetanjek.jpetanjek_zadaca_3.Bridge.RefinedAbstraction.PregledRaspolozivihMjesta;
import jpetanjek.jpetanjek_zadaca_3.Bridge.RefinedAbstraction.PregledRaspolozivihVozila;
import jpetanjek.jpetanjek_zadaca_3.Bridge.RefinedAbstraction.Prijelaz;
import jpetanjek.jpetanjek_zadaca_3.Bridge.RefinedAbstraction.RacunIspis;
import jpetanjek.jpetanjek_zadaca_3.Bridge.RefinedAbstraction.RacuniKorisnika;
import jpetanjek.jpetanjek_zadaca_3.Bridge.RefinedAbstraction.Stanje;
import jpetanjek.jpetanjek_zadaca_3.Bridge.RefinedAbstraction.VracanjeVozila;
import jpetanjek.jpetanjek_zadaca_3.Bridge.RefinedAbstraction.ZaradaNajam;
import jpetanjek.jpetanjek_zadaca_3.Singleton.Tvrtka;
import jpetanjek.jpetanjek_zadaca_3.Singleton.VirtualnoVrijeme;

/**
 *
 * @author Josip Petanjek
 */
public class Aktivnost implements IAktivnost {

    public int id;
    public String vrijeme;
    public String linija;

    public Aktivnost() {
    }

    @Override
    public String toString() {
        return linija;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(String vrijeme) {
        this.vrijeme = vrijeme;
    }

    public static Aktivnost AktivnostFromString(String linija) throws Exception {

        String[] aktivnosti = linija.split(";", -1);
        switch (Integer.parseInt(aktivnosti[0].trim())) {
            case 1:
                PregledRaspolozivihVozila pregledRaspolozivihVozila = new PregledRaspolozivihVozila();
                pregledRaspolozivihVozila.setId(1);
                pregledRaspolozivihVozila.setVrijeme(VirtualnoVrijeme.PocistiDatum(aktivnosti[1]));
                pregledRaspolozivihVozila.setId_korisnika(Integer.parseInt(aktivnosti[2].trim()));
                pregledRaspolozivihVozila.setId_lokacije(Integer.parseInt(aktivnosti[3].trim()));
                pregledRaspolozivihVozila.setId_vrste_vozila(Integer.parseInt(aktivnosti[4].trim()));
                pregledRaspolozivihVozila.linija = linija;
                return pregledRaspolozivihVozila;
            case 2:
                NajamVozila najamVozila = new NajamVozila();
                najamVozila.setId(2);
                najamVozila.setVrijeme(VirtualnoVrijeme.PocistiDatum(aktivnosti[1]));
                najamVozila.setId_korisnika(Integer.parseInt(aktivnosti[2].trim()));
                najamVozila.setId_lokacije(Integer.parseInt(aktivnosti[3].trim()));
                najamVozila.setId_vrste_vozila(Integer.parseInt(aktivnosti[4].trim()));
                najamVozila.linija = linija;
                return najamVozila;
            case 3:
                PregledRaspolozivihMjesta pregledRaspolozivihMjesta = new PregledRaspolozivihMjesta();
                pregledRaspolozivihMjesta.setId(3);
                pregledRaspolozivihMjesta.setVrijeme(VirtualnoVrijeme.PocistiDatum(aktivnosti[1]));
                pregledRaspolozivihMjesta.setId_korisnika(Integer.parseInt(aktivnosti[2].trim()));
                pregledRaspolozivihMjesta.setId_lokacije(Integer.parseInt(aktivnosti[3].trim()));
                pregledRaspolozivihMjesta.setId_vrste_vozila(Integer.parseInt(aktivnosti[4].trim()));
                pregledRaspolozivihMjesta.linija = linija;
                return pregledRaspolozivihMjesta;
            case 4:
                VracanjeVozila vracanjeVozila = new VracanjeVozila();
                vracanjeVozila.setId(4);
                vracanjeVozila.setVrijeme(VirtualnoVrijeme.PocistiDatum(aktivnosti[1]));
                vracanjeVozila.setId_korisnika(Integer.parseInt(aktivnosti[2].trim()));
                vracanjeVozila.setId_lokacije(Integer.parseInt(aktivnosti[3].trim()));
                vracanjeVozila.setId_vrste_vozila(Integer.parseInt(aktivnosti[4].trim()));
                vracanjeVozila.setBroj_km(Integer.parseInt(aktivnosti[5].trim()));
                if (aktivnosti.length == 7) {
                    vracanjeVozila.setOpis_problema(aktivnosti[6].trim());
                } else {
                    vracanjeVozila.setOpis_problema("");
                }
                vracanjeVozila.linija = linija;
                return vracanjeVozila;
            case 5:
                Prijelaz prijelaz = new Prijelaz();
                prijelaz.setId(5);
                prijelaz.setDatoteka(aktivnosti[1].trim());
                prijelaz.linija = linija;
                return prijelaz;
            case 6:
                Stanje stanje = new Stanje();
                stanje.linija=linija;
                int brojKomandi1 = 0;
                if (aktivnosti[1].contains("struktura")) {
                    stanje.setStruktura(true);
                    brojKomandi1++;
                } else {
                    stanje.setStruktura(false);
                }

                if (aktivnosti[1].contains("stanje")) {
                    stanje.setStanje(true);
                    brojKomandi1++;
                } else {
                    stanje.setStanje(false);
                }

                String[] splitter1 = aktivnosti[1].trim().split(" ", -1);

                if (splitter1.length == brojKomandi1 + 1) {
                    stanje.setId(Integer.parseInt(splitter1[brojKomandi1]));
                } else {
                    stanje.setId(-1);
                }

                return stanje;

            case 7:
                ZaradaNajam zaradaNajam = new ZaradaNajam();
                zaradaNajam.linija=linija;
                //splitanje po space
                int brojKomandi = 0;
                //System.out.println(aktivnosti[1].trim());
                if (aktivnosti[1].contains("struktura")) {
                    zaradaNajam.setStruktura(true);
                    brojKomandi++;
                } else {
                    zaradaNajam.setStruktura(false);
                }

                if (aktivnosti[1].contains("najam")) {
                    zaradaNajam.setNajam(true);
                    brojKomandi++;

                } else {
                    zaradaNajam.setNajam(false);
                }

                if (aktivnosti[1].contains("zarada")) {
                    zaradaNajam.setZarada(true);
                    brojKomandi++;
                } else {
                    zaradaNajam.setZarada(false);
                }

                //System.out.println(brojKomandi);
                String[] splitter = aktivnosti[1].trim().split(" ", -1);

                switch (brojKomandi) {
                    case 1:
                        PostaviDatume(zaradaNajam, splitter[1], splitter[2]);
                        break;
                    case 2:
                        for (String object : splitter) {
                            System.out.println(object);
                        }
                        PostaviDatume(zaradaNajam, splitter[2], splitter[3]);
                        break;
                    case 3:
                        PostaviDatume(zaradaNajam, splitter[3], splitter[4]);
                        break;
                    default: //neispravan format
                        break;
                }

                //System.out.println("Lenght " + splitter.length);
                //OVO DEUGGGATI KASNIJE
                if (splitter.length == brojKomandi + 3) {
                    zaradaNajam.setId(Integer.parseInt(splitter[brojKomandi + 2]));
                } else {
                    zaradaNajam.setId(-1);
                }

                return zaradaNajam;

            case 8:

                RacunIspis racunIspis = new RacunIspis();
                racunIspis.linija=linija;
                //splitanje po space
                int brojKomandi2 = 0;
                
                if (aktivnosti[1].contains("struktura")) {
                    racunIspis.setStruktura(true);
                    brojKomandi2++;
                } else {
                    racunIspis.setStruktura(false);
                }

                if (aktivnosti[1].contains("racuni")||aktivnosti[1].contains("računi")||aktivnosti[1].contains("rauni")||aktivnosti[1].contains("ra?uni") ) {
                    racunIspis.setRacuni(true);
                    brojKomandi2++;

                } else {
                    racunIspis.setRacuni(false);
                }
                
                //System.out.println("Ispis "+aktivnosti[1]);

                //System.out.println(brojKomandi);
                String[] splitter3 = aktivnosti[1].trim().split(" ", -1);

                switch (brojKomandi2) {
                    case 1:
                        PostaviDatume(racunIspis, splitter3[1], splitter3[2]);
                        break;
                    case 2:
                        for (String object : splitter3) {
                            //System.out.println(object);
                        }
                        PostaviDatume(racunIspis, splitter3[2], splitter3[3]);
                        break;
                    default: //neispravan format
                        break;
                }

                //System.out.println("Lenght " + splitter.length);
                //OVO DEUGGGATI KASNIJE
                //System.out.println("Uspjesno postavljeni datumi " + splitter3.length + " " + brojKomandi2);
                if (splitter3.length >= brojKomandi2 + 3) {
                    racunIspis.setId(Integer.parseInt(splitter3[brojKomandi2 + 2]));
                } else {
                    racunIspis.setId(-1);
                }

                return racunIspis;
                
            case 9:
                FinancijskoStanje financijskoStanje = new FinancijskoStanje();
                financijskoStanje.setId(9);
                financijskoStanje.linija=linija;
                return financijskoStanje;
                
            case 10:
                RacuniKorisnika racuniKorisnika = new RacuniKorisnika();
                racuniKorisnika.linija=linija;
                
                String[] splitter10 = aktivnosti[1].trim().split(" ", -1);
                
                racuniKorisnika.setId(10);
                racuniKorisnika.setId_korisnika(Integer.parseInt(splitter10[0].trim()));
                PostaviDatume(racuniKorisnika, splitter10[1], splitter10[2]);
                return racuniKorisnika;
                
            case 11:
                PlacanjeDugovanjaKorisnika placanjeDugovanjaKorisnika = new PlacanjeDugovanjaKorisnika();
                placanjeDugovanjaKorisnika.linija=linija;
                
                String[] splitter11 = aktivnosti[1].trim().split(" ", -1);
                
                placanjeDugovanjaKorisnika.setId(11);
                placanjeDugovanjaKorisnika.setId_korisnika(Integer.parseInt(splitter11[0].trim()));
                placanjeDugovanjaKorisnika.setIznos(Double.parseDouble(splitter11[1].trim().replace(",", ".")));
                return placanjeDugovanjaKorisnika;
                
            case 0:
                KrajPrograma krajPrograma = new KrajPrograma();
                krajPrograma.setId(5);
                krajPrograma.setVrijeme(VirtualnoVrijeme.PocistiDatum(aktivnosti[1]));
                krajPrograma.linija = linija;
                return krajPrograma;
            default:
                throw new Exception("Neispravan id aktivnosti, nije moguće stvoriti objekt.");
        }
    }

    private static void PostaviDatume(ZaradaNajam zaradaNajam, String string, String string0) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date datumOd = format.parse(string);
            Date datumDo = format.parse(string0);
            if (datumOd.after(datumDo)) {
                Date temp = new Date();
                temp = datumOd;
                datumOd = datumDo;
                datumDo = temp;
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(datumDo);
            calendar.add(Calendar.DATE, 1);
            datumDo = calendar.getTime();
            
            //System.out.println("datumOd: " + datumOd + " datumDo: " + datumDo);
            zaradaNajam.setDatumOd(datumOd);
            zaradaNajam.setDatumDo(datumDo);

        } catch (ParseException ex) {
            Logger.getLogger(Aktivnost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void PostaviDatume(RacunIspis racuniIspis, String string, String string0) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date datumOd = format.parse(string);
            Date datumDo = format.parse(string0);
            if (datumOd.after(datumDo)) {
                Date temp = new Date();
                temp = datumOd;
                datumOd = datumDo;
                datumDo = temp;
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(datumDo);
            calendar.add(Calendar.DATE, 1);
            datumDo = calendar.getTime();
            
            //System.out.println("datumOd: " + datumOd + " datumDo: " + datumDo);
            racuniIspis.setDatumOd(datumOd);
            racuniIspis.setDatumDo(datumDo);

        } catch (ParseException ex) {
            Logger.getLogger(Aktivnost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void PostaviDatume(RacuniKorisnika racuniKorisnika, String string, String string0) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date datumOd = format.parse(string);
            Date datumDo = format.parse(string0);
            if (datumOd.after(datumDo)) {
                Date temp = new Date();
                temp = datumOd;
                datumOd = datumDo;
                datumDo = temp;
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(datumDo);
            calendar.add(Calendar.DATE, 1);
            datumDo = calendar.getTime();
            
            //System.out.println("datumOd: " + datumOd + " datumDo: " + datumDo);
            racuniKorisnika.setDatumOd(datumOd);
            racuniKorisnika.setDatumDo(datumDo);

        } catch (ParseException ex) {
            Logger.getLogger(Aktivnost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void izvedi() throws Exception {
            Tvrtka.getInstanca().getController().setModel(this.toString());
    }

}
