/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 * Enumeração que representa os possíveis valores de uma carta. Os valores são 
 * associados a índices, permitindo realizar eventuais comparações entre cartas.
 * 
 * @author Anderson
 * @author Felipe
 */
public enum Valor {

    /**
     * 
     */
    AS("As", 0),

    /**
     * 
     */
    DOIS("Dois", 1),

    /**
     * 
     */
    TRES("Tres", 2),

    /**
     *
     */
    QUATRO("Quatro", 3),

    /**
     *
     */
    CINCO("Cinco", 4),

    /**
     *
     */
    SEIS("Seis", 5),

    /**
     *
     */
    SETE("Sete", 6),

    /**
     *
     */
    OITO("Oito", 7),

    /**
     *
     */
    NOVE("Nove", 8),

    /**
     *
     */
    DEZ("Dez", 9),

    /**
     *
     */
    VALETE("Val.", 10),

    /**
     *
     */
    DAMA("Dama", 11),

    /**
     *
     */
    REI("Rei", 12);

    private final String nome;
    private final int indice;

    Valor(final String nome, final int indice) {
        this.nome = nome;
        this.indice = indice;
    }

    /**
     * Retorna o índice de uma carta. Indicado para realizar comparações entre 
     * valores de cartas.
     * 
     * @return
     */
    public int getIndice() {
        return indice;
    }

    @Override
    public String toString() {
        return nome;
    }
}
