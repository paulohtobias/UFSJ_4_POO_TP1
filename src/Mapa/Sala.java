/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Item.Chave;
import Item.Diamante;
import Item.Item;
import Item.Ouro;
import Item.Pocao;
import Mapa.Porta.Porta_Estado;
import Personagem.Troll;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author paulo
 */
public class Sala {
    private int id;
    private Porta portaA = null;
    private Porta portaB = null;
    private Porta portaC = null;
    
    private ArrayList<Item> itens;
    private ArrayList<Troll> trolls;
    
    public Sala(int id, int salaA){
        this.id = id;
        this.portaA = new Porta(this.id, salaA);
        this.itens = new ArrayList<>();
        this.setItens();
        this.setTrolls();
    }
    public Sala(int id, int salaA, int salaB){
        this(id, salaA);
        this.portaB = new Porta(this.id, salaB);
    }
    public Sala(int id, int salaA, int salaB, int salaC){
        this(id, salaA, salaB);
        this.portaC = new Porta(this.id, salaC);
    }
    public Sala(String str){
        int id_size = str.indexOf(':');
        this.id = Integer.parseInt(str.substring(0, id_size));
        
        this.itens = new ArrayList<Item>();
        
        //Criando as portas.
        int salas_tamI = str.indexOf('(', id_size) + 1;
        int salas_tamF = str.indexOf(')', salas_tamI);
        String[] salas = str.substring(salas_tamI, salas_tamF).split(",");
        
        this.portaA = new Porta(this.id, Integer.parseInt(salas[0]));
        try{
            this.portaB = new Porta(this.id, Integer.parseInt(salas[1]));
            this.portaC = new Porta(this.id, Integer.parseInt(salas[2]));
        }catch(Exception e){}
        
        //Identificando as portas trancadas.
        int trancadas_tamI = str.indexOf('(', salas_tamF) + 1;
        int trancadas_tamF = str.indexOf(')', trancadas_tamI);
        String[] trancadas = str.substring(trancadas_tamI, trancadas_tamF).split(",");
        for(String tranca: trancadas){
            switch(tranca){
                case "A":
                    this.portaA.setEstado(Porta_Estado.TRANCADA);
                    break;
                case "B":
                    this.portaB.setEstado(Porta_Estado.TRANCADA);
                    break;
                case "C":
                    this.portaC.setEstado(Porta_Estado.TRANCADA);
                    break;
            }
        }
        
        //Verificando se há chaves na sala.
        int chaves_tamI = str.indexOf('(', trancadas_tamF) + 1;
        int chaves_tamF = str.indexOf(')', chaves_tamI);
        String[] chaves = str.substring(chaves_tamI, chaves_tamF).split(",");
        for(String chave: chaves){
            String[] chave_salas = chave.split("-");
            int chave_sala1 = Integer.parseInt(chave_salas[0]);
            int chave_sala2 = Integer.parseInt(chave_salas[1]);
            this.itens.add(new Chave(chave_sala1, chave_sala2));
        }
        
        //Preenchendo itens e trolls.
        this.setItens();
        this.setTrolls();
    }
    
    public int getId(){
        return this.id;
    }
    
    public Item getItem(String str_item){
        //str_item = str_item.toLowerCase();
        for(Item item : this.itens){
            if(item.toString().startsWith(str_item)){
                return item;
            }
        }
        return null;
    }
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
    
    private void setItens(){
        Random rand = new Random();
        int maxItens = 5;
        int qtdItens = rand.nextInt(maxItens + 1);
        int item;
        
        for(int i=0; i<qtdItens; i++){
            item = rand.nextInt(3);
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
                    //this.itens.add(new Machado());
                    break;
            }
        }
    }
    
    public void removerItem(Item item){
        this.itens.remove(item);
    }
    
    private void setTrolls(){
        this.trolls = new ArrayList<Troll>();
        
        Random rand = new Random();
        int maxTrolls = 2;
        int qtdTrolls = rand.nextInt(maxTrolls + 1);
        
        for(int i=0; i<qtdTrolls; i++){
            this.trolls.add(new Troll(String.format("Troll %d", i)));
        }
    }
    
    public void Listar(){
        System.out.printf("Sala %d\n", this.id);
        System.out.println("  Portas:");
        if(this.portaA != null){
            System.out.print("    Porta A: " + this.portaA.getSala2());
        }
        if(this.portaB != null){
            System.out.print("    Porta B: " + this.portaB.getSala2());
        }
        if(this.portaC != null){
            System.out.print("    Porta C: " + this.portaC.getSala2());
        }
        System.out.println();
        
        System.out.printf("  Itens: %d\n", this.itens.size());
        for(Item item : this.itens){
            System.out.print("    ");
            //System.out.println(item.getClass().getTypeName());
            System.out.println(item);
        }
        
        System.out.println("  Trolls:");
        for(Troll troll : this.trolls){
            System.out.println("    " + troll.getId());
        }
    }
}
