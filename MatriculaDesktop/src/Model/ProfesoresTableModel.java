/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import BusinessLogic.Curso;
import BusinessLogic.Profesor;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author guill
 */
public class ProfesoresTableModel extends AbstractTableModel {
    
    public static final int CEDULA=0;
    public static final int NOMBRE=1;
    public static final int EMAIL=2;
    public static final int TELEFONO=3;
    
    String[] colNames = new String[4];
    int[] cols;
    List<Profesor> rows;

    public ProfesoresTableModel(int[] cols, List<Profesor> profesores) {
        this.cols = cols;
        this.rows = profesores;
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
        Profesor c = rows.get(row);
        switch(cols[col]){
            case CEDULA: return c.getCedula().getCedula();
            case NOMBRE: return c.getNombre();
            case EMAIL: return c.getCedula().getEmail();
            case TELEFONO: return c.getTelefono();            
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
        colNames[CEDULA] = "Cédula";
        colNames[NOMBRE] = "Nombre";
        colNames[EMAIL] = "Email";
        colNames[TELEFONO] = "Teléfono";        
    }
    
    public Profesor getRowAt(int row) {
        return rows.get(row);
    }
    
}
