/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.exceptions;

/**
 * Armazena uma mensagem informando o que motivou o lançamento de uma exceção 
 * durante o jogo.
 * 
 * @author Anderson
 * @author Felipe
 */
@SuppressWarnings("serial")
public class JogoException extends Exception {

    /**
     * Recebe como parâmetro a mensagem de erro a ser transmitida com o 
     * lançamento da exceção.
     * 
     * @param mensagemErro
     */
    public JogoException(final String mensagemErro) {
        super("ERRO: "+mensagemErro);
    }
}
