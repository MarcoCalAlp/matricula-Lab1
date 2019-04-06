/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import BusinessLogic.Alumno;
import BusinessLogic.Usuario;
import java.util.Observer;

/**
 *
 * @author guill
 */
public class ProfesorModel extends java.util.Observable{
    
    Alumno filter;

    public ProfesorModel() {
        Usuario u = new Usuario();
        filter = new Alumno();
        filter.setCedula(u);
    }
    
    

    public ProfesorModel(Alumno filter) {
        this.filter = filter;
    }

    public Alumno getFilter() {
        return filter;
    }

    public void setFilter(Alumno filter) {
        this.filter = filter;
    }    

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o); //To change body of generated methods, choose Tools | Templates.
        setChanged();
        notifyObservers();
    }
    
    
}
