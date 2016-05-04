/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.junit.Test;

import Model.managers.GerenciadorBaralho;
import junit.framework.TestCase;

/**
 *
 * @author Anderson
 * @author Felipe
 */
public class GerenciadorBaralhoTest extends TestCase{
    
    public GerenciadorBaralhoTest() {
    }

   
    /**
     * Testa se o baralho está retornando as 52 cartas.
     */
    @Test
    public void testGetCartas() {
        System.out.println("Testar metodo que retorna as cartas");
       
        System.out.println("getCartas");
        assertEquals(GerenciadorBaralho.getInstance().getCartas().size(), 52);
    }
    
}
