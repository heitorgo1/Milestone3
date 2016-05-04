package Model.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Model.stacks.Fileira;
import util.Carta;

/**Classe que representa a regra de distribução de cartas para fileiras no Freecell*/
public class RegraDistribFileiraFreecell implements RegraDistribuicaoFileira {

	/**As cartas do baralho são distribuídas da seguinte forma: 7 cartas para as 4 primeiras fileiras,
	 * 6 cartas para as 4 últimas, todas para cima.
	 * @param baralho	Baralho de onde vem as cartas
	 * @param fileiras	Coleção de fileiras onde serão inseridas as cartas*/
	@Override
	public void distribuir(Stack<Carta> baralho, Fileira[] fileiras) {
		
		
		for (int i = 0; i < fileiras.length; i++) {
			
			List<Carta> cartasFileira = new ArrayList<>();
			
			if (i <= 3)  {
				for (int j = 0; j < 7; j++) {
					Carta carta = baralho.pop();
					carta.virarFace();
					cartasFileira.add(carta);
				}
			} else {
				for (int j = 0; j < 6; j++) {
					Carta carta = baralho.pop();
					carta.virarFace();
					cartasFileira.add(carta);
				}
			}
			
			fileiras[i].preencher(cartasFileira);
		}
	}

}
