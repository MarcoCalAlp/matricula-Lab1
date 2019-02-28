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
public class Curso {
   private String codigo;
   private Carrera codCarrera;
   private Integer cicloNumero;
   private Integer cicloAnio;
   private String nombre;
   private Integer creditos;
   private Integer horas;

    public Curso() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Carrera getCodCarrera() {
        return codCarrera;
    }

    public void setCodCarrera(Carrera codCarrera) {
        this.codCarrera = codCarrera;
    }

    public Integer getCicloNumero() {
        return cicloNumero;
    }

    public void setCicloNumero(Integer cicloNumero) {
        this.cicloNumero = cicloNumero;
    }

    public Integer getCicloAnio() {
        return cicloAnio;
    }

    public void setCicloAnio(Integer cicloAnio) {
        this.cicloAnio = cicloAnio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }
   
   
    
}
