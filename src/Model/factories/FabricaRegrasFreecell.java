package Model.factories;

import Model.rules.RegraDistribFileiraFreecell;
import Model.rules.RegraDistribuicaoFileira;
import Model.rules.RegraInsercaoFileira;
import Model.rules.RegraInsercaoFileiraFreecell;

/**
 * Fabrica concreta com as regras do jogo Freecell. Fornece o número de 
 * fileiras e os objetos que representam as regras de inserção e distribuição.
 * 
 */
public class FabricaRegrasFreecell extends FabricaRegrasJogo{

	 private static final int NUM_FILEIRAS = 8;
	
	@Override
	public int getNumFileiras() {
		return NUM_FILEIRAS;
	}

	@Override
	public RegraInsercaoFileira getRegraInsercaoFileira() {
		return new RegraInsercaoFileiraFreecell();
	}

	@Override
	public RegraDistribuicaoFileira getRegraDistribuicaoFileira() {
		return new RegraDistribFileiraFreecell();
	}

}
