/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Item;

import Personagem.Jogador;

/**
 *
 * @author paulo
 */
public class Machado extends Item {
    
    /**
     * Usa o machado no jogador.<br><br>
     * 
     * Condições:<br>
     *  1: Jogador tem poção no inventário: A poção é perdida.<br>
     *  2: Jogador tem moeda: A moeda é zerada.<br>
     *  3: Jogador não tem poção nem moeda: Fim de jogo<br>
     * 
     * @param jogador jogador que será atingido.
     * @return {@code false}: jogador tinha poção ou moeda. Estas foram removidas
     *                        e o jogador ainda pode continuar jogando.
     *         {@code true}: jogador não tem poções nem moedas.
     */
    public Boolean Usar(Jogador jogador){
        Item pocao = jogador.getItem("potion");
        if(pocao != null){
            System.out.println(jogador.getNome() + " perdeu uma poção");
            jogador.Largar(pocao);
            return false;
        }
        
        if(jogador.getMoeda() > 0){
            System.out.println(jogador.getNome() + " perdeu todo o dinheiro");
            jogador.zerarMoeda();
            return false;
        }
        System.out.println(" ----===== GAME OVER =====----");
        System.exit(0);
        return true;
    }
    
    /**
     * Retorna o machado em forma de string.
     * 
     * Sobrecarga do método toString.
     * 
     * @return "axe"
     */
    public String toString(){
        return "axe";
    }
}
