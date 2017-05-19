/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufsj_4_poo_tp1;

import Item.Item;
import Mapa.Sala;
import Mapa.Mapa;
import Mapa.Porta;
import Personagem.Comando;
import Personagem.Jogador;
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
        Scanner scan = new Scanner(System.in);
        
        Mapa mapa = new Mapa();
        Sala salaAtual;
        
        System.out.print("Nome do jogador: ");
        String nome = scan.nextLine();
        Jogador jogador = new Jogador(nome);

        String acao;
        while(true){ //loop principal
            System.out.printf("%s> ", jogador.getId());
            acao = scan.nextLine();
            salaAtual = mapa.getSala(jogador.getSalaAtual());

            Comando.getComando(salaAtual, jogador, acao);
        }
    }
}
