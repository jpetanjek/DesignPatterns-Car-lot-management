package jpetanjek.jpetanjek_zadaca_3.FactoryMethod.ConcreteProduct;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.Product.DatotekaProduct;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Cjenik;
import jpetanjek.jpetanjek_zadaca_3.Singleton.Tvrtka;

/**
 *
 * @author Josip Petanjek
 */
public class CjenikDatotekaProduct implements DatotekaProduct {

    @Override
    public List<Cjenik> getConcreteProducts(String nazivDatoteke) {
        List<Cjenik> output = new ArrayList<>();
        String linija = "";

        try ( BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(nazivDatoteke), "UTF-8"))) {
            br.readLine(); //za prvu liniju
            while ((linija = br.readLine()) != null) {

                String[] cjenikLinija = linija.split(";", -1);
                Cjenik cjenik = new Cjenik();
                try {
                    if (Tvrtka.getInstanca().getVoziloById(Integer.parseInt(cjenikLinija[0].trim())) != null) {

                        cjenik.setId_vrste_vozila(Integer.parseInt(cjenikLinija[0].trim()));
                        cjenik.setNajam(Double.parseDouble(cjenikLinija[1].trim().replace(",", ".")));
                        cjenik.setPo_satu(Double.parseDouble(cjenikLinija[2].trim().replace(",", ".")));
                        cjenik.setPo_km(Double.parseDouble(cjenikLinija[3].trim().replace(",", ".")));

                        //System.out.println(Double.parseDouble(cjenikLinija[1].trim().replace(",", ".")));
                        //System.out.println(cjenik.getId_vrste_vozila() + " " + cjenik.getNajam() + " " + cjenik.getPo_satu() + " " + cjenik.getPo_km() + " ");
                        output.add(cjenik);
                    } else {
                        throw new Exception("Ne postoji vozilo sa ovim id.");
                    }
                } catch (Exception e) {
                    Tvrtka.getInstanca().getController().setError(linija);
                    Tvrtka.getInstanca().getController().setError("Zapis cjenik neispravan, nije moguće stvoriti objekt!" + e.getMessage());
                }
            }
        } catch (Exception e) {
            Tvrtka.getInstanca().getController().setError("Dogodila se greška prilikom čitanja datoteke cjenik" + e.getMessage());
            System.exit(-1);
        }
        return output;
    }

}
