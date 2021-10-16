/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author Usuario
 */
public class Contratos {
    private String  id_contrato, numero_contrato, id_propiedad, id_tpcontrato, cedula_cliente, cedula_usuario, fecha_del_contrato, valor_de_entrada, costo, saldo, fecha_registro;

    public Contratos(String id_contrato, String numero_contrato, String id_propiedad, String id_tpcontrato, String cedula_cliente, String cedula_usuario, String fecha_del_contrato, String valor_de_entrada, String costo, String saldo, String fecha_registro) {
        this.id_contrato = id_contrato;
        this.numero_contrato = numero_contrato;
        this.id_propiedad = id_propiedad;
        this.id_tpcontrato = id_tpcontrato;
        this.cedula_cliente = cedula_cliente;
        this.cedula_usuario = cedula_usuario;
        this.fecha_del_contrato = fecha_del_contrato;
        this.valor_de_entrada = valor_de_entrada;
        this.costo = costo;
        this.saldo = saldo;
        this.fecha_registro = fecha_registro;
    }

    public String getId_contrato() {
        return id_contrato;
    }

    public void setId_contrato(String id_contrato) {
        this.id_contrato = id_contrato;
    }

    public String getNumero_contrato() {
        return numero_contrato;
    }

    public void setNumero_contrato(String numero_contrato) {
        this.numero_contrato = numero_contrato;
    }

    public String getId_propiedad() {
        return id_propiedad;
    }

    public void setId_propiedad(String id_propiedad) {
        this.id_propiedad = id_propiedad;
    }

    public String getId_tpcontrato() {
        return id_tpcontrato;
    }

    public void setId_tpcontrato(String id_tpcontrato) {
        this.id_tpcontrato = id_tpcontrato;
    }

    public String getCedula_cliente() {
        return cedula_cliente;
    }

    public void setCedula_cliente(String cedula_cliente) {
        this.cedula_cliente = cedula_cliente;
    }

    public String getCedula_usuario() {
        return cedula_usuario;
    }

    public void setCedula_usuario(String cedula_usuario) {
        this.cedula_usuario = cedula_usuario;
    }

    public String getFecha_del_contrato() {
        return fecha_del_contrato;
    }

    public void setFecha_del_contrato(String fecha_del_contrato) {
        this.fecha_del_contrato = fecha_del_contrato;
    }

    public String getValor_de_entrada() {
        return valor_de_entrada;
    }

    public void setValor_de_entrada(String valor_de_entrada) {
        this.valor_de_entrada = valor_de_entrada;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
	
    
    
    
}
