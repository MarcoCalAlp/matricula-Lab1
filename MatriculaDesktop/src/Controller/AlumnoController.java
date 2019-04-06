/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.AlumnoModel;
import Model.Model;
import View.AlumnoView;

/**
 *
 * @author guill
 */
public class AlumnoController {
    
    Model domainModel;
    AlumnoModel model;
    AlumnoView view;
    public String accion;

    public AlumnoController(AlumnoView view, AlumnoModel model, Model domainModel) {
        this.domainModel = domainModel;
        this.model = model;
        this.view = view;
        view.setController(this);
        view.setModel(model);
    }

    public Model getDomainModel() {
        return domainModel;
    }

    public void setDomainModel(Model domainModel) {
        this.domainModel = domainModel;
    }

    public AlumnoModel getModel() {
        return model;
    }

    public void setModel(AlumnoModel model) {
        this.model = model;
    }

    public AlumnoView getView() {
        return view;
    }

    public void setView(AlumnoView view) {
        this.view = view;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    
    
}
