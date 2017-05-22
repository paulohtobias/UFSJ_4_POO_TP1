/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personagem;
import Item.Chave;
import Item.Diamante;
import Item.Item;
import Item.Ouro;
import Item.Pocao;
import Mapa.Porta;
import Mapa.Porta.Porta_Estado;
import Mapa.Sala;

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
    
    public Boolean Sair(){
        //A primeira verificação é se o jogador está próximo à uma porta.
        if(this.getProximaPorta() == null){
            return false;
        }
        
        //Verifica se a porta está aberta.
        //Pode ser feito usando o método da classe Personagem.
        if(super.Sair() == true){
            return true;
        }
        
        //Se a porta estiver fechada, então ela é aberta e a função
        //da classe personagem é chamada novamente, desta vez com sucesso;
        if(this.getProximaPorta().getEstado() == Porta_Estado.FECHADA){
            this.getProximaPorta().setEstado(Porta_Estado.ABERTA);
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
    
    public Boolean Trancar(){
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
    
}
