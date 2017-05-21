/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Item.Item;
import Personagem.Personagem;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author paulo
 */
public class Mapa {
    private Sala[] salas;
    private final int numSalas = 20;
    
    public Mapa(){
        this("mapa1.txt");
    }
    
    public Mapa(String arquivo){
        this.salas = new Sala[numSalas];
        
        FileReader fr = null;
        BufferedReader br = null;
        
        try{
            fr = new FileReader(arquivo);
            br = new BufferedReader(fr);
            
            String linha;
            
            br = new BufferedReader(new FileReader(arquivo));
            
            for(int i=0; i<numSalas && (linha = br.readLine()) != null; i++){
                this.salas[i] = new Sala(linha);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Sala[] getSalas(){
        return this.salas;
    }
    
    public Sala getSala(int salaId){
        for(int i=0; i<this.numSalas; i++){
            if(this.salas[i].getId() == salaId){
                return this.salas[i];
            }
        }
        return null;
    }
    
    /*public void MoverJogador(){
        if(this.jogador.getProximaPorta() != null){
            this.jogador.Sair();
        }
    }*/
}
