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
 * @author Angel
 */
public class Clientes {

    private String cedula_cliente;
    private String cedula_usuario;
    private String nombre_cliente;
    private String apellido_paterno;
    private String apellido_materno;
    private String referencia;
    private String provincia;
    private String parroquia;
    private String canton;
    private String correo;
    private String celular;
    private String telefono;
    private String fecha_nacimiento;
   
   
    private String fecha_registro;
    private String nombrecompleto;
    
    private String direccion;

    public Clientes() {
    }
    
    
    

    public Clientes(String cedula_cliente, String cedula_usuario, String nombre_cliente, String apellido_paterno, String apellido_materno, String referencia, String provincia, String parroquia, String canton, String correo, String celular, String telefono, String fecha_nacimiento,   String fecha_registro) {
        this.cedula_cliente = cedula_cliente;
        this.cedula_usuario = cedula_usuario;
        this.nombre_cliente = nombre_cliente;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.referencia = referencia;
        this.provincia = provincia;
        this.parroquia = parroquia;
        this.canton = canton;
        this.correo = correo;
        this.celular = celular;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        
        
        this.fecha_registro = fecha_registro;
        
        this.nombrecompleto = nombre_cliente+" "+apellido_paterno+" "+apellido_materno;
        
        this.direccion = provincia+" "+parroquia+" "+canton;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
    

    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
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

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

   

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getParroquia() {
        return parroquia;
    }

    public void setParroquia(String parroquia) {
        this.parroquia = parroquia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    static public List<Clientes> listarClientes(String[][] lista) {
        List<Clientes> Res = new ArrayList<>();
        for (String[] Objeto : lista) {
            try {

                String cedula_cliente = Objeto[0];
                String cedula_usuario = Objeto[1];
                String nombre_cliente = Objeto[2];
                String apellido_paterno = Objeto[3];
                String apellido_materno = Objeto[4];
                String referencia = Objeto[5];
                String provincia = Objeto[6];
                String parroquia = Objeto[7];
                String canton = Objeto[8];
                String correo = Objeto[9];
                String celular = Objeto[10];
                String telefono = Objeto[11];
                String fecha_nacimiento = Objeto[12];
                
                String fecha_registro = Objeto[13];

                Clientes c = new Clientes(cedula_cliente, cedula_usuario, nombre_cliente, apellido_paterno, apellido_materno, referencia, provincia, parroquia, canton, correo, celular, telefono, fecha_nacimiento, fecha_registro);
                Res.add(c);
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }

        }
        return Res;
    }
}
