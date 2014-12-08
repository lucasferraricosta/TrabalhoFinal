/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Lucas
 */
public class Trabalho {

    private int id;
    private int idPeca;
    private int idFuncionario;
    private String dataEntrega;
    private int horasTrabalhadas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(int idPeca) {
        this.idPeca = idPeca;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(int horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public Trabalho(int id, int idPeca, int idFuncionario, String dataEntrega, int horasTrabalhadas) {
        this.id = id;
        this.idPeca = idPeca;
        this.idFuncionario = idFuncionario;
        this.dataEntrega = dataEntrega;
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public Trabalho() {
    }

}
