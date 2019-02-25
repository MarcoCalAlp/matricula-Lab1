/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package LogicaDeNegocio;

import java.util.Date;

/**
 *
 * @author guill
 */
public class Curso {
    private String codigo;
    private String codCarrera;
    private int cicloNumero;
    private Date cicloAnio;    
    private String nombre;
    private int creditos;
    private int horas;

    public Curso() {
    }

    public Curso(String codigo, String codCarrera, int cicloNumero, Date cicloAnio, String nombre, int creditos, int horas) {
        this.codigo = codigo;
        this.codCarrera = codCarrera;
        this.cicloNumero = cicloNumero;
        this.cicloAnio = cicloAnio;
        this.nombre = nombre;
        this.creditos = creditos;
        this.horas = horas;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodCarrera() {
        return codCarrera;
    }

    public void setCodCarrera(String codCarrera) {
        this.codCarrera = codCarrera;
    }

    public int getCicloNumero() {
        return cicloNumero;
    }

    public void setCicloNumero(int cicloNumero) {
        this.cicloNumero = cicloNumero;
    }

    public Date getCicloAnio() {
        return cicloAnio;
    }

    public void setCicloAnio(Date cicloAnio) {
        this.cicloAnio = cicloAnio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
 
}
