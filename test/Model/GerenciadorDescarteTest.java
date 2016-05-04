/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import java.util.Stack;

import org.junit.Test;

import Model.exceptions.JogoException;
import Model.managers.GerenciadorDescarte;
import junit.framework.TestCase;
import util.Carta;
import util.Naipe;
import util.Valor;

/**
 *
 * @author Anderson
 * @author Felipe
 */
public class GerenciadorDescarteTest extends TestCase {

    GerenciadorDescarte descarte = GerenciadorDescarte.getInstance();
    List<Carta> cartasEstoque = new Stack<>();
    Carta carta = new Carta(Valor.AS, Naipe.COPAS);
    Carta carta2 = new Carta(Valor.CINCO, Naipe.COPAS);

    public GerenciadorDescarteTest() {

    }

    /**
     * Testa o método que preenche o estoque e limpa o descarte.
     */
    @Test
    public void testPreencher() {
        System.out.println("Testar metodo de preencher o descarte");
       
        System.out.println("preencher");

        cartasEstoque.add(carta);
        cartasEstoque.add(carta2);

        descarte.preencher(cartasEstoque);

    }

    /**
     * Testa o método de virar 3 cartas
     */
    @Test
    public void testVirarTresCartas() {
        System.out.println("Testar virar 3 cartas no descarte");
       
        descarte.virarTresCartas();
    }

    /**
     * Testa se a carta do topo do descarte foi a última adicionada .
     * @throws Model.JogoException
     */
    @Test
    public void testVerProxCarta() throws JogoException {
        System.out.println("Testar visualizar proxima carta");
       
        cartasEstoque.add(carta);
        cartasEstoque.add(carta);

        descarte.preencher(cartasEstoque);
        descarte.virarCartasEstoque();//jogar cartas do estoque no descarte
        

        try {

            assertEquals(descarte.verProxCarta(), carta);
            
        } catch (JogoException exc) {
            fail("As cartas deveriam ser iguais!");
        }
    }
}
