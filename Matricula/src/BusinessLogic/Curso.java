/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

/**
 *
 * @author marcovinicio
 */
public class Curso {
   private String codigo;
   private Carrera codCarrera;
   private Ciclo cicloCurso;
   private String nombre;
   private Integer creditos;
   private Integer horas;

   public Curso() {
       codigo="";
       codCarrera = new Carrera();
       cicloCurso = new Ciclo();
       nombre="";
       creditos=0;
       horas=0;
       
    }
   
   public Curso(String cod,Carrera codC,Ciclo cicloC,String nom,Integer cred,Integer hor) {
       this.codigo=cod;
       this.codCarrera=codC;
       this.cicloCurso=cicloC;
       this.nombre=nom;
       this.creditos=cred;
       this.horas=hor;
    }
   
   
    public Ciclo getCicloCurso() {
        return cicloCurso;
    }

    public void setCicloCurso(Ciclo cicloCurso) {
        this.cicloCurso = cicloCurso;
    }
   
    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Carrera getCodCarrera() {
        return codCarrera;
    }

    public void setCodCarrera(Carrera codCarrera) {
        this.codCarrera = codCarrera;
    }

   
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }
    
    
   @Override
    public String toString(){
        String curso;
        curso = "Codigo de curso:"+ this.getCodigo()+"\n"+
                "Nombre de curso:"+ this.getNombre()+"\n"+
                "Creditos de curso:"+this.getCreditos()+"\n"+
                "Horas de curso:"+this.getHoras()+"\n"+
                "Ciclo perteneciente:"+this.getCicloCurso().getNumero()+ "del año "+ this.cicloCurso.getAnnio()+"\n"+        
                "Carrera Perteneciente:"+ this.getCodCarrera().getNombre();
        return curso;      
    }
   
   
    
}
