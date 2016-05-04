/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.managers;

import java.util.Stack;

import Model.exceptions.JogoException;
import Model.rules.RegraDistribuicaoFileira;
import Model.rules.RegraInsercaoFileira;
import Model.stacks.Fileira;
import util.Carta;

/**
 * Armazena as fileiras, gerenciando o acesso e a manipulação das mesmas.
 * 
 * @author Anderson
 * @author Felipe
 */
public final class GerenciadorFileiras {

    private final int NUM_FILEIRAS;
    private static GerenciadorFileiras INSTANCE;    
    private Fileira[] fileiras; 
    private RegraDistribuicaoFileira regraDistribuicao;

    private GerenciadorFileiras(final int numFileiras) {
        
        NUM_FILEIRAS = numFileiras;
        fileiras = new Fileira[NUM_FILEIRAS];
        criarFileiras();
    }

    //  Singleton
    public static GerenciadorFileiras getInstance(final int numFileiras) {

        if (INSTANCE == null) {
            INSTANCE = new GerenciadorFileiras(numFileiras);
        }
        return INSTANCE;
    }

    /**
     * Cria as fileiras, de acordo com o valor especificado no atributo
     * "NUM_FILEIRAS".
     * 
     * @see Fileira
     */
    public void criarFileiras() {
        for (int i = 0; i < fileiras.length; i++) {
            fileiras[i] = new Fileira();
        }
    }

    /**
     * Elimina o conteúdo de todas as fileiras.
     * 
     * @see Fileira
     */
    public void limpar() {
        for (Fileira atual : fileiras) {
            atual.limpar();
        }
    }
    
    /**
     * Define uma regra de validação de inserção de cartas para todas as 
     * fileiras.
     * 
     * @param regraInsercao Regra de validação de inserção
     */
    public void setRegras(RegraInsercaoFileira regraInsercao, 
            RegraDistribuicaoFileira regraDistribuicao){
        
        this.regraDistribuicao = regraDistribuicao;
        for(Fileira atual : fileiras){
            atual.setRegraInsercao(regraInsercao);
        }
    }

    /**
     * Distribui as cartas entre as fileiras de acordo com as regras do jogo.     
     * 
     * @param baralho Cartas a serem distribuídas
     * @see RegraDistribuicaoFileira
     */
    public void distribuirCartas(Stack<Carta> baralho) {
        regraDistribuicao.distribuir(baralho, fileiras);
    }

    /**
     * Vira a carta que está na extremidade da fileira selecionada, caso aquela
     * esteja com a face para cima. A ação é ignorada se não houver carta na 
     * fileira ou se a face da carta no topo da pilha estiver voltada para cima.
     * 
     * @param numFileira Número da fileira
     */
    public void virarFaceTopo(final int numFileira) {
        fileiras[numFileira].virarFaceTopo();
    }
    
    /**
     * Testa se a inserção de uma carta, em uma das fileiras selecionadas, é
     * válida. Lança uma exceção caso a referência da carta seja nula, se o
     * número da filera for inexistente ou se a operação violar as regras de
     * inserção em fileiras.
     * 
     * @param testada Carta a ser inserida
     * @param fileiraDestino Número da fileira de destino
     * @throws JogoException
     * @see Fileira
     */
    public void validarInsercao(Carta testada, int fileiraDestino) throws JogoException{
        
        if (testada == null) {
            throw new JogoException("Nenhuma carta foi passada como parâmetro!");
        }
            
        if ( (fileiraDestino < 0) && (fileiraDestino >= fileiras.length) ) {
            throw new JogoException("O número da fileira deve estar entre 0 e "+(fileiras.length-1)+".");    
        }

        Fileira destino = fileiras[fileiraDestino];        
        destino.validarInsercao(testada);
    }
    
    /**
     * Insere uma carta na fileira selecionada. 
     * 
     * @param movida Carta a ser inserida
     * @param fileiraDestino Número da fileira de destino
     */
    public void inserirCarta(Carta movida, int fileiraDestino) {
        Fileira destino = fileiras[fileiraDestino];
        destino.inserir(movida);       
    }

