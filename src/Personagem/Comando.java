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
    
    /**
     * Se os comandos dos trolls serão mostrados na tela ou silenciosos
     */
    static Boolean logTroll = false;
    
    /**
     * String com uma ajuda de cada comando
     */
    static String help = 
        "Comandos\n\n" +
        " help              Mostra este menu de ajuda\n" +
        " view:             Mostra as informações do jogador, e sala\n" +
        " moveTo OBJETO     Posiciona o personagem próximo à OBJETO\n" +
        " pickup            Pega o ITEM que está próximo ao personagem\n" +
        " drop ITEM         Retira ITEM do inventário e o deixa no chão da sala\n" + 
        " close             Fecha a PORTA que está próxima ao jogador, caso tenha poções no inventário\n" +
        " exit              Sai da sala caso o jogador esteja próximo a uma porta e esta possa ser/está aberta\n" +
        " throwAxe TROLL    Joga um machado em TROLL caso este esteja na mesma sala que o jogador\n" +
        " logTroll VALOR    Liga/Desliga as mensagens dos trolls. VALUE = liga/desliga\n" +
        " quit              Encerra o jogo\n\n";
    
    /**
     * Recebe o comando de um personagem e processa os eventos.
     * 
     * @param mapa o mapa onde as ações ocorrem
     * @param personagem o personagem (Jogador ou Troll) que fez a ação
     * @param comando o comando a ser processado
     */
    static public void getComando(Mapa mapa, Personagem personagem, String comando){
        //Valores que serão usados algumas vezes na função.
        int salaAtual_id = personagem.getSalaAtual();
        Sala salaAtual = mapa.getSala(salaAtual_id);
        
        //Divide o comando em duas partes. "ação" e "objeto"
        String[] comandos = comando.trim().split("\\s+", 2);
        
        String acao = comandos[0].toLowerCase();
        String objeto = null;
        if(comandos.length > 1){
            objeto = comandos[1].toLowerCase();
        }
        
        //COMANDOS SIMPLES        
        if(acao.equals("view")){
            mapa.Listar(salaAtual_id);
            return;
        }
        
        //Verifica se o persongaem é jogador ou troll
        Boolean log = (personagem instanceof Jogador) || logTroll;
        //Guarda o resultado de algumas ações
        Boolean resultado;
        
        if(acao.equals("pickup")){
            if(objeto != null){
                //Se o comando foi dado como pickup item, então o comando
                //moveTo item é gerado implicitamente.
                getComando(mapa, personagem, "moveto " + objeto);
            }
            Item pegado = personagem.Pegar();
            if(pegado != null){
                salaAtual.removerItem(pegado);
                if(log == true){
                    System.out.println(personagem.getNome() + " pegou " + pegado);
                }
            }else if(log == true){
                System.out.println("Lista cheia.");
            }
            return;
        }
        
        if(acao.equals("close")){
            if(personagem.getProximaPorta() == null){
                System.out.println("Nao esta proximo a uma sala");
                return;
            }
            Sala sala2 = mapa.getSala(personagem.getProximaPorta().getSala2(personagem.getSalaAtual()));
            resultado = ((Jogador)personagem).FecharPorta();
            if(resultado == true){
                System.out.printf("Porta %d-%d trancada\n", salaAtual.getId(), sala2.getId());
            }else{
                System.out.printf("A porta %d-%d nao pode ser trancada\n", salaAtual.getId(), sala2.getId());
            }
        }
        
        if(acao.equals("exit")){
            resultado = personagem.Sair();
            if(log == true){
                if(resultado == true){
                    System.out.println(personagem.getNome() + " se moveu para a sala " + personagem.getSalaAtual());
                }else{
                    System.out.println(personagem.getNome() + " nao conseguiu sair da sala");
                }
            }
            return;
        }
        
        if(acao.equals("help")){
            System.out.println(help);
            return;
        }
        
        if(acao.equals("quit")){
            System.exit(0);
        }
        
        //COMANDOS COMPOSTOS        
        if(objeto == null){
            return;
        }
        
        if(acao.equals("moveto")){
            
            Item item = salaAtual.getItem(objeto);
            Porta porta = salaAtual.getPorta(objeto);
            if(item != null){
                personagem.Mover(item);
                if(log == true){
                    System.out.println(personagem.getNome() + " está próximo de " + personagem.getProximoItem());
                }
                return;
            }
            if(porta != null){
                personagem.Mover(porta);
                if(log == true){
                    System.out.println(personagem.getNome() + " está próximo de " + personagem.getProximaPorta());
                }
            }
            return;
        }
        
        if(acao.equals("drop")){
            Item item = personagem.Largar(objeto);
            if(item != null){
                salaAtual.addItem(item);
                System.out.println("Largou " + objeto);
            }else{
                System.out.println("Não foi possível largar " + objeto);
            }
        }
        
        if(acao.equals("throwaxe")){
            if(personagem instanceof Jogador){
                //Busca o troll que foi atacado
                Troll troll = mapa.getTroll( mapa.trollsSala(personagem.getSalaAtual()), objeto);
                
                ((Jogador)personagem).Arremessar();
                if(troll != null){
                    mapa.removerTroll(troll);
                    System.out.println(personagem.getNome() + " matou " + troll.getNome());
                }else{
                    System.out.println(objeto + " não existe.");
                }
            }else{
                System.out.println(personagem.getNome() + " atacou você!!\n");
                resultado = ((Troll)personagem).Arremessar(mapa.getJogador());
            }
        }
        
        if(acao.equals("logtroll")){
            if(objeto.equals("liga")){
                logTroll = true;
            }else if(objeto.equals("desliga")){
                logTroll = false;
            }
            return;
        }
    }
}
