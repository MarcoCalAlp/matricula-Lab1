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
        cedula= new Usuario();
        nombre="";
        telefono="";
    }
    
    public Profesor(Usuario ced,String nom,String tel) {
        this.cedula = ced;
        this.nombre = nom;
        this.telefono = tel;
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
        String profesor;
        profesor = cedula.getCedula()+"\n"+
                "Nombre del profesor:"+this.nombre+"\n"+
                "Telefono del profesor:"+this.telefono+"\n";
        return profesor;
    }
    
    
    
        
        
}
