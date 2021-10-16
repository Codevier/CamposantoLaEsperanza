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
public class Propiedad {

    private String id_propiedad;
    private String cedula_usuario;
    private String titulo_de_propiedad;
    private String tipo;
    private String puerta;
    private String bloque;
    private String cara;
    
    private String fecha_registro;
    private String codigo_unificado;
    private String estado;

    

    public Propiedad(String id_propiedad, String cedula_usuario, String titulo_de_propiedad, String tipo, String puerta, String bloque, String cara, String fecha_registro, String estado) {
        this.id_propiedad = id_propiedad;
        this.cedula_usuario = cedula_usuario;
        this.titulo_de_propiedad = titulo_de_propiedad;
        this.tipo = tipo;
        this.puerta = puerta;
        this.bloque = bloque;
        this.cara = cara;
        this.fecha_registro = fecha_registro;
        this.estado = estado;
        this.codigo_unificado=puerta+"-"+bloque+"-"+cara;
    }
    
    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    

    public String getCodigo_unificado() {
        return codigo_unificado;
    }

    public void setCodigo_unificado(String codigo_unificado) {
        this.codigo_unificado = codigo_unificado;
    }
    
    
    

    
    
    
    public String getId_propiedad() {
        return id_propiedad;
    }

    public void setId_propiedad(String id_propiedad) {
        this.id_propiedad = id_propiedad;
    }

    public String getCedula_usuario() {
        return cedula_usuario;
    }

    public void setCedula_usuario(String cedula_usuario) {
        this.cedula_usuario = cedula_usuario;
    }

    public String getTitulo_de_propiedad() {
        return titulo_de_propiedad;
    }

    public void setTitulo_de_propiedad(String titulo_de_propiedad) {
        this.titulo_de_propiedad = titulo_de_propiedad;
    }

    

    public String getBloque() {
        return bloque;
    }

    public void setBloque(String bloque) {
        this.bloque = bloque;
    }

    public String getCara() {
        return cara;
    }

    public void setCara(String cara) {
        this.cara = cara;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPuerta() {
        return puerta;
    }

    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }

    

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    static public List<Propiedad> listarPropiedades(String[][] lista) {
        List<Propiedad> Res = new ArrayList<>();
        for (String[] Objeto : lista) {
            try {
                              
                String id_propiedad= Objeto[0];
                String cedula_usuario= Objeto[1];
                String titulo_de_propiedad= Objeto[2];
                String tipo= Objeto[3];
                String puerta= Objeto[4];
                String bloque= Objeto[5];
                String cara= Objeto[6];
                String fecha_registro= Objeto[7];
                
                String estado= Objeto[8];

                Propiedad p = new Propiedad(id_propiedad, cedula_usuario, titulo_de_propiedad, tipo, puerta, bloque, cara, fecha_registro,estado);
                Res.add(p);
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }

        }
        return Res;
    }


}
