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
import java.util.ArrayList;

/**
 *
 * @author paulo
 */
public class Jogador extends Personagem {
    
    private ArrayList<Ouro> ouro;
    private ArrayList<Diamante> diamante;
    
    public Jogador(String id) {
        super(id, 5);
        
        this.ouro = new ArrayList<>();
        this.diamante = new ArrayList<>();
    }

    public int getOuro() {
        return this.ouro.size();
    }

    public int getDiamante() {
        return this.diamante.size();
    }
    
    public int getMoeda(){
        return this.getDiamante() + this.getOuro();
    }
    
    public void incrementarOuro(Ouro ouro){
        this.ouro.add(ouro);
    }
    
    public void incrementarDiamante(Diamante diamante){
        this.diamante.add(diamante);
    }
    
    public void zerarMoeda(){
        this.ouro.clear();
        this.diamante.clear();
    }
    
    public void Listar(){
        //Nome
        System.out.println("Nome: " + this.getId());
        
        //Moeda
        System.out.printf("  Gold: %d | Diamond: %d | Total: %d\n", this.getOuro(), this.getDiamante(), this.getMoeda());
        
        //Sala atual
        System.out.println("  Sala Atual: " + this.getSalaAtual());
        
        //Próximos
        System.out.println("  Proximo a:");
        System.out.printf("    Item: %s", (this.getProximoItem() != null)?this.getProximoItem():"N/A");
        System.out.printf(" | Porta: %s\n", (this.getProximaPorta()!= null)?this.getProximaPorta():"N/A");
        
        //Itens
        System.out.printf("  %d Iten(s): ", this.getItens().size());
        for(Item item: this.getItens()){
            System.out.print("<" + item + "> ");
        }
        System.out.println();
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
