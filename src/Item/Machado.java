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
    public void Usar (Jogador jogador){
        if(jogador.getMoeda() > 0){
            jogador.zerarMoeda();
        }else{
            System.out.println(" ----===== GAME OVER AQUI ????? =====-----");
        }
    }
    
    public String toString(){
        return "axe";
    }
}
