/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package laboratorio.pkg1;

import BusinessLogic.Carrera;
import BusinessLogic.Curso;
import BusinessLogic.MatriculaCarrera;
import BusinessLogic.Profesor;
import DataAccess.GlobalException;
import DataAccess.NoDataException;
import DataAccess.servicioCarrera;
import DataAccess.servicioCurso;
import DataAccess.servicioMatriculaCarrera;
import DataAccess.servicioProfesor;
import java.util.List;

/**
 *
 * @author guill
 */
public class Laboratorio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws GlobalException, NoDataException {
        // TODO code application logic here
         servicioCarrera sr = new servicioCarrera();
         servicioCurso sc = new servicioCurso();
         servicioProfesor sp = new servicioProfesor();
         servicioMatriculaCarrera sm = new  servicioMatriculaCarrera();
            List<MatriculaCarrera> lmc = sm.obtenerMatriculasCarrera("INGSYS");
            List<Profesor> lp = sp.obtenerProfesNombre("Marco");
            List<Profesor> lp2 = sp.obtenerProfesCedula("444444");
            List<Curso> c  = sc.obtenerCursosCarrera("INGSYS");
            List<Carrera> lm = sr.obtenerCarrerasNombre("Ingieneria en Sistemas");
            List<Carrera> lc = sr.obtenerCarrerasCodigo("ENSING");
            
            
    }
    
}
