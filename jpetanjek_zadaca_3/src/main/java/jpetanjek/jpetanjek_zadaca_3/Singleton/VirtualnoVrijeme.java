package jpetanjek.jpetanjek_zadaca_3.Singleton;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Lokacije_kapaciteti;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Vozilo;
import jpetanjek.jpetanjek_zadaca_3.State.NaPunjenju;
import jpetanjek.jpetanjek_zadaca_3.State.Slobodno;

/**
 *
 * @author Joc
 */
public class VirtualnoVrijeme {

    private static volatile VirtualnoVrijeme Instanca;
    private static String vrijeme;

    public static long izracunajSate(String vrijemeOd, String vrijemeDo) {
        long returnme = 0;
        try {
            //„2020-10-16 08:01:00“
            Date datumOd = FiltrirajDatum(vrijemeOd);
            Date datumDo = FiltrirajDatum(vrijemeDo);
            
            //System.out.println("Izracunato  vrijemeDo "+vrijemeDo + " vrijemeOd "+vrijemeOd + " datumOd " + datumOd + " datumDo " + datumDo + " izracun : " +(datumDo.getTime()-datumOd.getTime()) +" final "+ (datumDo.getTime()-datumOd.getTime())/1000/60/60);
            return (datumDo.getTime()-datumOd.getTime());
        } catch (ParseException ex) {
            System.out.println("Neispravan format vremena.");
            //Logger.getLogger(VirtualnoVrijeme.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnme;
    }
    
    public static Date FiltrirajDatum(String datum) throws ParseException {
        Date vrijemeVar = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(PocistiDatum(datum));
        return vrijemeVar;
    }
    
    public static String PocistiDatum(String datum){
        String filtriranDatum = datum.replaceAll("[^0-9-: ]", "");
        //System.out.println("Filtrirani datum: " + filtriranDatum);
        return filtriranDatum;
    }

    public static boolean ispravnost(String vrijeme) {
        boolean returnme = false;
        if (izracunajSate(VirtualnoVrijeme.getInstanca().getVrijeme(), vrijeme) > 0) {
            return true;
        }
        return returnme;
    }

    private VirtualnoVrijeme() {
    }

    public static VirtualnoVrijeme getInstanca() {
        if (Instanca == null) {
            synchronized ((VirtualnoVrijeme.class)) {
                Instanca = new VirtualnoVrijeme();
            }
        }
        return Instanca;
    }

    public static void setInstance(VirtualnoVrijeme instanca) {
        Instanca = instanca;
    }

    public static String getVrijeme() {
        return vrijeme;
    }

    public static void setVrijeme(String vrijeme) {
        String filtriranDatum = vrijeme.replaceAll("[^0-9-: ]", "");
        //System.out.println("Postavljeno vrijeme na: " + filtriranDatum);
        VirtualnoVrijeme.getInstanca().vrijeme = filtriranDatum;
        
        //Povecaj baterije
        if (Tvrtka.getInstanca().getSviKapaciteti() != null) {
            for (Lokacije_kapaciteti lokacije_kapaciteti : Tvrtka.getInstanca().getSviKapaciteti()) {
                int brojRaspolozivihFix = 0;
                for (Vozilo vozilo : lokacije_kapaciteti.getVozila()) {
                    if (vozilo.baterija < 100) {
                        vozilo.napuniBateriju();
                        vozilo.setState(new NaPunjenju());
                    }
                    //zadaca 2 modifikacija
                    if (vozilo.baterija == 100 && vozilo.ispravno_stanje) {
                        brojRaspolozivihFix++;
                        //Zadaca 3 modifikacija
                        vozilo.setState(new Slobodno());
                    }else{
                        //System.out.println("Greska? "+ vozilo.naziv + " " + vozilo.baterija);
                    }
                }
                //koristiti ovo u slucaju da se misli na raspolozivo = 100%
                lokacije_kapaciteti.setRaspolozivi(brojRaspolozivihFix);
                
            }
        }
    }
    
    public static void reverseVrijeme(String vrijeme){
        String filtriranDatum = vrijeme.replaceAll("[^0-9-: ]", "");
        //System.out.println("Postavljeno vrijeme na: " + filtriranDatum);
        VirtualnoVrijeme.getInstanca().vrijeme = filtriranDatum;
    }

}
