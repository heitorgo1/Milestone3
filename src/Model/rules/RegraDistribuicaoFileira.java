/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.rules;

import java.util.Stack;

import Model.managers.GerenciadorFileiras;
import Model.stacks.Fileira;
import util.Carta;

/**
 * Abstração para uma regra de distribuição de cartas em fileiras.
 * 
 * @author Anderson
 * @see GerenciadorFileiras
 */
public interface RegraDistribuicaoFileira {
    
    /**
     * Realiza a distribuição de cartas de acordo com o algoritmo específico de
     * um jogo
     * 
     * @param baralho Cartas a serem distribuídas
     * @param fileiras Grupo de fileiras que receberão as cartas
     */
    public abstract void distribuir(Stack<Carta> baralho, final Fileira[] fileiras);
    
}
