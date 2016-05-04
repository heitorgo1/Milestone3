package Model.managers;

import Model.exceptions.JogoException;
import Model.stacks.CelulaLivre;
import util.Carta;

/**Gerencia uma coleção de Freecells*/
public class GerenciadorCelulaLivre {
	
	/**Quantidade de Freecells do jogo*/
	private static final int NUM_FREECELLS = 4;
	
	/**Instância única dessa classe (Padrão Singleton)*/
	private static  GerenciadorCelulaLivre INSTANCE;
	
	/**Coleção de Freecells*/
	private CelulaLivre[] cells = new CelulaLivre[NUM_FREECELLS];
	
	private GerenciadorCelulaLivre() {
		criarCelulasLivres();
	}

	/**Novas instâncias de CelulaLivre para a coleção de Freecells */
	private void criarCelulasLivres() {
		
		for (int i = 0; i < NUM_FREECELLS; i++) {
			cells[i] = new CelulaLivre();
		}
	}
	
	/**Pegar instância única dessa classe.
	 * @return	Instância da classe*/
	public static GerenciadorCelulaLivre getInstance(){
		if (INSTANCE == null) INSTANCE = new GerenciadorCelulaLivre();
		return INSTANCE;
	}
	
	/**Verifica a inserção de uma carta numa Freecell de determinado índice.
	 * @param movida	Carta movida
	 * @param cell	Inteiro representando indice da Freecell receptora*/
	public void validarInsercao(Carta movida, int cell) throws JogoException {
	        
	        if (movida == null) {
	            throw new JogoException("Nenhuma carta foi movida! Tente novamente.");
	        }
	        
	        //  Evita problemas com índices errados
	        if ( (cell < 0) || (cell >= cells.length) ) {
	            throw new JogoException("O número da célula livre deve estar entre 0 e "+(cells.length - 1)+".");               
	        }
	        
	        CelulaLivre destino = cells[cell];
	        destino.validarInsercao(movida);
	}
	
	/**Efetua a inserção de uma carta em uma determinada Freecell
	 * @param movida	Carta a ser inserida
	 * @param cell	Indica da Freecell receptora*/
	public void inserirCarta(Carta movida, int cell) throws JogoException{
        CelulaLivre destino = cells[cell];
        destino.inserir(movida);
    }
	
	/**Pegar a carta do topo de uma determinada Freecell, retirando-a da Freecell
	 * @return Carta do topo*/
	public Carta getCartaTopo(int cell) throws JogoException{
        
        if ( (cell < 0) || (cell >= cells.length) ) {
            throw new JogoException("O número da célula livre deve estar entre 0 e "+(cells.length - 1)+".");
        }
        
        return cells[cell].getTopo();
    }
	
	/**Pegar a carta do topo de uma determinada Freecell, sem retirá-la
	 * @return Carta do topo*/
	 public Carta verCartaTopo(int cell) throws JogoException{
	        
	        if ( (cell < 0) || (cell >= cells.length) ) {
	            throw new JogoException("O número da célula livre deve estar entre 0 e "+(cells.length - 1)+".");
	        }
	                
	        return cells[cell].verTopo();
	 }
	 
	 /**Limpa todas as Freecell do gerenciador*/
	 public void limparCelulas() {
		 for (CelulaLivre cell: cells) {
			 cell.limpar();
		 }
	 }
	 
	 public String toString() {

	        StringBuilder exibicao = new StringBuilder();

	        exibicao.append("=========\n");
	        exibicao.append("FREECELLS\n");
	        exibicao.append("=========\n\n");

	        for (int i = 0; i < NUM_FREECELLS; i++) {
	            exibicao.append("[FREECELL ");
	            exibicao.append(i);
	            exibicao.append("]\t");
	        }
	        exibicao.append("\n");

	        for (int i = 0; i < NUM_FREECELLS; i++) {
	            
	            try{
	                exibicao.append(cells[i].verTopo());
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
