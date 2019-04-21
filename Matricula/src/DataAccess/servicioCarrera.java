/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import BusinessLogic.Carrera;
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
public class servicioCarrera extends Servicio {
    private static final String CARRERASXNOM= "{?= call carrerasXnom(?)}";
    private static final String CARRERASXCOD= "{?= call carrerasXcod(?)}";
    private static final String DELETECARRERAS= "{ call remover_Carr(?)}";
    private static final String INSERTARCARRERAS= "{ call inserta_carrera(?,?,?)}";
    private static final String ACTUALIZARCARRERAS= "{ call actualiza_carrera(?,?,?)}";
    
    
    /** Creates a new instance of servicioCarrera */
    public servicioCarrera() {
        
    }
    //===========================OBTENER CARRERAS POR NOMBRE===================================
    public List<Carrera> obtenerCarrerasNombre(String nombre) throws GlobalException, NoDataException {
        List<Carrera> carr = new ArrayList<Carrera>();
        CallableStatement pstmt=null;
         ResultSet rs = null;
        //------
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        //------
        try {
            pstmt = conexion.prepareCall(CARRERASXNOM);
            pstmt.setString(2, nombre);
	    pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.executeUpdate();
            
            rs = (ResultSet) pstmt.getObject(1);
            
            while(rs.next()){
                carr.add(carrera(rs));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("No existen registros");
        } 
        finally {
            try
            {
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } 
            catch (SQLException e) 
            {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        //Retorna la lista de carreras
        return carr;
        
    }
    //============================OBTENER CARRERAS POR CODIGO====================================
    public List<Carrera> obtenerCarrerasCodigo(String codigo) throws GlobalException, NoDataException {
        List<Carrera> carr = new ArrayList<Carrera>();
        CallableStatement pstmt=null;
         ResultSet rs = null;
        //--------
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        //--------
        try {
            pstmt = conexion.prepareCall(CARRERASXCOD);
            pstmt.setString(2, codigo);
	    pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.executeUpdate();
            
            rs = (ResultSet) pstmt.getObject(1);
            
            while(rs.next()){
                carr.add(carrera(rs));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("No existen registros");
        } 
        finally {
            try
            {
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } 
            catch (SQLException e) 
            {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        //Retorna la lista
        return carr;
        
    }
    
    //===========INSERTAR CARRERAS
    
    public void insertarCarrera(String codCarr,String nomCarr,String titulo) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        
        CallableStatement pstmt=null;
        try {
            pstmt = conexion.prepareCall(INSERTARCARRERAS);
            pstmt.setString(1, codCarr);
	    pstmt.setString(2, nomCarr);
            pstmt.setString(3, titulo);
            boolean resultado = pstmt.execute();
            if (resultado == true) {
                throw new NoDataException("No se realizo la insercion de la carrera");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("No existe la carrera");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }   
        
    }
    
    //=================ACTULIZAR CARRERAS
     public void actualizarCarrera(String codCarr,String nomCarr,String titulo) throws GlobalException, NoDataException {
   
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        //------
        CallableStatement pstmt=null;
        try {
            pstmt = conexion.prepareCall(ACTUALIZARCARRERAS);
            pstmt.setString(1, codCarr);
	    pstmt.setString(2, nomCarr);
            pstmt.setString(3, titulo);
            boolean resultado = pstmt.execute();
            if (resultado == true) {
                throw new NoDataException("No se realizo la modificacion de la carrera");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("No existe la carrera");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }   
        
    }
    //====================ELIMINAR CARRERAS================================
    public void eliminarCarrera(String codigo) throws GlobalException, NoDataException {       

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt=null;
        try {
            pstmt = conexion.prepareCall(DELETECARRERAS);
            pstmt.setString(1, codigo);
             boolean resultado = pstmt.execute();
            if (resultado == true) {
                throw new NoDataException("No se realizo la eliminacion de la carrera");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("No existe la carrera");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }       
    }
    
     //============================CONVERSOR DE RESULTSET A CARRERA====================================
     private Carrera carrera(ResultSet rs){
        try{
            Carrera ti = new Carrera();
            ti.setCodigo(rs.getString("codigo"));
            ti.setNombre(rs.getString("nombre"));
            ti.setTitulo(rs.getString("titulo"));
            return ti;
        }
        catch(SQLException e){
            return null;
        }
    }
    
}
