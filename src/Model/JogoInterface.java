/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.exceptions.JogoException;

/**
 * Fachada do jogo para uma visualização. Contém as estruturas e os métodos 
 * necessários para criação e manipulação de cartas.
 * 
 * @author Anderson
 * @author Felipe
 */
public interface JogoInterface{

    /**
     * Método responsável por iniciar as classes necessárias para o início do 
     * jogo.
     */
    public void iniciar();
    
    /**
     * Configura a variável utilizada para definir quantas cartas do estoque 
     * serão viradas por vez.
     *     
     */
    public void alterarNumeroCartasViradas();   
    
    /**
     * Responsável por virar uma certa quantidade de cartas (uma ou três) no 
     * estoque. A quantidade é definida pelo método "alterarNumeroCartasViradas".
     *    
     * @throws JogoException
     * @see setVirarTresCartas#alterarNumeroCartasViradas
     */
    public void virarCartasEstoque() throws JogoException;    

    /**
     * Vira a carta de uma fileira. Lança uma exceção se não houver uma carta
     * na fileira.
     * 
     * @param fileira Número da fileira cuja carta deve ser virada
     * @throws JogoException
     */
    public void virarCartaFileira(final int fileira) throws JogoException;

    /**
     * Move a carta no topo do descarte para uma fileira. Lança uma exceção, 
     * caso o movimento viole as regras do jogo.
     * 
     * @param fileira Número da fileira de destino
     * @throws JogoException
     */
    public void moverDescarteFileira(final int fileira) throws JogoException;

    /**
     * Move a carta no topo do descarte para uma fundação. Lança uma exceção 
     * caso o movimento viole as regras do jogo ou os parâmetros sejam inválidos.
     * 
     * @param fundacao Número da fundação de destino
     * @throws JogoException
     */
    public void moverDescarteFundacao(final int fundacao) throws JogoException;

    /**
     * Move a carta que está no fim de uma fileira para uma fundação.
     * 
     * @param fileira Número da fileira de origem
     * @param fundacao Número da fundação de destino
     * @throws JogoException
     */
    public void moverFileiraFundacao(final int fileira, final int fundacao) throws JogoException;

    /**
     * Move a carta que está no fim de uma fileira para o fim de outra. Lança 
     * uma exceção caso o movimento viole as regras do jogo ou os parâmetros
     * sejam inválidos.
     * 
     * @param fileiraOrigem Número da fileira de origem
     * @param numCarta Indice da carta na fileira de origem
     * @param fileiraDestino Número da fileira de destino
     * @throws JogoException
     */
    public void moverFileiraFileira(final int fileiraOrigem, final int numCarta, 
            final int fileiraDestino) throws JogoException;

    /**
     * Move a carta no topo de uma fundação para uma fileira. Lança 
     * uma exceção caso o movimento viole as regras do jogo ou os parâmetros
     * sejam inválidos.
     * 
     * @param fundacao Número da fundação de origem
     * @param fileira Número da fileira de destino
     * @throws JogoException
     */
    public void moverFundacaoFileira(final int fundacao, final int fileira) throws JogoException;

    /**
     * Retorna "true" caso o jogo tenha sido vencido.
     * 
     * @return Status de vitória
     */
    public boolean jogoVencido();

    /**
     * Exibe o estado atual da mesa. Mostra o conteúdo da pilha de descarte, 
     * fileiras e fundações.
     * 
     * @return String representando a mesa.
     */
    public String exibirMesa();
}
