/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import BusinessLogic.Alumno;
import BusinessLogic.Profesor;
import Model.Model;
import View.Login;
import matriculadesktop.Application;

/**
 *
 * @author guill
 */
public class LoginController {
    
    public static Alumno alumno;
    public static Profesor profesor;
    
    public Model domainModel;
    
    private Login view;

    public LoginController(Login view) {
        this.view = view;
        view.setController(this);
    }        

    public Login getView() {
        return view;
    }

    public void setView(Login view) {
        this.view = view;
    }    
    
    public static Alumno getAlumno() {
        return alumno;
    }

    public static void setAlumno(Alumno alumno) {
        LoginController.alumno = alumno;
    }

    public static Profesor getProfesor() {
        return profesor;
    }

    public static void setProfesor(Profesor profesor) {
        LoginController.profesor = profesor;
    }
    
    public void doLogin(/*String rol*/){
        Application.CONTENEDOR.setVisible(true);
        Application.LOGIN.setVisible(false);
        
        /*
        BD devuelve todos los usuarios, para saber el rol se valida la cedula
        
        
        */
        /*
        switch(rol){
            case"Alumno":
                domainModel.MatriculasXcedula(alumno);
                break;
        
        }
        */
    }
    
    public String sesion(){
        if(alumno!=null){
            return "Alumno";
        }
        if(profesor!=null){
            return "Profesor";
        }
        return "TODO";
    }
    
}
