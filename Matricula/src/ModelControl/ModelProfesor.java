/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import BusinessLogic.Profesor;
import DataAccess.GlobalException;
import DataAccess.NoDataException;
import DataAccess.servicioProfesor;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author marcovinicio
 */
public class ModelProfesor {
    
    private servicioProfesor sp;
    private static ModelProfesor uniqueInstance;
    
    public static ModelProfesor instance(){
        if (uniqueInstance == null){
            uniqueInstance = new ModelProfesor();
        }
        return uniqueInstance;
    }
    private ModelProfesor(){
        sp = new servicioProfesor();
    }
    
    //===========================OBTENER PROFES POR NOMBRE===================================
    public List<Profesor> profesXnombre(String nombre) throws GlobalException, NoDataException {
       return sp.obtenerProfesNombre(nombre);
    }
    
    //===========================OBTENER PROFES POR NOMBRE===================================
    public List<Profesor> profesXCedula(String cedula) throws GlobalException, NoDataException {
        return sp.obtenerProfesCedula(cedula);
    }
    
    
}
