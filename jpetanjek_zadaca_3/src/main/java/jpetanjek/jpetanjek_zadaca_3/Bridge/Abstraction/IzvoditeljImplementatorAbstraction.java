package jpetanjek.jpetanjek_zadaca_3.Bridge.Abstraction;

import jpetanjek.jpetanjek_zadaca_3.Bridge.Implementor.IAktivnost;

/**
 *
 * @author Josip Petanjek
 */
public abstract class IzvoditeljImplementatorAbstraction {
    protected IAktivnost iAktivnost;
    public IzvoditeljImplementatorAbstraction(IAktivnost iAktivnost){
        this.iAktivnost=iAktivnost;
    }
    abstract public void izvediAktivnost() throws Exception;
}
