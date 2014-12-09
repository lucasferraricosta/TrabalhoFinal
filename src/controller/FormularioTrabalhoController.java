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
        Trabalho trabalho = new Trabalho();
        if (this.view.getAcao() == "editar") {
            TrabalhoDAO trabalhoDAO = new TrabalhoDAO();
            trabalho = trabalhoDAO.retornaDados(this.view.getIdTrabalho());
            this.view.getCampoTrabalhoNome().setText(trabalho.getNome());
            this.view.getCampoTrabalhoDataEntrega().setText(trabalho.getDataEntrega());
        }
        
        int indiceSelecionadoPeca = -1;
        int indiceSelecionadoFuncionario = -1;
        
        DefaultComboBoxModel selectFuncionario = new DefaultComboBoxModel();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        List<Funcionario> listaFuncionarios = funcionarioDAO.listar();
        for(int i = 0;i<listaFuncionarios.size();i++){
            selectFuncionario.addElement(listaFuncionarios.get(i).getId()+" - "+listaFuncionarios.get(i).getNome());
            if(listaFuncionarios.get(i).getId() == trabalho.getIdFuncionario()){
                indiceSelecionadoFuncionario = i;
            }
        }
        this.view.getSelectTrabalhoFuncionario().setModel(selectFuncionario);
        
        DefaultComboBoxModel selectPeca = new DefaultComboBoxModel();
        PecaDAO pecaDAO = new PecaDAO();
        List<Peca> listaPecas = pecaDAO.listar();
        for(int i = 0;i<listaPecas.size();i++){
            selectFuncionario.addElement(listaPecas.get(i).getId()+" - "+listaPecas.get(i).getNome());
            if(listaPecas.get(i).getId() == trabalho.getIdPeca()){
                indiceSelecionadoPeca = i;
            }
        }
        this.view.getSelectTrabalhoPeca().setModel(selectPeca);
                
        
        if (this.view.getAcao() == "editar") {
            this.view.getSelectTrabalhoFuncionario().getModel().setSelectedItem(this.view.getSelectTrabalhoFuncionario().getModel().getElementAt(indiceSelecionadoFuncionario));
            this.view.getSelectTrabalhoPeca().getModel().setSelectedItem(this.view.getSelectTrabalhoPeca().getModel().getElementAt(indiceSelecionadoPeca));
        }

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
            String selectFuncionario = this.view.getSelectTrabalhoFuncionario().getSelectedItem().toString();
            String[] idNomeFuncionario = selectFuncionario.split(" - ");
            int idFuncionario = Integer.parseInt(idNomeFuncionario[0]);
            model.setIdFuncionario(idFuncionario);
            String selectPeca = this.view.getSelectTrabalhoPeca().getSelectedItem().toString();
            String[] idNomePeca = selectPeca.split(" - ");
            int idPeca = Integer.parseInt(idNomePeca[0]);
            model.setIdPeca(idPeca);
            if (this.view.getAcao() == "cadastrar") {
                trabalhoDAO.cadastrar(model);
            } else if (this.view.getAcao() == "editar") {
                model.setId(this.view.getIdTrabalho());
                trabalhoDAO.alterar(model);
            }
            this.view.getCampoTrabalhoNome().setText("");
            this.view.getCampoTrabalhoDataEntrega().setText("");
            this.view.getSelectTrabalhoFuncionario().setSelectedIndex(-1);
            this.view.getSelectTrabalhoPeca().setSelectedIndex(-1);
            this.view.dispose();
            pai.atualizaTabela();
        } else if (e.getSource() == this.view.getBotaoTrabalhoCancelar()) {
            this.view.getCampoTrabalhoNome().setText("");
            this.view.getCampoTrabalhoDataEntrega().setText("");
            this.view.getSelectTrabalhoFuncionario().setSelectedIndex(-1);
            this.view.getSelectTrabalhoPeca().setSelectedIndex(-1);
            this.view.dispose();
            pai.atualizaTabela();
        }

    }

}
