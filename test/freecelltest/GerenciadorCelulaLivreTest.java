package freecelltest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Model.exceptions.JogoException;
import Model.managers.GerenciadorCelulaLivre;
import util.Carta;
import util.Naipe;
import util.Valor;

public class GerenciadorCelulaLivreTest {
	

	private Carta carta = new Carta(Valor.DOIS, Naipe.COPAS);
	GerenciadorCelulaLivre gcl = GerenciadorCelulaLivre.getInstance();
	 
	public GerenciadorCelulaLivreTest() throws JogoException{
		gcl.validarInsercao(carta, 0);
	}
	
	@Test
	public void inserirCartaTest() throws JogoException{
		gcl.inserirCarta(carta, 1);
		
		assertTrue (gcl.verCartaTopo(1).equals(carta));
	}
}
