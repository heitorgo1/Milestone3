/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.EmptyStackException;
import java.util.Stack;

import org.junit.Test;

import Model.exceptions.JogoException;
import Model.factories.FabricaRegrasJogo;
import Model.factories.FabricaRegrasPaciencia;
import Model.managers.GerenciadorFileiras;
import junit.framework.TestCase;
import util.Carta;
import util.Naipe;
import util.Valor;

/**
 *
 * @author Anderson
 * @author Felipe
 */
public class GerenciadorFileirasTest extends TestCase {
    
    FabricaRegrasJogo fabricaRegras = new FabricaRegrasPaciencia();
    Stack<Carta> baralho = new Stack<>();
    Carta carta = new Carta(Valor.AS, Naipe.COPAS);
    Carta carta2 = new Carta(Valor.CINCO, Naipe.COPAS);
    GerenciadorFileiras fileiras = GerenciadorFileiras.getInstance(fabricaRegras.getNumFileiras());

    public GerenciadorFileirasTest() {
        
        fileiras.setRegras(fabricaRegras.getRegraInsercaoFileira(), fabricaRegras.getRegraDistribuicaoFileira());

    }

    /**
     * Test of criarFileiras method, of class GerenciadorFileiras.
     */
    @Test
    public void testCriarFileiras() {
        
        System.out.println("Testar criar Fileiras");

        //cria-se NUM_FILEIRAS, 
        fileiras.criarFileiras();

        //insere carta em uma fileira
        fileiras.inserirCarta(carta, 6);

        //tenta puxar a carta do topo da pilha (se houver carta o teste funcionou)
        try {
            fileiras.getCartaTopoFileira(6);
        } catch (JogoException exc) {
            fail(exc.getMessage());
        }

    }

    /**
     * Testa o método de resetar todas as fileiras.
     * @throws Model.JogoException
     */
    @Test
    public void testResetarFileiras() throws JogoException {
        System.out.println("Testar resetar Fileiras");

        //insere uma carta em uma fileira
        fileiras.inserirCarta(carta, 0);

        //reseta todas as fileiras
        fileiras.limpar();

        //tenta puxar a carta do topo da pilha (se não houver carta o teste funcionou)
        try {
            fileiras.getCartaTopoFileira(0);
            fail("Uma exceção deveria ser lançada!");
        } catch (JogoException exc) {
            assertTrue(true);
        }
    }

    /**
     * Testa a distribuição de cartas do baralho para as fileiras.
     * @throws Model.JogoException
     */
    @Test
    public void testDistribuirCartas() throws JogoException {

        // NAO PRECISA TESTAR.
        System.out.println("Testar distribuir Cartas");
        int i = 0;
        while (i <= 52) {
            baralho.add(carta);
            i++;
        }

        try {
            fileiras.limpar();
            fileiras.distribuirCartas(baralho);

        } catch (EmptyStackException exc) {
            fail(exc.getMessage());
        }

    }

    /**
     * Testa a distribuição de cartas de um baralho menor que 52 cartas para as
     * fileiras. O teste falha se não lançar uma exceção.
     * @throws Model.JogoException
     */
    @Test
    public void testDistribuirCartasBaralhoMenor() throws JogoException {
        System.out.println("Testar distribuir Cartas com baralho menor que 52 cartas");
        int i = 0;
        while (i <= 25) {
            baralho.add(carta);
            i++;
        }

        try {
            fileiras.distribuirCartas(baralho);
            fail("Uma exceção deveria ser lançada!");
        } catch (EmptyStackException exc) {
            assertTrue(true);
        }
    }

