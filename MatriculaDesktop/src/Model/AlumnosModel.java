/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import BusinessLogic.Alumno;
import BusinessLogic.Curso;
import BusinessLogic.MatriculaCarrera;
import BusinessLogic.Profesor;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 *
 * @author guill
 */
public class AlumnosModel extends java.util.Observable{
    
    Alumno filter;
    AlumnosTableModel alumnos;
    String mensaje;
    
    public AlumnosModel() {
        
    }
    
    public AlumnosModel(Alumno filter, AlumnosTableModel alumnos) {
        this.filter = filter;
        this.alumnos = alumnos;
    }
    
    public void init(){
        filter = new Alumno();
        List<Alumno> rows = new ArrayList<Alumno>();
        this.setAlumnos(rows);
        clearErrors();
    }

    public void setAlumnos(List<Alumno> alumnos) {
        int[] cols = {AlumnosTableModel.CEDULA,AlumnosTableModel.NOMBRE,AlumnosTableModel.TELEFONO,AlumnosTableModel.EMAIL,AlumnosTableModel.FN};
        this.alumnos = new AlumnosTableModel(cols, alumnos);
        setChanged();
        notifyObservers();
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
        
    public Alumno getFilter() {
        return filter;
    }

    public void setFilter(Alumno filter) {
        this.filter = filter;
    }

    public AlumnosTableModel getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(AlumnosTableModel alumnos) {
        this.alumnos = alumnos;
    }
    
    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o); //To change body of generated methods, choose Tools | Templates.
        setChanged();
        notifyObservers();
    }
    
    public void clearErrors(){
        setMensaje(""); 
    }
    
}
