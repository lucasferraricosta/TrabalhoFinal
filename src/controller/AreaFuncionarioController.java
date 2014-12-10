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

public class AreaFuncionarioController implements ActionListener {

    private AreaFuncionarioView view = null;
    private int idFuncionario;

    public AreaFuncionarioView getView() {
        return this.view;
    }

    public int getIdFuncionario() {
        return this.idFuncionario;
    }

    public AreaFuncionarioController(AreaFuncionarioView view, int idFuncionario) {
        // Aponto para a View  deste Controller
        this.view = view;
        this.idFuncionario = idFuncionario;
        //Definindo os listeners para os botoes dessa view.
        this.view.getBotaoFuncionarioHoras().addActionListener(this);
        this.view.getBotaoFuncionarioSair().addActionListener(this);

        this.view.getTabelaFuncionarioTrabalhos().getColumnModel().getColumn(0).setPreferredWidth(30);
        this.view.getTabelaFuncionarioTrabalhos().getColumnModel().getColumn(1).setPreferredWidth(30);
        this.view.getTabelaFuncionarioTrabalhos().getColumnModel().getColumn(2).setPreferredWidth(30);
        this.view.getTabelaFuncionarioTrabalhos().getColumnModel().getColumn(3).setPreferredWidth(30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Ações da Tela
        if (e.getSource() == this.view.getBotaoFuncionarioHoras()) {
            if (this.view.getTabelaFuncionarioTrabalhos().getSelectedRowCount() > 0) {
                TrabalhoDAO trabalhoDAO = new TrabalhoDAO();
                int idTrabalho = Integer.parseInt(this.view.getTabelaFuncionarioTrabalhos().getValueAt(this.view.getTabelaFuncionarioTrabalhos().getSelectedRow(), 0).toString());
                Trabalho trabalho = new Trabalho();
                trabalho.setId(idTrabalho);
                trabalho.setHorasTrabalhadas(Integer.parseInt(this.view.getCampoFuncionarioHorasTrabalhadas().getText()));
                trabalhoDAO.adicionarHoras(trabalho);
                JOptionPane.showMessageDialog(this.getView(), "Horas Adicionadas com sucesso.");
                this.view.getCampoFuncionarioHorasTrabalhadas().setText("");
                this.atualizaTabela();
            } else {
                JOptionPane.showMessageDialog(view, "Selecione um item na lista.");
            }
        } else if (e.getSource() == this.view.getBotaoFuncionarioSair()) {
            this.view.getCampoFuncionarioHorasTrabalhadas().setText("");
            this.view.dispose();
        }

    }

    public void atualizaTabela() {
        DefaultTableModel modeloTabela = (DefaultTableModel) this.view.getTabelaFuncionarioTrabalhos().getModel();
        modeloTabela.setNumRows(0);
        TrabalhoDAO trabalhoDAO = new TrabalhoDAO();
        List<Trabalho> lista = trabalhoDAO.listar();
        for (Trabalho trabalho : lista) {
            modeloTabela.addRow(new Object[]{trabalho.getId(), trabalho.getNome(), trabalho.getDataEntrega(), trabalho.getHorasTrabalhadas()});
        }
        this.view.getTabelaFuncionarioTrabalhos().setModel(modeloTabela);
    }
}
