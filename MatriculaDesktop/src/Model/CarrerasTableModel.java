/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import BusinessLogic.Carrera;
import BusinessLogic.Curso;
import BusinessLogic.Profesor;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author guill
 */
public class CarrerasTableModel extends AbstractTableModel {
    
    public static final int NOMBRE=0;
    public static final int CODIGO=1;
    public static final int TITULO=2;
    
    String[] colNames = new String[4];
    int[] cols;
    List<Carrera> rows;

    public CarrerasTableModel(int[] cols, List<Carrera> carreras) {
        this.cols = cols;
        this.rows = carreras;
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
        Carrera c = rows.get(row);
        switch(cols[col]){
            case NOMBRE: return c.getNombre();
            case CODIGO: return c.getCodigo();
            case TITULO: return c.getTitulo();            
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
        colNames[CODIGO] = "Código";
        colNames[TITULO] = "Título";        
    }
    
    public Carrera getRowAt(int row) {
        return rows.get(row);
    }
    
}
