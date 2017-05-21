package Personagem;

import Item.Item;
import Mapa.Porta;
import Mapa.Sala;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author paulo
 */
public class Comando {
    
    static public void getComando(Sala salaAtual, Personagem personagem, String comando){
        String[] comandos = comando.trim().split("\\s+", 2);
        
        String acao = comandos[0].toLowerCase();
        
        //COMANDOS SIMPLES
        if(acao.equals("view")){
            salaAtual.Listar();
            return;
        }
        
        Boolean saida = (personagem instanceof Jogador);
        
        if(acao.equals("pickup")){
            Item pegado = personagem.Pegar();
            if(pegado != null){
                salaAtual.removerItem(pegado);
            }else if(saida == true){
                System.out.println("Lista cheia.");
            }
            return;
        }
        
        if(acao.equals("exit")){
            Boolean resultado = personagem.Sair();
            if(saida == true){
                if(resultado == true){
                    System.out.println(personagem.getId() + " se moveu para a sala " + personagem.getSalaAtual());
                }else{
                    System.out.println("Nao foi possivel sair da sala");
                }
            }
        }
        
        //COMANDOS COMPOSTOS        
        String sujeito;
        if(comandos.length > 1){
            sujeito = comandos[1].toLowerCase();
        }else{
            return;
        }
        
        if(acao.equals("moveto")){
            
            Item item = salaAtual.getItem(sujeito);
            Porta porta = salaAtual.getPorta(sujeito);
            if(item != null){
                personagem.Mover(item);
                if(saida == true){
                    System.out.println("moveu para " + personagem.getProximoItem().getTipo());
                }
                return;
            }
            if(porta != null){
                personagem.Mover(porta);
                if(saida == true){
                    System.out.println("moveu para " + sujeito);
                }
                return;
            }
            return;
        }
        
        if(acao.equals("drop")){
            Boolean resultado = personagem.Largar(sujeito);
            if(saida == true){
                if(resultado == true){
                    System.out.println("Largou " + sujeito);
                }else{
                    System.out.println("Não foi possível largar " + sujeito);
                }
            }
        }
    }
}
