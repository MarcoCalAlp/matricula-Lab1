/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BusinessLogic;

/**
 *
 * @author guill
 */
public class Carrera {
    private String codigo;
    private String nombre;
    private String titulo;

    public Carrera(String codigo, String nombre, String titulo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.titulo = titulo;
    }

    public Carrera() {
        codigo="";
        nombre="";
        titulo="";
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        String carrera;
        carrera = "Codigo de Carrera:" + this.codigo +"\n"+
                "Nombre de Carrera:" + this.nombre + "\n"+
                "Titulo de Carrera:" + this.titulo;
        return carrera;
    }
    
    
    
    
}
