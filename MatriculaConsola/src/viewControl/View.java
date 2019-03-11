/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewControl;

import BusinessLogic.Alumno;
import BusinessLogic.Carrera;
import BusinessLogic.Curso;
import BusinessLogic.MatriculaCarrera;
import BusinessLogic.Profesor;
import DataAccess.GlobalException;
import DataAccess.NoDataException;
import ModelControl.ModelCarrera;
import ModelControl.ModelCurso;
import ModelControl.ModelMatriculaCarrera;
import ModelControl.ModelProfesor;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author marcovinicio
 */
public class View {
    
    
    private static View uniqueInstance;

    public View() {
    }
    
    
    
    public static View instance(){
        if (uniqueInstance == null){
            uniqueInstance = new View();
        }
        return uniqueInstance;
    }
    
    public int controlMatricula() throws GlobalException, NoDataException{
        Scanner capt = new Scanner(System.in);
        System.out.println("      ____________________________________________________________________       ");
        System.out.println("     |                                                                    |      ");
        System.out.println("     |                            -Bienvenido-                            |      ");
        System.out.println("     |                       -Sistema de Matricula-                       |      ");
        System.out.println("     |                                                                    |      ");
        System.out.println("     |      - Presione [1] para ingresar al menu de cursos                |      ");
        System.out.println("     |      - Presione [2] para ingresar al menu de profesores            |      ");
        System.out.println("     |      - Presione [3] para ingresar al menu de carreras              |      ");
        System.out.println("     |      - Presione [4] para ingresar al menu de alumnos               |      ");
        System.out.println("     |____________________________________________________________________|      ");
        
        System.out.println("Digite el numero del item del menu que desea seleccionar: ");
        int a = 0;
        try{
            a = capt.nextInt();
            if(a<1 || a>4){  
             throw new Exception();
            }
        }
        
        catch(Exception e){
            System.out.println("!!!Error, tiene que ingresar una opcion valida ");
            evaluaMenu();
        }
        
       return a;
    }

    public void evaluaMenu() throws GlobalException, NoDataException {
        int opciones = 0;
	do{
	opciones = controlMatricula();
	switch(opciones){
	case 1: evaluaMenuCursos();
		break;

	case 2:
                evaluaMenuProfesores();
		break;
		   
	case 3: evaluaMenuCarreras();
                
		break;

	case 4: evaluaMenuMatriculaAlumnos(); 
		break;
        

	default:
                System.out.println("!!!Error, tiene que ingresar una opcion valida ");
                evaluaMenu();
	}
	} while(opciones != 4 && opciones != 0);
    }
    
    
    
    private void evaluaMenuCursos() throws GlobalException, NoDataException {
        int opciones = 0;
	do{
	opciones = controlCursos();
	switch(opciones){
	case 1: BusqCursosNombre();
		break;

	case 2:
                BusqCursosCodigo();
		break;
		   
	case 3: BusqCursosCarrera();
		break;
                
        case 4: evaluaMenu();
		break;

	default:
                System.out.println("!!!Error, tiene que ingresar una opcion valida ");
                evaluaMenu();
	}
	} while(opciones != 5 && opciones != 0);
    }

    private void evaluaMenuProfesores() throws GlobalException, NoDataException {
        int opciones = 0;
	do{
	opciones = controlProfesores();
	switch(opciones){
	case 1: BusqProfesNombre();
		break;

	case 2: BusqProfesCedula();
		break;
        case 3: evaluaMenu();
		break;
	default:
                System.out.println("!!!Error, tiene que ingresar una opcion valida ");
                evaluaMenu();
	}
	} while(opciones != 4 && opciones != 0);
    
    }

    private void evaluaMenuCarreras() throws GlobalException, NoDataException {
         int opciones = 0;
	do{
	opciones = controlCarreras();
	switch(opciones){
	case 1: BusqCarrNombre();
		break;

	case 2: BusqCarrCodigo();
		break;
        case 3: evaluaMenu();
		break;
	default:
                System.out.println("!!!Error, tiene que ingresar una opcion valida ");
                evaluaMenu();
	}
	} while(opciones != 4 && opciones != 0);
    }

    private void evaluaMenuMatriculaAlumnos() throws GlobalException, NoDataException {
        int opciones = 0;
	do{
	opciones = controlMatriculaAlumnos();
	switch(opciones){
	case 1: BusqAlumnosNombre();
		break;

	case 2:
                BusqAlumnosCodigo();
		break;
		   
	case 3: BusqAlumnoCarrera();
		break;
                
        case 4: evaluaMenu();
		break;

	default:
                System.out.println("!!!Error, tiene que ingresar una opcion valida ");
                evaluaMenu();
	}
	} while(opciones != 5 && opciones != 0);
    }
    
