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
public class CarteraVencida {

    private String cedula_cliente;
    private String nombre_completo;
    private String item;
    private String fecha_adquisicion;
    private String fecha_ultimo_pago;
    private String fecha_transcurrida;
    private String valor_adquisicion;
    private String valor_cancelado;
    private String valor_que_adeuda;
    private String valor_vencido;
    private String correo;

    
    public CarteraVencida(String cedula_cliente, String nombre_completo, String item, String fecha_adquisicion, String fecha_ultimo_pago, String fecha_transcurrida, String valor_adquisicion, String valor_cancelado, String valor_que_adeuda, String valor_vencido, String correo) {
        this.cedula_cliente = cedula_cliente;
        this.nombre_completo = nombre_completo;
        this.item = item;
        this.fecha_adquisicion = fecha_adquisicion;
        this.fecha_ultimo_pago = fecha_ultimo_pago;
        this.fecha_transcurrida = fecha_transcurrida;
        this.valor_adquisicion = valor_adquisicion;
        this.valor_cancelado = valor_cancelado;
        this.valor_que_adeuda = valor_que_adeuda;
        this.valor_vencido = valor_vencido;
        this.correo = correo;
    }

    
    
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getFecha_adquisicion() {
        return fecha_adquisicion;
    }

    public void setFecha_adquisicion(String fecha_adquisicion) {
        this.fecha_adquisicion = fecha_adquisicion;
    }

    public String getFecha_ultimo_pago() {
        return fecha_ultimo_pago;
    }

    public void setFecha_ultimo_pago(String fecha_ultimo_pago) {
        this.fecha_ultimo_pago = fecha_ultimo_pago;
    }

    public String getFecha_transcurrida() {
        return fecha_transcurrida;
    }

    public void setFecha_transcurrida(String fecha_transcurrida) {
        this.fecha_transcurrida = fecha_transcurrida;
    }

    public String getValor_adquisicion() {
        return valor_adquisicion;
    }

    public void setValor_adquisicion(String valor_adquisicion) {
        this.valor_adquisicion = valor_adquisicion;
    }

    public String getValor_cancelado() {
        return valor_cancelado;
    }

    public void setValor_cancelado(String valor_cancelado) {
        this.valor_cancelado = valor_cancelado;
    }

    public String getValor_que_adeuda() {
        return valor_que_adeuda;
    }

    public void setValor_que_adeuda(String valor_que_adeuda) {
        this.valor_que_adeuda = valor_que_adeuda;
    }

    public String getValor_vencido() {
        return valor_vencido;
    }

    public void setValor_vencido(String valor_vencido) {
        this.valor_vencido = valor_vencido;
    }

    

    static public List<CarteraVencida> listarCarteraVencida(String[][] lista) {
        List<CarteraVencida> Res = new ArrayList<>();
        for (String[] Objeto : lista) {
            try {

                String cedula_cliente = Objeto[0];
                String nombre_completo = Objeto[1];
                String item = Objeto[2];
                String fecha_adquisicion = Objeto[3];
                String fecha_ultimo_pago = Objeto[4];
                String fecha_transcurrida = Objeto[5];
                String valor_adquisicion = Objeto[6];
                String valor_cancelado = Objeto[7];
                String valor_que_adeuda = Objeto[8];
                String valor_vencido = Objeto[9];
                String correo = Objeto[10];
                CarteraVencida c = new CarteraVencida(cedula_cliente, nombre_completo, item, fecha_adquisicion, fecha_ultimo_pago, fecha_transcurrida, valor_adquisicion, valor_cancelado, valor_que_adeuda, valor_vencido,correo);
                Res.add(c);
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }

        }
        return Res;
    }

}
