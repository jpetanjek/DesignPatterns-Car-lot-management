package jpetanjek.jpetanjek_zadaca_3.FactoryMethod.ConcreteProduct;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.Product.DatotekaProduct;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Aktivnost;
import jpetanjek.jpetanjek_zadaca_3.Singleton.Tvrtka;

/**
 *
 * @author Josip Petanjek
 */
public class AktivnostDatotekaProduct implements DatotekaProduct  {

    @Override
    public List<Aktivnost> getConcreteProducts(String nazivDatoteke) {
        
        List<Aktivnost> output = new ArrayList<>();
        String linija = "";

        try ( BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(nazivDatoteke), StandardCharsets.UTF_8))) {
            br.readLine(); //za prvu liniju
            while ((linija = br.readLine()) != null) {
                
                try {
                    
                    Aktivnost aktivnost = Aktivnost.AktivnostFromString(linija);
                    
                    output.add(aktivnost);
                } catch (Exception e) {
                    Tvrtka.getInstanca().getController().setError(linija);
                    Tvrtka.getInstanca().getController().setError("Zapis aktivnosti neispravan, nije moguće stvoriti objekt! Iznimka: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            Tvrtka.getInstanca().getController().setError("Dogodila se greška prilikom čitanja datoteke aktivnosti " +  e.getMessage());
        }
        return output;
        
    }
    
}
