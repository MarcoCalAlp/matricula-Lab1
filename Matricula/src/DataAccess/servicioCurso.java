/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import BusinessLogic.Carrera;
import BusinessLogic.Ciclo;
import BusinessLogic.Curso;
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
public class servicioCurso extends Servicio {
    
    private static final String CURSOSXNOM= "{?= call cursosXnom(?)}";
    private static final String CURSOSXCOD= "{?= call cursosXcodigo(?)}";
    private static final String CURSOSXCARRERA= "{?= call cursosXcarrera(?)}";

    public servicioCurso() {
    }
    
     //===========================OBTENER CURSOS POR CODIGO CARRERA===================================
    public List<Curso> obtenerCursosCarrera(String codCarr) throws GlobalException, NoDataException {
        List<Curso> curs = new ArrayList<Curso>();
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
            pstmt = conexion.prepareCall(CURSOSXCARRERA);
            pstmt.setString(2, codCarr);
	    pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.executeUpdate();
            
            rs = (ResultSet) pstmt.getObject(1);
            
            while(rs.next()){
                curs.add(curso(rs));
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
        return curs;
        
    }
    
     //===========================OBTENER CURSOS POR NOMBRE===================================
    public List<Curso> obtenerCursosNombre(String nombre) throws GlobalException, NoDataException {
        List<Curso> curs = new ArrayList<Curso>();
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
            pstmt = conexion.prepareCall(CURSOSXNOM);
            pstmt.setString(2, nombre);
	    pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.executeUpdate();
            
            rs = (ResultSet) pstmt.getObject(1);
            
            while(rs.next()){
                curs.add(curso(rs));
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
        return curs;
        
    }
    
    //===========================OBTENER CURSOS POR CODIGO===================================
    public List<Curso> obtenerCursosCodigo(String codigo) throws GlobalException, NoDataException {
        List<Curso> curs = new ArrayList<Curso>();
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
            pstmt = conexion.prepareCall(CURSOSXCOD);
            pstmt.setString(2, codigo);
	    pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.executeUpdate();
            
            rs = (ResultSet) pstmt.getObject(1);
            
            while(rs.next()){
                curs.add(curso(rs));
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
        return curs;
        
    }
    
    //============================CONVERSOR DE RESULTSET A CARRERA====================================
     private Carrera carrera(ResultSet rs){
        try{
            Carrera ti = new Carrera();
            ti.setCodigo(rs.getString(8));
            ti.setNombre(rs.getString(9));
            ti.setTitulo(rs.getString(10));
            return ti;
        }
        catch(SQLException e){
            return null;
        }
        
     }
     //============================CONVERSOR DE RESULTSET A CARRERA====================================
     private Ciclo ciclo(ResultSet rs){
        try{
            Ciclo ti = new Ciclo();
            ti.setAnnio(rs.getInt(11));
            ti.setNumero(rs.getInt(12));
            ti.setInicioD(rs.getDate(13));
            ti.setFinalD(rs.getDate(14));
            return ti;
        }
        catch(SQLException e){
            return null;
        }
        
     }
     
     //============================CONVERSOR DE RESULTSET A CURSO====================================
     private Curso curso(ResultSet rs){
        try{
            Curso ti = new Curso();
            ti.setCodigo(rs.getString(1));
            ti.setCicloCurso(ciclo(rs));
            ti.setNombre(rs.getString(5));
            ti.setCreditos(rs.getInt(6));
            ti.setHoras(rs.getInt(7));
            ti.setCodCarrera(carrera(rs));
            return ti;
        }
        catch(SQLException e){
            return null;
        }
        
     }
    
}
