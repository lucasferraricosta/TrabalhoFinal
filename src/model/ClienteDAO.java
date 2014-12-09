/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class ClienteDAO {

    private Connection conn = null;

    public ClienteDAO() {
        try {
            //Registra JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //Abrindo a conexão: ATENÇÃO OS DOIS PARÂMETROS VAZIOS("") SÃO USUÁRIO E SENHA, RESPECTIVAMENTE.
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdtrabalhofinal", "root", "");

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }//Fim try
    }

    public void cadastrar(Cliente cliente) {
        try {

            //Executa a query de inserção
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO cliente (nome,pessoa, cpf_cnpj, contato, login, senha) VALUES (?,?,?,?,?,?)");

            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getPessoa());
            pstmt.setString(3, cliente.getCpfCnpj());
            pstmt.setString(4, cliente.getContato());
            pstmt.setString(5, cliente.getLogin());
            pstmt.setString(6, cliente.getSenha());

            pstmt.execute();

            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        }//Fim try
    }

    public void alterar(Cliente cliente) {
        try {

            //Executa a query de alteração
            PreparedStatement pstmt = conn.prepareStatement("UPDATE cliente SET "
                    + "nome = ? ,"
                    + "pessoa = ?,"
                    + "cpf_cnpj = ? ,"
                    + "contato = ? ,"
                    + "login = ? ,"
                    + "senha = ? "
                    + "WHERE idcliente = ? ");

            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getPessoa());
            pstmt.setString(3, cliente.getCpfCnpj());
            pstmt.setString(4, cliente.getContato());
            pstmt.setString(5, cliente.getLogin());
            pstmt.setString(6, cliente.getSenha());
            pstmt.setInt(7, cliente.getId());

            pstmt.execute();

            pstmt.close();

        } catch (SQLException e) {
            System.out.println(e);
        }//Fim try
    }

    public void excluir(Cliente cliente) {
        try {

            //Executa a query de exclusão
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM cliente  "
                    + "WHERE idcliente = ? ");

            pstmt.setInt(1, cliente.getId());

            pstmt.execute();

            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        }//Fim try
    }

    public List<Cliente> listar() {

        try {
            PreparedStatement pstmt;
            List<Cliente> listaClientes = new ArrayList<>();

            String sql = "select * from cliente";

            // Cria a PreparedStatement com o SQL
            pstmt = conn.prepareStatement(sql);
            //Executa a query de seleção
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                // criando o objeto cliente
                Cliente cliente = new Cliente(rs.getInt("idcliente"), rs.getString("nome"), rs.getString("pessoa"), rs.getString("cpf_cnpj"), rs.getString("contato"), rs.getString("login"), rs.getString("senha"));
                // adicionando o objeto à lista
                listaClientes.add(cliente);
            }
            rs.close();
            pstmt.close();

            return listaClientes;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Cliente retornaDados(int idcliente) {
        try {
            PreparedStatement pstmt;
            List<Cliente> listaClientes = new ArrayList<>();

            String sql = "select * from cliente WHERE idcliente = ?";

            // Cria a PreparedStatement com o SQL
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, idcliente);
            //Executa a query de seleção
            ResultSet rs = pstmt.executeQuery();
            Cliente cliente = null;
            while (rs.next()) {
                cliente = new Cliente(rs.getInt("idcliente"), rs.getString("nome"), rs.getString("pessoa"), rs.getString("cpf_cnpj"), rs.getString("contato"), rs.getString("login"), rs.getString("senha"));
            }
            rs.close();
            pstmt.close();

            return cliente;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
