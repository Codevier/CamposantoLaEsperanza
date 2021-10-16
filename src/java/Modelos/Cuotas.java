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
public class Cuotas {
    
    private String id_cuota, id_contrato, fecha_registro, estado, fecha_programada, fecha_realizada, num_papeleta, valor_apagar, valor_cancelado, numero_cuota;

    public Cuotas(String id_cuota, String id_contrato, String fecha_registro, String estado, String fecha_programada, String fecha_realizada, String num_papeleta, String valor_apagar, String valor_cancelado, String numero_cuota) {
        this.id_cuota = id_cuota;
        this.id_contrato = id_contrato;
        this.fecha_registro = fecha_registro;
        this.estado = estado;
        this.fecha_programada = fecha_programada;
        this.fecha_realizada = fecha_realizada;
        this.num_papeleta = num_papeleta;
        this.valor_apagar = valor_apagar;
        this.valor_cancelado = valor_cancelado;
        this.numero_cuota = numero_cuota;
    }

    public String getId_cuota() {
        return id_cuota;
    }

    public void setId_cuota(String id_cuota) {
        this.id_cuota = id_cuota;
    }

    public String getId_contrato() {
        return id_contrato;
    }

    public void setId_contrato(String id_contrato) {
        this.id_contrato = id_contrato;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_programada() {
        return fecha_programada;
    }

    public void setFecha_programada(String fecha_programada) {
        this.fecha_programada = fecha_programada;
    }

    public String getFecha_realizada() {
        return fecha_realizada;
    }

    public void setFecha_realizada(String fecha_realizada) {
        this.fecha_realizada = fecha_realizada;
    }

    public String getNum_papeleta() {
        return num_papeleta;
    }

    public void setNum_papeleta(String num_papeleta) {
        this.num_papeleta = num_papeleta;
    }

    public String getValor_apagar() {
        return valor_apagar;
    }

    public void setValor_apagar(String valor_apagar) {
        this.valor_apagar = valor_apagar;
    }

    public String getValor_cancelado() {
        return valor_cancelado;
    }

    public void setValor_cancelado(String valor_cancelado) {
        this.valor_cancelado = valor_cancelado;
    }

    public String getNumero_cuota() {
        return numero_cuota;
    }

    public void setNumero_cuota(String numero_cuota) {
        this.numero_cuota = numero_cuota;
    }

    
    
    
    
	
    
}
