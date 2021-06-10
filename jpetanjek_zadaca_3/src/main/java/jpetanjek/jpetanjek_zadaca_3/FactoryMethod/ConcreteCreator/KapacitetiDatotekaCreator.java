package jpetanjek.jpetanjek_zadaca_3.FactoryMethod.ConcreteCreator;

import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.ConcreteProduct.KapacitetiDatotekaProduct;
import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.Creator.DatotekaCreator;
import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.Product.DatotekaProduct;

/**
 *
 * @author Josip Petanjek
 */
public class KapacitetiDatotekaCreator implements DatotekaCreator{

    @Override
    public DatotekaProduct makeProduct() {
        return new KapacitetiDatotekaProduct();
    }
    
}
