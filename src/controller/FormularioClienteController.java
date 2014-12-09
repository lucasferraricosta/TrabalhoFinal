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
public class FormularioClienteController implements ActionListener {

    private FormularioClienteView view = null;
    private ManterClientesController pai = null;
    
    public FormularioClienteView getView() {
        return this.view;
    }

    public FormularioClienteController(FormularioClienteView view, ManterClientesController pai) {
        // Aponto para a View  deste Controller
        this.view = view;
        this.pai = pai;
        //Definindo os listeners para os botoes dessa view.
        this.view.getBotaoClienteSalvar().addActionListener(this);
        this.view.getBotaoClienteCancelar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Ações da Tela
        if (e.getSource() == this.view.getBotaoClienteSalvar()) {
            //Chama a Tela de Gestão de Crianca
            Cliente model = new Cliente();
            ClienteDAO clienteDAO = new ClienteDAO();
            model.setNome(this.view.getCampoClienteNome().getText());
            if (this.view.getRbPessoaFisica().isSelected()) {
                model.setPessoa(this.view.getRbPessoaFisica().getText());
            } else if (this.view.getRbPessoaJuridica().isSelected()) {
                model.setPessoa(this.view.getRbPessoaJuridica().getText());
            }
            model.setCpfCnpj(this.view.getCampoClienteCpfCnpj().getText());
            model.setContato(this.view.getCampoClienteContato().getText());
            model.setLogin(this.view.getCampoClienteLogin().getText());
            model.setSenha(this.view.getCampoClienteSenha().getText());
            if (this.view.getAcao() == "cadastrar") {
                clienteDAO.cadastrar(model);
            } else if (this.view.getAcao() == "editar") {
                model.setId(this.view.getIdCliente());
                clienteDAO.alterar(model);
            }
            this.view.getCampoClienteNome().setText("");
            this.view.getRbPessoaFisica().setSelected(false);
            this.view.getRbPessoaJuridica().setSelected(false);
            this.view.getCampoClienteCpfCnpj().setText("");
            this.view.getCampoClienteContato().setText("");
            this.view.getCampoClienteLogin().setText("");
            this.view.getCampoClienteSenha().setText("");
            this.view.getCampoClienteRepeteSenha().setText("");
            System.exit(0);
            pai.atualizaTabela();
        } else if (e.getSource() == this.view.getBotaoClienteCancelar()) {
            System.exit(0);
        }

    }

}
