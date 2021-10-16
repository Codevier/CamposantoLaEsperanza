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
public class Ubicacion {

    public String UbicacionCodigo,
            ProvinciaCodigo,
            CantonCodigo,
            ParroquiaCodigo,
            ProvinciaNombre,
            CantonNombre,
            ParroquiaNombre;

    public Ubicacion(String UbicacionCodigo, String ProvinciaCodigo, String CantonCodigo, String ParroquiaCodigo, String ProvinciaNombre, String CantonNombre, String ParroquiaNombre) {
        this.UbicacionCodigo = UbicacionCodigo;
        this.ProvinciaCodigo = ProvinciaCodigo;
        this.CantonCodigo = CantonCodigo;
        this.ParroquiaCodigo = ParroquiaCodigo;
        this.ProvinciaNombre = ProvinciaNombre;
        this.CantonNombre = CantonNombre;
        this.ParroquiaNombre = ParroquiaNombre;
    }

    public String getUbicacionCodigo() {
        return UbicacionCodigo;
    }

    public void setUbicacionCodigo(String UbicacionCodigo) {
        this.UbicacionCodigo = UbicacionCodigo;
    }

    public String getProvinciaCodigo() {
        return ProvinciaCodigo;
    }

    public void setProvinciaCodigo(String ProvinciaCodigo) {
        this.ProvinciaCodigo = ProvinciaCodigo;
    }

    public String getCantonCodigo() {
        return CantonCodigo;
    }

    public void setCantonCodigo(String CantonCodigo) {
        this.CantonCodigo = CantonCodigo;
    }

    public String getParroquiaCodigo() {
        return ParroquiaCodigo;
    }

    public void setParroquiaCodigo(String ParroquiaCodigo) {
        this.ParroquiaCodigo = ParroquiaCodigo;
    }

    public String getProvinciaNombre() {
        return ProvinciaNombre;
    }

    public void setProvinciaNombre(String ProvinciaNombre) {
        this.ProvinciaNombre = ProvinciaNombre;
    }

    public String getCantonNombre() {
        return CantonNombre;
    }

    public void setCantonNombre(String CantonNombre) {
        this.CantonNombre = CantonNombre;
    }

    public String getParroquiaNombre() {
        return ParroquiaNombre;
    }

    public void setParroquiaNombre(String ParroquiaNombre) {
        this.ParroquiaNombre = ParroquiaNombre;
    }

    static public List<Ubicacion> listarUbicacion(String[][] lista) {
        List<Ubicacion> Res = new ArrayList<>();
        for (String[] Objeto : lista) {
            try {

                String UbicacionCodigo = Objeto[0];
                String ProvinciaCodigo = Objeto[1];
                String CantonCodigo = Objeto[2];
                String ParroquiaCodigo = Objeto[3];
                String ProvinciaNombre = Objeto[4];
                String CantonNombre = Objeto[5];
                String ParroquiaNombre = Objeto[6];

                Ubicacion u = new Ubicacion(UbicacionCodigo, ProvinciaCodigo, CantonCodigo, ParroquiaCodigo, ProvinciaNombre, CantonNombre, ParroquiaNombre);
                Res.add(u);
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }

        }
        return Res;
    }
    

}
