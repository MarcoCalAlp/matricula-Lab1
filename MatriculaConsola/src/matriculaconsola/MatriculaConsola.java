/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matriculaconsola;

import DataAccess.GlobalException;
import DataAccess.NoDataException;
import viewControl.View;

/**
 *
 * @author marcovinicio
 */
public class MatriculaConsola {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws GlobalException, NoDataException {
        View.instance().evaluaMenu();
    }
    
}
