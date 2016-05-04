package View;

import java.util.Scanner;

import Model.exceptions.JogoException;
import facade.FreecellFacade;

public class VisaoFreecell {
	
	private FreecellFacade modeloJogo;
    private StringBuilder menuPrincipal;
    private static Scanner teclado = new Scanner(System.in);
    private static final int OPCAO_SAIR_JOGO = 10;

    
    public VisaoFreecell(FreecellFacade ff) {
    	modeloJogo = ff;
    	menuPrincipal = new StringBuilder();
    	criarComponentes();
    }
    
    public void criarComponentes() {

        menuPrincipal.append("\n\n=========\n");
        menuPrincipal.append("FREECELL\n");
        menuPrincipal.append("=========\n\n");
        menuPrincipal.append("MENU PRINCIPAL\n\n");
        menuPrincipal.append("Escolha uma das opções a seguir:\n\n");
        menuPrincipal.append(" 1 - Mover de célula para fileira\n");
        menuPrincipal.append(" 2 - Mover de célula para fundação\n");
        menuPrincipal.append(" 3 - Mover de fileira para célula\n");
        menuPrincipal.append(" 4 - Mover de fundação para célula\n");
        menuPrincipal.append(" 5 - Mover de fileira para fundação\n");
        menuPrincipal.append(" 6 - Mover de fileira para fileira\n");
        menuPrincipal.append(" 7 - Mover de fundação para fileira\n");
        menuPrincipal.append(" 8 - Ver mesa\n");
        menuPrincipal.append(" 9 - Reiniciar\n");
        menuPrincipal.append(" "); menuPrincipal.append(OPCAO_SAIR_JOGO); menuPrincipal.append(" - Sair do jogo\n");
    }
	
	public void exibir() {

        int opcao = -1;

        while (true) {

            if (modeloJogo.jogoVencido()) {
                System.out.println("Fim de jogo: VOCÊ VENCEU! PARABÉNS!!!");
                System.out.println("Pressione qualquer tecla para encerrar o jogo.");
                teclado.nextLine();
                break;
            } else {
                try {
                    
                    System.out.println(modeloJogo.exibirMesa()+"\n"+ menuPrincipal);
                    opcao = getOpcao();

                    if (opcao == OPCAO_SAIR_JOGO) {
                        break;
                    }

                    executarMenuPrincipal(opcao);
                    
                } catch (JogoException ex) {
                    System.err.println(ex.getMessage());
                    System.out.println("Pressione qualquer tecla para continuar...");
                    teclado.nextLine();
                }
            }
        }

        System.out.println("Encerrando o jogo...");
        System.exit(0);
    }
	
	 private static int getOpcao() throws JogoException {

	        try {
	            int opcaoSelecionada = Integer.parseInt(teclado.nextLine());
	            return opcaoSelecionada;
	        } catch (NumberFormatException e) {
	            throw new JogoException("A opção informada deve ser um número!");
	        }
	    }
	 
	 private void executarMenuPrincipal(int opcaoSelecionada) throws JogoException {

	        int fileiraOrigem, fileiraDestino, fundacaoOrigem, fundacaoDestino, cartaSelecionada;
	        int cellOrigem, cellDestino;
	        
	        switch (opcaoSelecionada) {
	            case 1:
	            	System.out.print("Insira o número da célula: ");
	                cellOrigem = getOpcao();
	                System.out.print("Insira o número da fileira: ");
	                fileiraDestino = getOpcao();
	                modeloJogo.moverCelulaFileira(cellOrigem, fileiraDestino);
	                return;
	            case 2:
	            	System.out.print("Insira o número da célula: ");
	                cellOrigem = getOpcao();
	                System.out.print("Insira o número da fundação: ");
	                fundacaoDestino = getOpcao();
	                modeloJogo.moverCelulaFundacao(cellOrigem, fundacaoDestino);
	                return;
	            case 3:
	            	System.out.print("Insira o número da fileira: ");
	                fileiraOrigem = getOpcao();
	                System.out.print("Insira o número da célula: ");
	                cellDestino = getOpcao();
	                modeloJogo.moverFileiraCelula(fileiraOrigem, cellDestino);
	                return;
	            case 4:
	            	System.out.print("Insira o número da fundação: ");
	                fundacaoOrigem = getOpcao();
	                System.out.print("Insira o número da célula: ");
	                cellDestino = getOpcao();
	                modeloJogo.moverFundacaoCelula(fundacaoOrigem, cellDestino);
	                return;
	            case 5:
	            	System.out.print("Insira o número da fileira: ");
	                fileiraOrigem = getOpcao();
	                System.out.print("Insira o número da fundação: ");
	                fundacaoDestino = getOpcao();
	                modeloJogo.moverFileiraFundacao(fileiraOrigem, fundacaoDestino);
	                return;
	            case 6:
	            	System.out.print("Insira o número da fileira de origem: ");
	                fileiraOrigem = getOpcao();
	                System.out.print("Insira o índice da carta que deseja selecionar na fileira: ");
	                cartaSelecionada = getOpcao();
	                System.out.print("Insira o número da fileira de destino: ");
	                fileiraDestino = getOpcao();
	                modeloJogo.moverFileiraFileira(fileiraOrigem, cartaSelecionada, fileiraDestino);
	                return;
	            case 7:
	            	System.out.print("Insira o número da fundação: ");
	                fundacaoOrigem = getOpcao();
	                System.out.print("Insira o número da fileira: ");
	                fileiraDestino = getOpcao();
	                modeloJogo.moverFundacaoFileira(fundacaoOrigem, fileiraDestino);
	                return;
	            case 8:
	            	System.out.println(modeloJogo.exibirMesa()
	                        + "\n\nPressione qualquer tecla para voltar ao menu...");
	                teclado.nextLine();
	                return;
	            case 9:
	            	System.out.println("Reiniciando...");
	            	modeloJogo.iniciar();
	            case OPCAO_SAIR_JOGO:
	                return;
	            default:
	                throw new JogoException("Opção inválida. Tente novamente.");
	        }
	    }
}
