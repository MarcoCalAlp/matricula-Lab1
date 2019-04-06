/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

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

/**
 *
 * @author guill
 */
public class Model {
    
    private static Model uniqueInstance;
    
    private ModelCurso modelCurso;
    private ModelProfesor modelProfesor;
    private ModelCarrera modelCarrera;  
    private ModelMatriculaCarrera modelMatriculaCarrera;
    
    public static Model instance(){
        if (uniqueInstance == null) {
            uniqueInstance = new Model();
        }
        return uniqueInstance;
    }
    
    private Model(){
        modelCurso = ModelCurso.instance();
        modelProfesor = ModelProfesor.instance();
        modelCarrera = ModelCarrera.instance();
        modelMatriculaCarrera = ModelMatriculaCarrera.instance();
    }
    
    //MANTENIMIENTOS DE CURSOS
    public List<Curso> CursosXcarrera(String codCarr) throws GlobalException, NoDataException {
        return modelCurso.CursosXcarrera(codCarr);
    }
    
    public List<Curso> CursosXnombre(Curso c) throws GlobalException, NoDataException {
        return modelCurso.CursosXnombre(c.getNombre());
    }
    
    public List<Curso> CursosXcodigo(Curso c) throws GlobalException, NoDataException {
        return modelCurso.CursosXcodigo(c.getCodigo());
    }
    
    //MANTENIMIENTOS DE PROFESORES 
    public List<Profesor> ProfesXnombre(Profesor p) throws GlobalException, NoDataException {
        String s = p.getNombre();
        return modelProfesor.profesXnombre(s);
    }

    public List<Profesor> ProfesXCedula(Profesor p) throws GlobalException, NoDataException {
        return modelProfesor.profesXCedula(p.getCedula().getCedula());
    }
    
    //MANTENIMIENTOS DE CARRERAS
    public List<Carrera> CarrerasXnombre(Carrera c) throws GlobalException, NoDataException {
        return modelCarrera.carrerasXnombre(c.getNombre());
    }

    public List<Carrera> CarrerasXcodigo(Carrera c) throws GlobalException, NoDataException {
        return modelCarrera.carrerasXcodigo(c.getCodigo());
    }
    
    //MANTENIMIENTOS DE ALUMNOS
    public List<MatriculaCarrera> MatriculasXnombre(Alumno a) throws GlobalException, NoDataException {
        return modelMatriculaCarrera.matriculasXnombre(a.getNombre());
    }
    
    public List<MatriculaCarrera> MatriculasXcedula(Alumno a) throws GlobalException, NoDataException {
        return modelMatriculaCarrera.matriculasXcedula(a.getCedula().getCedula());
    }
    
    public List<MatriculaCarrera> MatriculasXcarrera(String a) throws GlobalException, NoDataException {
        return modelMatriculaCarrera.matriculasXcarrera(a);
    }
    
    /*
    public List<MatriculaCarrera> MatriculasXcedula(String cedu, String clave) throws GlobalException, NoDataException {
        return modelMatriculaCarrera.matriculasXcedula(cedu,clave);
    }
    */
    
}
