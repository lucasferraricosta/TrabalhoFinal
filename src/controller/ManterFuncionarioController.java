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

public class ManterFuncionarioController implements ActionListener {

    private ManterFuncionarioView view = null;

    public ManterFuncionarioView getView() {
        return this.view;
    }

    public ManterFuncionarioController(ManterFuncionarioView view) {
        // Aponto para a View  deste Controller
        this.view = view;
        //Definindo os listeners para os botoes dessa view.
        this.view.getBotaoFuncionarioCadastrar().addActionListener(this);
        this.view.getBotaoFuncionarioVisualizar().addActionListener(this);
        this.view.getBotaoFuncionarioEditar().addActionListener(this);
        this.view.getBotaoFuncionarioExcluir().addActionListener(this);
        this.view.getBotaoFuncionarioVoltar().addActionListener(this);
        
        
        this.view.getListaFuncionarios().getColumnModel().getColumn(0).setPreferredWidth(20);
        this.view.getListaFuncionarios().getColumnModel().getColumn(1).setPreferredWidth(150);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Ações da Tela
        if (e.getSource() == this.view.getBotaoFuncionarioCadastrar()) {
            FormularioFuncionarioView tela = new FormularioFuncionarioView("cadastrar");
            FormularioFuncionarioController controle = new FormularioFuncionarioController(tela, this);
            controle.getView().setVisible(true);
        } else if (e.getSource() == this.view.getBotaoFuncionarioVisualizar()) {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            int idFuncionario = Integer.parseInt(this.view.getListaFuncionarios().getValueAt(this.view.getListaFuncionarios().getSelectedRow(), 0).toString());
            Funcionario funcionario = funcionarioDAO.retornaDados(idFuncionario);
            String texto = "Id: " + funcionario.getId() + "\n";
            texto += "Nome: " + funcionario.getNome() + "\n";
            texto += "Sexo: " + funcionario.getSexo() + "\n";
            texto += "CPF: " + funcionario.getCpf() + "\n";
            texto += "Idade: " + funcionario.getIdade()+ "\n";
            texto += "Login: " + funcionario.getLogin() + "\n";
            texto += "Senha: " + funcionario.getSenha();
            this.view.getTextAreaDadosFuncionario().setText(texto);
        } else if (e.getSource() == this.view.getBotaoFuncionarioEditar()) {
            FormularioFuncionarioView tela = new FormularioFuncionarioView("editar", Integer.parseInt(this.view.getListaFuncionarios().getValueAt(this.view.getListaFuncionarios().getSelectedRow(), 0).toString()));
            FormularioFuncionarioController controle = new FormularioFuncionarioController(tela, this);
            controle.getView().setVisible(true);
        } else if (e.getSource() == this.view.getBotaoFuncionarioExcluir()) {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            int idFuncionario = Integer.parseInt(this.view.getListaFuncionarios().getValueAt(this.view.getListaFuncionarios().getSelectedRow(), 0).toString());
            Funcionario funcionario = new Funcionario();
            funcionario.setId(idFuncionario);
            funcionarioDAO.excluir(funcionario);
            JOptionPane.showMessageDialog(this.getView(), "Funcionário excluído com sucesso.");
            this.atualizaTabela();
        } else if (e.getSource() == this.view.getBotaoFuncionarioVoltar()) {
            this.view.getTextAreaDadosFuncionario().setText("");
            this.view.dispose();
        }

    }

    public void atualizaTabela() {
        DefaultTableModel modeloTabela = (DefaultTableModel) this.view.getListaFuncionarios().getModel();
        modeloTabela.setNumRows(0);
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        List<Funcionario> lista = funcionarioDAO.listar();
        for (Funcionario funcionario : lista) {
            modeloTabela.addRow(new Object[]{funcionario.getId(), funcionario.getNome()});
        }
        this.view.getListaFuncionarios().setModel(modeloTabela);
    }
}
