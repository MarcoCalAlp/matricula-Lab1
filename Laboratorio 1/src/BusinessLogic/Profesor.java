/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

/**
 *
 * @author marcovinicio
 */
public class Profesor {
    
    private Usuario cedula;
    private String nombre;
    private String telefono;

    public Profesor() {
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

    @Override
    public String toString() {
        return "Profesor{" + "cedula=" + cedula + ", nombre=" + nombre + ", telefono=" + telefono + '}';
    }
    
    
        
        
}
