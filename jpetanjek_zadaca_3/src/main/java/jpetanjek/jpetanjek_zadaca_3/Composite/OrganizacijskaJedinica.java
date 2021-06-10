/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.Composite;

import java.util.List;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Lokacija;

/**
 *
 * @author Joc
 */
public interface OrganizacijskaJedinica {
    public void setId(int id);
    public int getId();
    public String getNaziv();
    public void setNaziv(String naziv);
    public void DodajOrgJed(OrganizacijskaJedinica nesto);
    public void UkloniOrgJed(OrganizacijskaJedinica nesto);
    public List<OrganizacijskaJedinica> DohvatiOrgJed();
    public void DodajLok(Lokacija nesto);
    public void UkloniLok(Lokacija nesto);
    public List<Lokacija> DohvatiLok();
    public void setListaOrgJed(List<OrganizacijskaJedinica> listaOrgJed);
    public void setListaLokacija(List<Lokacija> listaLokacija);
    public int getNadredeni();
    public void setNadredeni(int nesto);
}
