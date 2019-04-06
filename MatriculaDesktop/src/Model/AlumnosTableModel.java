/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import BusinessLogic.Alumno;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author guill
 */
public class AlumnosTableModel extends AbstractTableModel {
    
    public static final int NOMBRE=1;
    public static final int CEDULA=0;
    public static final int TELEFONO=2;
    public static final int EMAIL=3;
    public static final int FN=4;
    //public static final int FI=5;
    
    
    
    String[] colNames = new String[5];
    int[] cols;
    List<Alumno> rows;

    public AlumnosTableModel(int[] cols, List<Alumno> alumnos) {
        this.cols = cols;
        this.rows = alumnos;
        initColNames();
    }
    
    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Alumno c = rows.get(row);
        switch(cols[col]){
            case NOMBRE: return c.getNombre();
            case CEDULA: return c.getCedula().getCedula();
            case TELEFONO: return c.getTelefono();
            case EMAIL: return c.getCedula().getEmail();
            case FN: return c.getNacimiento();
            //case FI: return c.getCedula().getCedula();                
            default: return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return colNames[cols[column]];
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch(cols[col]){
            default: return super.getColumnClass(col);
        } //To change body of generated methods, choose Tools | Templates.
    }
    
    private void initColNames() {
        colNames[NOMBRE] = "Nombre";
        colNames[CEDULA] = "Código";
        colNames[TELEFONO] = "Teléfono";  
        colNames[EMAIL] = "Email";   
        colNames[FN] = "Fecha de Nacimiento";           
    }
    
    public Alumno getRowAt(int row) {
        return rows.get(row);
    }
    
}
