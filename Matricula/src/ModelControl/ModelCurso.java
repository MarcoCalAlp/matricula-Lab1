/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import BusinessLogic.Ciclo;
import BusinessLogic.Curso;
import DataAccess.GlobalException;
import DataAccess.NoDataException;
import DataAccess.servicioCurso;
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
public class ModelCurso {
    private servicioCurso sc;
    private static ModelCurso uniqueInstance;
    
    public static ModelCurso instance(){
        if (uniqueInstance == null){
            uniqueInstance = new ModelCurso();
        }
        return uniqueInstance;
    }
    private ModelCurso(){
        sc = new servicioCurso();
    }
    
    //===========================OBTENER CURSOS POR CODIGO CARRERA===================================
    public List<Curso> CursosXcarrera(String codCarr) throws GlobalException, NoDataException {
        return sc.obtenerCursosCarrera(codCarr);
    }
    
     //===========================OBTENER CURSOS POR NOMBRE===================================
    public List<Curso> CursosXnombre(String nombre) throws GlobalException, NoDataException {
        return sc.obtenerCursosNombre(nombre);
    }
    
    //===========================OBTENER CURSOS POR CODIGO===================================
    public List<Curso> CursosXcodigo(String codigo) throws GlobalException, NoDataException {
        return sc.obtenerCursosCodigo(codigo);
    }
    
    public void eliminaCursos(String codigo) throws GlobalException, NoDataException {
        sc.eliminarCurso(codigo);
    }
    
    public void insertarCursos(Curso e) throws GlobalException, NoDataException{
        sc.insertarCursos(e);
    }
    public void actualizarCursos(Curso e) throws GlobalException, NoDataException{
        sc.actualizarCursos(e.getCodigo(), e.getCodCarrera().getCodigo(), e.getCicloCurso().getNumero(),
        e.getCicloCurso().getAnnio(),e.getNombre(),e.getCreditos(), e.getHoras());
    }
    
    public Ciclo obtieneCiclos(Integer num , Integer Annio) throws GlobalException, NoDataException{
       return sc.obtenerCiclos(num, Annio);
    }
    
}
