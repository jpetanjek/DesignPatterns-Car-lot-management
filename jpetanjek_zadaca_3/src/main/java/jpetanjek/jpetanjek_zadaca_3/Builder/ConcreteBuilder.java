/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.Builder;

import jpetanjek.jpetanjek_zadaca_3.Composite.OrganizacijskaJedinica;
import jpetanjek.jpetanjek_zadaca_3.Composite.OrganizacijskaJedinicaComposite;
import jpetanjek.jpetanjek_zadaca_3.Iterator.Iterator;
import jpetanjek.jpetanjek_zadaca_3.Iterator.IteratorRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Lokacija;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.OrgJedModel;
import jpetanjek.jpetanjek_zadaca_3.Singleton.Tvrtka;

/**
 *
 * @author Joc
 */
public class ConcreteBuilder implements TvrtkaBuilder {

    private List<Integer> dodaneLokacije = new ArrayList<>();

    private OrganizacijskaJedinicaComposite instance;

    public ConcreteBuilder() {
        this.instance = new OrganizacijskaJedinicaComposite();
    }

    @Override
    public OrganizacijskaJedinicaComposite Construct() {
        return instance;
    }

    //Refaktor bez modela ---- NIJE POTREBNO sa modelom
    @Override
    public TvrtkaBuilder BuildComposite(List<OrgJedModel> lista) {

        //izvorne dodati u instance
        StvoritiIzvornu(lista);
        //stvoriti djecu
        StvoritiDjecu(lista);

        return this;
    }

    private void StvoritiIzvornu(List<OrgJedModel> lista) {
        OrgJedModel zaObrisati = new OrgJedModel();

        //dohvati ih
        for (OrgJedModel orgJedModel : lista) {
            if (orgJedModel.isIzvorni()) {

                zaObrisati = orgJedModel;

                List<Lokacija> lokacije = DohvatiLokacije(orgJedModel.getLokacije());
                //Instanciranje
                OrganizacijskaJedinicaComposite org = new OrganizacijskaJedinicaComposite();
                org.setListaOrgJed(new ArrayList<>());
                org.setListaLokacija(lokacije);
                org.setId(orgJedModel.getId());
                org.setNaziv(orgJedModel.getNaziv());
                org.setNadredeni(-1);

                instance = org;

                //maknuti iz liste
                //lista.remove(orgJedModel);
            }
        }
        lista.remove(zaObrisati);
        if (instance == null) {
            //baci iznimku - Ne postoji izvorna organizacijska jedinica! Dogoditi ce se loop!
        }
    }

    private List<Lokacija> DohvatiLokacije(List<Integer> lokacije) {
        List<Lokacija> returnme = new ArrayList<>();
        for (Integer integer : lokacije) {
            if (Tvrtka.getInstanca().getLokacijaById(integer) == null) {
                System.out.println("baci iznimku - ne postoji lokacija " + integer);
                //baci iznimku - ne postoji lokacija
            } else if (!DodanaLokacija(integer)) {
                dodaneLokacije.add(integer);
                returnme.add(Tvrtka.getInstanca().getLokacijaById(integer));
                //System.out.println("Dodana lokacija " + integer);
            } else if (DodanaLokacija(integer)) {
                System.out.println("baci iznimku - ova lokacija je vec dodana, preskacem ju " + integer);
                //baci iznimku - ova lokacija je vec dodana, preskacem ju
            }
        }
        return returnme;
    }

    private void StvoritiDjecu(List<OrgJedModel> lista) {

        lista = ProciscavanjeListe(lista);
        while (!lista.isEmpty()) {
            List<OrgJedModel> zaObrisati = new ArrayList<>();
            for (OrgJedModel orgJedModel : lista) {
                IteratorRepository ir = new IteratorRepository(instance);
                Iterator i = ir.getIterator();
                while (i.hasNext()) {
                    OrganizacijskaJedinica oi = (OrganizacijskaJedinica) i.getNext();
                    if (oi.getId() == orgJedModel.getNadredeni() && oi != null) {
                        List<Lokacija> lokacije = DohvatiLokacije(orgJedModel.getLokacije());

                        OrganizacijskaJedinicaComposite org = new OrganizacijskaJedinicaComposite();
                        org.setListaOrgJed(new ArrayList<>());
                        org.setListaLokacija(lokacije);
                        org.setId(orgJedModel.getId());
                        org.setNaziv(orgJedModel.getNaziv());
                        org.setNadredeni(orgJedModel.getNadredeni());

                        oi.DodajOrgJed(org);

                        //System.out.println("Dodajem u " + oi.getNaziv() + " mu " + org.getNaziv());

                        zaObrisati.add(orgJedModel);
                    }
                }
            }
            for (OrgJedModel orgJedModel : zaObrisati) {
                //System.out.println("Brisem: " + orgJedModel.getNaziv());
                lista.remove(orgJedModel);
            }
            //System.out.println("Lista velicina: " + lista.size());
        }

        /*
        IteratorRepository ir = new IteratorRepository(instance);
        Iterator i = ir.getIterator();
        System.out.println("PRINT OVOGA GOVNA");
        while (i.hasNext()) {
            OrganizacijskaJedinica oi = (OrganizacijskaJedinica) i.getNext();
            //System.out.println(oi.getId()+" "+oi.getNaziv());
        }

        System.out.println("PrintV2");
        IteratorRepository ir2 = new IteratorRepository(instance);
        Iterator i2 = ir2.getIterator();
        while (i2.hasNext()) {
            OrganizacijskaJedinica oi = (OrganizacijskaJedinica) i2.getNext();
            System.out.println(oi.getId() + " " + oi.getNaziv());
            for (OrganizacijskaJedinica organizacijskaJedinica : oi.DohvatiOrgJed()) {
                System.out.println("Sadrzi org jed: " + organizacijskaJedinica.getId() + " " + organizacijskaJedinica.getNaziv());
            }
            if (!oi.DohvatiLok().isEmpty()) {
                for (Lokacija lokacija : oi.DohvatiLok()) {
                    System.out.println("Sadrzi lokacije: " + lokacija.getNaziv());
                }
            }

        }

         */
    }

    private boolean DodanaLokacija(Integer integer) {
        boolean returnme = false;
        for (Integer integer1 : dodaneLokacije) {
            if (Objects.equals(integer1, integer)) {
                return true;
            }
        }
        return returnme;
    }

    private List<OrgJedModel> ProciscavanjeListe(List<OrgJedModel> lista) {
        //birsanje onih orgJedModel kojima nadredeni ne postoje
        List<OrgJedModel> zaObrisati = new ArrayList<>();
        for (OrgJedModel orgJedModel : lista) {
            boolean pronaden=false;
            for (OrgJedModel elementPromatranja : lista) {
                if(orgJedModel.getNadredeni()==elementPromatranja.getId()||orgJedModel.getNadredeni()==instance.getId()){
                    pronaden=true;
                    //System.out.println("Pronaden nadredeni za "+orgJedModel.getId());
                    break;
                }
            }
            if(!pronaden){
                System.out.println("Baci exception - Nije pronadena nadredena organizacijska jedinica za " + orgJedModel.getId());
                zaObrisati.add(orgJedModel);
            }
        }
        
        for (OrgJedModel orgJedModel : zaObrisati) {
            System.out.println("Baci exception - Brisem organizacijsku jedinicu " + orgJedModel.getId());
            lista.remove(orgJedModel);
        }
        
        return lista;
    }

}
