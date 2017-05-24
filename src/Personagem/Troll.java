/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personagem;

import Item.Item;
import Item.Machado;
import Mapa.Sala;

/**
 *
 * @author paulo
 */
public class Troll extends Personagem {
    
    /**
     * Construtor da classe troll.
     * 
     * @param nome nome identificador do troll
     * @param salaInicial sala onde o troll começará
     */
    public Troll(String nome, int salaInicial) {
        super(nome, 1);
        this.setSalaAtual(salaInicial);
    }
    
    /**
     * Verifica se o troll possui e tenta usá-lo em {@code jogador}.
     * 
     * @param jogador o alvo do troll
     * @return {@code true} se o troll tinha machado para arremessar no jogador
     */
    public Boolean Arremessar(Jogador jogador){
        Item machado = this.getItem("axe");
        if(machado != null){
            ((Machado)machado).Usar(jogador);
            this.getItens().remove(machado);
            return true;
        }
        return false;
    }
    
    /**
     * Pequena IA para os trolls. Gera seus comandos automaticamente baseados
     * na {@code salaAtual} e no {@code jogador}.
     * 
     * @param salaAtual {@code Sala} onde o troll está.
     * @param jogador jogador inimigo
     * @return uma string no mesmo formato utilizado pelo jogador que será
     *         enviada para a função {@code getComando}
     */
    public String gerarComando(Sala salaAtual, Jogador jogador){
        //Verificando se o troll já possui um machado.
        Item machado = this.getItem("axe");
        
        if( (machado == null) ){
            //Troll não possui machado.
            if(this.getProximoItem() == null){
                //E não está próximo de um.
                if((salaAtual.getItem("axe") != null)){
                    //Tem um machado na sala.
                    return "moveTo axe";
                }
            }else{
                return "pickup";
            }
        }else{
            if((this.getSalaAtual() == jogador.getSalaAtual())){
                //Troll e jogador estão na mesma sala.
                return "throwAxe " + jogador.getNome();
            }
        }
            
        //Se não puder fazer mais nada, então tenta sair da sala.
        if(this.getProximaPorta() != null){
            return "exit";
        }else{
            return "moveTo " + salaAtual.getPortaAleatoria();
        }
    }
    
    /**
     * Método de comparação entre dois trolls. Compara dois trolls pelo nome
     * 
     * @param troll troll que terá seu nome comparado
     * @return {@code true} se os trolls têm nomes iguais
     */
    public Boolean equals(Troll troll){
        return (this.getNome().equals(troll.getNome()));
    }
}
