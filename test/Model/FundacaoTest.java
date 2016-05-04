/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Stack;

import org.junit.Test;

import Model.exceptions.JogoException;
import Model.managers.GerenciadorBaralho;
import Model.stacks.Fundacao;
import util.Carta;
import util.Naipe;
import util.Valor;

/**
 *
 * @author Anderson
 * @author Felipe
 */
public class FundacaoTest {
    
    Fundacao fundacao = new Fundacao();
    GerenciadorBaralho gerBar = new GerenciadorBaralho();
    Stack<Carta> grupoCartas = new Stack<>();
    Carta carta = new Carta(Valor.AS, Naipe.COPAS);
    Carta carta2 = new Carta(Valor.CINCO, Naipe.COPAS);
    
    public FundacaoTest() {
       
    }

    
    /**
     * Testa o método de inserção em uma fundação.
     */
    @Test
    public void testInserir() throws Exception {
        System.out.println("Testa inserir carta em uma fundacao");
       
       try{
           fundacao.inserir(carta);
       }
       catch(JogoException exc){
           fail(exc.getMessage());
       }
    }

    /**
     * Testa o método que retorna a cara do topo de uma fundação.
     */
    @Test
    public void testVerTopo() throws Exception {
        System.out.println("Testar ver carta do topo");
       
        fundacao.inserir(carta);
        fundacao.inserir(carta);
        fundacao.inserir(carta2);
        
        assertEquals(fundacao.verTopo(), carta2);
        
    }

    

    /**
     * Testa o método de retirar todas as cartas de uma fundação.
     * se retorou exceção, está correto
     */
    @Test
    public void testLimpar() throws Exception{
        System.out.println("Testar limpar uma fundacao");
       
        //insere as cartas
        fundacao.inserir(carta);
        fundacao.inserir(carta);
        fundacao.inserir(carta2);
        
        //limpa fileira
        fundacao.limpar();
        
        //checa se há carta na fileira
        try{
            fundacao.verTopo();
            fail("Uma exceção deveria ser lançada!");
        }
        catch(JogoException exc){
            assertTrue(true);
        }
    }

    /**
     * Testa se a fundação já possui todas as cartas do mesmo naipe (13 cartas).
     */
    @Test
    public void testEstaCompletaTrue() throws Exception{
        System.out.println("Testar se a fundacao esta completa");
       
        fundacao.inserir(carta);
        fundacao.inserir(carta);
        fundacao.inserir(carta2);
        fundacao.inserir(carta);
        fundacao.inserir(carta);
        fundacao.inserir(carta2);
        fundacao.inserir(carta);
        fundacao.inserir(carta);
        fundacao.inserir(carta2);
        fundacao.inserir(carta);
        fundacao.inserir(carta);
        fundacao.inserir(carta2);
        fundacao.inserir(carta2);
        
         assertEquals(fundacao.estaCompleta(), true);
    }
    
    /**
     * Testa se a fundação já possui todas as cartas do mesmo naipe (13 cartas).
     * Porém, nesse teste não ha 13 cartas na fundação, estão espera-se um false.
     */
    @Test
    public void testEstaCompletaFalse() throws Exception{
        System.out.println("Testar o metodo que retorna se a fundacao nao esta completa");
       
        fundacao.inserir(carta);
        fundacao.inserir(carta);
        fundacao.inserir(carta2);
        fundacao.inserir(carta);
        fundacao.inserir(carta);
        fundacao.inserir(carta2);
        fundacao.inserir(carta);
        fundacao.inserir(carta);
       
        
         assertEquals(fundacao.estaCompleta(), false);
    }

    /**
     * Testa se a fundação está vazia.
     */
    @Test
    public void testEstaVazia() {
        System.out.println("Testar sea fundacao esta vazia");
       
       assertTrue(fundacao.estaVazia());
    }

    /**
     * Testa o tamanho da fundação.
     */
    @Test
    public void testTamanho() throws Exception {
        System.out.println("Testar o tamanho da fundacao");
       
         fundacao.inserir(carta);
        fundacao.inserir(carta);
        fundacao.inserir(carta2);
        
        assertEquals(fundacao.tamanho(), 3);
    }
    
}
