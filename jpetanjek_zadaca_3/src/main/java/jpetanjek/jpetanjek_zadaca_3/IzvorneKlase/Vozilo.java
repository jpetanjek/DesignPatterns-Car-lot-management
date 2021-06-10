package jpetanjek.jpetanjek_zadaca_3.IzvorneKlase;

import jpetanjek.jpetanjek_zadaca_3.Singleton.VirtualnoVrijeme;
import jpetanjek.jpetanjek_zadaca_3.State.State;

/**
 *
 * @author Josip Petanjek
 */
public class Vozilo {
    
    private State state;

   public void setState(State state){
      this.state = state;		
   }

   public State getState(){
      return state;
   }
    
    public int idVrsteVozila;
    public String naziv;
    public long vrijeme_punjenja;
    public long domet;
    
    public long prijedeni_km;
    public String vrijemeUnajmljivanjaVracanja; //kod vracanja za izracun kad je unamljeno, kod unamljivanja za izracun baterije
    public double baterija;
    
    //Zadaca 2 nadogradnja
    public boolean ispravno_stanje;
    public int broj_unajmljivanja;
    
    public int idVozila;
    
    public int idLokacijaNajma;

    public int getIdLokacijaNajma() {
        return idLokacijaNajma;
    }

    public void setIdLokacijaNajma(int idLokacijaNajma) {
        this.idLokacijaNajma = idLokacijaNajma;
    }

    public int getIdVozila() {
        return idVozila;
    }

    public void setIdVozila(int idVozila) {
        this.idVozila = idVozila;
    }

    public int getBroj_unajmljivanja() {
        return broj_unajmljivanja;
    }

    public void setBroj_unajmljivanja(int broj_unajmljivanja) {
        this.broj_unajmljivanja = broj_unajmljivanja;
    }

    public boolean getIspravno_stanje() {
        return ispravno_stanje;
    }

    public void setIspravno_stanje(boolean ispravno_stanje) {
        this.ispravno_stanje = ispravno_stanje;
    }
    
    
    public Vozilo(int id, String naziv, long vrijeme_punjenja, long domet) {
        this.idVrsteVozila = id;
        this.naziv = naziv;
        this.vrijeme_punjenja = vrijeme_punjenja;
        this.domet = domet;
    }
    

    public void napuniBateriju(){
        long satiPunjenja = VirtualnoVrijeme.izracunajSate(vrijemeUnajmljivanjaVracanja, VirtualnoVrijeme.getVrijeme())/60/60/1000;
        //System.out.println("vrijeme punjenja " +this.vrijeme_punjenja+" Izracunati sati punjenja: " + VirtualnoVrijeme.izracunajSate(vrijemeUnajmljivanjaVracanja, VirtualnoVrijeme.getVrijeme())/60/60/1000 + " baterija izracun: "+ (double)(satiPunjenja/(double)vrijeme_punjenja)*100);
        baterija = (satiPunjenja/(double)vrijeme_punjenja)*100;
        if(baterija>100){
            baterija=100;
        }
        //System.out.println(this.naziv +" moja baterija je " + this.baterija);
    }
    
    public Vozilo() {
    }
    
    

    public int getIdVrsteVozila() {
        return idVrsteVozila;
    }

    public void setIdVrsteVozila(int id) {
        this.idVrsteVozila = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public long getVrijeme_punjenja() {
        return vrijeme_punjenja;
    }

    public void setVrijeme_punjenja(long vrijeme_punjenja) {
        this.vrijeme_punjenja = vrijeme_punjenja;
    }

    public long getDomet() {
        return domet;
    }

    public void setDomet(long domet) {
        this.domet = domet;
    }

    public long getPrijedeni_km() {
        return prijedeni_km;
    }

    public void setPrijedeni_km(long prijedeni_km) {
        this.prijedeni_km = prijedeni_km;
    }

    public String getVrijemeUnajmljivanjaVracanja() {
        return vrijemeUnajmljivanjaVracanja;
    }

    public void setVrijemeUnajmljivanjaVracanja(String vrijemeUnajmljivanjaVracanja) {
        this.vrijemeUnajmljivanjaVracanja = vrijemeUnajmljivanjaVracanja;
    }

    public double getBaterija() {
        return baterija;
    }

    public void setBaterija(double baterija) {
        this.baterija = baterija;
    }
    
    
    
}
