/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.FactoryMethod.ConcreteCreator;

import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.ConcreteProduct.TvrtkaDatotekaProduct;
import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.Creator.DatotekaCreator;
import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.Product.DatotekaProduct;

/**
 *
 * @author Joc
 */
public class TvrtkaDatotekaCreator implements DatotekaCreator{

    @Override
    public DatotekaProduct makeProduct() {
        return new TvrtkaDatotekaProduct();
    }
    
}
