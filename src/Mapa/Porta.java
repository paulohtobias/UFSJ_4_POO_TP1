/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

/**
 *
 * @author paulo
 */

public class Porta {
    private int sala1;
    private int sala2;
    
    public enum Porta_Estado{
        TRANCADA, FECHADA, ABERTA
    };
    private Porta_Estado estado;
    
    public Porta(int sala1, int sala2) {
        this.sala1 = sala1;
        this.sala2 = sala2;
        
        this.estado = Porta_Estado.ABERTA;
    }
    
    public Porta(int sala1, int sala2, Porta_Estado estado){
        this.sala1 = sala1;
        this.sala2 = sala2;
        
        this.estado = estado;
    }

    public int getSala1() {
        return sala1;
    }
    
    public int getSala2() {
        return sala2;
    }

    public Porta_Estado getEstado() {
        return estado;
    }

    public void setEstado(Porta_Estado estado) {
        this.estado = estado;
    }
    
}
