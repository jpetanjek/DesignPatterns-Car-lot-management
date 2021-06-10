package jpetanjek.jpetanjek_zadaca_3.IzvorneKlase;

import jpetanjek.jpetanjek_zadaca_3.Composite.OrganizacijskaJedinica;
import java.util.Date;
import java.util.List;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Lokacija;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Racun;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Vozilo;
import jpetanjek.jpetanjek_zadaca_3.Facade.FacadeSustavPronalaska;
import jpetanjek.jpetanjek_zadaca_3.Singleton.Tvrtka;

public class FormatiranjeTablica {

    //tekst -lijevo -dt
    //brojke -desno -dc
    //long -desno -dd i -dc
    
    public  static String formatiraj(String unos){
        //tekst -lijevo -dt
        
        int brojZnakova=Tvrtka.getInstanca().getDt();
        int padding = (brojZnakova - unos.length()) ;
        if (padding <= 0) {
            return "|"+unos.substring(0, brojZnakova)+"|";
        }
        
        String desno = "%" + (padding) + "s|";
        
        
        String lijevo = "|%s";
        
        String strFormat = lijevo + "%s" + desno;
        String formattedString = String.format(strFormat, "", "$", "");
        String returnme = formattedString.replace(' ', ' ').replace("$", unos);
        return returnme;
        
    }
    
    public  static String formatirajNaslovInt(String unos){
        //tekst -lijevo -dt
        
        int brojZnakova=Tvrtka.getInstanca().getDc();
        int padding = (brojZnakova - unos.length()) ;
        if (padding <= 0) {
            return "|" +unos.substring(0, brojZnakova)+"|" ;
        }
        
        String lijevo = "|%" + (padding) + "s";
        
        
        String desno = "%s|";
        
        String strFormat = lijevo + "%s" + desno;
        String formattedString = String.format(strFormat, "", "$", "");
        String returnme = formattedString.replace(' ', ' ').replace("$", unos);
        return returnme;
        
    }
    
    public  static String formatirajNaslovDouble(String unos){
        //tekst -lijevo -dt
        
        int brojZnakova=Tvrtka.getInstanca().getDc()+Tvrtka.getInstanca().getDd();
        int padding = (brojZnakova - unos.length()) ;
        if (padding <= 0) {
            return "|" +unos.substring(0, brojZnakova)+"|" ;
        }
        
        String lijevo = "|%" + (padding) + "s";
        
        
        String desno = "%s|";
        
        String strFormat = lijevo + "%s" + desno;
        String formattedString = String.format(strFormat, "", "$", "");
        String returnme = formattedString.replace(' ', ' ').replace("$", unos);
        return returnme;
        
    }
    
    public  static String formatiraj(int broj){
        //brojke -desno -dc
        
        int brojZnakova=Tvrtka.getInstanca().getDc();
        String unos=String.valueOf(broj);
        int padding = (brojZnakova - unos.length()) ;
        if (padding <= 0) {
            return "|" +unos.substring(0, brojZnakova)+"|" ;
        }
        
        String lijevo = "|%" + (padding) + "s";
        
        
        String desno = "%s|";
        
        String strFormat = lijevo + "%s" + desno;
        String formattedString = String.format(strFormat, "", "$", "");
        String returnme = formattedString.replace(' ', ' ').replace("$", unos);
        return returnme;
        
        
    }
    
    public  static String formatiraj(double broj){
        //long -desno -dd i -dc
        //ne radi ispravno
        
        int brojZnakova=Tvrtka.getInstanca().getDc()+Tvrtka.getInstanca().getDd();
        String unos=zaokruzi(broj,Tvrtka.getInstanca().getDd());
        int padding = ((brojZnakova) - unos.length());
        if (padding <= 0) {
            return "|" +unos.substring(0, brojZnakova)+"|" ;
        }
        
        String lijevo = "|%" + (padding) + "s";
        
        
        String desno = "%s|";
        
        String strFormat = lijevo + "%s" + desno;
        String formattedString = String.format(strFormat, "", "$", "");
        String returnme = formattedString.replace(' ', ' ').replace("$", unos);
        return returnme;
        
        
        
    }
    
    private static String zaokruzi(double broj, int decimala)
        {
            return String.format("%."+decimala+"f", broj);
        }
    
    

    public static String getLokacijeOrganizacijeLinija(OrganizacijskaJedinica nesto) {
        String returnme = "";
        int trenutniBroj = 1;
        for (Lokacija lokacija : nesto.DohvatiLok()) {
            returnme += lokacija.getNaziv();
            if (nesto.DohvatiLok().size() != trenutniBroj) {
                returnme += ", ";
                trenutniBroj++;
            }
        }
        return returnme;
    }

    static int getKumulativnoBrojNajmovaLokacija(OrganizacijskaJedinica organizacijskaJedinica, Date datumOd, Date datumDo) {
        int returnme = 0;
        for (Lokacija lokacija : organizacijskaJedinica.DohvatiLok()) {
            for (Vozilo vozilo : Tvrtka.getSvaVozila()) {
                if (Tvrtka.getKapacitetByVoziloLokacija(vozilo.idVrsteVozila, lokacija.getId()) != null) {
                    List<Racun> racuniLokacije = FacadeSustavPronalaska.getInstanca().PronadiRacuneLokacije(lokacija.getId());

                    for (Racun racun : racuniLokacije) {
                        //vrsta vozila, datumOd, datumDo
                        //System.out.println("datumOd after " + racun.getDatumNajma().after(datumOd) + " datumOd equal " + racun.getDatumNajma().equals(datumOd) + " datumDo before " + racun.getDatumNajma().before(datumDo) + " datumDo equals " + racun.getDatumNajma().equals(datumOd));
                        if (racun.getIdVrsteVozila() == vozilo.getIdVrsteVozila() && (racun.getDatumNajma().after(datumOd) || racun.getDatumNajma().equals(datumOd)) && (racun.getDatumNajma().before(datumDo) || racun.getDatumNajma().equals(datumOd))) {
                            returnme++;
                        }
                    }
                }
            }

        }
        return returnme;
    }

    static int getKumulativnoBrojDanaLokacija(OrganizacijskaJedinica organizacijskaJedinica, Date datumOd, Date datumDo) {
        int returnme = 0;
        for (Lokacija lokacija : organizacijskaJedinica.DohvatiLok()) {
            for (Vozilo vozilo : Tvrtka.getSvaVozila()) {
                if (Tvrtka.getKapacitetByVoziloLokacija(vozilo.idVrsteVozila, lokacija.getId()) != null) {
                    List<Racun> racuniLokacije = FacadeSustavPronalaska.getInstanca().PronadiRacuneLokacije(lokacija.getId());

                    for (Racun racun : racuniLokacije) {
                        //vrsta vozila, datumOd, datumDo
                        //System.out.println("datumOd after " + racun.getDatumNajma().after(datumOd) + " datumOd equal " + racun.getDatumNajma().equals(datumOd) + " datumDo before " + racun.getDatumNajma().before(datumDo) + " datumDo equals " + racun.getDatumNajma().equals(datumOd));
                        if (racun.getIdVrsteVozila() == vozilo.getIdVrsteVozila() && (racun.getDatumNajma().after(datumOd) || racun.getDatumNajma().equals(datumOd)) && (racun.getDatumNajma().before(datumDo) || racun.getDatumNajma().equals(datumOd))) {
                            returnme += (racun.getDatumNajma().getTime() - racun.getDatumVracanja().getTime()) / 1000 / 60 / 60 / 24;
                        }
                    }
                }

            }
        }
        return returnme;
    }
}
