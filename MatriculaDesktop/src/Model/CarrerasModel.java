/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import BusinessLogic.Carrera;
import BusinessLogic.Curso;
import BusinessLogic.Profesor;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 *
 * @author guill
 */
public class CarrerasModel extends java.util.Observable{
    
    Carrera filter;
    CarrerasTableModel carreras;
    String mensaje;
    
    public CarrerasModel() {
        
    }
    
    public CarrerasModel(Carrera filter, CarrerasTableModel carreras) {
        this.filter = filter;
        this.carreras = carreras;
    }
    
    public void init(){
        filter = new Carrera();
        List<Carrera> rows = new ArrayList<Carrera>();
        this.setCarreras(rows);
        clearErrors();
    }

    public void setCarreras(List<Carrera> carreras) {
        int[] cols = {CarrerasTableModel.NOMBRE, CarrerasTableModel.CODIGO, CarrerasTableModel.TITULO};
        this.carreras = new CarrerasTableModel(cols, carreras);
        setChanged();
        notifyObservers();
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
        
    public Carrera getFilter() {
        return filter;
    }

    public void setFilter(Carrera filter) {
        this.filter = filter;
    }

    public CarrerasTableModel getCarreras() {
        return carreras;
    }

    public void setCarreras(CarrerasTableModel carreras) {
        this.carreras = carreras;
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
