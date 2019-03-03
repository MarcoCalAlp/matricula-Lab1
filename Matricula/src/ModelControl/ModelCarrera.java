/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import BusinessLogic.Carrera;
import DataAccess.GlobalException;
import DataAccess.NoDataException;
import DataAccess.servicioCarrera;
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
public class ModelCarrera {
    
    private servicioCarrera sc;
    private static ModelCarrera uniqueInstance;
    
    public static ModelCarrera instance(){
        if (uniqueInstance == null){
            uniqueInstance = new ModelCarrera();
        }
        return uniqueInstance;
    }
    private ModelCarrera(){
        sc = new servicioCarrera();
    }
    
    public List<Carrera> carrerasXnombre(String nombre) throws GlobalException, NoDataException {
        return sc.obtenerCarrerasNombre(nombre);
        
    }
    //============================OBTENER CARRERAS POR CODIGO====================================
    public List<Carrera> carrerasXcodigo(String codigo) throws GlobalException, NoDataException {
        return sc.obtenerCarrerasCodigo(codigo);
    }
    
}
