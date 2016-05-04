package Model.rules;

import static util.Valor.AS;

import java.util.Stack;

import Model.exceptions.JogoException;
import util.Carta;

/**Regra de inserção em Fileira do Freecell*/
public class RegraInsercaoFileiraFreecell  extends RegraInsercaoFileira{

	/**Verifica se a carta pode ser inserida em uma fileira. A carta inserida não pode ser AS
	 * , deve ser sucessora da carta do topo e de cor diferente da carta do topo. Caso a Fileira esteja vazia,
	 * qualquer carta pode ser aceita.
	 * @param inserida	Carta a ser inserida
	 * @param fileira	Fileira onde a carta será inserida*/
	@Override
	public void testarInsercao(Carta inserida, Stack<Carta> fileira) throws JogoException {
		 if(fileira.empty()){            
	            return;
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
