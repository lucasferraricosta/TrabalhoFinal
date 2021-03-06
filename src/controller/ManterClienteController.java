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

public class ManterClienteController implements ActionListener {

    private ManterClienteView view = null;

    public ManterClienteView getView() {
        return this.view;
    }

    public ManterClienteController(ManterClienteView view) {
        // Aponto para a View  deste Controller
        this.view = view;
        //Definindo os listeners para os botoes dessa view.
        this.view.getBotaoClienteCadastrar().addActionListener(this);
        this.view.getBotaoClienteVisualizar().addActionListener(this);
        this.view.getBotaoClienteEditar().addActionListener(this);
        this.view.getBotaoClienteExcluir().addActionListener(this);
        this.view.getBotaoClienteVoltar().addActionListener(this);

        this.view.getListaClientes().getColumnModel().getColumn(0).setPreferredWidth(20);
        this.view.getListaClientes().getColumnModel().getColumn(1).setPreferredWidth(150);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Ações da Tela
        if (e.getSource() == this.view.getBotaoClienteCadastrar()) {
            FormularioClienteView tela = new FormularioClienteView("cadastrar");
            FormularioClienteController controle = new FormularioClienteController(tela, this);
            controle.getView().setVisible(true);
        } else if (e.getSource() == this.view.getBotaoClienteVisualizar()) {
            if (this.view.getListaClientes().getSelectedRowCount() > 0) {
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
            } else {
                JOptionPane.showMessageDialog(view, "Selecione um item na lista.");
            }
        } else if (e.getSource() == this.view.getBotaoClienteEditar()) {
            if (this.view.getListaClientes().getSelectedRowCount() > 0) {
                FormularioClienteView tela = new FormularioClienteView("editar", Integer.parseInt(this.view.getListaClientes().getValueAt(this.view.getListaClientes().getSelectedRow(), 0).toString()));
                FormularioClienteController controle = new FormularioClienteController(tela, this);
                controle.getView().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(view, "Selecione um item na lista.");
            }
        } else if (e.getSource() == this.view.getBotaoClienteExcluir()) {
            if (this.view.getListaClientes().getSelectedRowCount() > 0) {
                int resposta = JOptionPane.showConfirmDialog(view, "Têm certeza que deseja excluir?");
                if (resposta == 0) {
                    ClienteDAO clienteDAO = new ClienteDAO();
                    int idCliente = Integer.parseInt(this.view.getListaClientes().getValueAt(this.view.getListaClientes().getSelectedRow(), 0).toString());
                    Cliente cliente = new Cliente();
                    cliente.setId(idCliente);
                    clienteDAO.excluir(cliente);
                    JOptionPane.showMessageDialog(this.getView(), "Cliente excluído com sucesso.");
                    this.atualizaTabela();
                }
            } else {
                JOptionPane.showMessageDialog(view, "Selecione um item na lista.");
            }
        } else if (e.getSource() == this.view.getBotaoClienteVoltar()) {
            this.view.getTextAreaDadosCliente().setText("");
            this.view.dispose();
        }

    }

    public void atualizaTabela() {
        DefaultTableModel modeloTabela = (DefaultTableModel) this.view.getListaClientes().getModel();
        modeloTabela.setNumRows(0);
        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> lista = clienteDAO.listar();
        for (Cliente cliente : lista) {
            modeloTabela.addRow(new Object[]{cliente.getId(), cliente.getNome()});
        }
        this.view.getListaClientes().setModel(modeloTabela);
    }
}
