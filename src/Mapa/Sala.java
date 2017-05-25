/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Item.Chave;
import Item.Diamante;
import Item.Item;
import Item.Machado;
import Item.Ouro;
import Item.Pocao;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author paulo
 */
public class Sala {
    /**
     * Número identificador da sala.
     */
    private final int id;
    
    /**
     * Porta A da sala.
     */
    private Porta portaA = null;
    
    /**
     * Porta B da sala.
     */
    private Porta portaB = null;
    
    /**
     * Porta C da sala.
     */
    private Porta portaC = null;
    
    /**
     * Lista de itens que estão na sala.
     */
    private final ArrayList<Item> itens;
    
    /**
     * Construtor de Sala. Cria uma sala vazia e adiciona alguns itens aleatórios
     * 
     * @param id número identificador da sala
     */
    public Sala(int id){
        this.id = id;
        this.itens = new ArrayList<>();
        this.setItens();
    }
    
    /**
     * Liga a sala à {@code sala2} pela porta {@code porta1_id}. Precisa ser
     * chamada a partir da sala 2 para conectar-se com a sala 1. Esta função
     * garante que ambas as salas compartilhem a mesma porta. Portanto, trancar
     * a porta da sala 1 que a conecta à sala 2 significa que a porta da sala 2
     * que dá acesso à sala 1 também estará trancada sem precisar de código
     * adicional.
     * <br>
     * Exemplo: {@code sala1.Conectar("A", sala5)} conectará a sala 1 à sala 5
     * através da porta A da sala A. Quanto à sala 5, se a porta que a conecta
     * à sala 1 já existia, então as duas portas são associadas. Caso contrário,
     * uma nova porta é criada e o acesso é possível apenas da sala 1 para a 5.
     * Para ligar a sala 5 à sala 1, é preciso chamar a função a partir da sala
     * 5 e especificar qual porta da sala 5 levará à sala 1
     * 
     * @param porta1_id qual porta da sala estará conectada à {@code sala2}.
     *                  Pode ser "A", "B", ou "C"
     * @param sala2 qual sala a {@code porta1_id} dará acesso
     */
    public void Conectar(String porta1_id, Sala sala2){
        switch(porta1_id){
            case "A":
                if(sala2.getPorta(this.id) == null){
                    this.portaA = new Porta(this.id, sala2.id);
                }else{
                    this.portaA = sala2.getPorta(this.id);
                }
                break;
            case "B":
                if(sala2.getPorta(this.id) == null){
                    this.portaB = new Porta(this.id, sala2.id);
                }else{
                    this.portaB = sala2.getPorta(this.id);
                }
                break;
            case "C":
                if(sala2.getPorta(this.id) == null){
                    this.portaC = new Porta(this.id, sala2.id);
                }else{
                    this.portaC = sala2.getPorta(this.id);
                }
                break;
        }
    }
    
    /**
     * Retorna o número identificador da sala.
     * 
     * @return {@code id}
     */
    public int getId(){
        return this.id;
    }
    
    /**
     * Verifica se {@code str_item} existe na sala e o retorna.
     * 
     * @param str_item {@code String} representando o item desejado
     * @return Item encontrado. Retorna {@code null} se não encontrar o item
     */
    public Item getItem(String str_item){
        //str_item = str_item.toLowerCase();
        for(Item item : this.itens){
            if(item.toString().startsWith(str_item)){
                return item;
            }
        }
        return null;
    }
    
    /**
     * Adiciona um item na sala.
     * 
     * @param item Item para ser adicionado na sala
     */
    public void addItem(Item item){
        if(item != null){
            this.itens.add(item);
        }
    }
    
    /**
     * Porta A da sala.
     * 
     * @return {@code portaA}
     */
    public Porta getPortaA() {
        return this.portaA;
    }

    /**
     * Porta B da sala. Pode ser {@code null}
     * 
     * @return {@code portaB}
     */
    public Porta getPortaB() {
        return this.portaB;
    }

    /**
     * Porta C da sala. Pode ser {@code null}
     * 
     * @return {@code portaC}
     */
    public Porta getPortaC() {
        return this.portaC;
    }
    
    /**
     * Retorna a porta de acordo com {@code str_porta}.
     * 
     * @param str_porta {@code String} string no formato do jogo. Exemplo:
     *                  "a door", "b door" ou "c door"
     * @return A porta correspondente à {@code str_porta}. Ou {@code null} caso
     *         {@code str_porta} seja uma string inválida.
     */
    public Porta getPorta(String str_porta){
        if(str_porta.equals("a door")){
            return this.portaA;
        }
        if(str_porta.equals("b door")){
            return this.portaB;
        }
        if(str_porta.equals("c door")){
            return this.portaC;
        }
        return null;
    }
    
