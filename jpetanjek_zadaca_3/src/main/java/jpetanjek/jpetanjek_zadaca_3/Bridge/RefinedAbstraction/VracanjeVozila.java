package jpetanjek.jpetanjek_zadaca_3.Bridge.RefinedAbstraction;

import jpetanjek.jpetanjek_zadaca_3.Bridge.Implementor.IAktivnost;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Aktivnost;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Cjenik;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Lokacija;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Lokacije_kapaciteti;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Osoba;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Racun;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Vozilo;
import jpetanjek.jpetanjek_zadaca_3.Singleton.SustavRacuna;
import jpetanjek.jpetanjek_zadaca_3.Singleton.Tvrtka;
import jpetanjek.jpetanjek_zadaca_3.Singleton.VirtualnoVrijeme;
import jpetanjek.jpetanjek_zadaca_3.State.NaPunjenju;
import jpetanjek.jpetanjek_zadaca_3.State.Neispravno;

/**
 *
 * @author Josip Petanjek
 */
public class VracanjeVozila extends Aktivnost implements IAktivnost {

    private int id_korisnika;
    private int id_lokacije;
    private int id_vrste_vozila;
    private long broj_km;
    private  String opis_problema;

    

    @Override
    public void izvedi() throws Exception {
        super.izvedi();

        Osoba osoba = Tvrtka.getInstanca().getOsobaById(id_korisnika);
        Lokacija lokacija = Tvrtka.getInstanca().getLokacijaById(id_lokacije);
        
            if (VirtualnoVrijeme.ispravnost(vrijeme)) {
                if (lokacija != null) {
                    if (osoba != null) {
                        if (osoba.getAktivniNajam(id_vrste_vozila) != null) {
                            Vozilo vozilo = osoba.getAktivniNajam(id_vrste_vozila);
                            Cjenik cjenik = Tvrtka.getInstanca().getCjenikById(vozilo.getIdVrsteVozila());
                            if (cjenik != null) {
                                Lokacije_kapaciteti kapacitet = Tvrtka.getInstanca().getKapacitetByVoziloLokacija(vozilo.getIdVrsteVozila(), lokacija.getId());
                                if (kapacitet != null && kapacitet.getBroj_mjesta() > kapacitet.getVozila().size()) {
                                    if (vozilo.getDomet() >= broj_km) {
                                        if (vozilo.getIdVrsteVozila() == id_vrste_vozila) {

                                            vozilo.setPrijedeni_km(vozilo.getPrijedeni_km() + broj_km);

                                            long brojSati = VirtualnoVrijeme.izracunajSate(vozilo.getVrijemeUnajmljivanjaVracanja(), this.vrijeme) / 60 / 1000;
                                            if(brojSati%60!=0){
                                                brojSati = brojSati /60;
                                                brojSati++;
                                            }else{
                                                brojSati=brojSati / 60;
                                            }
                                            
                                            double racun = cjenik.getNajam() + (brojSati * cjenik.getPo_satu()) + (broj_km * cjenik.getPo_km());
                                            
                                            //provjeriti da li je ispravno stanje
                                            if(!opis_problema.equals("")){
                                                opis_problema =  " te prijavljuje da vozilo ima problem '"+ opis_problema +"'";
                                                vozilo.setIspravno_stanje(false);
                                                
                                                //Zadaca 3 modifikacija, state
                                                vozilo.setState(new Neispravno());
                                            }else{
                                                vozilo.setState(new NaPunjenju());
                                            }

                                            String ispis = "U " + vrijeme + " korisnik " + osoba.getImePrezime()
                                                    + " na lokaciji " + lokacija.getNaziv() + " vraća unajmljeni " + vozilo.getNaziv()
                                                    + " koji ima ukupno " + vozilo.getPrijedeni_km()
                                                    + " km"+opis_problema+". Stavke računa su: 1 najam  " + vozilo.getNaziv() + " - "
                                                    + cjenik.getNajam() + " kn, najma je bio "
                                                    + brojSati + " sata - " + brojSati + " * " + cjenik.getPo_satu() + " kn = "
                                                    + (brojSati * cjenik.getPo_satu()) + " kn, prethodno stanje bilo je "
                                                    + (vozilo.getPrijedeni_km() - broj_km) + " km znači da je prošao " + broj_km
                                                    + " km  - " + broj_km + " * " + cjenik.getPo_km() + " kn = " + (broj_km * cjenik.getPo_km())
                                                    + " kn. Račun ukupno iznosi " + cjenik.getNajam() + " kn + " + (brojSati * cjenik.getPo_satu())
                                                    + " kn  + " + (broj_km * cjenik.getPo_km()) + " kn = " + racun + "kn.";
                                            Tvrtka.getInstanca().getController().setModel(ispis);
                                            
                                            //Izradi racun preko sustava
                                            Racun noviRacun = new Racun();
                                            
                                            noviRacun.setDatumNajma(VirtualnoVrijeme.FiltrirajDatum(this.vrijeme));
                                            noviRacun.setDatumVracanja(VirtualnoVrijeme.FiltrirajDatum(vozilo.getVrijemeUnajmljivanjaVracanja()));
                                            noviRacun.setIdLokacijaVracanja(lokacija.getId());
                                            noviRacun.setIdLokacijaNajma(vozilo.getIdLokacijaNajma());
                                            noviRacun.setIdVrsteVozila(vozilo.getIdVrsteVozila());
                                            noviRacun.setOsoba(osoba);
                                            noviRacun.setTekst(ispis);
                                            noviRacun.setZarada(racun);
                                            //DZ 3 - zapisi dugovanje ako ima ugovor
                                            if(osoba.getUgovor()==1){
                                                osoba.dugovanje+=racun;
                                                noviRacun.setPlaceni(false);
                                            }else{
                                                noviRacun.setPlaceni(true);
                                            }
                                            
                                            //dodaj ga u sustav
                                            SustavRacuna.getInstanca().dodajRacun(noviRacun);
                                            


                                            //Postavi vozilu kada je vraceno
                                            vozilo.vrijemeUnajmljivanjaVracanja = this.vrijeme;
                                            //Isprazni bateriju
                                            vozilo.baterija=100-(((double)broj_km/(double)vozilo.domet)*100);
                                            //System.out.println(vozilo.baterija + " "+ broj_km + " "+ vozilo.domet + " " + (((double)broj_km/(double)vozilo.domet)*100));
                                            //Premjesti ga na lokaciju
                                            Tvrtka.getInstanca().dodajVoziloKapacitetu(vozilo, lokacija);
                                            //Obrisi ga sa osobe
                                            osoba.obrisiVoziloSaAktivnogNajama(vozilo);
                                            //Postavi virtualno vrijeme
                                            VirtualnoVrijeme.setVrijeme(this.vrijeme);
                                        } else {
                                            throw new Exception("Ovo nije tip vozila koje je korisnik unajmio." + vozilo.getIdVrsteVozila() + " " +  id_vrste_vozila);
                                        }
                                    } else {
                                        throw new Exception("Broj kilometara veći od dometa vozila.");
                                    }
                                } else {
                                    throw new Exception("Nije moguće vratiti ovo vozilo na ovu lokaciju.");
                                }
                            } else {
                                throw new Exception("Ne postoji cjenik sa ovim id.");
                            }
                        } else {
                            throw new Exception("Korisnik nema aktivan najam ove vrste vozila.");
                        }
                    } else {
                        throw new Exception("Ne postoji osoba sa ovim id.");
                    }
                } else {
                    throw new Exception("Ne postoji lokacija sa ovim id.");
                }
            } else {
                throw new Exception("Vrijeme akcije je prije trenutnog virtualnog vremena, nije moguće provesti akciju.");
            }

    }

    public int getId_korisnika() {
        return id_korisnika;
    }

    public void setId_korisnika(int id_korisnika) {
        this.id_korisnika = id_korisnika;
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

    public long getBroj_km() {
        return broj_km;
    }

    public void setBroj_km(long broj_km) {
        this.broj_km = broj_km;
    }
    
    public String getOpis_problema() {
        return opis_problema;
    }

    public void setOpis_problema(String opis_problema) {
        this.opis_problema = opis_problema;
    }

}
