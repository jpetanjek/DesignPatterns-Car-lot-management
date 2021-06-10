/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.Composite;

import jpetanjek.jpetanjek_zadaca_3.Decorator.ConcreteDecorator.CompositeConcreteDecorator;
import jpetanjek.jpetanjek_zadaca_3.Decorator.CompositeDecorator;
import java.util.Date;
import java.util.List;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Lokacija;

/**
 *
 * @author Joc
 */
public class OrganizacijskaJedinicaComposite extends CompositeDecorator implements OrganizacijskaJedinica  {

    public List<OrganizacijskaJedinica> listaOrgJed;
    public List<Lokacija> listaLokacija;

    
    public int id;
    public String naziv;
    public int nadredeni;


    @Override
    public String getNaziv() {
        return naziv;
    }

    @Override
    public void setListaOrgJed(List<OrganizacijskaJedinica> listaOrgJed) {
        this.listaOrgJed = listaOrgJed;
    }

    @Override
    public void setListaLokacija(List<Lokacija> listaLokacija) {
        this.listaLokacija = listaLokacija;
    }

    @Override
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void printStruktura() {
        //if(listaOrgJed.size()>0){
            CompositeDecorator nesto = new CompositeConcreteDecorator(this);
            nesto.printStruktura();
        //}
    }
    
    @Override
    public void printLokacije() {
        //if(listaOrgJed.size()>0){
            CompositeDecorator nesto = new CompositeConcreteDecorator(this);
            nesto.printLokacije();
        //}
    }
    
    @Override
    public void printNajam(Date datumOd, Date datumDo){
        //if(listaOrgJed.size()>0){
            CompositeDecorator nesto = new CompositeConcreteDecorator(this);
            nesto.printNajam(datumOd,datumDo);
        //}
    }
    
    @Override
    public void printZarada(Date datumOd, Date datumDo){
        //if(listaOrgJed.size()>0){
            CompositeDecorator nesto = new CompositeConcreteDecorator(this);
            nesto.printZarada(datumOd,datumDo);
        //}
    }
    
    @Override
    public void printRacun(Date datumOd, Date datumDo) {
        //if(listaOrgJed.size()>0){
            CompositeDecorator nesto = new CompositeConcreteDecorator(this);
            nesto.printRacun(datumOd,datumDo);
        //}
    }
    
    @Override
    public void printNajamZarada(Date datumOd, Date datumDo) {
        //if(listaOrgJed.size()>0){
            CompositeDecorator nesto = new CompositeConcreteDecorator(this);
            nesto.printNajamZarada(datumOd,datumDo);
        //}
    }

    @Override
    public void DodajOrgJed(OrganizacijskaJedinica nesto) {
        listaOrgJed.add(nesto);
    }

    @Override
    public void UkloniOrgJed(OrganizacijskaJedinica nesto) {
    }

    @Override
    public List<OrganizacijskaJedinica> DohvatiOrgJed() {
        return listaOrgJed;
    }

    @Override
    public void DodajLok(Lokacija nesto) {
    }

    @Override
    public void UkloniLok(Lokacija nesto) {
    }

    @Override
    public List<Lokacija> DohvatiLok() {
        return listaLokacija;
    }

    @Override
    public int getNadredeni() {
        return nadredeni;
    }

    @Override
    public void setNadredeni(int nesto) {
        nadredeni=nesto;
    }

    

    
    
    
    
}
