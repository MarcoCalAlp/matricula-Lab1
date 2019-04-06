/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matriculadesktop;

import Controller.AlumnoController;
import Controller.AlumnosController;
import Controller.CarrerasController;
import Controller.CursosController;
import Controller.LoginController;
import Controller.ProfesoresController;
import Model.AlumnoModel;
import Model.AlumnosModel;
import Model.CarrerasModel;
import Model.CursosModel;
import Model.Model;
import Model.ProfesoresModel;
import View.AlumnoView;
import View.AlumnosView;
import View.AppView;
import View.CarrerasView;
import View.CursosView;
import View.Login;
import View.ProfesoresView;

/**
 *
 * @author guill
 */
public class Application {

    public static Login LOGIN;
    
    public static AppView CONTENEDOR;
     public static CursosView CURSOSVIEW;
     public static ProfesoresView PROFESORESVIEW;
     public static CarrerasView CARRERASVIEW;
     public static AlumnosView ALUMNOSVIEW;
      public static AlumnoView ALUMNOVIEW;
     

    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
        Model domainModel = Model.instance();
        
        
        LOGIN = new Login();
        LoginController loginController = new LoginController(LOGIN);
        
        CONTENEDOR = new AppView();
        
        CursosModel cursosModel = new CursosModel();
        CursosView cursosView = new CursosView();
        CursosController cursosController = new CursosController(cursosView,cursosModel,domainModel);        
        CURSOSVIEW = cursosView;
        
        ProfesoresModel profesoresModel = new ProfesoresModel();
        ProfesoresView profesoresView = new ProfesoresView();
        ProfesoresController profesoresController = new ProfesoresController(profesoresView,profesoresModel,domainModel);        
        PROFESORESVIEW = profesoresView;
        
        CarrerasModel carrerasModel = new CarrerasModel();
        CarrerasView carrerasView = new CarrerasView();
        CarrerasController carrerasController = new CarrerasController(carrerasView,carrerasModel,domainModel);        
        CARRERASVIEW = carrerasView;
        
        AlumnosModel alumnosModel = new AlumnosModel();
        AlumnosView alumnosView = new AlumnosView();
        AlumnosController alumnosController = new AlumnosController(alumnosView,alumnosModel,domainModel);        
        ALUMNOSVIEW = alumnosView;
        
        CONTENEDOR.Contenedor.add(CURSOSVIEW);
        CONTENEDOR.Contenedor.add(PROFESORESVIEW);
        CONTENEDOR.Contenedor.add(CARRERASVIEW);
        CONTENEDOR.Contenedor.add(ALUMNOSVIEW);
        
        LOGIN.setVisible(true);
        
        AlumnoModel alumnoModel = new AlumnoModel();
        AlumnoView alumnoView = new AlumnoView();
        AlumnoController alumnoController = new AlumnoController(alumnoView,alumnoModel,domainModel);                
        ALUMNOVIEW = alumnoView;
        
        
    }
    
}
