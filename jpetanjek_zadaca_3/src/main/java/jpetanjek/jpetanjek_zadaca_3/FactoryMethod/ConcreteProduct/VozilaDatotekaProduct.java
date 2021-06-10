package jpetanjek.jpetanjek_zadaca_3.FactoryMethod.ConcreteProduct;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.Product.DatotekaProduct;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Vozilo;
import jpetanjek.jpetanjek_zadaca_3.Singleton.Tvrtka;
import jpetanjek.jpetanjek_zadaca_3.State.Slobodno;

/**
 *
 * @author Josip Petanjek
 */
public class VozilaDatotekaProduct implements DatotekaProduct {

    @Override
    public List<Vozilo> getConcreteProducts(String nazivDatoteke) {
        List<Vozilo> output = new ArrayList<>();
        
        String linija = "";

        try ( BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(nazivDatoteke), "UTF-8"))) {
            br.readLine(); //za prvu liniju
            while ((linija = br.readLine()) != null) {

                String[] voziloLinija = linija.split(";", -1);
                Vozilo vozilo = new Vozilo();
                try {
                    boolean postoji = false;
                    for (Vozilo vozilo1 : output) {
                        if(vozilo1.getIdVrsteVozila()==Integer.parseInt(voziloLinija[0].trim())){
                            postoji=true;
                        }
                    }
                    
                    if (postoji==false) {
                        
                        vozilo.setIdVrsteVozila(Integer.parseInt(voziloLinija[0].trim()));
                        vozilo.setNaziv(voziloLinija[1].trim());
                        vozilo.setVrijeme_punjenja(Long.parseLong(voziloLinija[2].trim()));
                        vozilo.setDomet(Long.parseLong(voziloLinija[3].trim()));
                        //Zadaca 2 modifikacija
                        vozilo.setIspravno_stanje(true);
                        vozilo.setBroj_unajmljivanja(0);
                        
                        //Zadaca 3 modifikacija, stanje
                        vozilo.setState(new Slobodno());

                        //System.out.println(vozilo.id + " " + vozilo.naziv + " " + vozilo.vrijeme_punjenja + " " + vozilo.domet + " ");
                        output.add(vozilo);
                    } else {
                        throw new Exception("Već postoji vozilo sa ovim id.");
                    }
                } catch (Exception e) {
                    Tvrtka.getInstanca().getController().setError(linija);
                    Tvrtka.getInstanca().getController().setError("Zapis vozila neispravan, nije moguće stvoriti objekt!" + e.getMessage());
                }
            }

        } catch (Exception e) {
            Tvrtka.getInstanca().getController().setError("Dogodila se greška prilikom čitanja datoteke vozila " +e);
            System.exit(-1);
        }
        return output;

    }

}
