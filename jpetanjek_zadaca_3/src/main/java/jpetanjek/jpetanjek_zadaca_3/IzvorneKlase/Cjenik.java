package jpetanjek.jpetanjek_zadaca_3.IzvorneKlase;

/**
 *
 * @author Josip Petanjek
 */
public class Cjenik {
    public int id_vrste_vozila;
    public double najam;
    public double po_satu;
    public double po_km;

    public Cjenik(int id_vrste_vozila, double najam, double po_satu, double po_km) {
        this.id_vrste_vozila = id_vrste_vozila;
        this.najam = najam;
        this.po_satu = po_satu;
        this.po_km = po_km;
    }

    public Cjenik() {
    }

    public int getId_vrste_vozila() {
        return id_vrste_vozila;
    }

    public void setId_vrste_vozila(int id_vrste_vozila) {
        this.id_vrste_vozila = id_vrste_vozila;
    }

    public double getNajam() {
        return najam;
    }

    public void setNajam(double najam) {
        this.najam = najam;
    }

    public double getPo_satu() {
        return po_satu;
    }

    public void setPo_satu(double po_satu) {
        this.po_satu = po_satu;
    }

    public double getPo_km() {
        return po_km;
    }

    public void setPo_km(double po_km) {
        this.po_km = po_km;
    }
    
    
}
