/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Item;

/**
 *
 * @author paulo
 */

public class Moeda extends Item {
    private int quantidade;
    
    public Moeda(){
        this.quantidade = 0;
    }
    
    public int getQuantidade(){
        return this.quantidade;
    }
    
    public void incrementar(int quantidade){
        this.quantidade+=quantidade;
    }
    public void decrementar(int quantidade){
        this.quantidade-=quantidade;
    }
    public void zerar(){
        this.quantidade = 0;
    }
}
