/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Stack;

import org.junit.Test;

import Model.exceptions.JogoException;
import Model.rules.RegraInsercaoFileiraPaciencia;
import junit.framework.TestCase;
import util.Carta;
import util.Naipe;
import util.Valor;

/**
 *
 * @author Anderson
 * @author Felipe
 */
public class RegraInsercaoPacienciaTest extends TestCase {

    RegraInsercaoFileiraPaciencia regra;
    Stack<Carta> fileira;

    Carta carta, carta2, carta3, carta4;

    public RegraInsercaoPacienciaTest() {
        regra = new RegraInsercaoFileiraPaciencia();
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
     * Testa a inserção de uma carta diferente de REI em uma fileira vazia. O
     * teste deve falhar se não for lançada uma exceção
     */
    @Test
    public void testInsercaoFileiraVazia() throws Exception {
        System.out.println("Testar inserção de carta diferente de Rei em fileira vazia");
        fileira.clear();

        try {
            regra.testarInsercao(carta3, fileira);
            fail("Uma exceção deveria ser lançada!");
        } catch (JogoException exc) {
            assertTrue(true);
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

    /**
     * Testa a inserção de uma carta que não é antecessora à que está na
     * fileira. O teste falha se não lançar uma exceção.
     */
    @Test
    public void testInsercaoCartaNaoAntecessora() {

        System.out.println("Testar insercao de carta nao antecessora");

        Stack<Carta> fila = new Stack<>();
        fila.clear();
        fila.push(carta2);

        try {
            regra.testarInsercao(carta4, fila);
            fail("Uma exceção deveria ser lançada!");
        } catch (JogoException exc) {
            assertTrue(true);
        }

    }
}
