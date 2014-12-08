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
public class TrabalhoDAO {

    private Connection conn = null;

    public TrabalhoDAO() {
        try {
            //Registra JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //Abrindo a conexão: ATENÇÃO OS DOIS PARÂMETROS VAZIOS("") SÃO USUÁRIO E SENHA, RESPECTIVAMENTE.
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdtrabalhofinal", "root", "");

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }//Fim try
    }

    public void cadastrar(Trabalho trabalho) {
        try {

            //Executa a query de inserção
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO trabalho (idpeca, idfuncionario, data_entrega) VALUES (?,?,?)");

            pstmt.setInt(1, trabalho.getIdPeca());
            pstmt.setInt(2, trabalho.getIdFuncionario());
            pstmt.setString(3, trabalho.getDataEntrega());

            pstmt.execute();

            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        }//Fim try
    }

    public void alterar(Trabalho trabalho) {
        try {

            //Executa a query de alteração
            PreparedStatement pstmt = conn.prepareStatement("UPDATE trabalho SET "
                    + "idpeca = ? ,"
                    + "idfuncionario = ?,"
                    + "data_entrega = ? ,"
                    + "horas_trabalhadas = ? ,"
                    + "WHERE idtrabalho = ? ");

            pstmt.setInt(1, trabalho.getIdPeca());
            pstmt.setInt(2, trabalho.getIdFuncionario());
            pstmt.setString(3, trabalho.getDataEntrega());
            pstmt.setInt(4, trabalho.getHorasTrabalhadas());
            pstmt.setInt(5, trabalho.getId());

            pstmt.execute();

            pstmt.close();

        } catch (SQLException e) {
            System.out.println(e);
        }//Fim try
    }

    public void excluir(Trabalho trabalho) {
        try {

            //Executa a query de exclusão
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM trabalho  "
                    + "WHERE idtrabalho = ? ");

            pstmt.setInt(1, trabalho.getId());

            pstmt.execute();

            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        }//Fim try
    }

    public List<Trabalho> listar() {

        try {
            PreparedStatement pstmt;
            List<Trabalho> listaTrabalhos = new ArrayList<>();

            String sql = "select * from trabalho";

            // Cria a PreparedStatement com o SQL
            pstmt = conn.prepareStatement(sql);
            //Executa a query de seleção
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                // criando o objeto cliente
                Trabalho trabalho = new Trabalho(rs.getInt("idtrabalho"), rs.getInt("idpeca"), rs.getInt("idfuncionario"), rs.getString("data_entrega"), rs.getInt("horas_trabalhadas"));
                // adicionando o objeto à lista
                listaTrabalhos.add(trabalho);
            }
            rs.close();
            pstmt.close();

            return listaTrabalhos;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}