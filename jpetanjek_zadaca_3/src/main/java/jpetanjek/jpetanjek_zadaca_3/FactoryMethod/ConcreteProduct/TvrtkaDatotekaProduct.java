/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.FactoryMethod.ConcreteProduct;

import jpetanjek.jpetanjek_zadaca_3.Composite.OrganizacijskaJedinica;
import jpetanjek.jpetanjek_zadaca_3.Composite.OrganizacijskaJedinicaComposite;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.Product.DatotekaProduct;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.OrgJedModel;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Vozilo;
import jpetanjek.jpetanjek_zadaca_3.Singleton.Tvrtka;

/**
 *
 * @author Joc
 */
public class TvrtkaDatotekaProduct implements DatotekaProduct {

    @Override
    public List<?> getConcreteProducts(String nazivDatoteke) {
        List<OrgJedModel> output = new ArrayList<>();

        String linija = "";

        try ( BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(nazivDatoteke), "UTF-8"))) {
            br.readLine(); //za prvu liniju
            while ((linija = br.readLine()) != null) {

                String[] orgLinija = linija.split(";", -1);
                OrgJedModel orgJed = new OrgJedModel();
                try {
                    boolean postoji = false;
                    for (OrgJedModel vozilo1 : output) {
                        if (vozilo1.getId() == Integer.parseInt(orgLinija[0].trim())) {
                            postoji = true;
                        }
                    }

                    if (postoji == false) {

                        /*
                        orgJed.setIdVrsteVozila(Integer.parseInt(orgLinija[0].trim()));
                        orgJed.setNaziv(orgLinija[1].trim());
                        orgJed.setVrijeme_punjenja(Long.parseLong(orgLinija[2].trim()));
                        orgJed.setDomet(Long.parseLong(orgLinija[3].trim()));
                        //Zadaca 2 modifikacija
                        orgJed.setIspravno_stanje(true);
                        orgJed.setBroj_unajmljivanja(0);
                         */
                        orgJed.setId(Integer.parseInt(orgLinija[0].trim()));
                        orgJed.setNaziv(orgLinija[1].trim());
                        if (orgLinija[2].trim().isEmpty()) {
                            orgJed.setIzvorni(true);
                        } else {
                            orgJed.setNadredeni(Integer.parseInt(orgLinija[2].trim()));
                        }
                        //System.out.println("Test: " + Integer.parseInt(orgLinija[2].trim()));
                        //orgJed.setNadredeni(Integer.parseInt(orgLinija[2].trim()));

                        List<Integer> privremena = new ArrayList<>();
                        if (!orgLinija[3].trim().isEmpty()) {
                            String[] lokacijaLinija = orgLinija[3].split(",", -1);
                            for (String string : lokacijaLinija) {
                                privremena.add(Integer.parseInt(string.trim()));
                                //System.out.println("TvrtkaDatotekaProduct lokacija: " + Integer.parseInt(string.trim()));
                            }
                        }
                        orgJed.setLokacije(privremena);

                        //System.out.println(orgJed.getId() + " " + orgJed.getNaziv() + " " + orgJed.getNadredeni());
                        if(orgJed.getId()!=orgJed.getNadredeni()){
                            output.add(orgJed);
                        }else{
                            throw new Exception("Organizacijska jedinica ne moze biti nadredena samoj sebi!");
                        }
                    } else {
                        throw new Exception("Već postoji organizacijska jedinica sa ovim id.");
                    }
                } catch (Exception e) {
                    Tvrtka.getInstanca().getController().setError(linija);
                    Tvrtka.getInstanca().getController().setError("Zapis tvrtke je neispravan, nije moguće stvoriti objekt!" + e.getMessage());
                }
            }

        } catch (Exception e) {
            Tvrtka.getInstanca().getController().setError("Dogodila se greška prilikom čitanja datoteke tvrtke " + e);
            System.exit(-1);
        }
        //builder ne return - vrati ono iz buildera
        return output;

    }

}
