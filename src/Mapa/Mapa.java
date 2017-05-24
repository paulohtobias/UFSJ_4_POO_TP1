/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Personagem.Jogador;
import Personagem.Troll;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author paulo
 */
public class Mapa {
    //Personagens
    /**
     * O jogador principal do jogo.
     */
    static Jogador jogador;
    
    /**
     * A Lista de trolls do mapa. Varia de 1 a {@code maxTrolls}
     */
    private final ArrayList<Troll> trolls;
    
    /**
     * Quantidade máxima de trolls no mapa.
     */
    private static int maxTrolls = 10;
    
    /**
     * Quantidade atual de trolls no mapa.
     */
    private int qtdTrolls;
    
    //Salas
    /**
     * Quantidade de salas no mapa.
     */
    private final int numSalas = 21;
    
    /**
     * Vetor de Salas.
     */
    private Sala[] salas;
    
    /**
     * Construtor "default". Se nenhum nome de arquivo foi passado como parâmetro, então
     * e mapa usado será mapa1.txt
     */
    public Mapa(){
        this("mapa1.txt");
    }
    
    /**
     * Construtor da classe Mapa.
     * @param arquivo None do arquivo que contém informações sobre as salas
     */
    public Mapa(String arquivo){
        //Personagens
            //Jogador
            Scanner scan = new Scanner(System.in);
            System.out.print("Nome do jogador: ");
            String nome = scan.nextLine();
            this.jogador = new Jogador(nome);
            //Trolls
            this.trolls = new ArrayList<>();
            //A quantidade inicial de trolls na sala é definida aleatóriamente.
            Random rand = new Random();
            qtdTrolls = rand.nextInt(maxTrolls) + 1;
            for(int i=0; i<qtdTrolls; i++){
                //A sala inicial de cada troll é definida aleatóriamente.
                int salaInicial = rand.nextInt(this.numSalas - 1) + 1;
                this.trolls.add(new Troll("Troll " + i, salaInicial));
            }
        
        //Salas
        this.salas = new Sala[numSalas];
        for(int i=0; i<numSalas; i++){
            this.salas[i] = new Sala(i);
        }
        
        //Lendo o arquivo e configurando as salas.
        FileReader fr = null;
        BufferedReader br = null;
        
        try{
            fr = new FileReader(arquivo);
            br = new BufferedReader(fr);
            
            String linha;
            
            br = new BufferedReader(new FileReader(arquivo));
            
            for(int i=0; i<numSalas && (linha = br.readLine()) != null; i++){
                //Identificando sala a ser configurada.
                int id_tam = linha.indexOf(':');
                int sala1_id = Integer.parseInt(linha.substring(0, id_tam));
                
                //Identificando as salas que serão conectadas.
                int salas_tamI = linha.indexOf('(', id_tam) + 1;
                int salas_tamF = linha.indexOf(')', salas_tamI);
                String[] salas_str = linha.substring(salas_tamI, salas_tamF).split(",");
                String[] porta1_id = {"A", "B", "C"};
                int j=0;
                for(String sala2_str: salas_str){
                    int sala2_id = Integer.parseInt(sala2_str);
                    this.getSala(sala1_id).Conectar(porta1_id[j++], this.getSala(sala2_id));
                }
                
                //Trancando as portas.
                int trancadas_tamI = linha.indexOf('(', salas_tamF) + 1;
                int trancadas_tamF = linha.indexOf(')', trancadas_tamI);
                String[] trancadas = linha.substring(trancadas_tamI, trancadas_tamF).split(",");
                for(String tranca: trancadas){
                    switch(tranca){
                        case "A":
                            this.getSala(sala1_id).getPortaA().Trancar();
                            break;
                        case "B":
                            this.getSala(sala1_id).getPortaB().Trancar();
                            break;
                        case "C":
                            this.getSala(sala1_id).getPortaC().Trancar();
                            break;
                    }
                }
                
                //Adicionando as chaves à lista de itens da sala.
                this.getSala(sala1_id).addChaves(linha.substring(trancadas_tamF));
            }
        }catch(Exception e){
            System.out.println("Erro (" + e.toString() + ") durante leitura do arquivo.");
            System.exit(1);
        }
    }
    
    /**
     * Retorna o jogador do mapa.
     * @return jogador
     */
    public Jogador getJogador(){
        return this.jogador;
    }

    /**
     * Retorna a lista de trolls do mapa.
     * @return ArrayList de trolls
     */
    public ArrayList<Troll> getTrolls() {
        return trolls;
    }
    
    /**
     * Retorna o troll cujo nome é igual à {@code troll_nome}.
     * 
     * @param troll_nome nome a ser buscado na lista de trolls
     * @return o troll encontrado. Pode ser {@code null}
     */
    public Troll getTroll(String troll_nome){
        return getTroll(this.trolls, troll_nome);
    }
    
    /**
     * Procura e retorna um troll em {@code trolls} cujo nome é igual à {@code troll_nome}
     * 
     * @param trolls lista de trolls a ser procurada. Normalmente é a lista com
     *               os trolls de uma determinada sala.
     * @param troll_nome nome a ser buscado em {@code trolls}
     * @return o troll encontrado. Pode ser {@code null}
     */
    public Troll getTroll(ArrayList<Troll> trolls, String troll_nome){
        for(Troll troll: trolls){
            if(troll.getNome().equals(troll_nome));
            return troll;
        }
        return null;
    }
    
    /**
     * Retorna uma lista com todos os trolls que estão na sala {@code sala_id}.
     * 
     * @param sala_id sala a ser listada.
     * @return uma lista com todos os trolls que estão na sala atualmente.
     */
    public ArrayList<Troll> trollsSala(int sala_id){
        ArrayList<Troll> trollsSala = new ArrayList<>();
        for(Troll troll: this.trolls){
            if(troll.getSalaAtual() == sala_id){
                trollsSala.add(troll);
            }
        }
        return trollsSala;
    }
    
    /**
     * Remove um troll da lista de trolls.
     * 
     * @param troll troll a ser removido.
     */
    public void removerTroll(Troll troll){
        this.trolls.remove(troll);
        this.qtdTrolls--;
    }
    
    /**
     * Retorna a sala do vetor de salas cujo id é igual à {@code sala_id}
     * @param sala_id sala a ser procurada
     * @return sala correspondente
     */
    public Sala getSala(int sala_id){
        for(int i=0; i<this.numSalas; i++){
            if(this.salas[i].getId() == sala_id){
                return this.salas[i];
            }
        }
        return null;
    }
    
    /**
     * Mostra tela o estado atual do jogo. Mostra os dados do jogador, da sala
     * atual e quais trolls estão nesta sala.
     * @param sala_id 
     */
    public void Listar(int sala_id){
        //Informações do Jogador
        this.getJogador().Listar();
        System.out.println();
        
        //Informações da sala
        this.getSala(sala_id).Listar();
        System.out.println();
        
        //Listando os trolls que estão na sala
        ArrayList<Troll> trolls = this.trollsSala(sala_id);
        System.out.printf("Trolls: %d =>", trolls.size());
        for(Troll troll: trolls){
            System.out.print("<" + troll.getNome() + ">");
        }
        System.out.println();
    }
}
