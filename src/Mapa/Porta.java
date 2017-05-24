/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

/**
 *
 * @author paulo
 */

public class Porta {
    /**
     * Representa uma das salas que fica em um lado da porta.
     */
    private int sala1;
    
    /**
     * Representa a outra sala que fica do outro lado da porta.
     */
    private int sala2;
    
    /**
     * Estado da porta: Aberta, Fechada ou Trancada.
     */
    private Porta_Estado estado;
    
    /**
     * Enum que define o estado da porta.
     */
    public enum Porta_Estado{
        ABERTA,
        FECHADA,
        TRANCADA
    };
    
    /**
     * Construtor de Porta. Os parâmetros passados aqui significam quais são
     * as duas salas de que é possível acessar desta porta.
     * 
     * @param sala1 identificador da sala 1
     * @param sala2 identificador da sala 2
     */
    public Porta(int sala1, int sala2) {
        this.sala1 = sala1;
        this.sala2 = sala2;
        
        this.estado = Porta_Estado.ABERTA;
    }

    /**
     * Retorna o identificador da sala 1.
     * @return sala1
     */
    public int getSala1() {
        return this.sala1;
    }

    /**
     * Retorna o identificador da sala 2.
     * @return sala2
     */
    public int getSala2() {
        return this.sala2;
    }
    
    /**
     * Retorna a "sala de dentro". Depende de qual sala é o ponto de vista.
     * <p>Exemplo: Suponha que {@code sala1 = 3} e {@code sala2 = 5}. Neste caso,
     * se a função for chamada da perspectiva da sala 3, a função retornará
     * 3 ({@code sala1}. Do mesmo modo, se a função for chamada da perspectiva
     * da sala 5, a função retornará 5 ({@code sala2}).</p>
     * 
     * @param sala_id sala de onde a perspectiva é vista como "dentro"
     * @return o número da sala de dentro.
     */
    public int getSala1(int sala_id) {
        if(this.sala1 == sala_id){
            return this.sala1;
        }else{
            return this.sala2;
        }
    }
    
    /**
     * Retorna a "sala de fora". Depende de qual sala é o ponto de vista.
     * <p>Exemplo: Suponha que {@code sala1 = 3} e {@code sala2 = 5}. Neste caso,
     * se a função for chamada da perspectiva da sala 3, a função retornará
     * 5 ({@code sala2}. Do mesmo modo, se a função for chamada da perspectiva
     * da sala 5, a função retornará 3 ({@code sala1}).</p>
     * 
     * @param sala_id sala de onde a perspectiva é vista como "fora"
     * @return o número da sala de fora.
     */
    public int getSala2(int sala_id) {
        if(this.sala1 == sala_id){
            return this.sala2;
        }else{
            return this.sala1;
        }
    }
    
    /**
     * Verifica se a porta está aberta.
     * 
     * @return {@code true} se estiver aberta
     */
    public Boolean estaAberta(){
        return (this.estado == Porta_Estado.ABERTA);
    }
    
    /**
     * Verifica se a porta está fechada.
     * 
     * @return {@code true} se estiver fechada
     */
    public Boolean estaFechada(){
        return (this.estado == Porta_Estado.FECHADA);
    }
    
    /**
     * Verifica se a porta está trancada.
     * 
     * @return {@code true} se estiver trancada
     */
    public Boolean estaTrancada(){
        return (this.estado == Porta_Estado.TRANCADA);
    }

    /**
     * Muda o estado da porta para aberta.
     */
    public void Abrir(){
        this.estado = Porta_Estado.ABERTA;
    }
    
    /**
     * Muda o estado da porta para fechada.
     */
    public void Fechar(){
        this.estado = Porta_Estado.FECHADA;
    }
    
    /**
     * Muda o estado da porta para trancada.
     */
    public void Trancar(){
        this.estado = Porta_Estado.TRANCADA;
    }
    
    /**
     * Retorna a porta em forma de string.
     * 
     * Sobrecarga do método toString.
     * 
     * @return Porta em forma de string no formato "door s1-s2 (ESTADO)"
     */
    public String toString(){
        return String.format("door %d-%d (%s)", this.sala1, this.sala2, this.estado.name());
    }
}
