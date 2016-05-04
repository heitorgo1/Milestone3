package starters;

import View.VisaoFreecell;
import facade.FreecellFacade;

/**
 * Classe que inicia a aplicação do jogo Freecell. Cria a interface com usuário e a 
 * associa ao modelo do jogo.
 * 
 */
public class FreecellStarter {

	public static void start() {
		FreecellFacade jogo = FreecellFacade.getInstance();
		VisaoFreecell visao = new VisaoFreecell(jogo);
		
        jogo.iniciar();
        visao.exibir();
	}
}
