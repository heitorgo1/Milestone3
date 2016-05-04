/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.factories;

import Model.rules.RegraDistribFileiraPaciencia;
import Model.rules.RegraDistribuicaoFileira;
import Model.rules.RegraInsercaoFileira;
import Model.rules.RegraInsercaoFileiraPaciencia;

/**
 * Fabrica concreta com as regras do jogo Paciência. Fornece o número de 
 * fileiras e os objetos que representam as regras de inserção e distribuição.
 * 
 * @author Anderson
 */
public final class FabricaRegrasPaciencia extends FabricaRegrasJogo{
    
    private static final int NUM_FILEIRAS = 7;
        
    @Override
	public int getNumFileiras() {
        return NUM_FILEIRAS;
    }

    @Override
    public RegraInsercaoFileira getRegraInsercaoFileira() {
        return new RegraInsercaoFileiraPaciencia();
    }

    @Override
    public RegraDistribuicaoFileira getRegraDistribuicaoFileira() {
        return new RegraDistribFileiraPaciencia();
    }
    
}
