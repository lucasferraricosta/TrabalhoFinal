package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.*;
import view.*;

public class FormularioFuncionarioController implements ActionListener {

    private FormularioFuncionarioView view = null;
    private ManterFuncionarioController pai = null;
    
    public FormularioFuncionarioView getView() {
        return this.view;
    }

    public FormularioFuncionarioController(FormularioFuncionarioView view, ManterFuncionarioController pai) {
        // Aponto para a View  deste Controller
        this.view = view;
        this.pai = pai;
        //Definindo os listeners para os botoes dessa view.
        this.view.getBotaoFuncionarioSalvar().addActionListener(this);
        this.view.getBotaoFuncionarioCancelar().addActionListener(this);
        Funcionario funcionario = new Funcionario();
        if (this.view.getAcao() == "editar") {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            funcionario = funcionarioDAO.retornaDados(this.view.getIdFuncionario());
            this.view.getCampoFuncionarioNome().setText(funcionario.getNome());
            if (funcionario.getSexo().equals("Masculino")) {
                this.view.getRbSexoMasculino().setSelected(true);
            } else if (funcionario.getSexo().equals("Feminino")) {
                this.view.getRbSexoFeminino().setSelected(true);
            }
            this.view.getCampoFuncionarioCpf().setText(funcionario.getCpf());
            this.view.getCampoFuncionarioIdade().setText(""+funcionario.getIdade());
            this.view.getCampoFuncionarioLogin().setText(funcionario.getLogin());
            this.view.getCampoFuncionarioSenha().setText(funcionario.getSenha());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Ações da Tela
        if (e.getSource() == this.view.getBotaoFuncionarioSalvar()) {
            //Chama a Tela de Gestão de Crianca
            Funcionario model = new Funcionario();
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            model.setNome(this.view.getCampoFuncionarioNome().getText());
            if (this.view.getRbSexoMasculino().isSelected()) {
                model.setSexo(this.view.getRbSexoMasculino().getText());
            } else if (this.view.getRbSexoFeminino().isSelected()) {
                model.setSexo(this.view.getRbSexoFeminino().getText());
            }
            model.setCpf(this.view.getCampoFuncionarioCpf().getText());
            model.setIdade(Integer.parseInt(this.view.getCampoFuncionarioIdade().getText()));
            model.setLogin(this.view.getCampoFuncionarioLogin().getText());
            model.setSenha(this.view.getCampoFuncionarioSenha().getText());
            if (this.view.getAcao() == "cadastrar") {
                funcionarioDAO.cadastrar(model);
            } else if (this.view.getAcao() == "editar") {
                model.setId(this.view.getIdFuncionario());
                funcionarioDAO.alterar(model);
            }
            this.view.getCampoFuncionarioNome().setText("");
            this.view.getRbSexoMasculino().setSelected(false);
            this.view.getRbSexoFeminino().setSelected(false);
            this.view.getCampoFuncionarioCpf().setText("");
            this.view.getCampoFuncionarioIdade().setText("");
            this.view.getCampoFuncionarioLogin().setText("");
            this.view.getCampoFuncionarioSenha().setText("");
            this.view.dispose();
            pai.atualizaTabela();
        } else if (e.getSource() == this.view.getBotaoFuncionarioCancelar()) {
            this.view.dispose();
        }

    }

}
