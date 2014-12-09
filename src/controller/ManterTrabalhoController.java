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

public class ManterTrabalhoController implements ActionListener {

    private ManterTrabalhoView view = null;

    public ManterTrabalhoView getView() {
        return this.view;
    }

    public ManterTrabalhoController(ManterTrabalhoView view) {
        // Aponto para a View  deste Controller
        this.view = view;
        //Definindo os listeners para os botoes dessa view.
        this.view.getBotaoTrabalhoCadastrar().addActionListener(this);
        this.view.getBotaoTrabalhoVisualizar().addActionListener(this);
        this.view.getBotaoTrabalhoEditar().addActionListener(this);
        this.view.getBotaoTrabalhoExcluir().addActionListener(this);
        this.view.getBotaoTrabalhoVoltar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Ações da Tela
        if (e.getSource() == this.view.getBotaoTrabalhoCadastrar()) {
            FormularioTrabalhoView tela = new FormularioTrabalhoView("cadastrar");
            FormularioTrabalhoController controle = new FormularioTrabalhoController(tela, this);
            controle.getView().setVisible(true);
        } else if (e.getSource() == this.view.getBotaoTrabalhoVisualizar()) {
            TrabalhoDAO trabalhoDAO = new TrabalhoDAO();
            int idTrabalho = Integer.parseInt(this.view.getListaTrabalhos().getValueAt(this.view.getListaTrabalhos().getSelectedRow(), 0).toString());
            Trabalho trabalho = trabalhoDAO.retornaDados(idTrabalho);
            String texto = "Id: " + trabalho.getId() + "\n";
            texto += "Nome: " + trabalho.getNome() + "\n";
            texto += "Peca: " + trabalho.getIdPeca() + "\n";
            texto += "Funcionario: " + trabalho.getIdFuncionario()+ "\n";
            texto += "Data Entrega: " + trabalho.getDataEntrega()+ "\n";
            texto += "Horas Trabalhadas: " + trabalho.getHorasTrabalhadas();
            this.view.getTextAreaDadosTrabalho().setText(texto);
        } else if (e.getSource() == this.view.getBotaoTrabalhoEditar()) {
            FormularioTrabalhoView tela = new FormularioTrabalhoView("editar", Integer.parseInt(this.view.getListaTrabalhos().getValueAt(this.view.getListaTrabalhos().getSelectedRow(), 0).toString()));
            FormularioTrabalhoController controle = new FormularioTrabalhoController(tela, this);
            controle.getView().setVisible(true);
        } else if (e.getSource() == this.view.getBotaoTrabalhoExcluir()) {
            TrabalhoDAO trabalhoDAO = new TrabalhoDAO();
            int idTrabalho = Integer.parseInt(this.view.getListaTrabalhos().getValueAt(this.view.getListaTrabalhos().getSelectedRow(), 0).toString());
            Trabalho trabalho = new Trabalho();
            trabalho.setId(idTrabalho);
            trabalhoDAO.excluir(trabalho);
            this.atualizaTabela();
        } else if (e.getSource() == this.view.getBotaoTrabalhoVoltar()) {
            this.view.getTextAreaDadosTrabalho().setText("");
            System.exit(0);
        }

    }

    public void atualizaTabela() {
        DefaultTableModel modeloTabela = new DefaultTableModel();
        modeloTabela.setNumRows(0);
        TrabalhoDAO trabalhoDAO = new TrabalhoDAO();
        List<Trabalho> lista = trabalhoDAO.listar();
        for (Trabalho trabalho : lista) {
            modeloTabela.addRow(new Object[]{trabalho.getId(), trabalho.getNome()});
        }
        this.view.getListaTrabalhos().setModel(modeloTabela);
    }
}
