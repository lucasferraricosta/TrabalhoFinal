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
public class FormularioTrabalhoController implements ActionListener {

    private FormularioTrabalhoView view = null;
    private ManterTrabalhoController pai = null;
    
    public FormularioTrabalhoView getView() {
        return this.view;
    }

    public FormularioTrabalhoController(FormularioTrabalhoView view, ManterTrabalhoController pai) {
        // Aponto para a View  deste Controller
        this.view = view;
        this.pai = pai;
        //Definindo os listeners para os botoes dessa view.
        this.view.getBotaoTrabalhoSalvar().addActionListener(this);
        this.view.getBotaoTrabalhoCancelar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Ações da Tela
        if (e.getSource() == this.view.getBotaoTrabalhoSalvar()) {
            //Chama a Tela de Gestão de Crianca
            Trabalho model = new Trabalho();
            TrabalhoDAO trabalhoDAO = new TrabalhoDAO();
            model.setNome(this.view.getCampoTrabalhoNome().getText());
            if (this.view.getRbSexoMasculino().isSelected()) {
                model.setSexo(this.view.getRbSexoMasculino().getText());
            } else if (this.view.getRbSexoFeminino().isSelected()) {
                model.setSexo(this.view.getRbSexoFeminino().getText());
            }
            model.setCpf(this.view.getCampoTrabalhoCpf().getText());
            model.setIdade(Integer.parseInt(this.view.getCampoTrabalhoIdade().getText()));
            model.setLogin(this.view.getCampoTrabalhoLogin().getText());
            model.setSenha(this.view.getCampoTrabalhoSenha().getText());
            if (this.view.getAcao() == "cadastrar") {
                trabalhoDAO.cadastrar(model);
            } else if (this.view.getAcao() == "editar") {
                model.setId(this.view.getIdTrabalho());
                trabalhoDAO.alterar(model);
            }
            this.view.getCampoTrabalhoNome().setText("");
            this.view.getRbSexoMasculino().setSelected(false);
            this.view.getRbSexoFeminino().setSelected(false);
            this.view.getCampoTrabalhoCpf().setText("");
            this.view.getCampoTrabalhoIdade().setText("");
            this.view.getCampoTrabalhoLogin().setText("");
            this.view.getCampoTrabalhoSenha().setText("");
            this.view.getCampoTrabalhoRepeteSenha().setText("");
            System.exit(0);
            pai.atualizaTabela();
        } else if (e.getSource() == this.view.getBotaoTrabalhoCancelar()) {
            System.exit(0);
        }

    }

}
