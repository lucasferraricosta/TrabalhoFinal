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

public class AreaAdministradorController implements ActionListener {

    private AreaAdministradorView view = null;

    public AreaAdministradorView getView() {
        return this.view;
    }

    public AreaAdministradorController(AreaAdministradorView view) {
        // Aponto para a View  deste Controller
        this.view = view;
        //Definindo os listeners para os botoes dessa view.
        this.view.getbAdminclientes().addActionListener(this);
        this.view.getbAdminfuncionarios().addActionListener(this);
        this.view.getbAdmintrabalhos().addActionListener(this);
        this.view.getbAdminsair().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Ações da Tela
        if (e.getSource() == this.view.getbAdminclientes()) {
        } else if (e.getSource() == this.view.getbAdminfuncionarios()) {
            ManterFuncionarioView tela = new ManterFuncionarioView();
            ManterFuncionarioController controle = new ManterFuncionarioController(tela);
            controle.getView().setVisible(true);
            controle.atualizaTabela();
        } else if (e.getSource() == this.view.getbAdmintrabalhos()) {
        } else if (e.getSource() == this.view.getbAdminsair()) {
            this.getView().dispose();
        }

    }

}
