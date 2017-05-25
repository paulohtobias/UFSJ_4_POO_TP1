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
    
    /**
     * Construtor da Classe Personagem. Cria um novo personagem com {@code nome}
     * e cria um inventário que suporta até {@code maxItens}.
     * 
     * @param nome nome identificador do personagem
     * @param maxItens número máximo de itens no inventário
     */
    public Personagem(String nome, int maxItens){
        this.nome = nome;
        this.maxItens = maxItens;
        this.itens = new ArrayList<>();
        this.proximoItem = null;
        this.proximaPorta = null;
    }

    /**
     * Retorna o nome do personagem.
     * 
     * @return {@code nome}
     */
    public String getNome() {
        return this.nome;
    }
    
    /**
     * Retorna a capacidade máxima do inventário.
     * 
     * @return {@code maxItens}
     */
    public int getMaxItens() {
        return this.maxItens;
    }

    /**
     * Retorna o inventário do personagem.
     * 
     * @return {@code ArrayList<Item>} com todos os itens no inventário do
     *         personagem.
     */
    public ArrayList<Item> getItens() {
        return this.itens;
    }
    
    /**
     * Retorna o item cujo nome é correspondente à {@code item_str}, caso exista
     * um no inventário. Pode retornar {@code null}.
     * 
     * @param item_str {@code String} a ser comparada com cada item do inventário.
     * @return o {@code Item} que foi encontrado ou {@null}
     */
    public Item getItem(String item_str){
        for(Item item: this.getItens()){
            if(item.toString().startsWith(item_str)){
                return item;
            }
        }
        return null;
    }
    
    /**
     * Retorna a sala atual do personagem.
     * 
     * @return {@code salaAtual}
     */
    public int getSalaAtual() {
        return salaAtual;
    }
    
    /**
     * Retorna o Item ao qual o personagem está próximo.
     * 
     * @return {@code Item} ou {@code null}, caso o personagem não esteja próximo
     *         a um item
     */
    public Item getProximoItem(){
        return this.proximoItem;
    }
    
    /**
     * Retorna a Porta ao qual o personagem está próximo.
     * 
     * @return {@code Porta} ou {@code null}, caso o personagem não esteja próximo
     *         a uma porta
     */
    public Porta getProximaPorta(){
        return this.proximaPorta;
    }
    
    /**
     * Altera a sala do personagem.
     * 
     * @param salaAtual nova sala para qual o personagem estará
     */
    public void setSalaAtual(int salaAtual){
        this.salaAtual = salaAtual;
    }

    /**
     * Move o personagem para perto de um item.
     * @param item item que estará próximo ao personagem
     */
    public void Mover(Item item){
        this.proximoItem = item;
        this.proximaPorta = null;
    }
    
    /**
     * Move o personagem para perto de uma porta
     * @param porta porta que estará próximo ao personagem
     */
    public void Mover(Porta porta){
        this.proximaPorta = porta;
        this.proximoItem = null;
    }

    /**
     * Pega o item que está próximo ao personagem, caso haja algum e o
     * inventário aceite mais um item.
     * 
     * @return o {@code Item} que foi pego. Retorna {@code null} se não
     *         for possível pegar
     */
    public Item Pegar(){
        if( (this.itens.size() >= this.getMaxItens()) || (this.getProximoItem() == null) ){
            return null;
        }
        
        this.itens.add(this.getProximoItem());
        Item item = this.getProximoItem();
        this.proximoItem = null;
        return item;
    }
    
    /**
     * Remove o item do inventário do personagem.
     * 
     * @param item a ser removido
     */
    public void Largar(Item item){
        this.itens.remove(item);
    }
    
    /**
     * Remove e retorna o item do inventário do personagem.
     * 
     * @param str_item {@code String} representando o item
     * @return O item removido. Pode ser {@code null}
     */
    public Item Largar(String str_item){
        Item item = this.getItem(str_item);
        this.itens.remove(item);
        return item;
    }
    
    /**
     * Move o personagem de uma sala para outra. O movimento só é feito se
     * o personagem está perto de uma porta e esta está aberta.
     * 
     * @return {@code true} se foi possível sair da porta
     */
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
