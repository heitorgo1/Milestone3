/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.JogoInterface;
import Model.exceptions.JogoException;

import java.util.Scanner;

/**
 * Gerencia a interface com o usuário, exibindo informações sobre o jogo e
 * processando as opções selecionadas.
 * 
 * @author Anderson
 * @author Felipe
 */
public class VisaoPaciencia{
    
    private final JogoInterface modeloJogo;
    private final StringBuilder menuPrincipal;
    private static Scanner teclado = new Scanner(System.in);
    private static final int OPCAO_SAIR_JOGO = 10;

    /**
     * Cria a visão de linha de comando, associando-a ao modelo de um jogo.
     * 
     * @param modeloJogo
     */
    public VisaoPaciencia(JogoInterface modeloJogo) {        
        this.modeloJogo = modeloJogo;
        menuPrincipal = new StringBuilder();
    }

    /**
     * Cria os componentes necessários para exibição do menu.
     *  
     */
    public void criarComponentes() {

        menuPrincipal.append("\n\n=========\n");
        menuPrincipal.append("PACIENCIA\n");
        menuPrincipal.append("=========\n\n");
        menuPrincipal.append("MENU PRINCIPAL\n\n");
        menuPrincipal.append("Escolha uma das opções a seguir:\n\n");
        menuPrincipal.append(" 1 - Puxar carta do estoque\n");               
        menuPrincipal.append(" 2 - Virar uma/três cartas\n"); 
        menuPrincipal.append(" 3 - Virar carta de fileira\n");                             
        menuPrincipal.append(" 4 - Mover de descarte para fileira\n");
        menuPrincipal.append(" 5 - Mover de descarte para fundação\n");
        menuPrincipal.append(" 6 - Mover de fileira para fundação\n");
        menuPrincipal.append(" 7 - Mover de fileira para fileira\n");
        menuPrincipal.append(" 8 - Mover de fundação para fileira\n");
        menuPrincipal.append(" 9 - Ver mesa\n");
        menuPrincipal.append(" "); menuPrincipal.append(OPCAO_SAIR_JOGO); menuPrincipal.append(" - Sair do jogo\n");
    }
    
    /**
     * Inicia a visão, exibindo-a e processando a entrada do usuário.
     */
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

        switch (opcaoSelecionada) {
            case 1:
                System.out.println("Extraindo cartas do estoque...");
                modeloJogo.virarCartasEstoque();
                return;
            case 2:
                System.out.println("Alterando número de cartas viradas do estoque...");
                modeloJogo.alterarNumeroCartasViradas();
                return;
            case 3:
                System.out.print("Insira o número da fileira: ");
                fileiraDestino = getOpcao();
                modeloJogo.virarCartaFileira(fileiraDestino);
                return;
            case 4:
                System.out.print("Insira o número da fileira: ");
                modeloJogo.moverDescarteFileira(getOpcao());
                return;
            case 5:
                System.out.print("Insira o número da fundacao: ");
                modeloJogo.moverDescarteFundacao(getOpcao());
                return;
            case 6:
                System.out.print("Insira o número da fileira: ");
                fileiraOrigem = getOpcao();
                System.out.print("Insira o número da fundação: ");
                fundacaoDestino = getOpcao();
                modeloJogo.moverFileiraFundacao(fileiraOrigem, fundacaoDestino);
                return;
            case 7:
                System.out.print("Insira o número da fileira de origem: ");
                fileiraOrigem = getOpcao();
                System.out.print("Insira o índice da carta que deseja selecionar na fileira: ");
                cartaSelecionada = getOpcao();
                System.out.print("Insira o número da fileira de destino: ");
                fileiraDestino = getOpcao();
                modeloJogo.moverFileiraFileira(fileiraOrigem, cartaSelecionada, fileiraDestino);
                return;
            case 8:
                System.out.print("Insira o número da fundação: ");
                fundacaoOrigem = getOpcao();
                System.out.print("Insira o número da fileira: ");
                fileiraDestino = getOpcao();
                modeloJogo.moverFundacaoFileira(fundacaoOrigem, fileiraDestino);
                return;
            case 9:
                System.out.println(modeloJogo.exibirMesa()
                        + "\n\nPressione qualquer tecla para voltar ao menu...");
                teclado.nextLine();
                return;
            case OPCAO_SAIR_JOGO:
                return;
            default:
                throw new JogoException("Opção inválida. Tente novamente.");
        }
    }
}
