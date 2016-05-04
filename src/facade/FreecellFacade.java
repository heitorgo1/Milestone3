package facade;

import java.util.Stack;

import Model.exceptions.JogoException;
import Model.factories.FabricaRegrasFreecell;
import Model.factories.FabricaRegrasJogo;
import Model.managers.GerenciadorBaralho;
import Model.managers.GerenciadorCelulaLivre;
import Model.managers.GerenciadorFileiras;
import Model.managers.GerenciadorFundacoes;
import util.Carta;

/**Encapsula todas as operações do jogo*/
public class FreecellFacade {
	
	/**Fabrica de regras de inserção e distribuição*/
	private final FabricaRegrasJogo fabricaRegras;
    
    private final GerenciadorBaralho baralho;
    private final GerenciadorFileiras fileiras;
    private final GerenciadorFundacoes fundacoes;
    private final GerenciadorCelulaLivre freecells;
    
    private static FreecellFacade instance = null;
    
    private FreecellFacade(){
        fabricaRegras = new FabricaRegrasFreecell();
        baralho = GerenciadorBaralho.getInstance();
        fileiras = GerenciadorFileiras.getInstance(fabricaRegras.getNumFileiras());
        fileiras.setRegras(fabricaRegras.getRegraInsercaoFileira(), fabricaRegras.getRegraDistribuicaoFileira());
        fundacoes = GerenciadorFundacoes.getInstance();    
        freecells = GerenciadorCelulaLivre.getInstance();
    }
    
    public static FreecellFacade getInstance() {
    	if (instance == null) instance = new FreecellFacade();
    	return instance;
    }
    
    /**Iniciar novo jogo.*/
    public void iniciar() {
        distribuirCartas();
    }
    
    /**Cria um novo baralho e o distribui entre as fileiras.*/
    private void distribuirCartas() {
    	baralho.criarCartas();
        Stack<Carta> cartasBaralho = baralho.getCartas();

        fileiras.distribuirCartas(cartasBaralho);
        fundacoes.limparFundacoes();
        freecells.limparCelulas();
    }
    
    /**Movimento de uma fileira para uma fundação.
     * @param fileira	Fileira fonte
     * @param fundacao Fundacação destino*/
    public void moverFileiraFundacao(final int fileira, final int fundacao) 
            throws JogoException {        
        
        fundacoes.validarInsercao(fileiras.verCartaTopo(fileira), fundacao);
        fundacoes.inserirCarta(fileiras.getCartaTopoFileira(fileira), fundacao);        
            
        System.out.println("Jogada válida!");
    }
    
    /**Mesmo que o método anterior, mas entre Fileiras, sendo necessário o índice da carta movida para poder
     * realizar o movimento de múltiplas cartas.
     * @param fileiraOrigem Fileira de Origem
     * @param numCarta	Indice da Carta na Fileira a ser movida
     * @param fileiraDestino Fileira de Destino*/
    public void moverFileiraFileira(final int fileiraOrigem, final int numCarta,
            final int fileiraDestino) throws JogoException {
                     
        fileiras.validarInsercao(fileiras.verCarta(fileiraOrigem, numCarta), fileiraDestino);
        fileiras.inserirSequenciaCartas(fileiras.getSequenciaCartas(fileiraOrigem, numCarta),
                fileiraDestino);
 
        System.out.println("Jogada válida!");
    }  
    
    /**Mesmo que as anteriores*/
    public void moverFundacaoFileira(final int fundacao, final int fileira) throws JogoException {

        fileiras.validarInsercao(fundacoes.verCartaTopo(fundacao), fileira);
        fileiras.inserirCarta(fundacoes.getCartaTopo(fundacao), fileira);
        System.out.println("Jogada válida!");               
    }   
    /**Mesmo que as anteriores*/
    public void moverCelulaFileira(final int cell, final int fileira) throws JogoException{
    	fileiras.validarInsercao(freecells.verCartaTopo(cell), fileira);
        fileiras.inserirCarta(freecells.getCartaTopo(cell), fileira);
        System.out.println("Jogada válida!"); 
    }
    /**Mesmo que as anteriores*/
    public void moverCelulaFundacao(final int cell, final int fundacao) throws JogoException{
    	fundacoes.validarInsercao(freecells.verCartaTopo(cell), fundacao);
        fundacoes.inserirCarta(freecells.getCartaTopo(cell), fundacao);
        System.out.println("Jogada válida!"); 
    }
    /**Mesmo que as anteriores*/
    public void moverFundacaoCelula(final int fundacao, final int cell) throws JogoException {
    	freecells.validarInsercao(fundacoes.verCartaTopo(fundacao), cell);
    	freecells.inserirCarta(fundacoes.getCartaTopo(fundacao), cell);
        System.out.println("Jogada válida!");
    }
    /**Mesmo que as anteriores*/
    public void moverFileiraCelula(final int fileira, final int cell) throws JogoException {
    	freecells.validarInsercao(fileiras.verCartaTopo(fileira), cell);
    	freecells.inserirCarta(fileiras.getCartaTopoFileira(fileira), cell);
        System.out.println("Jogada válida!");
    }
    
    /**Verifica se o jogo já foi vencido.
     * @return Jogo vencido ou não*/
    public boolean jogoVencido(){
        return fundacoes.pilhasCompletas();
    }
    
    /**Auxilia na visão de jogo, gerando Strings para cada coleção de pilhas*/
    public String exibirMesa(){
        return freecells.toString() + fileiras.toString() + fundacoes.toString() +
                "\n\n";
    }  
}
