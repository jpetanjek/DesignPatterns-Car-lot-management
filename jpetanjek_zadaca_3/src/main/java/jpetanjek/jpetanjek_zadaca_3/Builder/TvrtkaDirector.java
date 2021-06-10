/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.Builder;

import jpetanjek.jpetanjek_zadaca_3.Composite.OrganizacijskaJedinicaComposite;
import java.util.ArrayList;
import java.util.List;
import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.OrgJedModel;

/**
 *
 * @author Joc
 */
public class TvrtkaDirector {
    private TvrtkaBuilder builder;
    
    public TvrtkaDirector(TvrtkaBuilder builder){
        this.builder=builder;
    }
    
    public OrganizacijskaJedinicaComposite construct(List<OrgJedModel> listaModela){
        return builder.BuildComposite(listaModela).Construct();
    }
    
}
