/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.*;
import model.*;
import view.*;

/**
 *
 * @author lucas.070395
 */
public class Main {
    public static void main(String args[]) {
        MenuPrincipalView view = new MenuPrincipalView();
        MenuPrincipalController controle = new MenuPrincipalController(view);
        controle.getView().setVisible(true);
    }
}
