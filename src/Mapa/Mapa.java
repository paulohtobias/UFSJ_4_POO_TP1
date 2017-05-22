/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Item.Chave;
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
    private final int numSalas = 21;
    
    public Mapa(){
        this("mapa1.txt");
    }
    
    public Mapa(String arquivo){
        this.salas = new Sala[numSalas];
        for(int i=0; i<numSalas; i++){
            this.salas[i] = new Sala(i);
        }
        
        FileReader fr = null;
        BufferedReader br = null;
        
        try{
            fr = new FileReader(arquivo);
            br = new BufferedReader(fr);
            
            String linha;
            
            br = new BufferedReader(new FileReader(arquivo));
            
            for(int i=0; i<numSalas && (linha = br.readLine()) != null; i++){
                
                //Identificando a primeira sala.
                int id_tam = linha.indexOf(':');
                int sala1_id = Integer.parseInt(linha.substring(0, id_tam));
                
                //Identificando as salas que serão conectadas.
                int salas_tamI = linha.indexOf('(', id_tam) + 1;
                int salas_tamF = linha.indexOf(')', salas_tamI);
                String[] salas_str = linha.substring(salas_tamI, salas_tamF).split(",");
                String[] porta1_id = {"A", "B", "C"};
                int j=0;
                for(String sala2_str: salas_str){
                    int sala2_id = Integer.parseInt(sala2_str);
                    this.getSala(sala1_id).Conectar(porta1_id[j++], this.getSala(sala2_id));
                }
                
                //Trancando as portas.
                int trancadas_tamI = linha.indexOf('(', salas_tamF) + 1;
                int trancadas_tamF = linha.indexOf(')', trancadas_tamI);
                String[] trancadas = linha.substring(trancadas_tamI, trancadas_tamF).split(",");
                for(String tranca: trancadas){
                    switch(tranca){
                        case "A":
                            this.getSala(sala1_id).getPortaA().setEstado(Porta.Porta_Estado.TRANCADA);
                            break;
                        case "B":
                            this.getSala(sala1_id).getPortaB().setEstado(Porta.Porta_Estado.TRANCADA);
                            break;
                        case "C":
                            this.getSala(sala1_id).getPortaC().setEstado(Porta.Porta_Estado.TRANCADA);
                            break;
                    }
                }
                
                //Adicionando as chaves à lista de itens da sala.
                this.getSala(sala1_id).addChaves(linha.substring(trancadas_tamF));
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
