package freecelltest;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

import util.Carta;
import util.Naipe;
import util.Valor;
import Model.exceptions.JogoException;
import Model.rules.RegraInsercaoFileiraFreecell;

public class RegraInsercaoFreecellTest {
	RegraInsercaoFileiraFreecell regra;
    Stack<Carta> fileira;

    Carta carta, carta2, carta3, carta4;

    public RegraInsercaoFreecellTest() {
        regra = new RegraInsercaoFileiraFreecell();
        fileira = new Stack<>();
        carta = new Carta(Valor.AS, Naipe.COPAS);
        carta2 = new Carta(Valor.REI, Naipe.COPAS);
        carta3 = new Carta(Valor.CINCO, Naipe.COPAS);
        carta4 = new Carta(Valor.QUATRO, Naipe.ESPADAS);
    }

    /**
     * Testa a inserção de uma carta REI em uma pilha vazia. O teste deve falhar
     * se lançar uma exceção
     */
    @Test
    public void testInsercaoReiEmFileiraVazia() throws Exception {
        System.out.println("Testar inserção de Rei em Fileira vazia");
        fileira.clear();

        try {
            regra.testarInsercao(carta2, fileira);
            assertTrue(true);
        } catch (JogoException exc) {
            fail("A jogada é válida. A exceção a seguir não deveria ser lançada:\n "
                    + exc.getMessage());
        }
    }
    
    /**
     * Testa a inserção de uma carta antecessora e de cor diferente da que está
     * na fileira. O teste deve falhar se lançar uma exceção.
     */
    @Test
    public void testTestarInsercaoEhAntecessora() throws Exception {
        System.out.println("Testar inserção de carta antecessora");
        Stack<Carta> fila = new Stack<>();
        fila.clear();        
        fila.push(carta3);

        try {
            regra.testarInsercao(carta4, fila);
            assertTrue(true);
        } catch (JogoException exc) {
            fail("A jogada é válida. A exceção a seguir não deveria ser lançada:\n "
                    + exc.getMessage());
        }

    }

}
