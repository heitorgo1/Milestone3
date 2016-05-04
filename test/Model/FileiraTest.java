/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Stack;

import org.junit.Test;

import Model.exceptions.JogoException;
import Model.managers.GerenciadorBaralho;
import Model.stacks.Fileira;
import util.Carta;
import util.Naipe;
import util.Valor;

/**
 *
 * @author Anderson
 * @author Felipe
 */
public class FileiraTest {
    
    Fileira fileira = new Fileira();
    GerenciadorBaralho gerBar = new GerenciadorBaralho();
    Stack<Carta> grupoCartas = new Stack<>();
    Carta carta = new Carta(Valor.AS, Naipe.COPAS);
    Carta carta2 = new Carta(Valor.CINCO, Naipe.COPAS);
    
    public FileiraTest() {
        fileira.preencher(gerBar.getCartas());
        grupoCartas.add(carta);
        grupoCartas.add(carta2);
    }

  

    /**
     * Testa se é possível visualizar uma carta que não está no topo (virada para baixo).
     * Se lançar exceção, passou no teste
     */
    @Test
    public void testVerCartaViradaParaBaixo() throws Exception {       
        System.out.println("Testar visualizar carta virada para baixo");
        try{
            fileira.verCarta(2);
            fail("Uma exceção deveria ser lançada!");
        }
        catch(JogoException exc){
            assertTrue(true);
        }
    }
    
    
    
    /**
     * Testa o método se a carta está virada.
     */
    @Test
    public void testCartaEstaVirada() throws Exception {  
        System.out.println("Testar ver o estado atual da carta");
       
            assertFalse(fileira.cartaEstaVirada(2));         
    }

   
    /**
     * Testa o método que diz qual a carta do topo da fileira.
     */
    @Test
    public void testVerTopo() throws Exception {
        System.out.println("Testa visualizar a carta do topo");
       
        try{
            fileira.virarFaceTopo();
            fileira.verTopo();
        }
        catch(JogoException exc){
            fail(exc.getMessage());
        }
    }

    /**
     * Testa o método que retira a carta do topo da fileira.
     */
    @Test
    public void testExtrairTopo() throws Exception {
        System.out.println("Testar retirar a carta do topo");
       
        try{
            fileira.virarFaceTopo();
            fileira.extrairTopo();
        }
        catch(JogoException exc){
            fail(exc.getMessage());
        }
    }

    /**
     * Testa o método que insere um grupo de cartas numa fileira.
     */
    @Test
    public void testInserirGrupoCartas() throws Exception {
        System.out.println("Testar inserir grupo de cartas");
       
       try{
           fileira.inserirGrupoCartas(grupoCartas);
       }
       catch(JogoException exc){
           exc.getMessage();
       }
    }

    /**
     * Testa o método de extrair várias cartas de uma fileira.
     */
    @Test
    public void testExtrairCartas() throws Exception {
        System.out.println("Testar extrair mais de uma carta");
       
       try{
           fileira.virarCartas();
           fileira.extrairCartas(5);
       }
       catch(JogoException exc){
           fail(exc.getMessage());
       }
    }

    /**
     * Test o método responsável por limpar uma fileira.
     */
    @Test
    public void testLimpar() {
        System.out.println("Testar método para limpar a fileira");
       
        fileira.limpar();
        try{
            fileira.verTopo();
        fail("Uma exceção deveria ser lançada!");
        }
        catch(JogoException exc){
            assertTrue(true);
        }
    }

   
    /**
     * Test of tamanho method, of class Fileira.
     */
    @Test
    public void testTamanho() {
        System.out.println("Testar o tamanho de uma fileira");
       
      assertEquals(fileira.tamanho(), 52);
    }
    
}
