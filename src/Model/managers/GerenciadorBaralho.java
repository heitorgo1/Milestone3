/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.managers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import util.Carta;
import util.Naipe;
import util.Valor;

/**
 * Gerencia a criação e o embaralhamento das cartas, fornecendo-as aos eventuais
 * clientes.
 * 
 * @author Anderson
 * @author Felipe
 */
public final class GerenciadorBaralho {

    private static final GerenciadorBaralho INSTANCE = new GerenciadorBaralho();

    private static final int MAX_CARTAS_BARALHO = (Valor.values().length) * (Naipe.values().length);
    private final List<Carta> baralho = new ArrayList<>(MAX_CARTAS_BARALHO);
    private Stack<Carta> pilhaBaralho = new Stack<Carta>();

    //  Singleton
    public static GerenciadorBaralho getInstance() {
        return INSTANCE;
    }
    
    /**
     * Inicializa o baralho, criando as 52 cartas.
     */
    public GerenciadorBaralho() {
        criarCartas();        
    }

    
    public void criarCartas() {

        for (Naipe naipeAtual : Naipe.values()) {
            for (Valor faceAtual : Valor.values()) {
                Carta carta = new Carta(faceAtual, naipeAtual);
                carta.virarFace();
                baralho.add(carta);
            }
        }
    }

    private void embaralhar() {
        Collections.shuffle(baralho);
    }
    
    /**
     * Retorna uma cópia das cartas criadas, devidamente embaralhadas.
     * 
     * @return Pilha de cartas
     */
    public Stack<Carta> getCartas() {

        embaralhar();
        pilhaBaralho.clear();
        pilhaBaralho.addAll(baralho);
        baralho.clear();
        return pilhaBaralho;
    }
}
