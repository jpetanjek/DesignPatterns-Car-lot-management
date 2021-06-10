/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpetanjek.jpetanjek_zadaca_3.State;

import jpetanjek.jpetanjek_zadaca_3.IzvorneKlase.Vozilo;

/**
 *
 * @author Joc
 */
public interface State {
   public void doAction(Vozilo context);
}