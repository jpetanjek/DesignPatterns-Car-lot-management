package jpetanjek.jpetanjek_zadaca_3.FactoryMethod.ConcreteProduct;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.Product.DatotekaProduct;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Lokacija;
import jpetanjek.jpetanjek_zadaca_3.Singleton.Tvrtka;

/**
 *
 * @author Josip Petanjek
 */
public class LokacijeDatotekaProduct implements DatotekaProduct {

    @Override
    public List<Lokacija> getConcreteProducts(String nazivDatoteke) {
        List<Lokacija> output = new ArrayList<>();
        String linija = "";

        try ( BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(nazivDatoteke), "UTF-8"))) {
            br.readLine(); //za prvu liniju
            while ((linija = br.readLine()) != null) {

                String[] lokacijaLinija = linija.split(";", -1);
                Lokacija lokacija = new Lokacija();
                try {
                    boolean postoji = false;
                    for (Lokacija vozilo1 : output) {
                        if(vozilo1.getId()==Integer.parseInt(lokacijaLinija[0].trim())){
                            postoji=true;
                        }
                    }
                    if (postoji==false) {

                        lokacija.setId(Integer.parseInt(lokacijaLinija[0].trim()));
                        lokacija.setNaziv(lokacijaLinija[1].trim());
                        lokacija.setAdresa(lokacijaLinija[2].trim());
                        lokacija.setGps(lokacijaLinija[3].trim());

                        //System.out.println(lokacija.id + " " + lokacija.naziv + " " + lokacija.getAdresa() + " " + lokacija.getGps() + " ");
                        output.add(lokacija);

                    } else {
                        throw new Exception("Već postoji lokacija sa ovim id.");
                    }
                } catch (Exception e) {
                    Tvrtka.getInstanca().getController().setError(linija);
                    Tvrtka.getInstanca().getController().setError("Zapis lokacija neispravan, nije moguće stvoriti objekt!" + e.getMessage());
                }
            }
        } catch (Exception e) {
            Tvrtka.getInstanca().getController().setError("Dogodila se greška prilikom čitanja datoteke lokacija" + e.getMessage());
            System.exit(-1);
        }
        return output;
    }

}
