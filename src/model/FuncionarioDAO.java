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
public class FuncionarioDAO {

    private Connection conn = null;

    public void cadastrar(Funcionario funcionario) {
        try {

            //Executa a query de inserção
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO funcionario (nome,idade, sexo, cpf, login, senha) VALUES (?,?,?,?,?,?)");

            pstmt.setString(1, funcionario.getNome());
            pstmt.setInt(2, funcionario.getIdade());
            pstmt.setString(3, funcionario.getSexo());
            pstmt.setString(4, funcionario.getCpf());
            pstmt.setString(5, funcionario.getLogin());
            pstmt.setString(6, funcionario.getSenha());

            pstmt.execute();

            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        }//Fim try
    }

    public void alterar(Funcionario funcionario) {
        try {

            //Executa a query de alteração
            PreparedStatement pstmt = conn.prepareStatement("UPDATE funcionario SET "
                    + "nome = ? ,"
                    + "idade = ?,"
                    + "sexo = ? ,"
                    + "cpf = ? ,"
                    + "login = ? ,"
                    + "senha = ? "
                    + "WHERE id = ? ");

            pstmt.setString(1, funcionario.getNome());
            pstmt.setInt(2, funcionario.getIdade());
            pstmt.setString(3, funcionario.getSexo());
            pstmt.setString(4, funcionario.getCpf());
            pstmt.setString(5, funcionario.getLogin());
            pstmt.setString(6, funcionario.getSenha());
            pstmt.setInt(7, funcionario.getId());

            pstmt.execute();

            pstmt.close();

        } catch (SQLException e) {
            System.out.println(e);
        }//Fim try
    }

    public void excluir(Funcionario funcionario) {
        try {

            //Executa a query de exclusão
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM funcionario  "
                    + "WHERE id = ? ");

            pstmt.setInt(1, funcionario.getId());

            pstmt.execute();

            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        }//Fim try
    }

    public List<Funcionario> listar() {

        try {
            PreparedStatement pstmt;
            List<Funcionario> listaFuncionarios = new ArrayList<>();

            String sql = "select * from funcionario";

            // Cria a PreparedStatement com o SQL
            pstmt = conn.prepareStatement(sql);
            //Executa a query de seleção
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                // criando o objeto cliente
                Funcionario funcionario = new Funcionario(rs.getInt("id"), rs.getString("nome"), rs.getInt("idade"), rs.getString("sexo"), rs.getString("cpf"), rs.getString("login"), rs.getString("senha"));
                // adicionando o objeto à lista
                listaFuncionarios.add(funcionario);
            }
            rs.close();
            pstmt.close();

            return listaFuncionarios;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
