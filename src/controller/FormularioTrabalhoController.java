package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.*;
import view.*;

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
            model.setDataEntrega(this.view.getCampoTrabalhoDataEntrega().getText());
            if (this.view.getAcao() == "cadastrar") {
                trabalhoDAO.cadastrar(model);
            } else if (this.view.getAcao() == "editar") {
                model.setId(this.view.getIdTrabalho());
                trabalhoDAO.alterar(model);
            }
            this.view.getCampoTrabalhoNome().setText("");
            this.view.getCampoTrabalhoDataEntrega().setText("");
            System.exit(0);
            pai.atualizaTabela();
        } else if (e.getSource() == this.view.getBotaoTrabalhoCancelar()) {
            System.exit(0);
        }

    }

}
