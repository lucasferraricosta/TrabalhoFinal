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
public class PecaDAO {

    private Connection conn = null;

    public PecaDAO() {
        try {
            //Registra JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //Abrindo a conexão: ATENÇÃO OS DOIS PARÂMETROS VAZIOS("") SÃO USUÁRIO E SENHA, RESPECTIVAMENTE.
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdtrabalhofinal", "root", "");

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }//Fim try
    }

    public void cadastrar(Peca peca) {
        try {

            //Executa a query de inserção
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO peca (nome, descricao, idcliente) VALUES (?,?,?)");

            pstmt.setString(1, peca.getNome());
            pstmt.setString(2, peca.getDescricao());
            pstmt.setInt(3, peca.getIdCliente());

            pstmt.execute();

            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        }//Fim try
    }

    public void alterar(Peca peca) {
        try {

            //Executa a query de alteração
            PreparedStatement pstmt = conn.prepareStatement("UPDATE peca SET "
                    + "nome = ? ,"
                    + "descricao = ?,"
                    + "idcliente = ? "
                    + "WHERE idpeca = ?");

            pstmt.setString(1, peca.getNome());
            pstmt.setString(2, peca.getDescricao());
            pstmt.setInt(3, peca.getIdCliente());
            pstmt.setInt(4, peca.getId());

            pstmt.execute();

            pstmt.close();

        } catch (SQLException e) {
            System.out.println(e);
        }//Fim try
    }

    public void excluir(Peca peca) {
        try {

            //Executa a query de exclusão
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM peca  "
                    + "WHERE idpeca = ? ");

            pstmt.setInt(1, peca.getId());

            pstmt.execute();

            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        }//Fim try
    }

    public List<Peca> listar(int idCliente) {

        try {
            PreparedStatement pstmt;
            List<Peca> listaPecas = new ArrayList<>();

            String sql = "select * from peca";
            // Cria a PreparedStatement com o SQL
            if(idCliente != 0){
                sql += " where idCliente = ?";
            }
            pstmt = conn.prepareStatement(sql);
            
            if(idCliente != 0){
                pstmt.setInt(1, idCliente);
            }
            //Executa a query de seleção
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                // criando o objeto cliente
                Peca peca = new Peca(rs.getInt("idpeca"), rs.getString("nome"), rs.getString("descricao"), rs.getInt("idcliente"));
                // adicionando o objeto à lista
                listaPecas.add(peca);
            }
            rs.close();
            pstmt.close();

            return listaPecas;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Peca retornaDados(int idpeca) {
        try {
            PreparedStatement pstmt;
            List<Peca> listaPecas = new ArrayList<>();

            String sql = "select * from peca WHERE idpeca = ?";

            // Cria a PreparedStatement com o SQL
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, idpeca);
            //Executa a query de seleção
            ResultSet rs = pstmt.executeQuery();
            Peca peca = null;
            while (rs.next()) {
                peca = new Peca(rs.getInt("idpeca"), rs.getString("nome"), rs.getString("descricao"), rs.getInt("idcliente"));
            }
            rs.close();
            pstmt.close();

            return peca;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

}
