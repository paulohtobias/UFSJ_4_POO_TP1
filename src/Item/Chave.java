/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Item;

import Mapa.Porta;

/**
 *
 * @author paulo
 */
public class Chave extends Item {
    int sala1;
    int sala2;
    
    /**
     * Metodo construtor da classe Chave. Uma chave é identificada pelo número
     * das suas duas salas. Ao tentar usar uma chave em uma porta, esses números
     * são comparados com a porta para ver se a chave é a certa.
     *
     * @param sala1 sala 1 da chave
     * @param sala2 sala 2 da chave
     */
    public Chave(int sala1, int sala2){
        this.sala1 = sala1;
        this.sala2 = sala2;
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
     * Tenta usar a chave na {@code porta} dada.
     * 
     * @param porta Porta que será destrancada. Precisa ser a porta equivalente
     *              à chave para poder ser aberta.
     * @return {@code true}: se a porta foi destrancada.
     */
    public Boolean Usar(Porta porta){
        if(porta != null){ //Checando para ver se a porta é validada.
            if(porta.estaTrancada()){
                //Verificando se sala1 e sala2 batem com os identificadores da porta.
                //Este passo é feito em duas etapas, visto que a sala1 da chave
                //pode ser igual à sala1 ou sala2 da porta. O mesmo ocorre para
                //sala2 da chave.

                if(this.sala1 == porta.getSala1() && this.sala2 == porta.getSala2()){
                    porta.Abrir();
                    return true;
                }
                if(this.sala1 == porta.getSala2() && this.sala2 == porta.getSala1()){
                    porta.Abrir();
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Retorna a chave em forma de string.
     * 
     * Sobrecarga do método toString.
     * 
     * @return Chave em forma de string no formato "key s1-s2"
     */
    public String toString(){
        return String.format("key %d-%d", this.sala1, this.sala2);
    }
}
