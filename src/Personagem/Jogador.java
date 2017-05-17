/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personagem;
import Item.Diamante;
import Item.Ouro;

/**
 *
 * @author paulo
 */
public class Jogador extends Personagem {
    
    private Ouro ouro;
    private Diamante diamante;
    
    public Jogador(String id) {
        super(id, 5);
        
        this.ouro = new Ouro();
        this.diamante = new Diamante();
    }

    public int getOuro() {
        return ouro.getQuantidade();
    }

    public int getDiamante() {
        return diamante.getQuantidade();
    }
    
    public void zerarMoeda(){
        this.ouro.zerar();
        this.diamante.zerar();
    }
    
}
