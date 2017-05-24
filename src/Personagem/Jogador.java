/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personagem;
import Item.Chave;
import Item.Diamante;
import Item.Item;
import Item.Machado;
import Item.Ouro;
import Item.Pocao;
import java.util.ArrayList;

/**
 *
 * @author paulo
 */
public class Jogador extends Personagem {
    /**
     * Lista que controla a quantidade de ouro do jogador. Cada item da lista
     * representa 1 moeda de ouro.
     */
    private final ArrayList<Ouro> ouro;
    
    /**
     * Lista que controla a quantidade de diamante do jogador. Cada item da
     * lista representa 1 diamante.
     */
    private final ArrayList<Diamante> diamante;
    
    /**
     * Construtuor de Jogador.
     * 
     * @param nome nome identificador do jogador 
     */
    public Jogador(String nome) {
        super(nome, 5);
        
        this.ouro = new ArrayList<>();
        this.diamante = new ArrayList<>();
    }

    /**
     * Retorna a quantidade de ouro do jogador.
     * @return quantidade de elementos da lista de ouro
     */
    public int getOuro() {
        return this.ouro.size();
    }

    /**
     * Retorna a quantidade de diamante do jogador.
     * @return quantidade de elementos da lista de diamante
     */
    public int getDiamante() {
        return this.diamante.size();
    }
    
    /**
     * Retorna a quantidade de total de ouro e diamante do jogador.
     * @return soma da quantidade de elementos da lista de ouro e diamante
     */
    public int getMoeda(){
        return this.getDiamante() + this.getOuro();
    }
    
    /**
     * Aumenta em um a quantidade de ouro do jogador.
     */
    public void incrementarOuro(){
        this.ouro.add(new Ouro());
    }
    
    /**
     * Aumenta em um a quantidade de diamante do jogador.
     */
    public void incrementarDiamante(){
        this.diamante.add(new Diamante());
    }
    
    /**
     * Zera a quantidade de ouro e diamante do jogador.
     */
    public void zerarMoeda(){
        this.ouro.clear();
        this.diamante.clear();
    }
    
    /**
     * Adiciona o item que está próximo ao jogador à lista de itens (ou moeda).
     * 
     * @return o item que foi pego (será {@code null} caso o jogador não esteja
     *  próximo a nenhum item.
     */
    public Item Pegar(){
        Item item = this.getProximoItem();
        if(item == null){
            //Se o o jogador não estiver próximo a um item, então não há nada a se fazer.
            return item;
        }
        
        //Se o item for ouro ou diamante, então a o que será incrementado será
        //a lista de moeda correspondente.
        if(item instanceof Ouro){
            this.incrementarOuro();
            return item;
        }
        if(item instanceof Diamante){
            this.incrementarDiamante();
            return item;
        }
            
        //Em caso de ser item normal, então a função da classe pai é chamada.
        return super.Pegar();
    }
    
    /**
     * Jogador tenta fechar a porta que está proxima a ele. São feitas duas
     * verificações: se o jogador está próximo à uma porta e se há poções no
     * seu inventário.
     * 
     * @return {@code true} se a porta foi fechada com sucesso
     */
    public Boolean FecharPorta(){
        if(this.getProximaPorta() == null){
            return false;
        }
        
        for(Item item: this.getItens()){
            if(item instanceof Pocao){
                if(((Pocao)item).Usar(this.getProximaPorta()) == true){
                    //Se a poção for usada com sucesso, então ela é removida da
                    //lista de itens.
                    this.getItens().remove(item);
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Jogador tenta sair da sala. Se a porta está aberta ou fechada, então o
     * jogador sai sem problemas. Se a porta está trancada, então ela será aberta
     * se o jogador possui a chave da porta em questão no seu inventário.
     * 
     * @return {@code true} se foi possível sair da sala e porta será aberta
     */
    public Boolean Sair(){
        //A primeira verificação é se o jogador está próximo à uma porta.
        if(this.getProximaPorta() == null){
            return false;
        }
        
        //Verifica se a porta está aberta.
        //Pode ser feito usando o método da classe Personagem.
        if(this.getProximaPorta().estaAberta()){
            return super.Sair();
        }
        
        //Se a porta estiver fechada, então ela é aberta e a função
        //da classe Personagem é chamada;
        if(this.getProximaPorta().estaFechada()){
            this.getProximaPorta().Abrir();
            return super.Sair();
        }
        
        //Se chegar até aqui, significa que a porta está trancada.
        //Portanto, verifica se o jogador tem uma chave para abrí-la.
        int index = 0;
        for(Item item: this.getItens()){
            if(item instanceof Chave){
                if(((Chave)item).Usar(this.getProximaPorta()) == true){
                    //O jogador possuía a chave que destranca a porta.
                    //Essa chave será usada.
                    this.getItens().remove(index);
                    return super.Sair();
                }
            }
            index++;
        }
        return false;
    }
    
    /**
     * Jogador arremessa um machado. Se o jogador possui um machado no inventário,
     * então este é removido e "gasto".
     * 
     * @return {@code true} se o jogador tinha um machado para usar
     */
    public Boolean Arremessar(){
        for(Item item: this.getItens()){
            if(item instanceof Machado){
                this.getItens().remove(item);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Mostra na tela as informações do jogador. Informações como {@code nome},
     * {@code ouro}, {@code diamante}, {@code salaAtual}, {@code proximoItem},
     * {@code proximaPorta} e os itens do inventário.
     */
    public void Listar(){
        //Nome
        System.out.println("Nome: " + this.getNome());
        
        //Moeda
        System.out.printf("  Gold: %d | Diamond: %d | Total: %d\n", this.getOuro(), this.getDiamante(), this.getMoeda());
        
        //Sala atual
        System.out.println("  Sala Atual: " + this.getSalaAtual());
        
        //Próximos
        System.out.println("  Proximo a:");
        System.out.printf("    Item: %s", (this.getProximoItem() != null)?this.getProximoItem():"N/A");
        System.out.printf(" | Porta: %s\n", (this.getProximaPorta()!= null)?this.getProximaPorta():"N/A");
        
        //Itens
        System.out.printf("  %d Item(s): ", this.getItens().size());
        for(Item item: this.getItens()){
            System.out.print("<" + item + "> ");
        }
        System.out.println();
    }
}
