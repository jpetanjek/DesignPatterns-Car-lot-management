package jpetanjek.jpetanjek_zadaca_3.Singleton;

/**
 *
 * @author Joc
 */
public class NacinRada {
    
    private static volatile NacinRada Instanca;
    private static String nacin;

    private NacinRada() {
    }
    
    public static NacinRada getInstanca() {
        if (Instanca == null) {
            synchronized ((NacinRada.class)) {
                Instanca = new NacinRada();
            }
        }
        return Instanca;
    }

    public static void setInstanca(NacinRada Instanca) {
        NacinRada.Instanca = Instanca;
    }

    public static String getNacin() {
        return nacin;
    }

    public static void setNacin(String nacin) {
        NacinRada.nacin = nacin;
    }
    
}
