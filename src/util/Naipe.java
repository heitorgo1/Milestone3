/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 * Enumeração que lista os naipes das cartas.
 * 
 * @author Anderson
 * @author Felipe
 */
public enum Naipe {

    /**
     * Representa o naipe de Copas e sua cor associada (vermelho).
     */
    COPAS("Copas", "Vermelho"),

    /**
     * Representa o naipe de Espadas e sua cor associada (preto).
     */
    ESPADAS("Esp.", "Preto"),

    /**
     * Representa o naipe de Ouros e sua cor associada (vermelho).
     */
    OUROS("Ouros", COPAS.getCor()),

    /**
     * Representa o naipe de Paus e sua cor associada (preto).
     */
    PAUS("Paus", ESPADAS.getCor());

    private final String nome;
    private final String cor;

    Naipe(final String nome, final String cor) {
        this.nome = nome;
        this.cor = cor;
    }

    @Override
    public String toString() {
        return nome;
    }

    /**
     * Retorna a cor do naipe selecionado
     * 
     * @return String da cor do naipe.
     */
    public String getCor() {
        return cor;
    }
}
