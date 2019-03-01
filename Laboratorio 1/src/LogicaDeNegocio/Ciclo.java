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
public class Ciclo {
    private Date annio;
    private int numero;
    private Date inicioD;
    private Date finalD;
    private List<Curso> cursos = new ArrayList<>();

    public Ciclo() {
    }
    
    public Ciclo(Date annio, int numero, Date inicioD, Date finalD) {
        this.annio = annio;
        this.numero = numero;
        this.inicioD = inicioD;
        this.finalD = finalD;
    }

    public Date getAnnio() {
        return annio;
    }

    public void setAnnio(Date annio) {
        this.annio = annio;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getInicioD() {
        return inicioD;
    }

    public void setInicioD(Date inicioD) {
        this.inicioD = inicioD;
    }

    public Date getFinalD() {
        return finalD;
    }

    public void setFinalD(Date finalD) {
        this.finalD = finalD;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
       
}
