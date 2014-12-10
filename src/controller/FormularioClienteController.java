package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.*;
import view.*;

public class FormularioClienteController implements ActionListener {

    private FormularioClienteView view = null;
    private ManterClienteController pai = null;
    
    public FormularioClienteView getView() {
        return this.view;
    }

    public FormularioClienteController(FormularioClienteView view, ManterClienteController pai) {
        // Aponto para a View  deste Controller
        this.view = view;
        this.pai = pai;
        //Definindo os listeners para os botoes dessa view.
        this.view.getBotaoClienteSalvar().addActionListener(this);
        this.view.getBotaoClienteCancelar().addActionListener(this);
        Cliente cliente = new Cliente();
        if (this.view.getAcao() == "editar") {
            ClienteDAO clienteDAO = new ClienteDAO();
            cliente = clienteDAO.retornaDados(this.view.getIdCliente());
            this.view.getCampoClienteNome().setText(cliente.getNome());
            if (cliente.getPessoa().equals("Física")) {
                this.view.getRbPessoaFisica().setSelected(true);
            } else if (cliente.getPessoa().equals("Jurídica")) {
                this.view.getRbPessoaJuridica().setSelected(true);
            }
            this.view.getCampoClienteCpfCnpj().setText(cliente.getCpfCnpj());
            this.view.getCampoClienteContato().setText(cliente.getContato());
            this.view.getCampoClienteLogin().setText(cliente.getLogin());
            this.view.getCampoClienteSenha().setText(cliente.getSenha());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Ações da Tela
        if (e.getSource() == this.view.getBotaoClienteSalvar()) {
            //Chama a Tela de Gestão de Crianca
            Cliente model = new Cliente();
            ClienteDAO clienteDAO = new ClienteDAO();
            model.setNome(this.view.getCampoClienteNome().getText());
            if (this.view.getRbPessoaFisica().isSelected()) {
                model.setPessoa(this.view.getRbPessoaFisica().getText());
            } else if (this.view.getRbPessoaJuridica().isSelected()) {
                model.setPessoa(this.view.getRbPessoaJuridica().getText());
            }
            model.setCpfCnpj(this.view.getCampoClienteCpfCnpj().getText());
            model.setContato(this.view.getCampoClienteContato().getText());
            model.setLogin(this.view.getCampoClienteLogin().getText());
            model.setSenha(this.view.getCampoClienteSenha().getText());
            if (this.view.getAcao() == "cadastrar") {
                clienteDAO.cadastrar(model);
                JOptionPane.showMessageDialog(this.getView(), "Cliente cadastrado com sucesso.");
            } else if (this.view.getAcao() == "editar") {
                model.setId(this.view.getIdCliente());
                clienteDAO.alterar(model);
                JOptionPane.showMessageDialog(this.getView(), "Cliente editado com sucesso.");
            }
            this.view.getCampoClienteNome().setText("");
            this.view.getRbPessoaFisica().setSelected(false);
            this.view.getRbPessoaJuridica().setSelected(false);
            this.view.getCampoClienteCpfCnpj().setText("");
            this.view.getCampoClienteContato().setText("");
            this.view.getCampoClienteLogin().setText("");
            this.view.getCampoClienteSenha().setText("");
            this.view.dispose();
            pai.atualizaTabela();
        } else if (e.getSource() == this.view.getBotaoClienteCancelar()) {
            this.view.dispose();
        }

    }

}
