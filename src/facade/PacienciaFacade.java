/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.Stack;

import Model.JogoInterface;
import Model.exceptions.JogoException;
import Model.factories.FabricaRegrasJogo;
import Model.factories.FabricaRegrasPaciencia;
import Model.managers.GerenciadorBaralho;
import Model.managers.GerenciadorDescarte;
import Model.managers.GerenciadorFileiras;
import Model.managers.GerenciadorFundacoes;
import util.Carta;

/**
 * Fachada do jogo Paciência. Contém as estruturas que representam o baralho,
 * o descarte, as fileiras e as fundações. Responsável pela lógica do jogo e 
 * pela validação dos movimentos realizados pelo jogador.
 * 
 * @author Anderson
 * @author Felipe
 */
public final class PacienciaFacade implements JogoInterface{
    
    private final FabricaRegrasJogo fabricaRegras;
    
    private final GerenciadorBaralho baralho;
    private final GerenciadorDescarte descarte;
    private final GerenciadorFileiras fileiras;
    private final GerenciadorFundacoes fundacoes;
    
    private int pontos;
    @SuppressWarnings("unused")
	private long tempo;
    
    private final static int PONTOS_MOV_PARA_FILEIRA = 5;
    private final static int PONTOS_MOV_PARA_FUNDACAO = 10;    
    
    /**
     * Inicializa a fachada do jogo Paciência. Cria os gerenciadores de descarte,
     * baralho, fileiras e fundações.
     */
    private PacienciaFacade(){
        fabricaRegras = new FabricaRegrasPaciencia();
        descarte = GerenciadorDescarte.getInstance();
        baralho = GerenciadorBaralho.getInstance();
        fileiras = GerenciadorFileiras.getInstance(fabricaRegras.getNumFileiras());
        fileiras.setRegras(fabricaRegras.getRegraInsercaoFileira(), fabricaRegras.getRegraDistribuicaoFileira());
        fundacoes = GerenciadorFundacoes.getInstance();        
    }
    
    private static class PacienciaFacadeSingletonHolder{
        private static final PacienciaFacade INSTANCIA = new PacienciaFacade();
    }
    
    /**
     * Retorna uma instância única da fachada, segundo o Padrão Singleton.
     * 
     * @return Instância única da classe.
     */
    public static PacienciaFacade getInstance() {
        return PacienciaFacadeSingletonHolder.INSTANCIA;
    }

    /**
     * Responsável por inicializar as variáveis de controle do jogo, definindo o 
     * estado inicial de uma partida. Zera os pontos e distribui as cartas 
     * segundo as regras.
     */
    @Override
    public void iniciar() {
        pontos = 0;
        tempo = 0;
        distribuirCartas();
    }
    
    private void distribuirCartas() {
        Stack<Carta> cartasBaralho = baralho.getCartas();

        fileiras.distribuirCartas(cartasBaralho);
        descarte.preencher(cartasBaralho);        
        fundacoes.limparFundacoes();
    }
    
    /**
     * Configura a variável utilizada para definir quantas cartas do estoque 
     * serão viradas por vez. O padrão é de três cartas viradas do estoque.
     * 
     */
    @Override
    public void alterarNumeroCartasViradas() {
        descarte.virarTresCartas();
    }    
        
    /**
     * Responsável por virar uma certa quantidade de cartas (uma ou três) no 
     * estoque. A quantidade é definida pelo método "alterarNumeroCartasViradas".
     *    
     * @throws JogoException
     * @see setVirarTresCartas#alterarNumeroCartasViradas
     */
    @Override
    public void virarCartasEstoque() throws JogoException{        
        descarte.virarCartasEstoque();
    }    
    
    /**
     * Vira a carta no topo de uma fileira, caso aquela esteja com a face para 
     * cima. A ação é ignorada se não houver carta na fileira ou se a face da 
     * carta no topo da pilha estiver voltada para cima.
     * 
     * @param fileira Número da fileira cuja carta deve ser virada
     */
    @Override
    public void virarCartaFileira(final int fileira) {                  
        fileiras.virarFaceTopo(fileira);
    }    
    
