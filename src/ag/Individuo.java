package ag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Individuo implements Comparable<Individuo> {

    private final Random random = new Random();
    private int[] genes;
    private Double aptidao;

    public Individuo(int qtdItens) {
        do {
            genes = new int[qtdItens];
            iniciarGenes();
        } while (!validar());
    }

    public double avaliar(){

        double apt=0;

        for (int i = 0; i < genes.length; i++) {
            if (genes[i] == 1) apt += Principal.valor[i];
        }
        return apt;
    }

    public boolean validar(){

        double p=0;

        for (int i = 0; i < genes.length; i++) {
            if (genes[i] == 1) p += Principal.peso[i];
        }

        if(p <= Principal.knapsackMaxSize){
            return true;
        }else{
            return false;
        }

    }

    public Individuo(int[] genes) {

        this.genes = genes;
        do {
            if (random.nextDouble() <= Genetico.taxaMutacao) {
                int posAleatoria = random.nextInt(genes.length);
                mutacao(posAleatoria);
            }
        }while (!validar());

    }

    private void iniciarGenes() {
        for (int i = 0; i < genes.length; i++) {
            if (Math.random() < 0.5) {
                genes[i] = 0;
            } else {
                genes[i] = 1;
            }
        }
    }

    private void mutacao(int posicao) {
        if (genes[posicao] == 1) {
            genes[posicao] = 0;
        } else {
            genes[posicao] = 1;
        }
    }

    public ArrayList<Integer> getAtributos() {
        ArrayList<Integer> atributos = new ArrayList<>();
        for (int i = 0; i < genes.length; i++) {
            if (genes[i] == 0) {
                atributos.add(i + 1);
            }
        }
        return atributos;
    }

    public void setGenes(int[] genes) {
        this.genes = genes;
    }

    public int[] getGenes() {
        return genes;
    }

    public void setAptidao(double aptidao) {
        this.aptidao = aptidao;
    }

    public double getAptidao() {
        return aptidao;
    }

    @Override
    public int compareTo(Individuo i) {
        return this.aptidao.compareTo(i.aptidao);
    }

    @Override
    public String toString() {
        return "cromossomes" + Arrays.toString(genes) + "aptidao" + aptidao;
    }
}
