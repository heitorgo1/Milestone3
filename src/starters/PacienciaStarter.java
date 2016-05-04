/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starters;

import Model.JogoInterface;
import View.VisaoPaciencia;
import facade.PacienciaFacade;

/**
 * Classe que inicia a aplicação do jogo. Cria a interface com usuário e a 
 * associa ao modelo do jogo.
 * 
 * @author Anderson
 */
public class PacienciaStarter {

    /**
     * @param args the command line arguments
     */
    public static void start() {
        
        JogoInterface jogo = PacienciaFacade.getInstance();
        VisaoPaciencia visao = new VisaoPaciencia(jogo);
        
        jogo.iniciar();
        visao.criarComponentes();
        visao.exibir();
    }
    
}
