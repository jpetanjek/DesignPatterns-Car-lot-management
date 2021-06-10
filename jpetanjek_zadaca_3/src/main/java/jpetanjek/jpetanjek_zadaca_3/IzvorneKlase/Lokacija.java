package jpetanjek.jpetanjek_zadaca_3.IzvorneKlase;

/**
 *
 * @author Josip Petanjek
 */
public class Lokacija {
    public int id;
    public String naziv;
    public String adresa;
    public String gps;

    public Lokacija(int id, String naziv, String adresa, String gps) {
        this.id = id;
        this.naziv = naziv;
        this.adresa = adresa;
        this.gps = gps;
    }

    public Lokacija() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }
    
    
    
}
