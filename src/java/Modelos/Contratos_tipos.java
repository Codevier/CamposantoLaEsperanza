/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Contratos_tipos {
    public String id_tipo_contrato, valor_boveda, valor_entrada, saldo, tipo_de_contrato, cantidad_meses, valor_mes;

    public Contratos_tipos(String id_tipo_contrato, String valor_boveda, String valor_entrada, String saldo, String tipo_de_contrato, String cantidad_meses, String valor_mes) {
        this.id_tipo_contrato = id_tipo_contrato;
        this.valor_boveda = valor_boveda;
        this.valor_entrada = valor_entrada;
        this.saldo = saldo;
        this.tipo_de_contrato = tipo_de_contrato;
        this.cantidad_meses = cantidad_meses;
        this.valor_mes = valor_mes;
    }

    public String getId_tipo_contrato() {
        return id_tipo_contrato;
    }

    public void setId_tipo_contrato(String id_tipo_contrato) {
        this.id_tipo_contrato = id_tipo_contrato;
    }

    public String getValor_boveda() {
        return valor_boveda;
    }

    public void setValor_boveda(String valor_boveda) {
        this.valor_boveda = valor_boveda;
    }

    public String getValor_entrada() {
        return valor_entrada;
    }

    public void setValor_entrada(String valor_entrada) {
        this.valor_entrada = valor_entrada;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getTipo_de_contrato() {
        return tipo_de_contrato;
    }

    public void setTipo_de_contrato(String tipo_de_contrato) {
        this.tipo_de_contrato = tipo_de_contrato;
    }

    public String getCantidad_meses() {
        return cantidad_meses;
    }

    public void setCantidad_meses(String cantidad_meses) {
        this.cantidad_meses = cantidad_meses;
    }

    public String getValor_mes() {
        return valor_mes;
    }

    public void setValor_mes(String valor_mes) {
        this.valor_mes = valor_mes;
    }
    
    static public List<Contratos_tipos> listarTiposContratos(String[][] lista) {
        List<Contratos_tipos> Res = new ArrayList<>();
        for (String[] Objeto : lista) {
            try {
                              
                String id_tipo_contrato= Objeto[0];
                String valor_boveda= Objeto[1];
                String valor_entrada= Objeto[2];
                String saldo= Objeto[3];
                String tipo_de_contrato= Objeto[4];
                String cantidad_meses= Objeto[5];
                String valor_mes= Objeto[6];

                Contratos_tipos c = new Contratos_tipos(id_tipo_contrato, valor_boveda, valor_entrada, saldo, tipo_de_contrato, cantidad_meses, valor_mes);
                Res.add(c);
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }

        }
        return Res;
    }
}
