/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.MVC;

/**
 *
 * @author Joc
 */
public class Controller {

    private String model;
    private String error;
    private Pogled pogled;

    public Controller(String model, Pogled pogled) {
        this.model = model;
        this.pogled = pogled;
        System.out.println("Postavljen " + pogled.toString());
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
        pogled.ispisError(error);
    }
    
    

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
        updateView();
    }

    public Pogled getPogled() {
        return pogled;
    }

    public void setPogled(Pogled pogled) {
        this.pogled = pogled;
    }

    public void updateView() {
        pogled.ispis(model);
    }

}
