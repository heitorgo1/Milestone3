package freecelltest;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import util.Carta;
import util.Naipe;
import util.Valor;
import Model.exceptions.JogoException;
import Model.stacks.CelulaLivre;

public class VerificaCelulaLivreTest {
	
	CelulaLivre celula = new CelulaLivre();
	Carta carta1 = new Carta(Valor.DOIS, Naipe.COPAS);
	Carta carta2 = new Carta(Valor.VALETE, Naipe.PAUS);
	
	public VerificaCelulaLivreTest(){}
	
	@Test
	public void testIniciaCelula()throws JogoException{
		System.out.println("Testa inicializa��o de Celula");
	       
		try{
            celula.verTopo();
        }
        catch(JogoException exc){
            assertTrue(true);
        }
	}
	
	@Test
	public void testIniciaCelula2()throws JogoException{
		try{
	        celula.inserir(carta1);   
			celula.verTopo();
	       }
	       catch(JogoException exc){
	           fail(exc.getMessage());
	       }
	}
	
	@Test
	public void testIniciaCelula3()throws JogoException{
		try{
	        celula.inserir(carta1);   
			celula.verTopo();
	       }
	       catch(JogoException exc){
	           fail(exc.getMessage());
	       }
		try{
            celula.inserir(carta2);
            assertTrue(celula.verTopo().equals(carta2));
        }
        catch(JogoException exc){
        	fail(exc.getMessage());
        }
	}
}
