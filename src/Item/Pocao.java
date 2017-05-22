/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Item;

import Mapa.Porta;
import Mapa.Sala;

/**
 *
 * @author paulo
 */
public class Pocao extends Item {
    public Boolean Usar(Porta porta){
        if(porta.getEstado() == Porta.Porta_Estado.ABERTA){
            porta.setEstado(Porta.Porta_Estado.FECHADA);
            return true;
        }
        return false;
    }
    
    public String toString(){
        return "potion";
    }
}
