/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Item;

import Personagem.Jogador;
import Personagem.Troll;

/**
 *
 * @author paulo
 */
public class Machado extends Item {
    public Boolean Usar (Jogador personagem){
        Item pocao = personagem.getItem("potion");
        if(pocao != null){
            personagem.removerItem(pocao);
            return false;
        }
        
        if(personagem.getMoeda() > 0){
            personagem.zerarMoeda();
            return false;
        }
        
        System.out.println(" ----===== GAME OVER AQUI ????? =====-----");
        return true;
    }
    
    public String toString(){
        return "axe";
    }
}
