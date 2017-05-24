/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personagem;

import Item.Item;
import Mapa.Porta;
import java.util.ArrayList;

/**
 *
 * @author paulo
 */
public class Personagem {
    /**
     * Nome identificador do personagem.
     */
    final private String nome;
    
    /**
     * Quantidade máxima de itens que o personagem pode carregar.
     */
    final private int maxItens;
    
    /**
     * Lista de itens do usuário.
     */
    final private ArrayList<Item> itens;
    
    /**
     * Sala atual do personagem.
     */
    private int salaAtual = 1;
    
    /**
     * {@code Item} que o personagem está próximo.
     */
    protected Item proximoItem;
    
    /**
     * {@code Porta} que o personagem está próximo.
     */
    protected Porta proximaPorta;
    
    public Personagem(String id, int maxItens){
        this.nome = id;
        this.maxItens = maxItens;
        this.itens = new ArrayList<>();
        this.proximoItem = null;
        this.proximaPorta = null;
    }

    public String getNome() {
        return this.nome;
    }
    
    public int getMaxItens() {
        return this.maxItens;
    }

    public ArrayList<Item> getItens() {
        return this.itens;
    }
    
    public Item getItem(String item_str){
        for(Item item: this.getItens()){
            if(item.toString().startsWith(item_str)){
                return item;
            }
        }
        return null;
    }
    
    public int getSalaAtual() {
        return salaAtual;
    }
    
    public Item getProximoItem(){
        return this.proximoItem;
    }
    
    public Porta getProximaPorta(){
        return this.proximaPorta;
    }
    
    public void setSalaAtual(int salaAtual){
        this.salaAtual = salaAtual;
    }

    public void Mover(Item item){
        this.proximoItem = item;
        this.proximaPorta = null;
    }
    
    public void Mover(Porta porta){
        this.proximaPorta = porta;
        this.proximoItem = null;
    }

    public Item Pegar(){
        if( (this.itens.size() >= this.getMaxItens()) || (this.getProximoItem() == null) ){
            return null;
        }
        
        this.itens.add(this.getProximoItem());
        Item item = this.getProximoItem();
        this.proximoItem = null;
        return item;
    }
    
    public void Largar(Item item){
        this.itens.remove(item);
    }
    
    public Item Largar(String str_item){
        Item item = this.getItem(str_item);
        this.itens.remove(item);
        return item;
    }
    
    public Boolean Sair(){
        if(this.getProximaPorta() == null){
            return false;
        }
        if(this.proximaPorta.estaAberta()){
            this.salaAtual = this.getProximaPorta().getSala2(this.salaAtual);
            this.proximaPorta = null;
            this.proximoItem = null;
            return true;
        }
        this.proximaPorta = null;
        return false;
    }
}
