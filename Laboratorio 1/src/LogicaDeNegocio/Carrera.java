/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package LogicaDeNegocio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author guill
 */
public class Carrera {
    private String codigo;
    private String nombre;
    private String titulo;
    private List<Curso> cursos = new ArrayList<>();

    public Carrera() {
    }

    public Carrera(String codigo, String nombre, String titulo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.titulo = titulo;
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

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
       
}
