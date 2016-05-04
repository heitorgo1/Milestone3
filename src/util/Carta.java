/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 * Representa uma carta do baralho, armazenando o valor, o naipe e o estado da
 * face. Por padrão, a carta é inicializada com a face virada para baixo.
 * 
 * @author Anderson
 * @author Felipe
 */

public final class Carta implements Comparable<Carta> {

    public final Valor VALOR;
    public final Naipe NAIPE;

    private boolean faceParaCima;

    //  Lançar uma exceção no lugar da mensagem e deixar que a interface trate o evento.
    private final String msgCartaParaBaixo = "(CARTA VIRADA)";

    public Carta(Valor valor, Naipe naipe) {
        this.VALOR = valor;
        this.NAIPE = naipe;
        this.faceParaCima = true;
    }

    /**
     * Retorna o naipe da carta
     * 
     * @return Naipe da carta
     */
    public Naipe getNaipe() {
        return NAIPE;
    }

    /**
     * Retorna o valor da carta
     * 
     * @return Valor da carta
     */
    public Valor getValor() {
        if (faceParaCima) {
            return VALOR;
        } else {
            return null;
        }
    }

    /**
     * Retorna uma string representando a cor da carta.
     * 
     * @return Representação da cor
     */
    public String getCor() {
        if (faceParaCima) {
            return NAIPE.getCor();
        } else {
            return null;
        }
    }

    /**
     * Altera o estado da face. Se a carta estiver virada para baixo, vira-a 
     * para cima. Caso contrário, vira-a para baixo.
     */
    public void virarFace() {
        faceParaCima = !faceParaCima;
    }

    /**
     * Retorna "true", se a carta está com a face para cima.
     * 
     * @return Estado da face
     */
    public boolean temFaceParaCima() {
        return faceParaCima;
    }

    @Override
    public String toString() {
        if (faceParaCima) {
            return VALOR + " de " + NAIPE;
        } else {
            return msgCartaParaBaixo;
        }
    }

    @Override
    public int compareTo(Carta comparada) {
        return VALOR.compareTo(comparada.VALOR);
    }

    /**
     * Retorna "true" se a carta possuir um valor inferior ao da outra carta.
     * 
     * @param outra Carta a ser comparada.
     * @return 
     */
    public boolean ehAntecessora(Carta outra) {
        return compareTo(outra) == -1;
    }

    /**
     * Retorna "true" se a carta possuir um valor superior ao da outra carta.
     * 
     * @param outra Carta a ser comparada.
     * @return 
     */
    public boolean ehSucessora(Carta outra) {
        return compareTo(outra) == 1;
    }
}
