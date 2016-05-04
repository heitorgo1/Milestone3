/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.stacks;

import static util.Valor.AS;
import static util.Valor.REI;

import java.util.Stack;

import Model.exceptions.JogoException;
import util.Carta;
import util.Valor;

/**
 * Representa uma fundação de cartas. Valida as inserções e remoções realizadas 
 * pelo jogador, além de realizar toda a manipulação de cartas requerida pelo jogo.
 * 
 * @author Anderson
 * @author Felipe
 */
public final class Fundacao {
    
    private Stack<Carta> pilhaCartas;    
    private static final int MAX_CARTAS = Valor.values().length;
    
    /**
     * Cria a estrutura que armazenará as cartas da fundação.
     */
    public Fundacao(){
        pilhaCartas = new Stack<>();
    }

    /**
     * Valida a inserção de UMA carta na extremidade da fundação. Lança uma 
     * exceção caso as regras de inserção sejam violadas, se o parâmetro 
     * passado for nulo ou a carta inserida estiver com a face voltada para 
     * baixo.
     * 
     * @param inserida Carta a ser inserida
     * @throws JogoException      
     */    
    public void validarInsercao(Carta inserida) throws JogoException{
        
        if (inserida == null) {
            throw new JogoException("Nenhuma carta foi movida! Tente novamente.");
        }
        
        if(!inserida.temFaceParaCima()){
            throw new JogoException("A carta está virada para baixo!");
        }
        
        if (pilhaCartas.empty()) {
            if(inserida.VALOR != AS){
                throw new JogoException("Apenas um AS pode ser movido para uma fundação vazia!");
            }
        }
        else{            
            Carta topoFundacao = pilhaCartas.peek();
            
            if(topoFundacao.VALOR == REI){
                throw new JogoException("JOGADA INVALIDA! A fundação ja esta completa.");
            }

            if (!inserida.ehSucessora(topoFundacao)) {
                throw new JogoException("JOGADA INVALIDA! A carta movida não eh sucessora "
                        + "imediata da carta que está no topo da fundação.");                        
            }

            if(topoFundacao.NAIPE != inserida.NAIPE){                     
                throw new JogoException("JOGADA INVALIDA! O naipe da carta eh diferente!");    
            }
        }
    }

    /**
     * Insere UMA carta na fundação. Assume-se que, previamente, a carta tenha 
     * sido considerada válida para inserção.
     * 
     * @param inserida Carta a ser inserida 
     */
    public void inserir(Carta movida) throws JogoException{        
        pilhaCartas.push(movida);
    }    
    
    /**
     * Retorna a carta na extremidade da fundação, SEM removê-la da pilha. Lança
     * uma exceção caso a fundação esteja vazia ou se a carta estiver virada para
     * baixo.
     * 
     * @return Carta na extremidade da fundação
     * @throws JogoException 
     */    
    public Carta verTopo() throws JogoException{
        
        if(pilhaCartas.empty()){
            throw new JogoException("Não há cartas na fundação!");            
        }
        
        Carta topo = pilhaCartas.peek();
        
        if(!topo.temFaceParaCima()){
            throw new JogoException("A carta selecionada está virada para baixo!");
        }
        
        return pilhaCartas.peek();
    }

    /**
     * Retorna a carta na extremidade da fundação, REMOVENDO-A da pilha. Lança
     * uma exceção caso a fundação esteja vazia ou se a carta estiver virada para
     * baixo.
     * 
     * @return Carta na extremidade da fundação
     * @throws JogoException 
     */
    public Carta getTopo() throws JogoException{
        
        if(pilhaCartas.empty()){
            throw new JogoException("Não há cartas na fundação!");            
        }
        
        if(!pilhaCartas.peek().temFaceParaCima()){
            throw new JogoException("A carta selecionada está virada para baixo!");
        }
        
        return pilhaCartas.pop();
    }
    
    /**
     * Elimina o conteúdo da fundação, caso não esteja vazia.
     * 
     */
    public void limpar(){
        
        if(!pilhaCartas.empty()){
            pilhaCartas.clear();
        }        
    }

    /**
     * Retorna "true" caso a fundação esteja na sua capacidade máxima.
     * @return 
     */    
    public boolean estaCompleta(){
        return pilhaCartas.size() == MAX_CARTAS;
    }
    
    /**
     * Retorna "true" caso a fundação esteja vazia.
     * @return 
     */    
    public boolean estaVazia(){
        return pilhaCartas.empty();
    }
    
    /**
     * Retorna a quantidade atual de cartas na fundação
     * @return Número de cartas na fileira
     */
    public int tamanho(){
        return pilhaCartas.size();
    }
}
