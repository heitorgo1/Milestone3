/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.stacks;

import java.util.List;
import java.util.Stack;

import Model.exceptions.JogoException;
import Model.rules.RegraInsercaoFileira;
import util.Carta;

/**
 * Representa uma fileira de cartas. Valida as inserções e remoções de cartas 
 * realizadas pelo jogador. A regra de validação é determinada pelo cliente,
 * segundo o Padrão Strategy.
 * 
 * @author Anderson
 * @author Felipe
 */
public final class Fileira {
    
    private Stack<Carta> pilhaCartas;
    private RegraInsercaoFileira regraInsercao;
    
    /**
     * Cria a estrutura que armazenará as cartas da fileira.
     */
    public Fileira(){
        pilhaCartas = new Stack<>();
    }    
    
    /**
     * Define a regra de inserção de cartas na fileira.
     * 
     * @param novaRegra Regra de inserção
     */
    public void setRegraInsercao(final RegraInsercaoFileira novaRegra){
        regraInsercao = novaRegra;
    }
    
    /**
     * Define o estado inicial da fileira, preenchendo a pilha com uma
     * determinada quantidade de cartas. As cartas presentes antes do preenchimento
     * são eliminadas.
     * 
     * @param cartas Lista de cartas
     */
    public void preencher(List<Carta> cartas) {
        limpar();
        pilhaCartas.addAll(cartas);
    }

    /**
     * Valida a inserção de UMA carta na extremidade da fileira. Lança uma 
     * exceção caso a regra de inserção seja violada ou o parâmetro passado
     * for nulo.
     * 
     * @param inserida Carta a ser inserida
     * @throws JogoException 
     * @see RegraInsercaoPaciencia
     */
    public void validarInsercao(Carta inserida) throws JogoException{        
        regraInsercao.testarInsercao(inserida, pilhaCartas);
    }
    
    /**
     * Insere UMA carta na fileira. Assume-se que, previamente, a carta tenha 
     * sido considerada válida para inserção.
     * 
     * @param inserida Carta a ser inserida 
     */
    public void inserir(Carta inserida) {                
        pilhaCartas.push(inserida);
    }    
    
    /**
     * Retorna uma carta de acordo com o seu respectivo índice na fileira. Lança 
     * uma exceção caso o índice seja inválido ou a carta esteja virada para baixo.
     * 
     * @param indiceCarta Indice da carta na fileira
     * @return 
     * @throws JogoException 
     */
    public Carta verCarta(final int indiceCarta) throws JogoException{
        
        if( (indiceCarta < 0) || (indiceCarta >= pilhaCartas.size()) ){
            throw new JogoException("Indice de carta invalido!");
        }
        
        Carta selecionada = pilhaCartas.get(indiceCarta);
        
        if(!selecionada.temFaceParaCima()){
            throw new JogoException("A carta está virada para baixo!");
        }
        
        return selecionada;
    }
    
    /**
     * Retorna "true" se a carta estiver com a face para cima. Lança uma exceção
     * se o índice da carta for inválido.
     * 
     * @param indiceCarta Indice da carta a ser verificada
     * @return
     * @throws JogoException 
     */
    public boolean cartaEstaVirada(final int indiceCarta) throws JogoException{
        
        if( (indiceCarta < 0) || (indiceCarta >= pilhaCartas.size()) ){
            throw new JogoException("Indice de carta invalido!");
        }
        
        return pilhaCartas.get(indiceCarta).temFaceParaCima();
    }
    
    /**
     * Vira a carta, na extremidade da fileira, caso esteja com a face voltada
     * para baixo. A ação é ignorada se não houver carta na fileira ou se a face 
     * da carta no topo da pilha estiver voltada para cima.
     * 
     */
    public void virarFaceTopo() {
                  
        Carta topo = pilhaCartas.peek();
        
        if(topo == null){
            return;
        }
        
        if(!topo.temFaceParaCima()){
            topo.virarFace();
        }
    }
    
