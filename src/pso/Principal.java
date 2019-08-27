package pso;

import java.util.ArrayList;

public class Principal {

    public static final int nuItens = 10;
    public static final double[] valor = {1,2,10,2,69,5,5,8,1,1};
    public static final double[] peso = {1,2,4,1,1,6,8,2,9,10};
    public static final double knapsackMaxSize = 15;


    public static void main(String[] args) {

        Nuvem nuvemParticulas = new Nuvem();
        nuvemParticulas.executarPSO();

        ArrayList<Integer> selecionados = nuvemParticulas.getMelhorSolucaoNuvem();
        System.out.println("------PROCESSO CONCLUIDO------");

    }
}
