/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
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
        this.view.getBotaoClienteCadastrar().addActionListener(this);
        this.view.getBotaoClienteVisualizar().addActionListener(this);
        this.view.getBotaoClienteEditar().addActionListener(this);
        this.view.getBotaoClienteExcluir().addActionListener(this);
        this.view.getBotaoClienteVoltar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Ações da Tela
        if (e.getSource() == this.view.getBotaoClienteCadastrar()) {
            FormularioClienteView tela = new FormularioClienteView("cadastrar");
            FormularioClienteController controle = new FormularioClienteController(tela);
            controle.getView().setVisible(true);
        } else if (e.getSource() == this.view.getBotaoClienteVisualizar()) {
            ClienteDAO clienteDAO = new ClienteDAO();
            int idCliente = Integer.parseInt(this.view.getListaClientes().getValueAt(this.view.getListaClientes().getSelectedRow(), 0).toString());
            Cliente cliente = clienteDAO.retornaDados(idCliente);
            String texto = "Id: " + cliente.getId() + "\n";
            texto += "Nome: " + cliente.getNome() + "\n";
            texto += "Pessoa: " + cliente.getPessoa() + "\n";
            texto += "CPF/CNPJ: " + cliente.getCpfCnpj() + "\n";
            texto += "Nome do contato: " + cliente.getContato() + "\n";
            texto += "Login: " + cliente.getLogin() + "\n";
            texto += "Senha: " + cliente.getSenha();
            this.view.getTextAreaDadosCliente().setText(texto);
        } else if (e.getSource() == this.view.getBotaoClienteEditar()) {
            FormularioClienteView tela = new FormularioClienteView("editar", Integer.parseInt(this.view.getListaClientes().getValueAt(this.view.getListaClientes().getSelectedRow(), 0).toString()));
            FormularioClienteController controle = new FormularioClienteController(tela);
            controle.getView().setVisible(true);
        } else if (e.getSource() == this.view.getBotaoClienteExcluir()) {
            ClienteDAO clienteDAO = new ClienteDAO();
            int idCliente = Integer.parseInt(this.view.getListaClientes().getValueAt(this.view.getListaClientes().getSelectedRow(), 0).toString());
            Cliente cliente = new Cliente();
            cliente.setId(idCliente);
            clienteDAO.excluir(cliente);
        } else if (e.getSource() == this.view.getBotaoClienteVoltar()) {
            this.view.getTextAreaDadosCliente().setText("");
            System.exit(0);
        }

    }

}
