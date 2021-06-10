package jpetanjek.jpetanjek_zadaca_3.FactoryMethod.Product;

/**
 *
 * @author Josip Petanjek
 */
import java.util.List;

public interface DatotekaProduct {

    List<?> getConcreteProducts(String nazivDatoteke);
}

