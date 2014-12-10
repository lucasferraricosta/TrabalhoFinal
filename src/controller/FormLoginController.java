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

public class FormLoginController implements ActionListener {

    private FormLoginView view = null;
    private String area;

    public FormLoginView getView() {
        return this.view;
    }

    public String getArea() {
        return this.area;
    }

    public FormLoginController(FormLoginView view, String area) {
        // Aponto para a View  deste Controller
        this.view = view;
        this.area = area;
        //Definindo os listeners para os botoes dessa view.
        this.view.getCampoLoginlogin().addActionListener(this);
        this.view.getbLoginsair().addActionListener(this);

        this.view.getTabelaFuncionarioTrabalhos().getColumnModel().getColumn(0).setPreferredWidth(30);
        this.view.getTabelaFuncionarioTrabalhos().getColumnModel().getColumn(1).setPreferredWidth(30);
        this.view.getTabelaFuncionarioTrabalhos().getColumnModel().getColumn(2).setPreferredWidth(30);
        this.view.getTabelaFuncionarioTrabalhos().getColumnModel().getColumn(3).setPreferredWidth(30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Ações da Tela
        if (e.getSource() == this.view.getCampoLoginlogin()) {
            if (this.area.equals("admin")) {
                if (this.view.getCampoLoginlogin().equals("admin") && this.view.getCampoLoginsenha().equals("admin")) {
                    AreaAdministradorView tela = new AreaAdministradorView();
                    AreaAdministradorController controle = new AreaAdministradorController(tela);
                    JOptionPane.showMessageDialog(this.getView(), "Login efetuado com sucesso.");
                    controle.getView().setVisible(true);
                    this.view.getCampoLoginlogin().setText("");
                    this.view.getCampoLoginsenha().setText("");
                    this.view.dispose();
                } else {
                    JOptionPane.showMessageDialog(this.getView(), "Dados incorretos.");
                    this.view.getCampoLoginsenha().setText("");
                }

            }
        } else if (e.getSource() == this.view.getbLoginsair()) {
            this.view.getCampoLoginlogin().setText("");
            this.view.getCampoLoginsenha().setText("");
            this.view.dispose();
        }

    }

}
