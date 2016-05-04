/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.junit.Test;

import Model.exceptions.JogoException;
import Model.managers.GerenciadorFundacoes;
import junit.framework.TestCase;
import util.Carta;
import util.Naipe;
import util.Valor;


/**
 *
 * @author Anderson
 * @author Felipe
 */
public class GerenciadorFundacoesTest extends TestCase{
   
    public GerenciadorFundacoesTest() {
        
    }

    /**
     * Testa retirar a carta que está no topo da fundação.     
     * @throws Model.JogoException
     */
    @Test
    public void testGetCartaTopo() throws JogoException {
        System.out.println("Testar obeter Carta do Topo");
        Carta cartaMovida = new Carta(Valor.AS, Naipe.COPAS);
      
        int fundacao = 1;
        
        //insere carta
        GerenciadorFundacoes.getInstance().inserirCarta(cartaMovida, fundacao);
       
        //pega a carta do topo
        try{
            GerenciadorFundacoes.getInstance().getCartaTopo(fundacao);
            
        }
        catch(JogoException exc){
           fail(exc.getMessage());  
        }
   
    }

   /**
     * Testa retirar uma carta de uma fundação vazia.
     * @throws Model.JogoException
     */
    @Test
    public void testGetCartaTopoFundacaoVazia() throws JogoException {
        System.out.println("Testar obter carta de fundacao vazia");
       try{
           GerenciadorFundacoes.getInstance().getCartaTopo(5);
           fail("Uma exceção deveria ser lançada!");
       }
       catch(JogoException exc){
           assertTrue(true);         
       }
       
    }
   
    /**
     * Testa a inserção de uma carta em uma fundação.
     * @throws Model.JogoException
     */
    @Test
    public void testInserirCarta() throws JogoException {
        System.out.println("Testar inserir Carta");        
        Carta cartaMovida = new Carta(Valor.AS, Naipe.COPAS);
        cartaMovida.virarFace();
        int fundacao = 1;
        try{
            GerenciadorFundacoes.getInstance().inserirCarta(cartaMovida, fundacao);
        }
       catch(JogoException exc){
           fail(exc.getMessage());        
       }
    }
    
     /**
     * Testa inserir uma carta em uma fundação inexistente.
     * O teste falha se não lançar uma exceção.
     * @throws Model.JogoException
     */
    @Test
    public void testInserirCartaFundacaoInexistente() throws JogoException {
        System.out.println("Testar inserir Carta");        
        Carta cartaMovida = new Carta(Valor.AS, Naipe.COPAS);
        cartaMovida.virarFace();
                
        int fundacao = 20;
        try{
           GerenciadorFundacoes.getInstance().inserirCarta(cartaMovida, fundacao);
           fail("Uma exceção deveria ser lançada!");
       }
       catch(ArrayIndexOutOfBoundsException exc){
           assertTrue(true);           
       }
                
    }
   
    /**
     * Testa se as fundações não estão cheias (13 cartas).
     */
    @Test
    public void testPilhasCompletas() {
       assertEquals(GerenciadorFundacoes.getInstance().pilhasCompletas(), false);
    }
       
}
