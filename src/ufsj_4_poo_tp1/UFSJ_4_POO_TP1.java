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
        
        String nome = scan.next();
        Jogador jogador = new Jogador(nome);
        
        String acao;
        while(true){ //loop principal
            acao = scan.nextLine();
            salaAtual = mapa.getSala(jogador.getSalaAtual());
            
            if(acao.equals("view")){
                salaAtual.Listar();
            }
            
            if(acao.startsWith("moveTo")){
                Item item = salaAtual.getItem(acao.substring(7));
                if(item != null){
                    jogador.Mover(item);
                    System.out.println("moveu para " + jogador.getProximoItem().toString());
                }else{
                    Porta porta = salaAtual.getPorta(acao.substring(7));
                    if(porta != null){
                        jogador.Mover(porta);
                        System.out.println("moveu para " + jogador.getProximaPorta().toString());
                    }
                }
            }
        }
    }

}
