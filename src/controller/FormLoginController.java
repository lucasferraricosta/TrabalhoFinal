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
        this.view.getbLoginlogin().addActionListener(this);
        this.view.getbLoginsair().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Ações da Tela
        if (e.getSource() == this.view.getbLoginlogin()) {
            if (this.area.equals("admin")) {
                if (this.view.getCampoLoginlogin().getText().equals("admin") && this.view.getCampoLoginsenha().getText().equals("admin")) {
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
            } else if (this.area.equals("funcionario")) {
                FuncionarioDAO funcDAO = new FuncionarioDAO();
                Funcionario func = funcDAO.logar(this.view.getCampoLoginlogin().getText(), this.view.getCampoLoginsenha().getText());
                if (func != null) {
                    AreaFuncionarioView tela = new AreaFuncionarioView();
                    AreaFuncionarioController controle = new AreaFuncionarioController(tela, func.getId());
                    JOptionPane.showMessageDialog(this.getView(), "Login efetuado com sucesso.");
                    controle.getView().setVisible(true);
                    controle.atualizaTabela();
                    this.view.getCampoLoginlogin().setText("");
                    this.view.getCampoLoginsenha().setText("");
                    this.view.dispose();
                } else {
                    JOptionPane.showMessageDialog(this.getView(), "Dados incorretos.");
                    this.view.getCampoLoginsenha().setText("");
                }
            } else if (this.area.equals("cliente")) {
                ClienteDAO clienteDAO = new ClienteDAO();
                Cliente cliente = clienteDAO.logar(this.view.getCampoLoginlogin().getText(), this.view.getCampoLoginsenha().getText());
                if (cliente != null) {
                    AreaClienteView tela = new AreaClienteView();
                    AreaClienteController controle = new AreaClienteController(tela, cliente.getId());
                    JOptionPane.showMessageDialog(this.getView(), "Login efetuado com sucesso.");
                    controle.getView().setVisible(true);
                    controle.atualizaTabela();
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
