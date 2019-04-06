/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import BusinessLogic.Curso;
import BusinessLogic.Profesor;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 *
 * @author guill
 */
public class ProfesoresModel extends java.util.Observable{
    
    Profesor filter;
    ProfesoresTableModel profesores;
    String mensaje;
    
    public ProfesoresModel() {
        
    }
    
    public ProfesoresModel(Profesor filter, ProfesoresTableModel profesores) {
        this.filter = filter;
        this.profesores = profesores;
    }
    
    public void init(){
        filter = new Profesor();
        List<Profesor> rows = new ArrayList<Profesor>();
        this.setProfesores(rows);
        clearErrors();
    }

    public void setProfesores(List<Profesor> profesores) {
        int[] cols = {ProfesoresTableModel.CEDULA, ProfesoresTableModel.NOMBRE, ProfesoresTableModel.EMAIL, ProfesoresTableModel.TELEFONO};
        this.profesores = new ProfesoresTableModel(cols, profesores);
        setChanged();
        notifyObservers();
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
        
    public Profesor getFilter() {
        return filter;
    }

    public void setFilter(Profesor filter) {
        this.filter = filter;
    }

    public ProfesoresTableModel getProfesores() {
        return profesores;
    }

    public void setProfesores(CursosTableModel cursos) {
        this.profesores = profesores;
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
