/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personagem;

import Item.Item;
import Item.Machado;
import Mapa.Sala;
import java.util.Random;

/**
 *
 * @author paulo
 */
public class Troll extends Personagem {
    
    public Troll(String id, int salaInicial) {
        super(id, 1);
        this.setSalaAtual(salaInicial);
    }
    
    public Boolean equals(Troll troll){
        return (this.getId().equals(troll.getId()));
    }
    
    public Boolean Arremessar(Jogador jogador){
        Item machado = this.getItem("axe");
        if(machado != null){
            ((Machado)machado).Usar(jogador);
            this.getItens().remove(machado);
            return true;
        }
        return false;
    }
    
    public String agir(Sala salaAtual, Jogador jogador){
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
                return "throwAxe " + jogador.getId();
            }
        }
            
        //Se não puder fazer mais nada, então tenta sair da sala.
        if(this.getProximaPorta() != null){
            return "exit";
        }else{
            return "moveTo " + salaAtual.getPortaAleatoria();
        }
    }
}
