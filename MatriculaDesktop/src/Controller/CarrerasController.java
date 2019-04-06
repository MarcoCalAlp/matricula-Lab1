/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import BusinessLogic.Carrera;
import BusinessLogic.Curso;
import DataAccess.GlobalException;
import DataAccess.NoDataException;
import Model.CarrerasModel;
import Model.CursosModel;
import Model.Model;
import View.CarrerasView;
import View.CursosView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonModel;
import javax.swing.JRadioButton;

/**
 *
 * @author guill
 */
public class CarrerasController {
    
    Model domainModel;
    CarrerasView view;
    CarrerasModel model;

    public CarrerasController(CarrerasView view, CarrerasModel model, Model domainModel) {
        model.init();
        this.domainModel = domainModel;
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public Model getDomain() {
        return domainModel;
    }

    /*
    public List<Curso> CursosXcarrera(String codCarr) throws GlobalException, NoDataException {
     return modelCurso.CursosXcarrera(codCarr);
     }
    
     public List<Curso> CursosXnombre(String codCarr) throws GlobalException, NoDataException {
     return modelCurso.CursosXnombre(codCarr);
     }
    
     public List<Curso> CursosXcodigo(String codCarr) throws GlobalException, NoDataException {
     return modelCurso.CursosXcodigo(codCarr);
     }
    */
    
    public void buscarCarreras() throws GlobalException, NoDataException {
        List<Carrera> c = new ArrayList<>();
        String busqueda = view.TFBusqueda.getText();
        
        /*
        //Para retornar todos los cursos
        if(busqueda.length()==0){
            
        }*/
        
        ButtonModel button = (ButtonModel) view.bG.getSelection();
        
        if(button==null){
            model.setMensaje("Debe seleccionar una opción de busqueda");
            return;
        }
        String seleccion = button.getActionCommand();
        
        
        switch(seleccion){
            case "NC":
                model.getFilter().setNombre(busqueda);
                c = domainModel.CarrerasXnombre(model.getFilter());
                break;
            case "CC":
                model.getFilter().setCodigo(busqueda);
                c = domainModel.CarrerasXcodigo(model.getFilter());
                break;            
            default:
                break;
        }
        if(c.isEmpty())
            model.setMensaje("No hay carreras resultantes para tal busqueda");
        model.setCarreras(c);
    }

    
}
