/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufsj_4_poo_tp1;

import Mapa.Mapa;
import Personagem.Comando;
import Personagem.Troll;
import java.util.Scanner;

/**
 *
 * @author paulo
 */
public class UFSJ_4_POO_TP1 {

    /**
     * @param args the command line arguments
     */    
    public static void main(String[] args) {
        Mapa mapa = new Mapa("mapa1.txt");

        Scanner scan = new Scanner(System.in);
        String acao;
        while(mapa.getJogador().getSalaAtual() != 0){ //loop principal            
            //jogador.Listar();
            System.out.printf("%s> ", mapa.getJogador().getNome());
            acao = scan.nextLine();
            Comando.getComando(mapa, mapa.getJogador(), acao);
            
            //Se o jogador utilizar os comandos view ou help, então o turno não
            //é contado e os trolls não farão sua jogada.
            if(! (acao.equals("view") || acao.equals("help")) ){
                //Jogadas dos trolls
                for(Troll troll: mapa.getTrolls()){
                    Comando.getComando(mapa, troll, troll.gerarComando(mapa.getSala(troll.getSalaAtual()), mapa.getJogador()));
                }
            }
        }
        scan.close();
    }
}
