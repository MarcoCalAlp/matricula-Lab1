/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import BusinessLogic.Alumno;
import BusinessLogic.Carrera;
import BusinessLogic.MatriculaCarrera;
import BusinessLogic.Profesor;
import BusinessLogic.Usuario;
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
public class servicioMatriculaCarrera extends Servicio {
    
    private static final String ALUMNOXNOM= "{?= call alumnoXnombre(?)}";
    private static final String ALUMNOXCED= "{?= call alumnoXcedula(?)}";
    private static final String ALUMNOXCARR= "{?= call alumnoXcarr(?)}";

    public servicioMatriculaCarrera() {
    }
    
    //====================================================================
    public List<MatriculaCarrera> obtenerMatriculasCedula(String cedula) throws GlobalException, NoDataException {
        List<MatriculaCarrera> mats = new ArrayList<MatriculaCarrera>();
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
            pstmt = conexion.prepareCall(ALUMNOXCED);
            pstmt.setString(2, cedula);
	    pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.executeUpdate();
            
            rs = (ResultSet) pstmt.getObject(1);
            
            while(rs.next()){
                mats.add(matricula(rs));
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
        //Retorna la lista de matriculas por carrera
        return mats;
        
    }
    //====================================================================
    public List<MatriculaCarrera> obtenerMatriculasNombre(String nombre) throws GlobalException, NoDataException {
        List<MatriculaCarrera> mats = new ArrayList<MatriculaCarrera>();
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
            pstmt = conexion.prepareCall(ALUMNOXNOM);
            pstmt.setString(2, nombre);
	    pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.executeUpdate();
            
            rs = (ResultSet) pstmt.getObject(1);
            
            while(rs.next()){
                mats.add(matricula(rs));
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
        //Retorna la lista de matriculas por carrera
        return mats;
        
    }
    //====================================================================
    public List<MatriculaCarrera> obtenerMatriculasCarrera(String codCarrera) throws GlobalException, NoDataException {
        List<MatriculaCarrera> mats = new ArrayList<MatriculaCarrera>();
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
            pstmt = conexion.prepareCall(ALUMNOXCARR);
            pstmt.setString(2, codCarrera);
	    pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.executeUpdate();
            
            rs = (ResultSet) pstmt.getObject(1);
            
            while(rs.next()){
                mats.add(matricula(rs));
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
        //Retorna la lista de matriculas por carrera
        return mats;
        
    }
    //====================================================================
    private Alumno alumno(ResultSet rs){
        try{
            Alumno ti = new Alumno();
            ti.setCedula(usuario(rs));
            ti.setNombre(rs.getString(8));
            ti.setTelefono(rs.getString(9));
            ti.setNacimiento(rs.getDate(10));
            return ti;
        }
        catch(SQLException e){
            return null;
        }
        
     }
    //====================================================================
     private Usuario usuario(ResultSet rs){
        try{
            Usuario ti = new Usuario();
            ti.setCedula(rs.getString(11));
            ti.setClave(rs.getString(12));
            ti.setEmail(rs.getString(13));
            return ti;
        }
        catch(SQLException e){
            return null;
        }
        
     }
     //====================================================================
     private Carrera carrera(ResultSet rs){
        try{
            Carrera ti = new Carrera();
            ti.setCodigo(rs.getString(4));
            ti.setNombre(rs.getString(5));
            ti.setTitulo(rs.getString(6));
            return ti;
        }
        catch(SQLException e){
            return null;
        }
        
     }
     //====================================================================
     private MatriculaCarrera matricula(ResultSet rs){
        try{
            MatriculaCarrera ti = new MatriculaCarrera();
            ti.setIdAlu(alumno(rs));
            ti.setIdCarr(carrera(rs));
            ti.setFechaInscripcion(rs.getDate(3));
            
            
            return ti;
        }
        catch(SQLException e){
            return null;
        }
        
     }
    
    
}
