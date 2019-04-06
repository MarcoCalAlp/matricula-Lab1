/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import java.sql.Date;

/**
 *
 * @author marcovinicio
 */
public class Ciclo {
    
private Integer  annio;
private Integer numero;
private Date inicioD;
private Date finalD;

    public Ciclo() {
        annio=0;
        numero=0;
        inicioD= new Date(1917,1,1);
        finalD= new Date(1917,6,1);
    }

    public Ciclo(Integer annio, Integer numero, Date inicioD, Date finalD) {
        this.annio = annio;
        this.numero = numero;
        this.inicioD = inicioD;
        this.finalD = finalD;
    }

    public Integer getAnnio() {
        return annio;
    }

    public void setAnnio(Integer annio) {
        this.annio = annio;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getInicioD() {
        return inicioD;
    }

    public void setInicioD(Date inicioD) {
        this.inicioD = inicioD;
    }

    public Date getFinalD() {
        return finalD;
    }

    public void setFinalD(Date finalD) {
        this.finalD = finalD;
    }

    @Override
    public String toString() {
        String ciclo;
        ciclo = "Ciclo numero " + this.numero + "del año "+ this.annio+"\n"+
                "Dia de inicio:"+this.inicioD+"\n"+
                "Dia de finalizacion:"+this.finalD+"\n";
        return ciclo;
    }
    
   
    
}
