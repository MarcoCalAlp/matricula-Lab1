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
     private static final String DELETEPROFESORES= "{ call remover_profe(?)}";
    private static final String INSERTARPROFESORES= "{ call inserta_profesor(?,?,?)}";
    private static final String ACTUALIZARPROFESORES= "{ call actualiza_profesor(?,?,?)}";
    private static final String DELETEUSUARIOS= "{ call remover_usuario(?)}";
     private static final String INSERTAUSUARIOS= "{ call inserta_usuario(?,?,?)}";
     
    
    
    

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
    
    //===========INSERTAR USUARIOS
    public void insertarUsuario(String cedula,String clave,String email) throws GlobalException, NoDataException {
  
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
            pstmt = conexion.prepareCall(INSERTAUSUARIOS);
            pstmt.setString(1, cedula);
	    pstmt.setString(2, clave);
            pstmt.setString(3, email);
           boolean resultado = pstmt.execute();
            if (resultado == true) {
                throw new NoDataException("No se realizo la insercion del usuario");
            }
            
        }   catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Llave duplicada");
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
    
     //===========INSERTAR PROFESORES
    public void insertarProfesor(String cedula,String nombre,String telefono) throws GlobalException, NoDataException {
        
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt=null;
        try {
            pstmt = conexion.prepareCall(INSERTARPROFESORES);
            pstmt.setString(1, cedula);
	    pstmt.setString(2,nombre);
            pstmt.setString(3,telefono);
            boolean resultado = pstmt.execute();
            if (resultado == true) {
                throw new NoDataException("No se realizo la insercion del usuario");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Llave duplicada");
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
    
      //===========ACTUALIZAR PROFESORES
    public void actualizarProfesor(String cedula,String nombre,String telefono) throws GlobalException, NoDataException {
        
       
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        
        CallableStatement pstmt=null;
        try {
            pstmt = conexion.prepareCall(ACTUALIZARPROFESORES);
            pstmt.setString(1, cedula);
	    pstmt.setString(2,nombre);
            pstmt.setString(3,telefono);
            boolean resultado = pstmt.execute();
            if (resultado == true) {
                throw new NoDataException("No se realizo la actualizacion del usuario");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Llave duplicada");
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
    
    public void eliminarUsuario(String codigo) throws GlobalException, NoDataException {
    
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
            pstmt = conexion.prepareCall(DELETEUSUARIOS);
            pstmt.setString(1, codigo);
           boolean resultado = pstmt.execute();
            if (resultado == true) {
                throw new NoDataException("No se realizo la eliminacion del usuario");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("No existe el usuario");
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
    
     public void eliminarProfesor(String codigo) throws GlobalException, NoDataException {
        
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
            pstmt = conexion.prepareCall(DELETEPROFESORES);
            pstmt.setString(1, codigo);
            pstmt.executeUpdate();
            boolean resultado = pstmt.execute();
            if (resultado == true) {
                throw new NoDataException("No se realizo la eliminacion del profesor");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("No existe el profesor");
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