    /**
     * Vira todas as cartas da fileira. Lança uma exceção se a fileira estiver 
     * vazia.
     * 
     * @throws JogoException
     */    
    public void virarCartas() throws JogoException{
        
        if(pilhaCartas.empty()){
            throw new JogoException("A fileira está vazia!");            
        }
        
        for(Carta atual : pilhaCartas){
            atual.virarFace();
        }
    }
    
    /**
     * Retorna a carta na extremidade da fileira, SEM removê-la da pilha. Lança
     * uma exceção caso a fileira esteja vazia ou se a carta estiver virada para
     * baixo.
     * 
     * @return Carta na extremidade da fileira
     * @throws JogoException 
     */
    public Carta verTopo() throws JogoException{
        
        if(pilhaCartas.empty()){
            throw new JogoException("A fileira está vazia!");            
        }
        
        if(!pilhaCartas.peek().temFaceParaCima()){
            throw new JogoException("A carta selecionada está virada para baixo!");
        }
        
        return pilhaCartas.peek();
    }
    
    /**
     * Retorna a carta na extremidade da fileira, REMOVENDO-A da pilha. Lança
     * uma exceção caso a fileira esteja vazia ou se a carta estiver virada para
     * baixo.
     * 
     * @return Carta na extremidade da fileira
     * @throws JogoException 
     */
    public Carta extrairTopo() throws JogoException{
        
        if(pilhaCartas.empty()){
            throw new JogoException("Não há cartas na fileira!");            
        }
        
        if(!pilhaCartas.peek().temFaceParaCima()){
            throw new JogoException("A carta selecionada está virada para baixo!");
        }
        
        return pilhaCartas.pop();
    }
    
    /**
     * Insere uma pilha de cartas na fileira. Assume-se que a inserção da 
     * carta na extremidade da pilha na fileira seja um movimento válido. Lança
     * uma exceção caso a pilha de cartas esteja vazia.
     * 
     * @throws JogoException 
     */    
    public void inserirGrupoCartas(Stack<Carta> cartasMovidas) throws JogoException{
        
        if(cartasMovidas.empty()){            
            throw new JogoException("Não foram passadas cartas para inserção!");
        }      
                
        while(!cartasMovidas.empty()){
            pilhaCartas.push(cartasMovidas.pop());
        }         
    }
    
    /**
     * Extrai uma pilha de cartas da fileira. O limite superior da pilha é 
     * definido pela carta com o índice especificado como parâmetro. 
     * Lança uma exceção caso a fileira esteja vazia, se o índice da carta 
     * estiver incorreto ou se a carta selecionada estiver virada para baixo.
     * 
     * @param indiceCartaSelecionada Indice da carta na fileira 
     * @return Pilha de cartas com índice a partir do especificado
     * @throws JogoException 
     */
    public Stack<Carta> extrairCartas(final int indiceCartaSelecionada) throws JogoException{
        
        if (pilhaCartas.empty()) {
            throw new JogoException("A fileira está vazia.");                    
        }
        
        if ( (indiceCartaSelecionada < 0) && (indiceCartaSelecionada >= pilhaCartas.size()) ) {    
            throw new JogoException("O índice da carta selecionada é inválido : ("+indiceCartaSelecionada+")");                
        }   
        
        Carta selecionada = pilhaCartas.get(indiceCartaSelecionada);

        if (!selecionada.temFaceParaCima()) {
            throw new JogoException("A carta selecionada está virada para baixo!");
        }                    

        Stack<Carta> cartasMovidas = new Stack<>();

        for (int i = (pilhaCartas.size() - 1); i >= indiceCartaSelecionada; i--) {
            cartasMovidas.push(pilhaCartas.pop());
        }
        
        return cartasMovidas;
    }
    
    /**
     * Elimina o conteúdo da fileira, caso não esteja vazia.
     * 
     */
    public void limpar(){
        
        if(!pilhaCartas.empty()){
            pilhaCartas.clear();
        }        
    }
    
    /**
     * Retorna "true" caso a fileira esteja vazia.
     * @return 
     */
    boolean estaVazia(){
        return pilhaCartas.empty();
    }
    
    /**
     * Retorna a quantidade atual de cartas na fileira
     * @return Número de cartas na fileira
     */
    public int tamanho(){
        return pilhaCartas.size();
    }
}
