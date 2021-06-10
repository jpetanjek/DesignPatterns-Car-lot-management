package jpetanjek.jpetanjek_zadaca_3.FactoryMethod.ConcreteProduct;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.Product.DatotekaProduct;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Lokacije_kapaciteti;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Vozilo;
import jpetanjek.jpetanjek_zadaca_3.Singleton.Tvrtka;

/**
 *
 * @author Josip Petanjek
 */
public class KapacitetiDatotekaProduct implements DatotekaProduct {

    @Override
    public List<Lokacije_kapaciteti> getConcreteProducts(String nazivDatoteke) {
        List<Lokacije_kapaciteti> output = new ArrayList<>();
        int brojId = 0;
        String linija = "";

        try ( BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(nazivDatoteke), "UTF-8"))) {
            br.readLine(); //za prvu liniju
            while ((linija = br.readLine()) != null) {

                String[] lokacijaLinija = linija.split(";", -1);
                Lokacije_kapaciteti lokacija = new Lokacije_kapaciteti();
                try {
                    if (Tvrtka.getInstanca().getLokacijaById(Integer.parseInt(lokacijaLinija[0].trim())) != null) {
                        if (Tvrtka.getInstanca().getVoziloById(Integer.parseInt(lokacijaLinija[1].trim())) != null) {
                            lokacija.setId_lokacije(Integer.parseInt(lokacijaLinija[0].trim()));
                            lokacija.setId_vrste_vozila(Integer.parseInt(lokacijaLinija[1].trim()));
                            lokacija.setBroj_mjesta(Integer.parseInt(lokacijaLinija[2].trim()));
                            lokacija.setRaspolozivi(Integer.parseInt(lokacijaLinija[3].trim()));

                            if (lokacija.getBroj_mjesta() >= lokacija.getRaspolozivi()) {

                                List<Vozilo> lista = new ArrayList();
                                Vozilo voziloDefault = Tvrtka.getInstanca().getVoziloById(lokacija.getId_vrste_vozila());
                                for (int i = 0; i < lokacija.getRaspolozivi(); i++) {
                                    Vozilo vozilo = new Vozilo();

                                    vozilo.setIdVrsteVozila(voziloDefault.getIdVrsteVozila());
                                    vozilo.setNaziv(voziloDefault.getNaziv());
                                    vozilo.setDomet(voziloDefault.getDomet());
                                    vozilo.setVrijeme_punjenja(voziloDefault.getVrijeme_punjenja());

                                    vozilo.setBaterija(100);
                                    vozilo.setPrijedeni_km(0);
                                    vozilo.setVrijemeUnajmljivanjaVracanja("");

                                    //Zadaca 2 modifikacije
                                    vozilo.setIdVozila(brojId++);
                                    vozilo.setBroj_unajmljivanja(0);
                                    vozilo.setIspravno_stanje(true);

                                    lista.add(vozilo);
                                }

                                lokacija.setVozila(lista);

                                //System.out.println(lokacija.getId_lokacije() + " " + lokacija.getId_vrste_vozila() + " " + lokacija.getBroj_mjesta()+ " " + lokacija.getRaspolozivi()+ " ");
                                output.add(lokacija);
                            } else {
                                throw new Exception("Broj raspolozivih vozila je veci od broja mjesta u datoteci kapaciteta, postavljeno na broj mjesta.");
                            }
                        } else {
                            throw new Exception("Ne postoji vozilo sa ovim id.");
                        }
                    } else {
                        throw new Exception("Ne postoji lokacija sa ovim id.");
                    }
                } catch (Exception e) {
                    Tvrtka.getInstanca().getController().setError(linija);
                    Tvrtka.getInstanca().getController().setError("Zapis kapaciteta lokacija neispravan, nije moguće stvoriti objekt!" + e.getMessage());
                }
            }
        } catch (Exception e) {
            Tvrtka.getInstanca().getController().setError("Dogodila se greška prilikom čitanja datoteke kapaciteta lokacija" + e.getMessage());
            System.exit(-1);
        }
        return output;
    }

}
