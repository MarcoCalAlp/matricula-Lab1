/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import BusinessLogic.Curso;
import BusinessLogic.Profesor;
import BusinessLogic.Usuario;
import DataAccess.GlobalException;
import DataAccess.NoDataException;
import Model.Model;
import Model.ProfesoresModel;
import View.ProfesoresView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonModel;

/**
 *
 * @author guill
 */
public class ProfesoresController {
    
    Model domainModel;
    ProfesoresView view;
    ProfesoresModel model;

    public ProfesoresController(ProfesoresView view, ProfesoresModel model, Model domainModel) {
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
    
    public void buscarProfesores() throws GlobalException, NoDataException {
        List<Profesor> c = new ArrayList<>();
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
            case "NP":
                model.getFilter().setNombre(busqueda);
                c = domainModel.ProfesXnombre(model.getFilter());
                break;
            case "NC":
                Usuario u = new Usuario();
                u.setCedula(busqueda);
                model.getFilter().setCedula(u);
                c = domainModel.ProfesXCedula(model.getFilter());
                break;            
            default:
                break;
        }
        if(c.isEmpty())
            model.setMensaje("No hay profesores resultantes para tal busqueda");
        model.setProfesores(c);
    }

    
}
