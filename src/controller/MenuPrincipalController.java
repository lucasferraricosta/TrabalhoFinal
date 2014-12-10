package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.*;
import view.*;

public class MenuPrincipalController implements ActionListener {

    private MenuPrincipalView view = null;

    public MenuPrincipalView getView() {
        return this.view;
    }

    public MenuPrincipalController(MenuPrincipalView view) {
        // Aponto para a View  deste Controller
        this.view = view;
        //Definindo os listeners para os botoes dessa view.
        this.view.getbMPadmin().addActionListener(this);
        this.view.getbMPclient().addActionListener(this);
        this.view.getbMPfunc1().addActionListener(this);
        this.view.getbMPsair().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Ações da Tela
        if (e.getSource() == this.view.getbMPadmin()) {
            FormLoginView tela = new FormLoginView();
            FormLoginController controle = new FormLoginController(tela, "admin");
            controle.getView().setVisible(true);
        } else if (e.getSource() == this.view.getbMPclient()) {
            FormLoginView tela = new FormLoginView();
            FormLoginController controle = new FormLoginController(tela, "cliente");
            controle.getView().setVisible(true);
        } else if (e.getSource() == this.view.getbMPfunc1()) {
            FormLoginView tela = new FormLoginView();
            FormLoginController controle = new FormLoginController(tela, "funcionario");
            controle.getView().setVisible(true);
        } else if (e.getSource() == this.view.getbMPsair()) {
            System.exit(0);
        }

    }

}
