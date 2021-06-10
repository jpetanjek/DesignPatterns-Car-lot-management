package jpetanjek.jpetanjek_zadaca_3.IzvorneKlase;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josip Petanjek
 */
public class Lokacije_kapaciteti {
    public int id_lokacije;
    public Lokacija lokacija;
    public int id_vrste_vozila;
    public List<Vozilo> vozila;
    public int broj_mjesta;
    public int raspolozivi; //vozila sa 100% baterije

    public Lokacije_kapaciteti(int id_lokacije, int id_vrste_vozila, int broj_mjesta, int raspolozivi) {
        this.id_lokacije = id_lokacije;
        this.id_vrste_vozila = id_vrste_vozila;
        this.broj_mjesta = broj_mjesta;
        this.raspolozivi = raspolozivi;
    }
    
    public int getNeraspolozivi(){
        int returnme=0;
        for (Vozilo vozilo : vozila) {
            if(!vozilo.ispravno_stanje){
                returnme++;
            }
        }
        return returnme;
    }

    public Lokacije_kapaciteti() {
    }

    public int getId_lokacije() {
        return id_lokacije;
    }

    public void setId_lokacije(int id_lokacije) {
        this.id_lokacije = id_lokacije;
    }

    public int getId_vrste_vozila() {
        return id_vrste_vozila;
    }

    public void setId_vrste_vozila(int id_vrste_vozila) {
        this.id_vrste_vozila = id_vrste_vozila;
    }

    public int getBroj_mjesta() {
        return broj_mjesta;
    }

    public void setBroj_mjesta(int broj_mjesta) {
        this.broj_mjesta = broj_mjesta;
    }

    public int getRaspolozivi() {
        return raspolozivi;
    }

    public void setRaspolozivi(int raspolozivi) {
        this.raspolozivi = raspolozivi;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

    public List<Vozilo> getVozila() {
        return vozila;
    }

    public void setVozila(List<Vozilo> vozila) {
        this.vozila = vozila;
    }
    
    public static Vozilo dohvatiVoziloSaBaterijom(List<Vozilo> vozila){
        Vozilo returnme= new Vozilo();
        returnme.broj_unajmljivanja=Integer.MAX_VALUE;    
        returnme.prijedeni_km=Long.MAX_VALUE;
        
        List<Vozilo> vozila_sa_punom_baterijom = new ArrayList<>();
        List<Vozilo> vozila_sa_najmanjim_najmom = new ArrayList<>();
        List<Vozilo> vozila_sa_najmanjim_km = new ArrayList<>();
        
        for (Vozilo vozilo : vozila) {
            //System.out.println("Dohvaceno vozilo sa baterijom u lok kap: " + vozilo.baterija);
            if(vozilo.baterija==100 && vozilo.getIspravno_stanje()){
                vozila_sa_punom_baterijom.add(vozilo);
            }
        }
        
        for (Vozilo vozilo : vozila_sa_punom_baterijom) {
            if(vozilo.getBroj_unajmljivanja()<returnme.getBroj_unajmljivanja()){
                returnme=vozilo;
                vozila_sa_najmanjim_najmom.clear();
                vozila_sa_najmanjim_najmom.add(vozilo);
            }else if(vozilo.getBroj_unajmljivanja()==returnme.getBroj_unajmljivanja()){
                vozila_sa_najmanjim_najmom.add(vozilo);
            }
        }
        
        for (Vozilo vozilo : vozila_sa_najmanjim_najmom) {
            if(vozilo.getPrijedeni_km()<returnme.getPrijedeni_km()){
                returnme=vozilo;
                vozila_sa_najmanjim_km.clear();
                vozila_sa_najmanjim_km.add(vozilo);
            }else if(vozilo.getPrijedeni_km()==returnme.getPrijedeni_km()){
                vozila_sa_najmanjim_km.add(vozilo);
            }            
        }
        
        for (Vozilo vozilo : vozila_sa_najmanjim_km) {
            if(vozilo.getIdVrsteVozila()<returnme.getIdVrsteVozila()){
                returnme=vozilo;
            }
        }
        
        return returnme;
    }
    
    public boolean obrisiVozilo (Vozilo vozilo){
        this.raspolozivi--;
        return this.vozila.remove(vozilo);
    }
}
