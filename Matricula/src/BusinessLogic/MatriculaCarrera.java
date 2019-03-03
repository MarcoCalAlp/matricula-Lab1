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
public class MatriculaCarrera {
    
    private Alumno idAlu ;
    private Carrera idCarr;
    private Date fechaInscripcion;

    public MatriculaCarrera() {
    }

    public Alumno getIdAlu() {
        return idAlu;
    }

    public void setIdAlu(Alumno idAlu) {
        this.idAlu = idAlu;
    }

    public Carrera getIdCarr() {
        return idCarr;
    }

    public void setIdCarr(Carrera idCarr) {
        this.idCarr = idCarr;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    @Override
    public String toString() {
        return "MatriculaCarrera{" + "idAlu=" + idAlu + ", idCarr=" + idCarr + ", fechaInscripcion=" + fechaInscripcion + '}';
    }
    
    
    
}
