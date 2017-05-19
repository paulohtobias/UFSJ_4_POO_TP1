/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Item.Item;
import Personagem.Personagem;

/**
 *
 * @author paulo
 */
public class Mapa {
    private Sala[] salas;
    private final int numSalas = 20;
    
    public Mapa(){
        this.salas = new Sala[numSalas];
        for(int i=0; i<numSalas; i++){
            this.salas[i] = new Sala(i, i+1);
        }
    }
    
    public Sala[] getSalas(){
        return this.salas;
    }
    
    public Sala getSala(int salaId){
        if(salaId >=0 && salaId <20){
            return this.salas[salaId];
        }
        return null;
    }
    
    /*public void MoverJogador(){
        if(this.jogador.getProximaPorta() != null){
            this.jogador.Sair();
        }
    }*/
}
