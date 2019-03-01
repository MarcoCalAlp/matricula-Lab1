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
public class Grupo {
    private int codigo;
    private int numGrupo;
    private String codCurso;
    private int numCiclo;
    private Date annioCiclo;
    private String idProfesor;
    private String horario;
    private Ciclo ciclo;

    public Grupo() {
    }

    public Grupo(int codigo, int numGrupo, String codCurso, int numCiclo, Date annioCiclo, String idProfesor, String horario, Ciclo ciclo) {
        this.codigo = codigo;
        this.numGrupo = numGrupo;
        this.codCurso = codCurso;
        this.numCiclo = numCiclo;
        this.annioCiclo = annioCiclo;
        this.idProfesor = idProfesor;
        this.horario = horario;
        this.ciclo = ciclo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getNumGrupo() {
        return numGrupo;
    }

    public void setNumGrupo(int numGrupo) {
        this.numGrupo = numGrupo;
    }

    public String getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(String codCurso) {
        this.codCurso = codCurso;
    }

    public int getNumCiclo() {
        return numCiclo;
    }

    public void setNumCiclo(int numCiclo) {
        this.numCiclo = numCiclo;
    }

    public Date getAnnioCiclo() {
        return annioCiclo;
    }

    public void setAnnioCiclo(Date annioCiclo) {
        this.annioCiclo = annioCiclo;
    }

    public String getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(String idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }
        
}