    //============MANTENIMIENTO CURSOS=============== 
    private int controlCursos() throws GlobalException, NoDataException {
        Scanner capt = new Scanner(System.in);
        System.out.println("      ____________________________________________________________________       ");
        System.out.println("     |                                                                    |      ");
        System.out.println("     |                            -Bienvenido-                            |      ");
        System.out.println("     |                       -Mantenimiento de Cursos-                    |      ");
        System.out.println("     |                                                                    |      ");
        System.out.println("     |    - Presione [1] para realizar la busqueda de cursos por nombre   |      ");
        System.out.println("     |    - Presione [2] para realizar la busqueda de cursos por codigo   |      ");
        System.out.println("     |    - Presione [3] para realizar la busqueda de cursos por carrera  |      ");
        System.out.println("     |    - Presione [4] para salir                                       |      ");
        System.out.println("     |____________________________________________________________________|      ");
        
        System.out.println("Digite el numero del item del menu que desea seleccionar: ");
        int a = 0;
        try{
            a = capt.nextInt();
            if(a<1 || a>4){  
             throw new Exception();
            }
        }
        
        catch(Exception e){
            System.out.println("!!!Error, tiene que ingresar una opcion valida ");
            evaluaMenuCursos();
        }
        
       return a;
    }

    

    private void BusqCursosNombre() throws GlobalException, NoDataException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("==>Digite el Nombre del Curso a buscar");
        String nombre = "";
        nombre = teclado.nextLine();
        List<Curso> cursos = ModelCurso.instance().CursosXnombre(nombre);
        for(Curso i: cursos){
        System.out.println("__________________________________________________");
        System.out.println("Nombre:"+ i.getNombre());
        System.out.println("Codigo:"+ i.getCodigo());
        System.out.println("Creditos:"+ i.getCreditos());
        System.out.println("Horas:"+ i.getHoras());
        System.out.println("Carrera perteneciente:"+ i.getCodCarrera().getNombre());
        System.out.println("Numero de Ciclo:"+ i.getCicloCurso().getNumero());
        System.out.println("Annio de Ciclo:"+ i.getCicloCurso().getAnnio());
        System.out.println("__________________________________________________");
        }
       
    }

    private void BusqCursosCarrera() throws GlobalException, NoDataException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("==>Digite el Codigo de Carrera al que pertenece el  curso a buscar");
        String codigoCarr = "";
        codigoCarr = teclado.nextLine();
        List<Curso> cursos = ModelCurso.instance().CursosXcarrera(codigoCarr);
        for(Curso i: cursos){
        System.out.println("__________________________________________________");
        System.out.println("Nombre:"+ i.getNombre());
        System.out.println("Codigo:"+ i.getCodigo());
        System.out.println("Creditos:"+ i.getCreditos());
        System.out.println("Horas:"+ i.getHoras());
        System.out.println("Carrera perteneciente:"+ i.getCodCarrera().getNombre());
        System.out.println("Numero de Ciclo:"+ i.getCicloCurso().getNumero());
        System.out.println("Annio de Ciclo:"+ i.getCicloCurso().getAnnio());
        System.out.println("__________________________________________________");
        }
    }
    
    private void BusqCursosCodigo() throws GlobalException, NoDataException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("==>Digite el codigo del curso");
        String codigo = "";
        codigo = teclado.nextLine();
        List<Curso> cursos = ModelCurso.instance().CursosXcodigo(codigo);
        for(Curso i: cursos){
        System.out.println("__________________________________________________");
        System.out.println("Nombre:"+ i.getNombre());
        System.out.println("Codigo:"+ i.getCodigo());
        System.out.println("Creditos:"+ i.getCreditos());
        System.out.println("Horas:"+ i.getHoras());
        System.out.println("Carrera perteneciente:"+ i.getCodCarrera().getNombre());
        System.out.println("Numero de Ciclo:"+ i.getCicloCurso().getNumero());
        System.out.println("Annio de Ciclo:"+ i.getCicloCurso().getAnnio());
        System.out.println("__________________________________________________");
        }
    }
    //============MANTENIMIENTO PROFES=============== 
    private int controlProfesores() throws GlobalException, NoDataException {
        Scanner capt = new Scanner(System.in);
        System.out.println("      ____________________________________________________________________       ");
        System.out.println("     |                                                                    |      ");
        System.out.println("     |                            -Bienvenido-                            |      ");
        System.out.println("     |                       -Mantenimiento de Profesores-                |      ");
        System.out.println("     |                                                                    |      ");
        System.out.println("     | - Presione [1] para realizar la busqueda de profesores por nombre  |      ");
        System.out.println("     | - Presione [2] para realizar la busqueda de profesores por cedula  |      ");
        System.out.println("     | - Presione [3] para salir                                          |      ");
        System.out.println("     |____________________________________________________________________|      ");
        
        System.out.println("Digite el numero del item del menu que desea seleccionar: ");
        int a = 0;
        try{
            a = capt.nextInt();
            if(a<1 || a>3){  
             throw new Exception();
            }
        }
        
        catch(Exception e){
            System.out.println("!!!Error, tiene que ingresar una opcion valida ");
            evaluaMenuProfesores();
        }
        
       return a;
    }

    private void BusqProfesNombre() throws GlobalException, NoDataException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("==>Digite el nombre del profesor");
        String nombre = "";
        nombre = teclado.nextLine();
        List<Profesor> profesores = ModelProfesor.instance().profesXnombre(nombre);
        for(Profesor i: profesores){
        System.out.println("__________________________________________________");
        System.out.println("Nombre:"+ i.getNombre());
        System.out.println("Cedula:"+ i.getCedula().getCedula());
        System.out.println("Email:"+ i.getCedula().getEmail());
        System.out.println("Telefono:"+ i.getTelefono());
        System.out.println("__________________________________________________");
        }
    }

    private void BusqProfesCedula() throws GlobalException, NoDataException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("==>Digite la cedula del profesor");
        String cedula = "";
        cedula = teclado.nextLine();
        List<Profesor> profesores = ModelProfesor.instance().profesXCedula(cedula);
        for(Profesor i: profesores){
        System.out.println("__________________________________________________");
        System.out.println("Nombre:"+ i.getNombre());
        System.out.println("Cedula:"+ i.getCedula().getCedula());
        System.out.println("Email:"+ i.getCedula().getEmail());
        System.out.println("Telefono:"+ i.getTelefono());
        System.out.println("__________________________________________________");
        }
    }
    
    //============MANTENIMIENTO CARRERAS=============== 
    private int controlCarreras() throws GlobalException, NoDataException {
        Scanner capt = new Scanner(System.in);
        System.out.println("      ____________________________________________________________________       ");
        System.out.println("     |                                                                    |      ");
        System.out.println("     |                            -Bienvenido-                            |      ");
        System.out.println("     |                       -Mantenimiento de Carreras-                  |      ");
        System.out.println("     |                                                                    |      ");
        System.out.println("     |   - Presione [1] para realizar la busqueda de Carreras por nombre  |      ");
        System.out.println("     |   - Presione [2] para realizar la busqueda de Carreras por codigo  |      ");
        System.out.println("     |   - Presione [3] para salir                                        |      ");
        System.out.println("     |____________________________________________________________________|      ");
        
        System.out.println("Digite el numero del item del menu que desea seleccionar: ");
        int a = 0;
        try{
            a = capt.nextInt();
            if(a<1 || a>3){  
             throw new Exception();
            }
        }
        
        catch(Exception e){
            System.out.println("!!!Error, tiene que ingresar una opcion valida ");
            evaluaMenuCarreras();
        }
        
       return a;
    }

    private void BusqCarrNombre() throws GlobalException, NoDataException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("==>Digite el nombre de la Carrera");
        String nombre = "";
        nombre = teclado.nextLine();
        List<Carrera> carreras = ModelCarrera.instance().carrerasXnombre(nombre);
        for(Carrera i: carreras){
        System.out.println("__________________________________________________");
        System.out.println("Nombre:"+ i.getNombre());
        System.out.println("Codigo:"+ i.getCodigo());
        System.out.println("Titulo:"+ i.getTitulo());
        System.out.println("__________________________________________________");
        }
    }

    private void BusqCarrCodigo() throws GlobalException, NoDataException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("==>Digite el codigo de la Carrera");
        String codigo = "";
        codigo = teclado.nextLine();
        List<Carrera> carreras = ModelCarrera.instance().carrerasXcodigo(codigo);
        for(Carrera i: carreras){
        System.out.println("__________________________________________________");
        System.out.println("Nombre:"+ i.getNombre());
        System.out.println("Codigo:"+ i.getCodigo());
        System.out.println("Titulo:"+ i.getTitulo());
        System.out.println("__________________________________________________");
        }
    }
    
    //============MANTENIMIENTO MATRICULA ALUMNOS=============== 
    private int controlMatriculaAlumnos() throws GlobalException, NoDataException {
        Scanner capt = new Scanner(System.in);
        System.out.println("      ____________________________________________________________________       ");
        System.out.println("     |                                                                    |      ");
        System.out.println("     |                            -Bienvenido-                            |      ");
        System.out.println("     |                       -Mantenimiento de Alumnos-                   |      ");
        System.out.println("     |                                                                    |      ");
        System.out.println("     |   - Presione [1] para realizar la busqueda de Alumnos por nombre   |      ");
        System.out.println("     |   - Presione [2] para realizar la busqueda de Alumnos por cedula   |      ");
        System.out.println("     |   - Presione [3] para realizar la busqueda de Alumnos por carrera  |      ");
        System.out.println("     |   - Presione [4] para salir                                        |      ");
        System.out.println("     |____________________________________________________________________|      ");
        
        System.out.println("Digite el numero del item del menu que desea seleccionar: ");
        int a = 0;
        try{
            a = capt.nextInt();
            if(a<1 || a>4){  
             throw new Exception();
            }
        }
        
        catch(Exception e){
            System.out.println("!!!Error, tiene que ingresar una opcion valida ");
            evaluaMenuMatriculaAlumnos();
        }
        
       return a;
    
    }

    

    private void BusqAlumnosNombre() throws GlobalException, NoDataException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("==>Digite el nombre del Alumno");
        String nombre = "";
        nombre = teclado.nextLine();
        List<MatriculaCarrera> alumnos = ModelMatriculaCarrera.instance().matriculasXnombre(nombre);
        for(MatriculaCarrera i: alumnos){
        System.out.println("__________________________________________________");
        System.out.println("Nombre:"+ i.getIdAlu().getNombre());
        System.out.println("Cedula:"+ i.getIdAlu().getCedula().getCedula());
        System.out.println("Telefono:"+ i.getIdAlu().getTelefono());
        System.out.println("Email:"+ i.getIdAlu().getCedula().getEmail());
        System.out.println("Fech Nacimiento:"+ i.getIdAlu().getNacimiento());
        System.out.println("Fech De inscripcion:"+ i.getFechaInscripcion());
        System.out.println("Carrera Matriculada:"+ i.getIdCarr().getNombre());;
        System.out.println("__________________________________________________");
        }
    }

    private void BusqAlumnosCodigo() throws GlobalException, NoDataException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("==>Digite la cedula  del Alumno");
        String cedula = "";
        cedula = teclado.nextLine();
        List<MatriculaCarrera> alumnos = ModelMatriculaCarrera.instance().matriculasXcedula(cedula);
        for(MatriculaCarrera i: alumnos){
        System.out.println("__________________________________________________");
        System.out.println("Nombre:"+ i.getIdAlu().getNombre());
        System.out.println("Cedula:"+ i.getIdAlu().getCedula().getCedula());
        System.out.println("Telefono:"+ i.getIdAlu().getTelefono());
        System.out.println("Email:"+ i.getIdAlu().getCedula().getEmail());
        System.out.println("Fech Nacimiento:"+ i.getIdAlu().getNacimiento());
        System.out.println("Fech De inscripcion:"+ i.getFechaInscripcion());
        System.out.println("Carrera Matriculada:"+ i.getIdCarr().getNombre());
        System.out.println("__________________________________________________");
        }
    }

    private void BusqAlumnoCarrera() throws GlobalException, NoDataException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("==>Digite el codigo de Carrera donde desea buscar los alumnos");
        String codCarrera = "";
        codCarrera = teclado.nextLine();
        List<MatriculaCarrera> alumnos = ModelMatriculaCarrera.instance().matriculasXcarrera(codCarrera);
        for(MatriculaCarrera i: alumnos){
        System.out.println("__________________________________________________");
        System.out.println("Nombre:"+ i.getIdAlu().getNombre());
        System.out.println("Cedula:"+ i.getIdAlu().getCedula().getCedula());
        System.out.println("Telefono:"+ i.getIdAlu().getTelefono());
        System.out.println("Email:"+ i.getIdAlu().getCedula().getEmail());
        System.out.println("Fech Nacimiento:"+ i.getIdAlu().getNacimiento());
        System.out.println("Fech De inscripcion:"+ i.getFechaInscripcion());
        System.out.println("Carrera Matriculada:"+ i.getIdCarr().getNombre());
        System.out.println("__________________________________________________");
        }
    }
    
    

}
