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
        
        for(String s: comandos){
            System.out.println(s);
        }
        
        String acao = comandos[0].toLowerCase();
        String sujeito;
        
        if(acao.equals("view")){
            salaAtual.Listar();
            return;
        }
        
        if(acao.equals("pickup")){
            Item pegado = personagem.Pegar();
            if(pegado != null){
                salaAtual.removerItem(pegado);
            }else{
                System.out.printf("Lista cheia.");
            }
            return;
        }
        
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
                System.out.println("moveu para " + personagem.getProximoItem().getTipo());
                return;
            }
            if(porta != null){
                personagem.Mover(porta);
                System.out.println("moveu para " + sujeito);
                return;
            }
            return;
        }
        
        if(acao.equals("drop")){
            System.out.println("Lista itens");
            for(Item asd: personagem.getItens()){
                System.out.println(asd.getTipo());
            }
            return;
            
            /*if(personagem.Largar(sujeito) == true){
                System.out.println("====LARGOU====");
            }else{
                System.out.println("==NAO LARGOU==");
            }*/
        }
    }
}
