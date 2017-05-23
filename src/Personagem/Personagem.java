/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personagem;

import Item.Chave;
import Item.Item;
import Mapa.Porta;
import Mapa.Sala;
import java.util.ArrayList;

/**
 *
 * @author paulo
 */
public class Personagem {
    private String id;
    private int maxItens;
    private ArrayList<Item> itens;
    private Item proximoItem;
    private Porta proximaPorta;
    private int salaAtual = 1;
    
    public Personagem(String id, int maxItens){
        this.id = id;
        this.maxItens = maxItens;
        this.itens = new ArrayList<>();
        this.proximoItem = null;
        this.proximaPorta = null;
    }

    public String getId() {
        return this.id;
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
    
    public void removerItem(Item item){
        this.itens.remove(item);
    }
    
    public Item getProximoItem(){
        return this.proximoItem;
    }
    
    public void setProximoItem(Item item){
        this.proximoItem = item;
    }
    
    public Porta getProximaPorta(){
        return this.proximaPorta;
    }

    public int getSalaAtual() {
        return salaAtual;
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
    
    public Boolean Largar(String str_item){
        for(Item i: this.itens){
            if(str_item.equals(i.toString()) == true){
                this.itens.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public Boolean Sair(){
        if(this.getProximaPorta() == null){
            return false;
        }
        if(this.getProximaPorta().getEstado() == Porta.Porta_Estado.ABERTA){
            this.salaAtual = this.getProximaPorta().getSala2(this.salaAtual);
            this.proximaPorta = null;
            this.proximoItem = null;
            return true;
        }
        return false;
    }
    
    public Boolean Usar(String str_item){
        return false;
    }
    
    public Boolean Trancar(){
        return false;
    }
}