    /**
     * Retorna a porta que conecta à sala {@code sala2_id}, caso esta exista.
     * 
     * @param sala2_id id de alguma sala adjacente
     * @return a Porta que conecta as duas salas ou {@code null} caso esta não exista
     */
    public Porta getPorta(int sala2_id){
        if(this.portaA != null){
            if(this.portaA.getSala2(this.id) == sala2_id){
                return this.portaA;
            }
        }
        if(this.portaB != null){
            if(this.portaB.getSala2(this.id) == sala2_id){
                return this.portaB;
            }
        }
        if(this.portaC != null){
            if(this.portaC.getSala2(this.id) == sala2_id){
                return this.portaC;
            }
        }
        return null;
    }
    
    /**
     * Escolhe uma porta aleatória (não nula) da sala e retorna sua string correspondente.
     * 
     * @return "A door", "B door" ou "C door" escolhido aleatóriamente
     */
    public String getPortaAleatoria(){
        Random rand = new Random();
        int cod_porta = rand.nextInt(3);
        
        if(cod_porta == 0){
            return "A door";
        }
        
        if(cod_porta == 1 && this.portaB != null){
            return "B door";
        }
        
        if(cod_porta == 2 && this.portaC != null){
            return "C door";
        }
        return "A door";
    }
    
    /**
     * Insere um máximo de 5 itens aleatóriamente na sala. Os itens podem ser
     * {@code Ouro}, {@code Diamante}, {@code Pocao} e {@code Machado}. Chaves
     * não são adicionadas aqui pois o local dessas não pode ser definido
     * aleatoriamente.
     */
    private void setItens(){
        Random rand = new Random();
        int maxItens = 5;
        int qtdItens = rand.nextInt(maxItens + 1);
        int item;
        
        for(int i=0; i<qtdItens; i++){
            item = rand.nextInt(4);
            switch(item){
                case 0: //Ouro
                    this.itens.add(new Ouro());
                    break;
                case 1: //Diamante
                    this.itens.add(new Diamante());
                    break;
                case 2: //Poção
                    this.itens.add(new Pocao());
                    break;
                case 3: //Machado
                    this.itens.add(new Machado());
                    break;
            }
        }
    }
    
    /**
     * Adiciona chaves na sala.
     * 
     * @param chaves_str {@code String} no formato "(s1-s2,s6-s19,...)" onde
     * s<i>i</i>-s<i>j</i> é a chave da porta que liga as salas <i>i</i> e <i>j</i>.
     * As chaves são separadas por vírgula e não tem limite de quantas chaves
     * podem ter na sala. "(,)" significa que a sala não tem chaves.
     */
    public void addChaves(String chaves_str){
        int chaves_tamI = chaves_str.indexOf('(') + 1;
        int chaves_tamF = chaves_str.indexOf(')', chaves_tamI);
        String[] chaves = chaves_str.substring(chaves_tamI, chaves_tamF).split(",");
        for(String chave: chaves){
            String[] chave_salas = chave.split("-");
            int chave_sala1 = Integer.parseInt(chave_salas[0]);
            int chave_sala2 = Integer.parseInt(chave_salas[1]);
            this.itens.add(new Chave(chave_sala1, chave_sala2));
        }
    }
    
    /**
     * Remove o item da sala.
     * 
     * @param item Item para ser removido da sala
     */
    public void removerItem(Item item){
        this.itens.remove(item);
    }
    
    /**
     * Mostra na tela as informações da sala, como {@code id}, portas (e para
     * onde levam) e itens.
     */
    public void Listar(){
        System.out.printf("Sala %d\n", this.id);
        System.out.print("  Portas:");
        if(this.portaA != null){
            System.out.printf(" A: (%d)", this.portaA.getSala2(this.id));
        }
        if(this.portaB != null){
            System.out.printf(" | B: (%d)", this.portaB.getSala2(this.id));
        }
        if(this.portaC != null){
            System.out.printf(" | C: (%d)", this.portaC.getSala2(this.id));
        }
        System.out.println();
        
        System.out.printf("  %d Item(s): ", this.itens.size());
        for(Item item : this.itens){
            System.out.print("<" + item + "> ");
        }
        System.out.println();
    }
}
