/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.managers;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

import Model.exceptions.JogoException;
import util.Carta;

/**
 * Responsável por gerenciar as operações que envolvem o estoque e o descarte.
 * 
 * @author Anderson
 * @author Felipe
 */

public final class GerenciadorDescarte {

    private static final GerenciadorDescarte INSTANCE = new GerenciadorDescarte();

    private boolean virarTresCartas;

    private Stack<Carta> pilhaEstoque = new Stack<>();
    private Stack<Carta> pilhaDescarte = new Stack<>();

    private static final int MAX_CARTAS_VISIVEIS = 3;
    private Deque<Carta> bufferCartasVisiveis = new ArrayDeque<Carta>(MAX_CARTAS_VISIVEIS);

    public static GerenciadorDescarte getInstance() {
        return INSTANCE;
    }

    /**
     * Preenche a pilha de estoque com uma lista de cartas.
     * 
     * @param cartas Lista de cartas
     */
    public void preencher(final List<Carta> cartas) {

        pilhaEstoque.clear();
        pilhaDescarte.clear();
        pilhaEstoque.addAll(cartas);

        virarCartasParaBaixo();
        virarTresCartas = true;
    }

    /**
     * Configura a opção de virar cartas do descarte. Se o valor anterior for
     * "true", passa a ser "false" e vice-versa. O padrão inicial é de três 
     * cartas viradas.
     * 
     */
    public void virarTresCartas() {
        virarTresCartas = !virarTresCartas;
    }
    
    /**
     * Vira cartas do estoque. O número de cartas a ser viradas depende do
     * atributo "virarTresCartas". O método assume que as cartas do estoque
     * estejam viradas para baixo. Lança uma exceção caso o buffer e a pilha
     * de descarte estejam vazios.
     * 
     * @throws JogoException 
     */
    public void virarCartasEstoque() throws JogoException{

        Carta extraida;

        //  Se acabaram as cartas do estoque, deve-se mover todas as cartas do descarte para ele.
        if (pilhaEstoque.empty()) {
            
            if(bufferCartasVisiveis.isEmpty() && pilhaDescarte.empty()){
                throw new JogoException("Não há mais cartas no estoque ou no descarte!");
            }

            //  Move as cartas visíveis para a pilha de descarte
            while (!bufferCartasVisiveis.isEmpty()) {                
                pilhaDescarte.push(bufferCartasVisiveis.pollLast());
            }

            //  Move a pilha de descarte, na ordem, para a pilha de estoque
            while (!pilhaDescarte.empty()) {
                extraida = pilhaDescarte.pop();
                extraida.virarFace();
                pilhaEstoque.push(extraida);
            }

            return;
        }

        //  Move as cartas do buffer para a pilha de descarte    
        while (!bufferCartasVisiveis.isEmpty()) {
            pilhaDescarte.push(bufferCartasVisiveis.pollLast());
        }

        if (virarTresCartas) {
            for (int i = 0; i < MAX_CARTAS_VISIVEIS; i++) {
                if (!pilhaEstoque.empty()) {
                    extraida = pilhaEstoque.pop();
                    extraida.virarFace();
                    bufferCartasVisiveis.push(extraida);
                }
            }
        } else {
            System.out.println("VIRAR TRÊS CARTAS FALSO!");
            extraida = pilhaEstoque.pop();
            extraida.virarFace();
            bufferCartasVisiveis.push(extraida);
            //  Notificar a visão para atualizar as cartas visíveis.
        }
    }
    
    private void virarCartasParaBaixo() {

        for (Carta atual : pilhaEstoque) {
            if (atual.temFaceParaCima()) {
                atual.virarFace();
            }
        }
    }      
    
    /**
     * Retorna a carta no topo do descarte, SEM removê-la. Lança uma exceção se
     * a pilha de descarte estiver vazia.
     * 
     * @return
     * @throws JogoException 
     */
    public Carta verProxCarta() throws JogoException{
        
        //  Verifica se é possível obter ao menos uma carta antes de tentar a extração
        if (bufferCartasVisiveis.isEmpty()) {
            if (!pilhaDescarte.empty()) {
                return pilhaDescarte.peek();
            } //  Não tem carta para extrair
            else {
                throw new JogoException("Não há carta no descarte!");
            }
        }
        
        return bufferCartasVisiveis.peek();
    }
    
    /**
     * Retorna a carta no topo do descarte, REMOVENDO-A do buffer. Lança uma 
     * exceção se não houver mais cartas no buffer e na pilha.
     * 
     * @return
     * @throws JogoException 
     */
    public Carta getProxCarta() throws JogoException{

        Carta retirada;

        //  Verifica se é possível obter ao menos uma carta antes de tentar a extração
        if (bufferCartasVisiveis.isEmpty()) {
            if (!pilhaDescarte.empty()) {
                bufferCartasVisiveis.push(pilhaDescarte.pop());
            } //  Não tem carta para extrair
            else {
                throw new JogoException("Não há carta no descarte!");
            }
        }

        //  Nesse ponto, já é possível obter ao menos uma carta para extração
        retirada = bufferCartasVisiveis.pop();

        if (bufferCartasVisiveis.isEmpty() && !pilhaDescarte.empty()) {
            bufferCartasVisiveis.push(pilhaDescarte.pop());
        }

        return retirada;
    }
    
    /**
     * Retorna uma representação, em String, dos dados referentes ao estoque e
     * descarte.
     * 
     * @return 
     */
    @Override
    public String toString() {

        StringBuilder exibicao = new StringBuilder();

        exibicao.append("================\n");
        exibicao.append("ESTOQUE/DESCARTE\n");
        exibicao.append("================\n\n");

        exibicao.append("Estoque (");
        exibicao.append(pilhaEstoque.size());
        exibicao.append(")\t\n\n");

        exibicao.append("Descarte [Buffer] (");
        exibicao.append(bufferCartasVisiveis.size());
        exibicao.append(")\n\n");

        exibicao.append("Descarte [Total] (");
        exibicao.append(bufferCartasVisiveis.size() + pilhaDescarte.size());
        exibicao.append("):\n\n");
        exibicao.append("Cartas do buffer:\n\n");

        if (bufferCartasVisiveis.isEmpty()) {
            exibicao.append("     -\n");
        } else {

            for (Carta atual : bufferCartasVisiveis) {
                if (atual != null) {
                    exibicao.append("\t");
                    exibicao.append(atual);
                    exibicao.append("\n");
                }
            }
        }

        exibicao.append("\n------------------------------------------------\n");
        return exibicao.toString();
    }
}
