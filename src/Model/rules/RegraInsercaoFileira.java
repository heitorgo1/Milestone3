/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.rules;

import java.util.Stack;

import Model.exceptions.JogoException;
import Model.stacks.Fileira;
import util.Carta;

/**
 * Abstração para uma regra de inserção de cartas em uma fileira.
 * 
 * @author Anderson
 * @author Felipe
 * @see Fileira
 */
public abstract class RegraInsercaoFileira {
    
    /**
     * Testa se a inserção de uma carta em uma fileira segue a regra do jogo.
     * Lança uma exceção, caso a regra seja violada ou os parâmetros estejam
     * incorretos.
     * 
     * @param movida Carta movida pelo jogador
     * @param fileira Referência para a pilha de cartas da fileira
     * @throws JogoException 
     * @see Fileira
     */
    public abstract void testarInsercao(final Carta movida, final Stack<Carta> fileira) throws JogoException;
}
