/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import BusinessLogic.Alumno;
import BusinessLogic.MatriculaCarrera;
import BusinessLogic.Usuario;
import DataAccess.GlobalException;
import DataAccess.NoDataException;
import Model.AlumnosModel;
import Model.Model;
import View.AlumnosView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonModel;

/**
 *
 * @author guill
 */
public class AlumnosController {
    
    Model domainModel;
    AlumnosView view;
    AlumnosModel model;

    public AlumnosController(AlumnosView view, AlumnosModel model, Model domainModel) {
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
    
    public void buscarAlumnos() throws GlobalException, NoDataException {
        List<MatriculaCarrera> c = new ArrayList<>();
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
            case "NA":
                model.getFilter().setNombre(busqueda);
                c = domainModel.MatriculasXnombre(model.getFilter());
                break;
            case "CA":
                Usuario u = new Usuario();
                u.setCedula(busqueda);
                model.getFilter().setCedula(u);
                c = domainModel.MatriculasXcedula(model.getFilter());
                break;    
            case "CC":
                c = domainModel.MatriculasXcarrera(busqueda);
                break;                  
            default:
                break;
        }
        if(c.isEmpty())
            model.setMensaje("No hay alumnos resultantes para tal busqueda");
        List<Alumno> a = new ArrayList<>();
        for(MatriculaCarrera m: c){
            a.add(m.getIdAlu());
        }
        model.setAlumnos(a);
    }

    
}