    /**
     * Testa o método de virar a carta do topo de uma fileira.
     * @throws Model.JogoException
     */
    @Test
    public void testVirarFaceTopo() throws JogoException {
        System.out.println("Testar virar carta do topo");
       
        Carta cartaA = new Carta(Valor.AS, Naipe.COPAS);

        fileiras.limpar();
        //insere uma carta
        fileiras.inserirCarta(cartaA, 0);

        //a carta deve está virada para cima
        assertTrue(fileiras.verCartaTopo(0).temFaceParaCima());

        //Vira a face da carta do topo.
        fileiras.virarFaceTopo(0);

        //a carta deve está virada para cima
        assertTrue(fileiras.verCartaTopo(0).temFaceParaCima());

    }

    /**
     * Testa a inserção de uma carta em uma fileira.
     * @throws Model.JogoException
     */
    @Test
    public void testInserirCarta() throws JogoException {
        System.out.println("inserirCarta");

        //  Comparar com o topo da pilha depois da inserção
        try {
            fileiras.limpar();
            fileiras.inserirCarta(carta, 0);
        } catch (ArrayIndexOutOfBoundsException exc) {
            fail(exc.getMessage());
        }

    }

    /**
     * Testa a inserção de uma carta em uma fileira inexistente. O teste falha
     * se não lançar uma exceção.
     * @throws Model.JogoException
     */
    @Test
    public void testInserirCartaFileiraInexistente() throws JogoException {
        System.out.println("Testar inserir Carta em uma fileira inexistente");
        try {
            fileiras.limpar();
            fileiras.inserirCarta(carta, 20);
            fail("Uma exceção deveria ser lançada!");
        } catch (ArrayIndexOutOfBoundsException exc) {
            assertTrue(true);
        }

    }

    /**
     * Testa a inserção de um grupo de cartas ao mesmo tempo em uma fileira.
     * @throws Model.JogoException
     */
    @Test
    public void testInserirSequenciaCartas() throws JogoException {
        System.out.println("inserirSequenciaCartas");
        Stack<Carta> listaCartas = new Stack<>();
        listaCartas.add(carta);
        listaCartas.add(carta2);
        listaCartas.add(carta);

        try {
            fileiras.limpar();
            fileiras.inserirSequenciaCartas(listaCartas, 0);
        } catch (JogoException exc) {
            fail(exc.getMessage());
        }
    }

    /**
     * Testa a inserção de um grupo de cartas ao mesmo tempo em uma fileira.
     * @throws Model.JogoException
     */
    @Test
    public void testGetSequenciaCartas() throws JogoException {
        
        System.out.println("Testar obter uma Sequencia Cartas");

        Stack<Carta> listaCartas = new Stack<>();
        Stack<Carta> temp = new Stack<>();

        Carta cartaA = new Carta(Valor.AS, Naipe.COPAS);
        Carta cartaB = new Carta(Valor.CINCO, Naipe.COPAS);
        Carta cartaC = new Carta(Valor.SETE, Naipe.COPAS);

        listaCartas.clear();
        listaCartas.push(cartaC);
        listaCartas.push(cartaB);
        listaCartas.push(cartaA);

        temp.push(cartaC);
        temp.push(cartaB);
        temp.push(cartaA);

        fileiras.limpar();
        
        //  insere uma sequência de cartas em uma fileira
        fileiras.inserirSequenciaCartas(listaCartas, 0);

        //compara a sequencia de cartas retiradas com um vetor de cartas teste. 
        assertEquals(temp, fileiras.getSequenciaCartas(0, 0));

    }

    /**
     * Testa o método de ver a carta que está no topo de uma fileira.
     * @throws Model.JogoException
     */
    @Test
    public void testVerCartaTopo() throws JogoException {
        System.out.println("Testar ver Carta do Topo");
        Stack<Carta> listaCartas = new Stack<>();

        listaCartas.add(carta2);
        listaCartas.add(carta2);
        listaCartas.add(carta);
        listaCartas.add(carta);

        fileiras.limpar();
        //insere carta em uma fileira
        fileiras.inserirSequenciaCartas(listaCartas, 0);//insere na pilha

        //compara se a carta do topo é a mesma que foi inserida
        assertEquals(fileiras.verCartaTopo(0), carta2);
    }

}
