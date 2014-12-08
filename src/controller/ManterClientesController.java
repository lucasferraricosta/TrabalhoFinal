/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.*;
import view.*;

/**
 *
 * @author Professor
 */
public class ManterClientesController implements ActionListener {

    private ManterClientesView view = null;

    public ManterClientesView getView() {
        return this.view;
    }

    public ManterClientesController(ManterClientesView view) {
        // Aponto para a View  deste Controller
        this.view = view;
        //Definindo os listeners para os botoes dessa view.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Ações da Tela
        if (e.getSource() == this.view.getBotaoClienteSalvar()) {
            
        } else if (e.getSource() == this.view.getBotaoClienteCancelar()) {
            System.exit(0);
        }

    }

}
