package Model.stacks;

import Model.exceptions.JogoException;
import util.Carta;

/**
 * Classe que representa uma Freecell do jogo Freecell.
 * Só pode conter 1 carta nela, virada para cima a qualquer instante
 * */
public class CelulaLivre {

	private Carta carta;
	
	public CelulaLivre() {
		carta = null;
	}
	
	/**Verifica se a carta a ser inserida pode ser inserida
	 * @param inserida	Carta inserida*/
	 public void validarInsercao(Carta inserida) throws JogoException{
		 if (inserida == null) throw new JogoException("Nenhuma carta foi movida! Tente novamente.");
		 if (carta != null) throw new JogoException("Já existe uma carta na célula");
	 }
	 
	 /**Insere a carta na Célula*/
	 public void inserir(Carta movida) throws JogoException{        
	      carta = movida;
	 }  
	 
	 /**Retorna a carta do topo da Célula
	  * @return Carta do topo*/
	 public Carta verTopo() throws JogoException {
		 if (carta == null) throw new JogoException("A célula está vazia!");
		 return carta;
	 }
	 
	 /**Retira a carta da Célula e a retorna
	  * @return Carta do topo*/
	 public Carta getTopo() {
		 Carta ref = carta;
		 carta = null;
		 return ref;
	 }
	 
	 /**Reinicia a Célula*/
	 public void limpar() {
		 carta = null;
	 }
	 
	 /**Verifica se a Célula está vazia
	  * @return Booleano representando se a Célula está vazia ou não*/
	 public boolean estaVazia() {
		return (carta==null);
	 }
}
