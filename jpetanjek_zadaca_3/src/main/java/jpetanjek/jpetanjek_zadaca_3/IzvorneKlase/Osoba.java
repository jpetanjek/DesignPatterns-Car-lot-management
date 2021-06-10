package jpetanjek.jpetanjek_zadaca_3.IzvorneKlase;

import java.util.Date;
import java.util.List;
import jpetanjek.jpetanjek_zadaca_3.ChainOfCommand.RacunRequest;
import jpetanjek.jpetanjek_zadaca_3.Singleton.Tvrtka;

/**
 *
 * @author Josip Petanjek
 */
public class Osoba {

    public int id;
    public String imePrezime;
    public int ugovor;
    public double dugovanje;
    public String zadnjiNajamVozila;

    public String getZadnjiNajamVozila() {
        return zadnjiNajamVozila;
    }

    public void setZadnjiNajamVozila(String zadnjiNajamVozila) {
        this.zadnjiNajamVozila = zadnjiNajamVozila;
    }

    public double getDugovanje() {
        return dugovanje;
    }

    public double getDugovanjeSuma() {
        double returnme = 0;
        RacunRequest request = new RacunRequest();
        request.setNesto(this.getId());
        List<Racun> racuniKorisnika = Tvrtka.getInstanca().getRacunHandler().izvedi(2, request).getListaRacuna();
        for (Racun racun : racuniKorisnika) {
            if (!racun.isPlaceni()) {
                returnme += racun.getZarada();
            }
        }
        return returnme;
    }

    public void setDugovanje(double dugovanje) {
        this.dugovanje = dugovanje;
    }

    public int getUgovor() {
        return ugovor;
    }

    public void setUgovor(int ugovor) {
        this.ugovor = ugovor;
    }

    //Zadaca
    public int brojNeispravnihVozila;

    public List<Vozilo> aktivniNajam;

    public Osoba(int id, String ime_prezime, int broj) {
        this.id = id;
        this.imePrezime = ime_prezime;
        this.brojNeispravnihVozila = broj;
    }

    public int getBrojNeispravnihVozila() {
        return brojNeispravnihVozila;
    }

    public void setBrojNeispravnihVozila(int brojNeispravnihVozila) {
        this.brojNeispravnihVozila = brojNeispravnihVozila;
    }

    public Osoba() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String ime_prezime) {
        this.imePrezime = ime_prezime;
    }

    public Vozilo getAktivniNajam(int id_vozila) {
        Vozilo returnme = null;

        for (Vozilo vozilo : aktivniNajam) {
            if (vozilo.getIdVrsteVozila() == id_vozila) {
                return vozilo;
            }
        }

        return returnme;
    }

    public boolean setAktivniNajam(Vozilo voziloZaPostaviti) {
        boolean returnme = false;
        boolean mozePostaviti = true;

        for (Vozilo vozilo : aktivniNajam) {
            if (vozilo.getIdVrsteVozila() == voziloZaPostaviti.getIdVrsteVozila()) {
                mozePostaviti = false;
                break;
            }
        }

        if (mozePostaviti) {
            aktivniNajam.add(voziloZaPostaviti);
            return true;
        }

        return returnme;
    }

    public void obrisiVoziloSaAktivnogNajama(Vozilo voziloZaBrisanje) {
        for (Vozilo vozilo : aktivniNajam) {
            if (vozilo.getIdVrsteVozila() == voziloZaBrisanje.getIdVrsteVozila()) {
                aktivniNajam.remove(vozilo);
                //System.out.println("MAKNUTO VOZILO " + vozilo.getIdVozila());
                break;
            }
        }
    }

}
