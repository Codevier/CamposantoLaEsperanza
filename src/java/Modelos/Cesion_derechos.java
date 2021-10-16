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
public class Cesion_derechos {
    
    private String id_contrato, numero_contrato,propiedad,cedula_cliente, nombre_completo,cedula_user, 
            fecha_del_contrato,valor_de_entrada, descrip_tipo, valor_propiedad, 
            entrada,saldo, cantidad_meses, valor_por_mes,   
            nombres_difunto, apellidos_difunto, cedula_difunto;

    public Cesion_derechos() {
    }

    
    public Cesion_derechos(String id_contrato, String numero_contrato, String propiedad, String cedula_cliente, String nombre_completo, String cedula_user, String fecha_del_contrato, String valor_de_entrada, String descrip_tipo, String valor_propiedad, String entrada, String saldo, String cantidad_meses, String valor_por_mes, String nombres_difunto, String apellidos_difunto, String cedula_difunto) {
        this.id_contrato = id_contrato;
        this.numero_contrato = numero_contrato;
        this.propiedad = propiedad;
        this.cedula_cliente = cedula_cliente;
        this.nombre_completo = nombre_completo;
        this.cedula_user = cedula_user;
        this.fecha_del_contrato = fecha_del_contrato;
        this.valor_de_entrada = valor_de_entrada;
        this.descrip_tipo = descrip_tipo;
        this.valor_propiedad = valor_propiedad;
        this.entrada = entrada;
        this.saldo = saldo;
        this.cantidad_meses = cantidad_meses;
        this.valor_por_mes = valor_por_mes;
        this.nombres_difunto = nombres_difunto;
        this.apellidos_difunto = apellidos_difunto;
        this.cedula_difunto = cedula_difunto;
        
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

    public String getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(String propiedad) {
        this.propiedad = propiedad;
    }

    public String getCedula_cliente() {
        return cedula_cliente;
    }

    public void setCedula_cliente(String cedula_cliente) {
        this.cedula_cliente = cedula_cliente;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getCedula_user() {
        return cedula_user;
    }

    public void setCedula_user(String cedula_user) {
        this.cedula_user = cedula_user;
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

    public String getDescrip_tipo() {
        return descrip_tipo;
    }

    public void setDescrip_tipo(String descrip_tipo) {
        this.descrip_tipo = descrip_tipo;
    }

    public String getValor_propiedad() {
        return valor_propiedad;
    }

    public void setValor_propiedad(String valor_propiedad) {
        this.valor_propiedad = valor_propiedad;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getCantidad_meses() {
        return cantidad_meses;
    }

    public void setCantidad_meses(String cantidad_meses) {
        this.cantidad_meses = cantidad_meses;
    }

    public String getValor_por_mes() {
        return valor_por_mes;
    }

    public void setValor_por_mes(String valor_por_mes) {
        this.valor_por_mes = valor_por_mes;
    }

    public String getNombres_difunto() {
        return nombres_difunto;
    }

    public void setNombres_difunto(String nombres_difunto) {
        this.nombres_difunto = nombres_difunto;
    }

    public String getApellidos_difunto() {
        return apellidos_difunto;
    }

    public void setApellidos_difunto(String apellidos_difunto) {
        this.apellidos_difunto = apellidos_difunto;
    }

    public String getCedula_difunto() {
        return cedula_difunto;
    }

    public void setCedula_difunto(String cedula_difunto) {
        this.cedula_difunto = cedula_difunto;
    }

    
    
    
    
    
    
}
