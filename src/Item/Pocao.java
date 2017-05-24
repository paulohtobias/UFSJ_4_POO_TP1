/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Item;

import Mapa.Porta;

/**
 *
 * @author paulo
 */
public class Pocao extends Item {
    /**
     * Tenta fechar a {@code porta}.
     * 
     * @param porta A porta que será fechada
     * @return {@code true} se a porta estava aberta e foi possível fechá-la.
     */
    public Boolean Usar(Porta porta){
        if(porta.estaAberta()){
            porta.Fechar();
            return true;
        }
        return false;
    }
    
    /**
     * Retorna a poção em forma de string.
     * 
     * Sobrecarga do método toString.
     * 
     * @return "potion"
     */
    public String toString(){
        return "potion";
    }
}
