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

        this.view.getListaTrabalhos().getColumnModel().getColumn(0).setPreferredWidth(20);
        this.view.getListaTrabalhos().getColumnModel().getColumn(1).setPreferredWidth(150);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Ações da Tela
        if (e.getSource() == this.view.getBotaoTrabalhoCadastrar()) {
            FormularioTrabalhoView tela = new FormularioTrabalhoView("cadastrar");
            FormularioTrabalhoController controle = new FormularioTrabalhoController(tela, this);
            controle.getView().setVisible(true);
        } else if (e.getSource() == this.view.getBotaoTrabalhoVisualizar()) {
            if (this.view.getListaTrabalhos().getSelectedRowCount() > 0) {
                TrabalhoDAO trabalhoDAO = new TrabalhoDAO();
                int idTrabalho = Integer.parseInt(this.view.getListaTrabalhos().getValueAt(this.view.getListaTrabalhos().getSelectedRow(), 0).toString());
                Trabalho trabalho = trabalhoDAO.retornaDados(idTrabalho);
                FuncionarioDAO funcDAO = new FuncionarioDAO();
                Funcionario func = funcDAO.retornaDados(trabalho.getIdFuncionario());
                PecaDAO pecaDAO = new PecaDAO();
                Peca peca = pecaDAO.retornaDados(trabalho.getIdPeca());
                String texto = "Id: " + trabalho.getId() + "\n";
                texto += "Nome: " + trabalho.getNome() + "\n";
                texto += "Peca: " + peca.getNome() + "\n";
                texto += "Funcionario: " + func.getNome() + "\n";
                texto += "Data Entrega: " + trabalho.getDataEntrega() + "\n";
                texto += "Horas Trabalhadas: " + trabalho.getHorasTrabalhadas();
                this.view.getTextAreaDadosTrabalho().setText(texto);
            } else {
                JOptionPane.showMessageDialog(view, "Selecione um item na lista.");
            }
        } else if (e.getSource() == this.view.getBotaoTrabalhoEditar()) {
            if (this.view.getListaTrabalhos().getSelectedRowCount() > 0) {
                FormularioTrabalhoView tela = new FormularioTrabalhoView("editar", Integer.parseInt(this.view.getListaTrabalhos().getValueAt(this.view.getListaTrabalhos().getSelectedRow(), 0).toString()));
                FormularioTrabalhoController controle = new FormularioTrabalhoController(tela, this);
                controle.getView().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(view, "Selecione um item na lista.");
            }
        } else if (e.getSource() == this.view.getBotaoTrabalhoExcluir()) {
            if (this.view.getListaTrabalhos().getSelectedRowCount() > 0) {
                int resposta = JOptionPane.showConfirmDialog(view, "Têm certeza que deseja excluir?");
                if (resposta == 0) {
                    TrabalhoDAO trabalhoDAO = new TrabalhoDAO();
                    int idTrabalho = Integer.parseInt(this.view.getListaTrabalhos().getValueAt(this.view.getListaTrabalhos().getSelectedRow(), 0).toString());
                    Trabalho trabalho = new Trabalho();
                    trabalho.setId(idTrabalho);
                    trabalhoDAO.excluir(trabalho);
                    JOptionPane.showMessageDialog(this.getView(), "Trabalho excluído com sucesso.");
                    this.atualizaTabela();
                }
            } else {
                JOptionPane.showMessageDialog(view, "Selecione um item na lista.");
            }
        } else if (e.getSource() == this.view.getBotaoTrabalhoVoltar()) {
            this.view.getTextAreaDadosTrabalho().setText("");
            this.view.dispose();
        }

    }

    public void atualizaTabela() {
        DefaultTableModel modeloTabela = (DefaultTableModel) this.view.getListaTrabalhos().getModel();
        modeloTabela.setNumRows(0);
        TrabalhoDAO trabalhoDAO = new TrabalhoDAO();
        List<Trabalho> lista = trabalhoDAO.listar(0);
        for (Trabalho trabalho : lista) {
            modeloTabela.addRow(new Object[]{trabalho.getId(), trabalho.getNome()});
        }
        this.view.getListaTrabalhos().setModel(modeloTabela);
    }
}
