package jpetanjek.jpetanjek_zadaca_3.FactoryMethod.ConcreteProduct;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.Product.DatotekaProduct;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Osoba;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Vozilo;
import jpetanjek.jpetanjek_zadaca_3.Singleton.Tvrtka;

/**
 *
 * @author Josip Petanjek
 */
public class OsobaDatotekaProduct implements DatotekaProduct {

    @Override
    public List<Osoba> getConcreteProducts(String nazivDatoteke) {
        List<Osoba> output = new ArrayList<>();
        String linija = "";

        try ( BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(nazivDatoteke), "UTF-8"))) {
            br.readLine(); //za prvu liniju
            while ((linija = br.readLine()) != null) {

                String[] osobaLinija = linija.split(";", -1);
                Osoba osoba = new Osoba();
                try {
                    boolean postoji = false;
                    for (Osoba vozilo1 : output) {
                        if(vozilo1.getId()==Integer.parseInt(osobaLinija[0].trim())){
                            postoji=true;
                        }
                    }
                    if (postoji==false) {

                        osoba.setId(Integer.parseInt(osobaLinija[0].trim()));
                        osoba.setImePrezime(osobaLinija[1]);
                        osoba.setBrojNeispravnihVozila(0);
                        osoba.setUgovor(Integer.parseInt(osobaLinija[2].trim()));
                        osoba.setDugovanje(0);
                        osoba.setZadnjiNajamVozila("");
                        
                        List<Vozilo> lista= new ArrayList<>();
                        osoba.aktivniNajam=lista;

                        //System.out.println(osoba.id + " " + osoba.imePrezime+ " "+ osoba.ugovor);
                        output.add(osoba);
                    } else {
                        throw new Exception("Već postoji osoba sa ovim id.");
                    }

                } catch (Exception e) {
                    Tvrtka.getInstanca().getController().setError(linija);
                    Tvrtka.getInstanca().getController().setError("Zapis Osoba neispravan, nije moguće stvoriti objekt!" + e.getMessage());
                }
            }

        } catch (Exception e) {
            Tvrtka.getInstanca().getController().setError("Dogodila se greška prilikom čitanja datoteke Osoba " + e.getMessage());
            System.exit(-1);
        }
        return output;
    }

}
