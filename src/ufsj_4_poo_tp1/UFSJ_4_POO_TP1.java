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
    public static void main2(String[] args){
        String str = "1: (6,2) (,) (A,B)";
        int i, j;
        int id_size = str.indexOf(':');
        int id = Integer.parseInt(str.substring(0, id_size));
        
        int salas_sizeI = str.indexOf('(', id_size) + 1;
        int salas_sizeF = str.indexOf(')', salas_sizeI);
        String[] salas = str.substring(salas_sizeI, salas_sizeF).split("(\\D)+");
        
        int chaves_tamI = str.indexOf('(', salas_sizeF) + 1;
        int chaves_tamF = str.indexOf(')', chaves_tamI);
        String[] chaves = str.substring(chaves_tamI, chaves_tamF).split("(\\D)+");
        for(String s: chaves){
            System.out.println("<" + s + ">");
        }
        
        //Identificando as portas trancadas.
        int trancadas_tamI = str.indexOf('(', chaves_tamF) + 1;
        int trancadas_tamF = str.indexOf(')', trancadas_tamI);
        String[] trancadas = str.substring(trancadas_tamI, trancadas_tamF).split(",");
    }
    
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
