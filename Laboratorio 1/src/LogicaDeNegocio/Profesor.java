/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package LogicaDeNegocio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author guill
 */
public class Profesor extends Usuario {
    
    private String nombre;
    private String telefono;
    private List<Grupo> grupos = new ArrayList<>();

    public Profesor() {
    }

    public Profesor(String cedula, String clave, String email, String nombre, String telefono) {
        super(cedula,clave,email);
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }    

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }
    
}
