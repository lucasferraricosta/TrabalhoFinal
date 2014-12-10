package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.*;
import view.*;

public class FormularioPecaController implements ActionListener {

    private FormularioPecaView view = null;
    private AreaClienteController pai = null;

    public FormularioPecaView getView() {
        return this.view;
    }

    public FormularioPecaController(FormularioPecaView view, AreaClienteController pai) {
        // Aponto para a View  deste Controller
        this.view = view;
        this.pai = pai;
        //Definindo os listeners para os botoes dessa view.
        this.view.getBotaoPecaSalvar().addActionListener(this);
        this.view.getBotaoPecaCancelar().addActionListener(this);
        Peca peca = new Peca();
        if (this.view.getAcao().equals("editar")) {
            PecaDAO pecaDAO = new PecaDAO();
            peca = pecaDAO.retornaDados(this.view.getIdPeca());
            this.view.getCampoPecaNome().setText(peca.getNome());
            this.view.getTextAreaPecaDescricao().setText(peca.getDescricao());
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Ações da Tela
        if (e.getSource() == this.view.getBotaoPecaSalvar()) {
            //Chama a Tela de Gestão de Crianca
            Peca model = new Peca();
            PecaDAO pecaDAO = new PecaDAO();
            model.setNome(this.view.getCampoPecaNome().getText());
            model.setDescricao(this.view.getTextAreaPecaDescricao().getText());
            model.setIdCliente(this.pai.getIdCliente());
            if (this.view.getAcao().equals("cadastrar")) {
                pecaDAO.cadastrar(model);
            } else if (this.view.getAcao().equals("editar")) {
                model.setId(this.view.getIdPeca());
                pecaDAO.alterar(model);
            }
            this.view.getCampoPecaNome().setText("");
            this.view.getTextAreaPecaDescricao().setText("");
            this.view.dispose();
            pai.atualizaTabela();
        } else if (e.getSource() == this.view.getBotaoPecaCancelar()) {
            this.view.getCampoPecaNome().setText("");
            this.view.getTextAreaPecaDescricao().setText("");
            this.view.dispose();
            pai.atualizaTabela();
        }

    }

}
