package jpetanjek.jpetanjek_zadaca_3.Bridge.ConcreteImplementator;

import jpetanjek.jpetanjek_zadaca_3.Bridge.Abstraction.IzvoditeljImplementatorAbstraction;
import jpetanjek.jpetanjek_zadaca_3.Bridge.Implementor.IAktivnost;

/**
 *
 * @author Josip Petanjek
 */
public class IzvoditeljAktivnosti extends IzvoditeljImplementatorAbstraction {
    public IzvoditeljAktivnosti (IAktivnost iAktivnost){
        super(iAktivnost);
    }
    
    @Override
    public void izvediAktivnost() throws Exception{
        iAktivnost.izvedi();
    }
    
}
