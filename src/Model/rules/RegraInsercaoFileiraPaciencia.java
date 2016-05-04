/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.rules;

import static util.Valor.AS;
import static util.Valor.REI;

import java.util.Stack;

import Model.exceptions.JogoException;
import Model.stacks.Fileira;
import util.Carta;

/**
 * Regra do jogo Paciência para inserção de cartas em uma fileira.
 * 
 * @author Anderson
 * @author Felipe
 * @see Fileira
 */
public final class RegraInsercaoFileiraPaciencia extends RegraInsercaoFileira{

    @Override
	public void testarInsercao(final Carta inserida, final Stack<Carta> fileira) throws JogoException {
        
        if(fileira.empty()){            
            if (inserida.VALOR != REI) {
                throw new JogoException("Apenas um REI pode ser inserido em uma fileira vazia!");                                                                        
            }
        }
        else {
            
            Carta topo = fileira.peek();
            
            if (topo.VALOR == AS) {
                throw new JogoException("JOGADA INVALIDA! Não eh possível "
                    + "inserir cartas quando a última carta da fileira "
                    + "de destino eh um AS."); 
            }

            if(!inserida.ehAntecessora(topo)){
                throw new JogoException("JOGADA INVALIDA! A carta inserida deve "
                        + "ter valor imediatamente inferior ao da última carta da "
                        + "fileira de destino");
            }

            if (inserida.getCor().equals(topo.getCor())) {
                throw new JogoException("JOGADA INVALIDA! A cor da carta inserida "
                    + "deve ser diferente da cor da última carta na fileira de destino.");    
            }
        }
    }    
}
