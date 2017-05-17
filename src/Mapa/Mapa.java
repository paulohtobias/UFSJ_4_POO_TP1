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
    Sala[] salas;
    final int numSalas = 20;
    
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
    
    public void getComando(Personagem personagem, String acao){
        Sala salaAtual;
        salaAtual = this.getSala(personagem.getSalaAtual());
            
            if(acao.equals("view")){
                salaAtual.Listar();
            }
            
            if(acao.startsWith("moveTo")){
                Item item = salaAtual.getItem(acao.substring(7));
                if(item != null){
                    personagem.Mover(item);
                    System.out.println("moveu para " + personagem.getProximoItem().toString());
                }else{
                    Porta porta = salaAtual.getPorta(acao.substring(7));
                    if(porta != null){
                        personagem.Mover(porta);
                        System.out.println("moveu para " + personagem.getProximaPorta().toString());
                    }
                }
            }
    }
    
    /*public void MoverJogador(){
        if(this.jogador.getProximaPorta() != null){
            this.jogador.Sair();
        }
    }*/
}
