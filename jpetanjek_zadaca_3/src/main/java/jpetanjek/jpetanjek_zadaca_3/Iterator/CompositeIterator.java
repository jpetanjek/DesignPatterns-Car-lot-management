/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.Iterator;

import jpetanjek.jpetanjek_zadaca_3.Composite.OrganizacijskaJedinica;
import jpetanjek.jpetanjek_zadaca_3.Composite.OrganizacijskaJedinicaComposite;
import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 *
 * @author Joc
 */
public class CompositeIterator implements Iterator<OrganizacijskaJedinica>{
    private OrganizacijskaJedinicaComposite instanca;
    Deque<OrganizacijskaJedinica> stack;

    public CompositeIterator(OrganizacijskaJedinicaComposite instanca) {
        this.instanca = instanca;
        stack=new LinkedList<>();
        stack.add(instanca);
    }
    
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public OrganizacijskaJedinica getNext() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        OrganizacijskaJedinica node = stack.pop();
        if (node != null) {
            if (node.DohvatiOrgJed()!= null && node.DohvatiOrgJed().size() > 0) {
                for (OrganizacijskaJedinica ojc : node.DohvatiOrgJed()) {
                    stack.add(ojc);
                }
            }
        }
        return node;
    }
    
    
}
