/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.managers;

import Model.exceptions.JogoException;
import Model.stacks.Fundacao;
import util.Carta;
import util.Valor;

/**
 * 
 * 
 * @author Anderson
 * @author Felipe
 */
public final class GerenciadorFundacoes {

    private static final int NUM_FUNDACOES = 4;
    private static GerenciadorFundacoes INSTANCIA;

    //  Define o tamanho máximo das pilhas de fundação
    private static final int CARTAS_POR_FUNDACAO = Valor.values().length;

    private Fundacao[] fundacoes = new Fundacao[NUM_FUNDACOES];

    private GerenciadorFundacoes() {
        criarFundacoes();
    }

    public static GerenciadorFundacoes getInstance() {
        if (INSTANCIA == null) {
            INSTANCIA = new GerenciadorFundacoes();
        }

        return INSTANCIA;
    }

    /**
     * Cria as fundações, de acordo com o valor especificado no atributo
     * "NUM_FUNDACOES".
     * 
     * @see Fundacao
     */    
    private void criarFundacoes() {

        for (int i = 0; i < fundacoes.length; i++) {
            fundacoes[i] = new Fundacao();
        }
    }
    
    /**
     * Testa se a inserção de uma carta, em uma das fundações selecionadas, é
     * válida. Lança uma exceção caso a referência da carta movida seja nula, 
     * se o número da fundação não existir ou se a operação violar as regras de
     * inserção em fundações.
     * 
     * @param movida Carta a ser inserida
     * @param fundacao Número da fileira de destino
     * @throws JogoException
     * @see Fundacao
     */    
    public void validarInsercao(Carta movida, int fundacao) throws JogoException{
        
        if (movida == null) {
            throw new JogoException("Nenhuma carta foi movida! Tente novamente.");
        }
        
        //  Evita problemas com índices errados
        if ( (fundacao < 0) || (fundacao >= fundacoes.length) ) {
            throw new JogoException("O número da fundação deve estar entre 0 e "+(fundacoes.length - 1)+".");               
        }
        
        Fundacao destino = fundacoes[fundacao];
        destino.validarInsercao(movida);
    }

    /**
     * Insere uma carta na fundação selecionada. 
     * 
     * @param movida Carta a ser inserida
     * @param fundacao Número da fundação de destino
     */    
    public void inserirCarta(Carta movida, int fundacao) throws JogoException{
        Fundacao destino = fundacoes[fundacao];
        destino.inserir(movida);
    }

    /**
     * Elimina o conteúdo de todas as fundações.
     * 
     * @see Fundacao
     */    
    public void limparFundacoes() {
        for (Fundacao atual : fundacoes) {
            atual.limpar();
        }
    }

    /**
     * Retorna a carta na extremidade da fundação, REMOVENDO-A desta fundação.
     * 
     * @param fundacao Número da fundação
     * @return
     * @throws JogoException 
     * @see Fundacao
     */    
    public Carta getCartaTopo(int fundacao) throws JogoException{
        
        if ( (fundacao < 0) || (fundacao >= fundacoes.length) ) {
            throw new JogoException("O número da fundação deve estar entre 0 e "+(fundacoes.length - 1)+".");
        }
        
        return fundacoes[fundacao].getTopo();
    }

    /**
     * Retorna a carta na extremidade da fundação selecionada. Lança uma exceção
     * se o número da fundação não existir, se a fundação estiver vazia ou a carta
     * estiver virada para baixo.
     * 
     * @param numFundacao Número da fundação
     * @return
     * @throws JogoException 
     * @see Fundacao
     */    
    public Carta verCartaTopo(int numFundacao) throws JogoException{
        
        if ( (numFundacao < 0) || (numFundacao >= fundacoes.length) ) {
            throw new JogoException("O número da fundação deve estar entre 0 e "+(fundacoes.length - 1)+".");
        }
                
        return fundacoes[numFundacao].verTopo();
    }
    
    /**
     * Indica se as fundações estão completas. Retorna "true" caso todas as 
     * fundações estejam completas. Caso contrário, retorna "false".
     * 
     * @return 
     */
    public boolean pilhasCompletas() {

        for (Fundacao atual : fundacoes) {
            if (atual.tamanho() < CARTAS_POR_FUNDACAO) {
                return false;
            }
        }
        return true;
    }    

    /**
     * Retorna uma representação, em String, das fundações e seus respectivos
     * conteúdos.
     * 
     * @return 
     */    
    @Override
    public String toString() {

        StringBuilder exibicao = new StringBuilder();

        exibicao.append("=========\n");
        exibicao.append("FUNDACOES\n");
        exibicao.append("=========\n\n");

        for (int i = 0; i < NUM_FUNDACOES; i++) {
            exibicao.append("[Fundação ");
            exibicao.append(i);
            exibicao.append("]\t");
        }
        exibicao.append("\n");

        for (int i = 0; i < NUM_FUNDACOES; i++) {
            
            try{
                exibicao.append(fundacoes[i].verTopo());
                exibicao.append("\t");
            }
            catch(JogoException ex){
                exibicao.append("     -");
                exibicao.append("\t\t");
            }            
        }

        exibicao.append("\n\n------------------------------------------------\n");
        return exibicao.toString();
    }

}