    /**
     * Move a carta no topo do descarte para uma fileira. Lança uma exceção, 
     * caso o movimento viole as regras do jogo.
     * 
     * @param fileira Número da fileira de destino
     * @throws JogoException
     */
    @Override
    public void moverDescarteFileira(final int fileira) throws JogoException {

        fileiras.validarInsercao(descarte.verProxCarta(), fileira);
        fileiras.inserirCarta(descarte.getProxCarta(), fileira);
        
        pontos += PONTOS_MOV_PARA_FILEIRA;        
        System.out.println("Jogada válida!");
    }  
    
    /**
     * Move a carta no topo do descarte para uma fundação. Lança uma exceção 
     * caso o movimento viole as regras do jogo ou os parâmetros sejam inválidos.
     * 
     * @param fundacao Número da fundação de destino
     * @throws JogoException
     */
    @Override
    public void moverDescarteFundacao(final int fundacao) throws JogoException {
     
        fundacoes.validarInsercao(descarte.verProxCarta(), fundacao);
        fundacoes.inserirCarta(descarte.getProxCarta(), fundacao);
        
        pontos += PONTOS_MOV_PARA_FUNDACAO;        
        System.out.println("Jogada válida!");        
    }    

    /**
     * Move a carta que está no fim de uma fileira para uma fundação.
     * 
     * @param fileira Número da fileira de origem
     * @param fundacao Número da fundação de destino
     * @throws JogoException
     */
    @Override
    public void moverFileiraFundacao(final int fileira, final int fundacao) 
            throws JogoException {        
        
        fundacoes.validarInsercao(fileiras.verCartaTopo(fileira), fundacao);
        fundacoes.inserirCarta(fileiras.getCartaTopoFileira(fileira), fundacao);        
            
        pontos += PONTOS_MOV_PARA_FUNDACAO;
        System.out.println("Jogada válida!");
    }
    
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
    @Override
    public void moverFileiraFileira(final int fileiraOrigem, final int numCarta,
            final int fileiraDestino) throws JogoException {
                     
        fileiras.validarInsercao(fileiras.verCarta(fileiraOrigem, numCarta), fileiraDestino);
        fileiras.inserirSequenciaCartas(fileiras.getSequenciaCartas(fileiraOrigem, numCarta),
                fileiraDestino);
 
        pontos += PONTOS_MOV_PARA_FILEIRA;       
        System.out.println("Jogada válida!");
    }     
    
    /**
     * Move a carta no topo de uma fundação para uma fileira. Lança 
     * uma exceção caso o movimento viole as regras do jogo ou os parâmetros
     * sejam inválidos.
     * 
     * @param fundacao Número da fundação de origem
     * @param fileira Número da fileira de destino
     * @throws JogoException
     */
    @Override
    public void moverFundacaoFileira(final int fundacao, final int fileira) throws JogoException {

        fileiras.validarInsercao(fundacoes.verCartaTopo(fundacao), fileira);
        fileiras.inserirCarta(fundacoes.getCartaTopo(fundacao), fileira);
        System.out.println("Jogada válida!");               
    }   
    
    /**
     * Retorna "true" caso o jogo tenha sido vencido.
     * 
     * @return Status de vitória
     */
    @Override
    public boolean jogoVencido(){
        return fundacoes.pilhasCompletas();
    }
    
    /**
     * Exibe o estado atual da mesa. Mostra o conteúdo da pilha de descarte, 
     * fileiras e fundações.
     * 
     * @return String representando a mesa.
     */
    @Override
    public String exibirMesa(){
        return descarte.toString() + fileiras.toString() + fundacoes.toString() +
                "\n\nPONTUAÇÃO: "+ pontos +"\n";
    }            
}
