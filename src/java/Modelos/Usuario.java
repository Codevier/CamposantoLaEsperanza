/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import com.itextpdf.text.pdf.codec.Base64;
import static com.sun.xml.ws.security.trust.sts.BaseSTSImpl.ENCRYPT_KEY;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Usuario
 */
public class Usuario {

    private String cedula_usuario;
    private String nombre_usuario;
    private String apellido_materno;
    private String apellido_paterno;
    private String provincia;
    private String canton;
    private String parroquia;
    private String referencia;
    private String tipo_usuario;
    private String celular;
    private String telefono;
    private String fecha_nacimiento;
    
    private String clave;
    private String correo;
    private String fecha_registro;
    private String direccion;
    private String nombre_completo;

    public Usuario() {
    }

    public Usuario(String cedula_usuario, String nombre_usuario, String apellido_materno, String apellido_paterno, String provincia, String canton, String parroquia, String referencia, String tipo_usuario, String celular, String telefono, String fecha_nacimiento,  String clave, String correo, String fecha_registro, String direccion) {
        this.cedula_usuario = cedula_usuario;
        this.nombre_usuario = nombre_usuario;
        this.apellido_materno = apellido_materno;
        this.apellido_paterno = apellido_paterno;
        this.provincia = provincia;
        this.canton = canton;
        this.parroquia = parroquia;
        this.referencia = referencia;
        this.tipo_usuario = tipo_usuario;
        this.celular = celular;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        
        this.clave = clave;
        this.correo = correo;
        this.fecha_registro = fecha_registro;
        this.direccion = direccion;
        this.nombre_completo=nombre_usuario+" "+apellido_paterno+" "+apellido_materno;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }
    
    
    
    

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getParroquia() {
        return parroquia;
    }

    public void setParroquia(String parroquia) {
        this.parroquia = parroquia;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCedula_usuario() {
        return cedula_usuario;
    }

    public void setCedula_usuario(String cedula_usuario) {
        this.cedula_usuario = cedula_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
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

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
    
    private static String decrypt(String encrypted) throws Exception {
	byte[] encryptedBytes=Base64.decode( encrypted.replace("\n", "") );
		
	Key aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");

	Cipher cipher = Cipher.getInstance("AES");
	cipher.init(Cipher.DECRYPT_MODE, aesKey);

	String decrypted = new String(cipher.doFinal(encryptedBytes));
        
	return decrypted;
	}

    static public List<Usuario> listarUsuarios(String[][] lista) {
        List<Usuario> Res = new ArrayList<>();
        for (String[] Objeto : lista) {
            try {

                String cedula_usuario = Objeto[0];
                String nombre_usuario = Objeto[1];
                String apellido_materno = Objeto[2];
                String apellido_paterno = Objeto[3];
                String provincia = Objeto[4];
                String canton = Objeto[5];
                String parroquia = Objeto[6];
                String referencia = Objeto[7];
                String tipo_usuario = Objeto[8];
                String celular = Objeto[9];
                String telefono = Objeto[10];
                String fecha_nacimiento = Objeto[11];
                
                //String clave = decrypt(Objeto[13]);
                String clave = Objeto[13];
                String correo = Objeto[13];
                String fecha_registro = Objeto[14];
                String direccion=provincia+"-"+canton+"-"+parroquia;

                Usuario p = new Usuario(cedula_usuario, nombre_usuario, apellido_materno, apellido_paterno, provincia, canton, parroquia, referencia, tipo_usuario, celular, telefono, fecha_nacimiento,  clave, correo, fecha_registro,direccion);
                Res.add(p);
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }

        }
        return Res;
    }

}
