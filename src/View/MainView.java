package View;

import java.util.Scanner;

import starters.FreecellStarter;
import starters.PacienciaStarter;

/**Apresenta a tela de seleção. Usuário pode escolher jogar Paciência ou Freecell*/
public class MainView {

	public static void main (String[] args) {
		
		System.out.println("Selecione o que deseja jogar: ");
		System.out.println("1 - Paciência");
		System.out.println("2 - FreeCell");
		
		Scanner sc = new Scanner(System.in);
		
		int op = sc.nextInt();
		
		switch (op) {
			case 1: PacienciaStarter.start(); break;
			case 2: FreecellStarter.start(); break;
		}
		
	}
}
