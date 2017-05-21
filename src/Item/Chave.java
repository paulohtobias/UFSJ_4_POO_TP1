/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Item;

import Mapa.Porta;
import Mapa.Porta.Porta_Estado;
import Mapa.Sala;

/**
 *
 * @author paulo
 */
public class Chave extends Item {
    int sala1;
    int sala2;
    
    public Chave(int sala1, int sala2){
        this.sala1 = sala1;
        this.sala2 = sala2;
    }

    public int getSala1() {
        return sala1;
    }

    public int getSala2() {
        return sala2;
    }
    
    public Boolean Usar(Porta porta){
        if(porta != null){
            if(this.sala1 == porta.getSala1() && this.sala2 == porta.getSala2()){
                porta.setEstado(Porta_Estado.ABERTA);
                return true;
            }
            if(this.sala1 == porta.getSala2() && this.sala2 == porta.getSala1()){
                porta.setEstado(Porta_Estado.ABERTA);
                return true;
            }
        }
        return false;
    }
    
    public String toString(){
        return String.format("key %d-%d", this.sala1, this.sala2);
    }
}
