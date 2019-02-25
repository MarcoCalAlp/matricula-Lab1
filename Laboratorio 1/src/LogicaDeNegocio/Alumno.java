/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package LogicaDeNegocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author guill
 */
public class Alumno extends Usuario {
    private String nombre;
    private String telefono;
    private Date nacimiento;
    private List<Grupo> grupos = new ArrayList<>();

    public Alumno() {
    }

    public Alumno(String cedula, String clave, String email, String nombre, String telefono, Date nacimiento) {
        super(cedula, clave, email);
        this.nombre = nombre;
        this.telefono = telefono;
        this.nacimiento = nacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }
    
}
