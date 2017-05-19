/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Item.Diamante;
import Item.Item;
import Item.Ouro;
import Item.Pocao;
import Personagem.Troll;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author paulo
 */
public class Sala {
    int id;
    private Porta portaA = null;
    private Porta portaB = null;
    private Porta portaC = null;
    
    private ArrayList<Item> itens;
    private ArrayList<Troll> trolls;
    
    public Sala(int id, int salaA){
        this.portaA = new Porta(this.id, salaA);
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
    
    public Item getItem(String str_item){
        //str_item = str_item.toLowerCase();
        for(Item item : this.itens){
            if(item.getTipo().equals(str_item)){
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
        this.itens = new ArrayList<Item>();
        
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
            System.out.println(item.getClass().getTypeName());
        }
        
        System.out.println("  Trolls:");
        for(Troll troll : this.trolls){
            System.out.println("    " + troll.getId());
        }
    }
}
