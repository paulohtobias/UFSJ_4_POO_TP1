package Personagem;

import Item.Item;
import Mapa.Mapa;
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
    
    static public void getComando(Mapa mapa, Personagem personagem, String comando){
        int salaAtual_id = personagem.getSalaAtual();
        Sala salaAtual = mapa.getSala(salaAtual_id);
        
        String[] comandos = comando.trim().split("\\s+", 2);
        
        String acao = comandos[0].toLowerCase();
        String sujeito = null;
        if(comandos.length > 1){
            sujeito = comandos[1].toLowerCase();
        }
        
        //COMANDOS SIMPLES
        if(acao.equals("view")){
            mapa.Listar(salaAtual_id);
            return;
        }
        
        Boolean jogador = (personagem instanceof Jogador);
        Boolean resultado;
        
        if(acao.equals("pickup")){
            if(sujeito != null){
                getComando(mapa, personagem, "moveto " + sujeito);
            }
            Item pegado = personagem.Pegar();
            if(pegado != null){
                salaAtual.removerItem(pegado);
            }else if(jogador == true){
                System.out.println("Lista cheia.");
            }
            return;
        }
        
        if(acao.equals("lock")){
            if(personagem.getProximaPorta() == null){
                System.out.println("Nao esta proximo a uma sala");
                return;
            }
            Sala sala2 = mapa.getSala(personagem.getProximaPorta().getSala2(personagem.getSalaAtual()));
            resultado = personagem.Trancar();
            if(resultado == true){
                System.out.printf("Porta %d-%d trancada\n", salaAtual.getId(), sala2.getId());
            }else{
                System.out.printf("A porta %d-%d nao pode ser trancada\n", salaAtual.getId(), sala2.getId());
            }
        }
        
        if(acao.equals("exit")){
            resultado = personagem.Sair();
            if(jogador == true){
                if(resultado == true){
                    System.out.println(personagem.getId() + " se moveu para a sala " + personagem.getSalaAtual());
                }else{
                    System.out.println("Nao foi possivel sair da sala");
                }
                return;
            }else{
                //O personagem é um Troll.
                if(resultado == true){
                    
                    System.out.println(personagem.getId() + " se moveu para a sala " + personagem.getSalaAtual());
                }else{
                    System.out.println("Nao foi possivel sair da sala");
                }
                return;
            }
        }
        
        if(acao.equals("quit")){
            System.exit(0);
        }
        
        //COMANDOS COMPOSTOS        
        if(sujeito == null){
            return;
        }
        
        if(acao.equals("moveto")){
            
            Item item = salaAtual.getItem(sujeito);
            Porta porta = salaAtual.getPorta(sujeito);
            if(item != null){
                personagem.Mover(item);
                if(jogador == true || true){
                    System.out.println("moveu para " + personagem.getProximoItem().toString());
                }
                return;
            }
            if(porta != null){
                personagem.Mover(porta);
                if(jogador == true || true){
                    System.out.println("moveu para " + sujeito);
                }
                return;
            }
            return;
        }
        
        if(acao.equals("drop")){
            Item item = personagem.Largar(sujeito);
            if(item != null){
                salaAtual.addItem(item);
                System.out.println("Largou " + sujeito);
            }else{
                System.out.println("Não foi possível largar " + sujeito);
            }
        }
        
        if(acao.equals("throwaxe")){
            if(jogador == true){
                ((Jogador)personagem).Arremessar();
                Troll troll = mapa.getTroll( mapa.trollsSala(personagem.getSalaAtual()), sujeito);
                if(troll != null){
                    mapa.removerTroll(troll);
                    System.out.println(personagem.getId() + " matou " + troll.getId());
                }else{
                    System.out.println(sujeito + " não existe.");
                }
            }else{
                resultado = ((Troll)personagem).Arremessar(mapa.getJogador());
                if(resultado == true){
                    System.out.println(personagem.getId() + " atacou você!!\n");
                }
            }
        }
    }
}
