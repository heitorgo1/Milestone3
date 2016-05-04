/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Model.managers.GerenciadorFileiras;
import Model.stacks.Fileira;
import util.Carta;

/**
 * Regra do jogo Paciência para distribuição de cartas em fileiras.
 * 
 * @author Anderson
 * @see GerenciadorFileiras
 */
public final class RegraDistribFileiraPaciencia implements RegraDistribuicaoFileira{

    @Override
    public void distribuir(Stack<Carta> baralho, final Fileira[] fileiras) {
        
        for (int i = 0; i < fileiras.length; i++) {
            
            List<Carta> cartasFileira = new ArrayList<>();
            
            for (int j = 0; j < i + 1; j++) {
                cartasFileira.add(baralho.pop());
            }
            
            fileiras[i].preencher(cartasFileira);
            fileiras[i].virarFaceTopo();
        }
    }
    
}
