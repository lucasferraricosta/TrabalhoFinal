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

public class AreaClienteController implements ActionListener {

    private AreaClienteView view = null;
    private int idCliente;
    
    public AreaClienteView getView() {
        return this.view;
    }
    
    public int getIdCliente(){
        return this.idCliente;
    }
    
    public AreaClienteController(AreaClienteView view, int idCliente) {
        // Aponto para a View  deste Controller
        this.view = view;
        this.idCliente = idCliente;
        //Definindo os listeners para os botoes dessa view.
        this.view.getBotaoPecaCadastrar().addActionListener(this);
        this.view.getBotaoPecaVisualizar().addActionListener(this);
        this.view.getBotaoPecaEditar().addActionListener(this);
        this.view.getBotaoPecaExcluir().addActionListener(this);
        this.view.getBotaoPecaVoltar().addActionListener(this);
        
        
        this.view.getListaPecas().getColumnModel().getColumn(0).setPreferredWidth(20);
        this.view.getListaPecas().getColumnModel().getColumn(1).setPreferredWidth(150);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Ações da Tela
        if (e.getSource() == this.view.getBotaoPecaCadastrar()) {
            FormularioPecaView tela = new FormularioPecaView("cadastrar");
            FormularioPecaController controle = new FormularioPecaController(tela, this);
            controle.getView().setVisible(true);
        } else if (e.getSource() == this.view.getBotaoPecaVisualizar()) {
            PecaDAO pecaDAO = new PecaDAO();
            int idPeca = Integer.parseInt(this.view.getListaPecas().getValueAt(this.view.getListaPecas().getSelectedRow(), 0).toString());
            Peca peca = pecaDAO.retornaDados(idPeca);
            String texto = "Id: " + peca.getId() + "\n";
            texto += "Nome: " + peca.getNome() + "\n";
            texto += "Descrição: " + peca.getDescricao();
            this.view.getTextAreaDadosPeca().setText(texto);
        } else if (e.getSource() == this.view.getBotaoPecaEditar()) {
            FormularioPecaView tela = new FormularioPecaView("editar", Integer.parseInt(this.view.getListaPecas().getValueAt(this.view.getListaPecas().getSelectedRow(), 0).toString()));
            FormularioPecaController controle = new FormularioPecaController(tela, this);
            controle.getView().setVisible(true);
        } else if (e.getSource() == this.view.getBotaoPecaExcluir()) {
            PecaDAO pecaDAO = new PecaDAO();
            int idPeca = Integer.parseInt(this.view.getListaPecas().getValueAt(this.view.getListaPecas().getSelectedRow(), 0).toString());
            Peca peca = new Peca();
            peca.setId(idPeca);
            pecaDAO.excluir(peca);
            this.atualizaTabela();
        } else if (e.getSource() == this.view.getBotaoPecaVoltar()) {
            this.view.getTextAreaDadosPeca().setText("");
            this.view.dispose();
        }

    }

    public void atualizaTabela() {
        DefaultTableModel modeloTabela = (DefaultTableModel) this.view.getListaPecas().getModel();
        modeloTabela.setNumRows(0);
        PecaDAO pecaDAO = new PecaDAO();
        List<Peca> lista = pecaDAO.listar();
        for (Peca peca : lista) {
            modeloTabela.addRow(new Object[]{peca.getId(), peca.getNome()});
        }
        this.view.getListaPecas().setModel(modeloTabela);
    }
}
