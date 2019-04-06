/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import BusinessLogic.Curso;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author guill
 */
public class CursosTableModel extends AbstractTableModel {
    
    public static final int CODIGO=0;
    public static final int NOMBRE=1;
    public static final int CREDITOS=2;
    public static final int HORAS=3;
    public static final int CARRERA=4;
    public static final int CICLO=5;

    
    String[] colNames = new String[6];
    int[] cols;
    List<Curso> rows;

    public CursosTableModel(int[] cols, List<Curso> cursos) {
        this.cols = cols;
        this.rows = cursos;
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
        Curso c = rows.get(row);
        switch(cols[col]){
            case CODIGO: return c.getCodigo();
            case NOMBRE: return c.getNombre();
            case CREDITOS: return c.getCreditos();
            case HORAS: return c.getHoras();
            case CARRERA: return c.getCodCarrera().getNombre();
            case CICLO: return c.getCicloCurso().getNumero();
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
        colNames[CODIGO] = "Codigo";
        colNames[NOMBRE] = "Nombre";
        colNames[CREDITOS] = "Creditos";
        colNames[HORAS] = "Horas";
        colNames[CARRERA] = "Carrera";
        colNames[CICLO] = "Ciclo";
    }
    
    public Curso getRowAt(int row) {
        return rows.get(row);
    }
    
}
