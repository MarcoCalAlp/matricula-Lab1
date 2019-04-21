/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import BusinessLogic.Alumno;
import BusinessLogic.Carrera;
import BusinessLogic.MatriculaCarrera;
import BusinessLogic.Usuario;
import DataAccess.GlobalException;
import DataAccess.NoDataException;
import DataAccess.servicioMatriculaCarrera;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author marcovinicio
 */
public class ModelMatriculaCarrera {
    
    private servicioMatriculaCarrera smc;
    private static ModelMatriculaCarrera uniqueInstance;
    
    public static ModelMatriculaCarrera instance(){
        if (uniqueInstance == null){
            uniqueInstance = new ModelMatriculaCarrera();
        }
        return uniqueInstance;
    }
    private ModelMatriculaCarrera(){
        smc = new servicioMatriculaCarrera();
    }
    
    public List<MatriculaCarrera> matriculasXcedula(String cedula) throws GlobalException, NoDataException {
        return smc.obtenerMatriculasCedula(cedula);
    }
    //====================================================================
    public List<MatriculaCarrera> matriculasXnombre(String nombre) throws GlobalException, NoDataException {
        return smc.obtenerMatriculasNombre(nombre);
        
    }
    //====================================================================
    public List<MatriculaCarrera> matriculasXcarrera(String codCarrera) throws GlobalException, NoDataException {
       return smc.obtenerMatriculasCarrera(codCarrera);
        
    }
    
    public void insertaAlumnos(Usuario u ,Carrera c,Alumno a,Date d) throws GlobalException, NoDataException{
       smc.insertarUsuario(u.getCedula(),u.getClave(),u.getEmail());
       smc.insertarAlumno(u.getCedula(),a.getNombre(),a.getTelefono(),a.getNacimiento());
       smc.insertarMatrCarr(u.getCedula(),c.getCodigo(),d);
    }
    
    public void eliminarAlumnos(String idAlumn,String idCarr) throws GlobalException, NoDataException{
        smc.eliminarMatriculaCarr(idAlumn,idCarr);
        smc.eliminarAlumns(idAlumn);
        smc.eliminarUsuario(idAlumn);
    }
    
    public void actualizaAlumnos(String ced,String name, String tel, Date nac) throws GlobalException, NoDataException{
        smc.actualizaAlumno(ced, name, tel, nac);
    }
}
