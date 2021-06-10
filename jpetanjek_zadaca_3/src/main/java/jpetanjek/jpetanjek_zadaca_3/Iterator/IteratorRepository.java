/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.Iterator;

import jpetanjek.jpetanjek_zadaca_3.Composite.OrganizacijskaJedinicaComposite;

/**
 *
 * @author Joc
 */
public class IteratorRepository implements IteratorContainer {
    private OrganizacijskaJedinicaComposite instanca;

    public IteratorRepository(OrganizacijskaJedinicaComposite instanca) {
        this.instanca = instanca;
    }
    
    
    
    @Override
    public Iterator getIterator() {
        return new CompositeIterator(instanca);
    }
    
}
