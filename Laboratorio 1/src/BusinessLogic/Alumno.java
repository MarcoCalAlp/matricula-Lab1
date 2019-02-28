/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import java.sql.Date;

/**
 *
 * @author marcovinicio
 */
public class Alumno {
    private Usuario cedula;
    private String nombre;
    private String telefono;
    private Date nacimiento;

    public Alumno() {
    }

    public Alumno(Usuario cedula, String nombre, String telefono, Date nacimiento) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.nacimiento = nacimiento;
    }

    public Usuario getCedula() {
        return cedula;
    }

    public void setCedula(Usuario cedula) {
        this.cedula = cedula;
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

    @Override
    public String toString() {
        return "Alumno{" + "cedula=" + cedula + ", nombre=" + nombre + ", telefono=" + telefono + ", nacimiento=" + nacimiento + '}';
    }
    
    
    
}
