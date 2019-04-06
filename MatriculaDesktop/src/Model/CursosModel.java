/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import BusinessLogic.Curso;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 *
 * @author guill
 */
public class CursosModel extends java.util.Observable{
    
    Curso filter;
    CursosTableModel cursos;
    String mensaje;
    
    public CursosModel() {
        
    }
    
    public CursosModel(Curso filter, CursosTableModel cursos) {
        this.filter = filter;
        this.cursos = cursos;
    }
    
    public void init(){
        filter = new Curso();
        List<Curso> rows = new ArrayList<Curso>();
        this.setCursos(rows);
        clearErrors();
    }

    public void setCursos(List<Curso> cursos) {
        int[] cols = {CursosTableModel.CODIGO, CursosTableModel.NOMBRE, CursosTableModel.CREDITOS, CursosTableModel.HORAS, CursosTableModel.CARRERA, CursosTableModel.CICLO};
        this.cursos = new CursosTableModel(cols, cursos);
        setChanged();
        notifyObservers();
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
        
    public Curso getFilter() {
        return filter;
    }

    public void setFilter(Curso filter) {
        this.filter = filter;
    }

    public CursosTableModel getCursos() {
        return cursos;
    }

    public void setCursos(CursosTableModel cursos) {
        this.cursos = cursos;
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
