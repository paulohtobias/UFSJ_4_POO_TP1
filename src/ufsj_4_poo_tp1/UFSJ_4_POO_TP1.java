/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufsj_4_poo_tp1;

import Mapa.Mapa;
import Mapa.Sala;
import Personagem.Comando;
import Personagem.Jogador;
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
    public static void main1(String[] args) {        
        Scanner scan = new Scanner(System.in);
        
        Mapa mapa = new Mapa("mapa1.txt");

        String acao;
        while(mapa.getJogador().getSalaAtual() != 0){ //loop principal            
            //jogador.Listar();
            System.out.printf("%s> ", mapa.getJogador().getId());
            acao = scan.nextLine();

            Comando.getComando(mapa, mapa.getJogador(), acao);
        }
        scan.close();
    }
    
    public static void main(String[] args) {        
        Scanner scan = new Scanner(System.in);
        
        Mapa mapa = new Mapa("mapa1.txt");
        
        /*System.out.print("Nome do Troll: ");
        String nome = scan.nextLine();*/
        Troll troll = mapa.getTroll("Troll 0");
        troll.setSalaAtual(7);

        String acao;
        while(troll.getSalaAtual() != 0){ //loop principal            
            //jogador.Listar();
            mapa.Listar(troll.getSalaAtual());
            System.out.println("Acao recomendada: " + troll.agir(mapa.getSala(troll.getSalaAtual()), mapa.getJogador()));
            System.out.printf("%s> ", troll.getId());
            acao = scan.nextLine();

            Comando.getComando(mapa, troll, acao);
        }
        scan.close();
    }
}
