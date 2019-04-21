/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewControl;

import BusinessLogic.Alumno;
import BusinessLogic.Carrera;
import BusinessLogic.Ciclo;
import BusinessLogic.Curso;
import BusinessLogic.MatriculaCarrera;
import BusinessLogic.Profesor;
import BusinessLogic.Usuario;
import DataAccess.GlobalException;
import DataAccess.NoDataException;
import ModelControl.ModelCarrera;
import ModelControl.ModelCurso;
import ModelControl.ModelMatriculaCarrera;
import ModelControl.ModelProfesor;
import static java.lang.Integer.parseInt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

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
    
    public int controlMatricula() throws GlobalException, NoDataException, ParseException{
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

    public void evaluaMenu() throws GlobalException, NoDataException, ParseException {
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
    
    
    
    private void evaluaMenuCursos() throws GlobalException, NoDataException, ParseException {
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
                
        case 4: this.ingresaCursos();
		break;
        case 5: this.modificaCursos();
		break;
                
        case 6: this.eliminaCursos();
		break;
                
        case 7: evaluaMenu();
		break;
        

	default:
                System.out.println("!!!Error, tiene que ingresar una opcion valida ");
                evaluaMenu();
	}
	} while(opciones != 7 && opciones != 0);
    }

    private void evaluaMenuProfesores() throws GlobalException, NoDataException, ParseException {
        int opciones = 0;
	do{
	opciones = controlProfesores();
	switch(opciones){
	case 1: BusqProfesNombre();
		break;

	case 2: BusqProfesCedula();
		break;
        case 3: ingresaProfesores();
		break;
        case 4: actualizaProfesores();
		break;
        case 5: eliminaProfesores();
		break;
        case 6: evaluaMenu();
		break;
	default:
                System.out.println("!!!Error, tiene que ingresar una opcion valida ");
                evaluaMenu();
	}
	} while(opciones != 7 && opciones != 0);
    
    }

    private void evaluaMenuCarreras() throws GlobalException, NoDataException, ParseException {
         int opciones = 0;
	do{
	opciones = controlCarreras();
	switch(opciones){
	case 1: BusqCarrNombre();
		break;
	case 2: BusqCarrCodigo();
		break;
        case 3: this.ingresaCarreras();
		break;
        case 4: this.modificaCarreras();
		break;
        case 5: this.eliminaCarreras();
		break;
        case 6: evaluaMenu();
		break;
	default:
                System.out.println("!!!Error, tiene que ingresar una opcion valida ");
                evaluaMenu();
	}
	} while(opciones <= 6 && opciones > 0);
    }

    private void evaluaMenuMatriculaAlumnos() throws GlobalException, NoDataException, ParseException {
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
                
        case 4: this.ingresaAlumnos();
		break;
        case 5: this.eliminaAlumnos();
		break;
        case 6: this.modificaAlumnos();
		break;
        case 7: evaluaMenu();
		break;

	default:
                System.out.println("!!!Error, tiene que ingresar una opcion valida ");
                evaluaMenu();
	}
	} while(opciones <= 7 && opciones > 0);
    }
    
    //============MANTENIMIENTO CURSOS=============== 
    private int controlCursos() throws GlobalException, NoDataException, ParseException {
        Scanner capt = new Scanner(System.in);
        System.out.println("      ____________________________________________________________________       ");
        System.out.println("     |                                                                    |      ");
        System.out.println("     |                            -Bienvenido-                            |      ");
        System.out.println("     |                       -Mantenimiento de Cursos-                    |      ");
        System.out.println("     |                                                                    |      ");
        System.out.println("     |    - Presione [1] para realizar la busqueda de cursos por nombre   |      ");
        System.out.println("     |    - Presione [2] para realizar la busqueda de cursos por codigo   |      ");
        System.out.println("     |    - Presione [3] para realizar la busqueda de cursos por carrera  |      ");
        System.out.println("     |    - Presione [4] para insertar un curso                           |      ");
        System.out.println("     |    - Presione [5] para actualizar un curso                         |      ");
        System.out.println("     |    - Presione [6] para eliminar un curso                           |      ");
        System.out.println("     |    - Presione [7] para salir                                       |      ");
        System.out.println("     |____________________________________________________________________|      ");
        
        System.out.println("Digite el numero del item del menu que desea seleccionar: ");
        int a = 0;
        try{
            a = capt.nextInt();
            if(a<1 || a>7){  
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
    
    private void eliminaCursos() throws GlobalException, NoDataException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("==>Digite el codigo del Curso a eliminar");
        String codigo = "";
        codigo = teclado.nextLine();
        ModelCurso.instance().eliminaCursos(codigo);
        System.out.println("¡Eliminado con exito!");
    }
    
    private void ingresaCursos() throws GlobalException, NoDataException {
        
        Scanner teclado = new Scanner(System.in);
        System.out.println("==>Digite el codigo del Curso a insertar");
        String codigo = teclado.nextLine();
        System.out.println("==>Digite el codigo de carrera al que pertenece el curso");
        String carr = teclado.nextLine();
        System.out.println("==>Digite el numero del ciclo al que pertenece el curso");
        String numCic  = teclado.nextLine();
        System.out.println("==>Digite el ano del ciclo al que pertenece el curso");
        String anoCicl  = teclado.nextLine();
         
        System.out.println("==>Digite el nombre del curso");
        String nomCur  = teclado.nextLine();
        System.out.println("==>Digite los creditos del curso");
        String cred = teclado.nextLine();
        System.out.println("==>Digite las horas del curso");
        String horCur = teclado.nextLine();
        Ciclo c1 = ModelCurso.instance().obtieneCiclos(Integer.parseInt(numCic), Integer.parseInt(anoCicl));
       
        Carrera c2 = ModelCarrera.instance().carrerasXcodigo(carr).get(0);
        Integer i1=Integer.parseInt(cred);
        Integer i2= Integer.parseInt(horCur);
        //Curso(String cod,Carrera codC,Ciclo cicloC,String nom,Integer cred,Integer hor)
        Curso ingresado = new Curso(codigo,c2,c1,nomCur,i1,i2);
        ModelCurso.instance().insertarCursos(ingresado);
        System.out.println("¡Ingresado con exito!");
    }
    
    
    private void modificaCursos() throws GlobalException, NoDataException {
        
        Scanner teclado = new Scanner(System.in);
        System.out.println("==>Digite el codigo del Curso que se va a modificar");
        String codigo = teclado.nextLine();
        System.out.println("==>Digite el codigo de la nueva carrera que se le quiere asignar");
        String carr = teclado.nextLine();
        System.out.println("==>Digite el numero del nuevo ciclo al que pertenece el curso");
        String numCic  = teclado.nextLine();
        System.out.println("==>Digite el ano del nuevo  ciclo al que pertenece el curso");
        String anoCicl  = teclado.nextLine();
         
        System.out.println("==>Digite el nuevo nombre del curso");
        String nomCur  = teclado.nextLine();
        System.out.println("==>Digite los nuevos  creditos del curso");
        String cred = teclado.nextLine();
        System.out.println("==>Digite las nuevas horas del curso");
        String horCur = teclado.nextLine();
        
        Ciclo c1 = ModelCurso.instance().obtieneCiclos(Integer.parseInt(numCic), Integer.parseInt(anoCicl));
        Carrera c2 = ModelCarrera.instance().carrerasXcodigo(carr).get(0);
        Integer i1=Integer.parseInt(cred);
        Integer i2= Integer.parseInt(horCur);
        //Curso(String cod,Carrera codC,Ciclo cicloC,String nom,Integer cred,Integer hor)
        Curso ingresado = new Curso(codigo,c2,c1,nomCur,i1,i2);
        ModelCurso.instance().actualizarCursos(ingresado);
        System.out.println("¡Ingresado con exito!");
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
    private int controlProfesores() throws GlobalException, NoDataException, ParseException {
        Scanner capt = new Scanner(System.in);
        System.out.println("      ____________________________________________________________________       ");
        System.out.println("     |                                                                    |      ");
        System.out.println("     |                            -Bienvenido-                            |      ");
        System.out.println("     |                       -Mantenimiento de Profesores-                |      ");
        System.out.println("     |                                                                    |      ");
        System.out.println("     | - Presione [1] para realizar la busqueda de profesores por nombre  |      ");
        System.out.println("     | - Presione [2] para realizar la busqueda de profesores por cedula  |      ");
        System.out.println("     | - Presione [3] para insertar un nuevo profesor                     |      ");
        System.out.println("     | - Presione [4] para actualizar un nuevo profesor                   |      ");
        System.out.println("     | - Presione [5] para eliminar un profesor existente                 |      ");
        System.out.println("     | - Presione [6] para salir                                          |      ");
        System.out.println("     |____________________________________________________________________|      ");
        
        System.out.println("Digite el numero del item del menu que desea seleccionar: ");
        int a = 0;
        try{
            a = capt.nextInt();
            if(a<1 || a>6){  
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
    
     private void ingresaProfesores() throws GlobalException, NoDataException, ParseException {
        
        Scanner teclado = new Scanner(System.in);
        System.out.println("==>Digite la cedula del Profesor");
        String cedula = teclado.nextLine();
        System.out.println("==>Digite el nombre del Profesor");
        String nomb = teclado.nextLine();
        System.out.println("==>Digite el email del Profesor");
        String email  = teclado.nextLine();
        
        
        
         
        System.out.println("==>Digite el codigo de la carrera al cual pertenece");
        String codCarr  = teclado.nextLine();
        System.out.println("==>Digite la contrasena a registrar");
        String passw = teclado.nextLine();
        System.out.println("==>Digite el numero telefonico");
        String phoneNum = teclado.nextLine();
        Usuario nuevo = new Usuario(cedula,passw,email);
        Profesor ingresado = new Profesor(nuevo,nomb,phoneNum);
     
        ModelProfesor.instance().insertaProfesor(nuevo,ingresado);
        System.out.println("¡Ingresado con exito!");
    }
     
     private void actualizaProfesores() throws GlobalException, NoDataException, ParseException {
        
        Scanner teclado = new Scanner(System.in);
        System.out.println("==>Digite la cedula del Profesor a buscar");
        String cedula = teclado.nextLine();
        System.out.println("==>Digite el nuevo nombre del Profesor");
        String nomb = teclado.nextLine();
        System.out.println("==>Digite el nuevo email del Profesor");
        String email  = teclado.nextLine();
         
        System.out.println("==>Digite el codigo actualizado de la carrera al cual pertenece");
        String codCarr  = teclado.nextLine();
        System.out.println("==>Digite la nueva contrasena ");
        String passw = teclado.nextLine();
        System.out.println("==>Digite el nuevo numero telefonico");
        String phoneNum = teclado.nextLine();
        Usuario actualizado = new Usuario(cedula,passw,email);
        Profesor ingresado = new Profesor(actualizado,nomb,phoneNum);
     
        ModelProfesor.instance().actualizaProfesor(ingresado);
        System.out.println("¡Actualizado con exito!");
    }
     
     private void eliminaProfesores() throws GlobalException, NoDataException, ParseException {
        
        Scanner teclado = new Scanner(System.in);
        System.out.println("==>Digite la cedula del Profesor a eliminar");
        String cedula = teclado.nextLine();
        System.out.println("==>Digite el codigo de carrera al que pertenece");
        String codCarr = teclado.nextLine();
       
     
        ModelProfesor.instance().eliminarProfesor(cedula);
        System.out.println("¡Actualizado con exito!");
    }
    
    //============MANTENIMIENTO CARRERAS=============== 
    private int controlCarreras() throws GlobalException, NoDataException, ParseException {
        Scanner capt = new Scanner(System.in);
        System.out.println("      ____________________________________________________________________       ");
        System.out.println("     |                                                                    |      ");
        System.out.println("     |                            -Bienvenido-                            |      ");
        System.out.println("     |                       -Mantenimiento de Carreras-                  |      ");
        System.out.println("     |                                                                    |      ");
        System.out.println("     |   - Presione [1] para realizar la busqueda de Carreras por nombre  |      ");
        System.out.println("     |   - Presione [2] para realizar la busqueda de Carreras por codigo  |      ");
        System.out.println("     |   - Presione [3] para realizar la insercion de una Carrera         |      ");
        System.out.println("     |   - Presione [4] para modificar los datos   de una Carrera         |      ");
        System.out.println("     |   - Presione [5] para eliminar una Carrera                         |      ");
        System.out.println("     |   - Presione [6] para salir                                        |      ");
        System.out.println("     |____________________________________________________________________|      ");
        
        System.out.println("Digite el numero del item del menu que desea seleccionar: ");
        int a = 0;
        try{
            a = capt.nextInt();
            if(a<1 || a>6){  
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
    
    private void ingresaCarreras() throws GlobalException, NoDataException, ParseException {
        
        Scanner teclado = new Scanner(System.in);
        System.out.println("==>El codigo de la Carrera");
        String codigo = teclado.nextLine();
        System.out.println("==>Digite el nombre de la Carrera");
        String nombre = teclado.nextLine();
        System.out.println("==>Digite el grado de la Carrera");
        String titulo  = teclado.nextLine();
        Carrera car = new Carrera(codigo,nombre,titulo);
        ModelCarrera.instance().insertaCarrera(car);
        System.out.println("¡Ingresado con exito!");
    }
    
    private void modificaCarreras() throws GlobalException, NoDataException, ParseException {
        
        Scanner teclado = new Scanner(System.in);
        System.out.println("==>El codigo de la Carrera a actualizar");
        String codigo = teclado.nextLine();
        System.out.println("==>Digite el nuevo nombre de la Carrera");
        String nombre = teclado.nextLine();
        System.out.println("==>Digite el nuevo grado de la Carrera");
        String titulo  = teclado.nextLine();
        Carrera car = new Carrera(codigo,nombre,titulo);
        ModelCarrera.instance().actualizaCarrera(car);
        System.out.println("¡Actualizado con exito!");
    }
    
    private void eliminaCarreras() throws GlobalException, NoDataException, ParseException {
        
        Scanner teclado = new Scanner(System.in);
        System.out.println("==>El codigo de la Carrera a eliminar");
        String codigo = teclado.nextLine();
        ModelCarrera.instance().eliminaCarrera(codigo);
        System.out.println("¡Eliminado con exito!");
    }
    
    //============MANTENIMIENTO MATRICULA ALUMNOS=============== 
    private int controlMatriculaAlumnos() throws GlobalException, NoDataException, ParseException {
        Scanner capt = new Scanner(System.in);
        System.out.println("      ____________________________________________________________________       ");
        System.out.println("     |                                                                    |      ");
        System.out.println("     |                            -Bienvenido-                            |      ");
        System.out.println("     |                       -Mantenimiento de Alumnos-                   |      ");
        System.out.println("     |                                                                    |      ");
        System.out.println("     |   - Presione [1] para realizar la busqueda de Alumnos por nombre   |      ");
        System.out.println("     |   - Presione [2] para realizar la busqueda de Alumnos por cedula   |      ");
        System.out.println("     |   - Presione [3] para realizar la busqueda de Alumnos por carrera  |      ");
        System.out.println("     |   - Presione [4] para realizar la insercion de un  alumno          |      ");
        System.out.println("     |   - Presione [5] para eliminar un alumno                           |      ");
        System.out.println("     |   - Presione [6] para actualizar un alumno                         |      ");
        System.out.println("     |   - Presione [7] para salir                                        |      ");
        System.out.println("     |____________________________________________________________________|      ");
        
        System.out.println("Digite el numero del item del menu que desea seleccionar: ");
        int a = 0;
        try{
            a = capt.nextInt();
            if(a<1 || a>7){  
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
    
     private void ingresaAlumnos() throws GlobalException, NoDataException, ParseException {
        
        Scanner teclado = new Scanner(System.in);
        System.out.println("==>Digite la cedula del Alumno");
        String cedula = teclado.nextLine();
        System.out.println("==>Digite el nombre del Alumno");
        String nomb = teclado.nextLine();
        System.out.println("==>Digite el email del Alumno");
        String email  = teclado.nextLine();
        System.out.println("==>Digite la fecha de nacimiento");
        String date  = teclado.nextLine();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        
        java.util.Date dateUtil = format.parse(date);
        java.sql.Date fecha_nac = new java.sql.Date(dateUtil.getTime()); 
        
         
        System.out.println("==>Digite el codigo de la carrera en el que se encuentra matriculado el alumno");
        String codCarr  = teclado.nextLine();
        System.out.println("==>Digite la contrasena a registrar");
        String passw = teclado.nextLine();
        System.out.println("==>Digite el numero telefonico");
        String phoneNum = teclado.nextLine();
        System.out.println("==>Digite la fecha de inscripcion");
        String date2  = teclado.nextLine();
        
        java.util.Date dateUtil2 = format.parse(date);
        java.sql.Date fecha_insc = new java.sql.Date(dateUtil2.getTime()); 
        
        Usuario nuevo = new Usuario(cedula,passw,email);
        Alumno ingresado =  new Alumno(nuevo,nomb,phoneNum, fecha_nac);
        Carrera  carr = ModelCarrera.instance().carrerasXcodigo(codCarr).get(0);
        ModelMatriculaCarrera.instance().insertaAlumnos(nuevo, carr, ingresado, (java.sql.Date) fecha_insc);
        System.out.println("¡Ingresado con exito!");
     
     }
     
      private void modificaAlumnos() throws GlobalException, NoDataException, ParseException {
        
        Scanner teclado = new Scanner(System.in);
        System.out.println("==>Digite la cedula del Alumno a Modificar");
        String cedula = teclado.nextLine();
        System.out.println("==>Digite el nuevo nombre del Alumno");
        String nomb = teclado.nextLine();
        System.out.println("==>Digite el nuevo numero telefonico");
        String phoneNum = teclado.nextLine();
        System.out.println("==>Digite la nueva fecha de nacimiento");
        String date  = teclado.nextLine();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date dateUtil = format.parse(date);
        java.sql.Date fecha_nac = new java.sql.Date(dateUtil.getTime()); 
       
       
        ModelMatriculaCarrera.instance().actualizaAlumnos(cedula, nomb, phoneNum, fecha_nac);
        System.out.println("¡Ingresado con exito!");
     
     }
     
     private void eliminaAlumnos() throws NoDataException, GlobalException{
        Scanner teclado = new Scanner(System.in);
        System.out.println("==>Digite la cedula del Alumno");
        String cedula = teclado.nextLine();
        System.out.println("==>Digite la carrera que curso el Alumno");
        String carr = teclado.nextLine();
        ModelMatriculaCarrera.instance().eliminarAlumnos(cedula, carr);
        System.out.println("¡Eliminado con exito!");
     }
    
    

}