    /**
     * Insere uma sequência de cartas na fileira selecionada. Assume-se que a 
     * inserção da carta na extremidade da pilha na fileira seja um movimento 
     * válido. Lança uma exceção caso a pilha de cartas esteja vazia.
     * 
     * @param cartasMovidas Lista de cartas
     * @param fileiraDestino Número da fileira de destino
     * @throws JogoException 
     * @see Fileira
     */
    public void inserirSequenciaCartas(Stack<Carta> cartasMovidas, final int fileiraDestino) 
            throws JogoException{                
        Fileira destino = fileiras[fileiraDestino];        
        destino.inserirGrupoCartas(cartasMovidas);
    }

    /**
     * Retorna a carta na extremidade da fileira, REMOVENDO-A desta fileira.
     * 
     * @param numFileira Número da fileira
     * @return
     * @throws JogoException 
     * @see Fileira
     */
    public Carta getCartaTopoFileira(final int numFileira) throws JogoException{

        if ( (numFileira < 0) && (numFileira >= fileiras.length) ) {
            throw new JogoException("O número da fileira deve estar entre 0 e "+(fileiras.length-1)+".");    
        }
        
        return fileiras[numFileira].extrairTopo();        
    }

    /**
     * Extrai uma pilha de cartas da fileira. O limite superior da pilha é 
     * definido pelo índice da carta, especificado como parâmetro. 
     * Lança uma exceção caso a fileira esteja vazia, se o índice da carta 
     * estiver incorreto ou se a carta selecionada estiver virada para baixo.
     * 
     * @param fileira
     * @param indiceCartaSelecionada
     * @return
     * @throws JogoException 
     * @see Fileira
     */
    public Stack<Carta> getSequenciaCartas(int fileira, final int indiceCartaSelecionada) throws JogoException{

        if ( (fileira < 0) && (fileira >= fileiras.length) ) {
            throw new JogoException("O número da fileira deve estar entre 0 e "+(fileiras.length-1)+".");
        }
        
        return fileiras[fileira].extrairCartas(indiceCartaSelecionada);                    
    }
    
    /**
     * Retorna uma carta da fileira, SEM removê-la.
     * 
     * @param numFileira Número da fileira
     * @param numCarta Indice da carta na fileira
     * @return
     * @throws JogoException 
     * @see Fileira
     */
    public Carta verCarta(final int numFileira, final int numCarta) throws JogoException{
        return fileiras[numFileira].verCarta(numCarta);
    }

    /**
     * Retorna a carta na extremidade da fileira selecionada. Lança uma exceção
     * se o número da fileira não existir, se a fileira estiver vazia ou a carta
     * estiver virada para baixo.
     * 
     * @param fileira Número da fileira
     * @return
     * @throws JogoException 
     * @see Fileira
     */
    public Carta verCartaTopo(final int fileira) throws JogoException{
        
        //  Evita problemas com índices errados
        if ( (fileira < 0) || (fileira >= fileiras.length) ) {
            throw new JogoException("O número da fileira deve estar entre 0 e "+(fileiras.length-1)+".");
        }
                
        return fileiras[fileira].verTopo();                
    }

    /**
     * Retorna uma representação, em String, das fileiras e seus respectivos
     * conteúdos.
     * 
     * @return 
     */
    @Override
    public String toString() {

        StringBuilder exibicao = new StringBuilder();

        exibicao.append("========\n");
        exibicao.append("FILEIRAS\n");
        exibicao.append("========\n\n");

        for (int i = 0; i < NUM_FILEIRAS; i++) {
            exibicao.append(" [Fileira ");
            exibicao.append(i);
            exibicao.append("]\t");
        }
        exibicao.append("\n");

        int l = 0;                
        
        for (int i = 0; i < NUM_FILEIRAS; i++) {

            for (int j = l; j < fileiras[i].tamanho(); j++, l++) {

                for (int k = 0; k < NUM_FILEIRAS; k++) {                    
                    
                    try{
                        if(fileiras[k].cartaEstaVirada(j)){
                            exibicao.append(fileiras[k].verCarta(j));
                            exibicao.append("\t"); 
                        }
                        else{
                            exibicao.append("(CARTA VIRADA)");
                            exibicao.append("\t"); 
                        }                                     
                    }                             
                    catch(JogoException ex){                        
                        exibicao.append("     -\t\t");                                              
                    }                    
                }

                exibicao.append("\n");
            }
        }
        exibicao.append("\n\n------------------------------------------------\n");
        return exibicao.toString();
    }
}
