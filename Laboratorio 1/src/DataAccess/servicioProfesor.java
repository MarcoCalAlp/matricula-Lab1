/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;


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
public class servicioProfesor extends Servicio {
    
    private static final String PROFESORXNOM= "{?= call profesorXnombre(?)}";
    private static final String PROFESORXCED= "{?= call profesorXcedula(?)}";

    public servicioProfesor() {
    }
    
    //===========================OBTENER PROFES POR NOMBRE===================================
    public List<Profesor> obtenerProfesNombre(String nombre) throws GlobalException, NoDataException {
        List<Profesor> profs = new ArrayList<Profesor>();
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
            pstmt = conexion.prepareCall(PROFESORXNOM);
            pstmt.setString(2, nombre);
	    pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.executeUpdate();
            
            rs = (ResultSet) pstmt.getObject(1);
            
            while(rs.next()){
                profs.add(profesor(rs));
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
        //Retorna la lista de profesores
        return profs;
        
    }
    
    //===========================OBTENER PROFES POR NOMBRE===================================
    public List<Profesor> obtenerProfesCedula(String cedula) throws GlobalException, NoDataException {
        List<Profesor> profs = new ArrayList<Profesor>();
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
            pstmt = conexion.prepareCall(PROFESORXCED);
            pstmt.setString(2, cedula);
	    pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.executeUpdate();
            
            rs = (ResultSet) pstmt.getObject(1);
            
            while(rs.next()){
                profs.add(profesor(rs));
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
        //Retorna la lista de profesores
        return profs;
        
    }
    
    //============================CONVERSOR DE RESULTSET A PROFESOR====================================
     private Profesor profesor(ResultSet rs){
        try{
            Profesor ti = new Profesor();
            ti.setCedula(usuario(rs));
            ti.setNombre(rs.getString("nombre"));
            ti.setTelefono(rs.getString("telefono"));
            return ti;
        }
        catch(SQLException e){
            return null;
        }
        
     }
     
     //============================CONVERSOR DE RESULTSET A USUARIO====================================
     private Usuario usuario(ResultSet rs){
        try{
            Usuario ti = new Usuario();
            ti.setCedula(rs.getString("cedula"));
            ti.setClave(rs.getString("clave"));
            ti.setEmail(rs.getString("email"));
            return ti;
        }
        catch(SQLException e){
            return null;
        }
        
     }
}
