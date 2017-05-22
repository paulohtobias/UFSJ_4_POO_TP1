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
    
    public Sala(int id){
        this.id = id;
        this.itens = new ArrayList<>();
        this.setItens();
        
        this.trolls = new ArrayList<>();
        this.setTrolls();
    }
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
    
    public Troll getTroll(String troll_id){
        for(Troll troll: this.trolls){
            if(troll.getId().toLowerCase().equals(troll_id)){
                return troll;
            }
        }
        return null;
    }
    
    public Porta getPortaA() {
        return this.portaA;
    }

    public Porta getPortaB() {
        return this.portaB;
    }

    public Porta getPortaC() {
        return this.portaC;
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
    
    public void removerItem(Item item){
        this.itens.remove(item);
    }
    
    private void setTrolls(){        
        Random rand = new Random();
        int maxTrolls = 2;
        int qtdTrolls = rand.nextInt(maxTrolls + 1);
        
        for(int i=0; i<qtdTrolls; i++){
            this.trolls.add(new Troll(String.format("Troll %d", i)));
        }
    }
    
    public void removerTroll(Troll troll){
        this.trolls.remove(troll);
    }
    
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
        
        System.out.printf("  %d Iten(s): ", this.itens.size());
        for(Item item : this.itens){
            System.out.print("<" + item + "> ");
            //System.out.println(item.getClass().getTypeName());
            //System.out.println(item);
        }
        System.out.println();
        
        System.out.printf("  %d Troll(s): ", this.trolls.size());
        for(Troll troll : this.trolls){
            System.out.println("<" + troll.getId() + "> ");
        }
        System.out.println();
    }
}
