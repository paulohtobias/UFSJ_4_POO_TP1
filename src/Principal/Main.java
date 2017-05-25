/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Mapa.Mapa;
import Personagem.Comando;
import Personagem.Troll;
import java.util.Scanner;

/**
 *
 * @author paulo
 */
public class Main {

    /**
     * @param args the command line arguments
     */    
    public static void main(String[] args) {
        Mapa mapa = new Mapa("mapa1.txt");
        
        //Mostrando os comandos na tela
        Comando.getComando(mapa, mapa.getJogador(), "help");

        Scanner scan = new Scanner(System.in);
        String acao = "";
        while(mapa.getJogador().getSalaAtual() != 0){ //loop principal
            
            //Testando o autoView
            if(Comando.autoView == true && acao.equals("exit")){
                System.out.println("AUTO VIEW");
                Comando.getComando(mapa, mapa.getJogador(), "view");
            }
            
            //Lendo o comando do jogador
            System.out.printf("%s> ", mapa.getJogador().getNome());
            acao = scan.nextLine();
            Boolean acaoValida = Comando.getComando(mapa, mapa.getJogador(), acao);
            
            //Se o jogador utilizar os comandos view, help ou logtroll, então o
            //turno não é contado e os trolls não farão sua jogada.
            if(acaoValida){
                //Jogadas dos trolls
                for(Troll troll: mapa.getTrolls()){
                    Comando.getComando(mapa, troll, troll.gerarComando(mapa.getSala(troll.getSalaAtual()), mapa.getJogador()));
                }
            }
        }
        scan.close();
    }
}
