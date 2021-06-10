package jpetanjek.jpetanjek_zadaca_3.FactoryMethod.ConcreteCreator;

import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.ConcreteProduct.AktivnostDatotekaProduct;
import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.Creator.DatotekaCreator;
import jpetanjek.jpetanjek_zadaca_3.FactoryMethod.Product.DatotekaProduct;

/**
 *
 * @author Josip Petanjek
 */
public class AktivnostDatotekaCreator implements DatotekaCreator{

    @Override
    public DatotekaProduct makeProduct() {
        return new AktivnostDatotekaProduct();
    }
    
}
