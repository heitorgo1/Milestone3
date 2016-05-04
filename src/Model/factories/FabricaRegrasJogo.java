/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.factories;

import Model.rules.RegraDistribuicaoFileira;
import Model.rules.RegraInsercaoFileira;

/**
 * Fabrica abstrata que fornece as regras de um jogo qualquer. Lista métodos 
 * usados para obter o número de fileiras e as regras de inserção e distribuição
 * de cartas nas fileiras.
 * 
 * @author Anderson
 */
public abstract class FabricaRegrasJogo {
    
    /**
     * Retorna o número de fileiras usadas em um jogo qualquer.
     * 
     * @return 
     */
    public abstract int getNumFileiras();
    
    /**
     * Retorna um objeto representando a regra de inserção em uma fileira.
     * @return 
     */
    public abstract RegraInsercaoFileira getRegraInsercaoFileira();
    
    /**
     * Retorna um objeto representando a regra de distribuição em uma fileira.
     * @return 
     */
    public abstract RegraDistribuicaoFileira getRegraDistribuicaoFileira();
    
}
