package pso;

import sample.Main;

import java.util.ArrayList;
import java.util.Arrays;

public class Particula implements Comparable<Particula> {

    static final double alfa = 0.5;
    static final double beta = 2.05;
    static final double gama = 2.05;
    static final double vmax = 6;
    static final boolean velControl = true;

    private final int[] posicaoAtual;
    private double valorPosicaoAtual;

    private final int[] melhorPosicao;
    private Double valorMelhorPosicao;

    private final double[] velocidade;

    public Particula() {

        posicaoAtual = new int[Main.nuItens];
        melhorPosicao = new int[Main.nuItens];
        velocidade = new double[Main.nuItens];

        valorMelhorPosicao = new Double(0);

        do {
            inicializarPosicao();
        }while(!validar());

        inicializarVelocidade();

    }

    public boolean validar(){

        double p=0;

        for (int i = 0; i < Main.nuItens; i++) {
            if (posicaoAtual[i] == 1) p += Main.peso[i];
        }

        if(p <= Main.knapsackMaxSize){
            return true;
        }else{
            return false;
        }

    }

    private void inicializarPosicao() {

        for (int i = 0; i < posicaoAtual.length; i++) {
            if (Math.random() < 0.5) {
                posicaoAtual[i] = 0;
            } else {
                posicaoAtual[i] = 1;
            }
        }

    }

    private void inicializarVelocidade() {
        for (int i = 0; i < velocidade.length; i++) {
            velocidade[i] = 1;
        }
    }

    public void avaliarSolucao() {

        valorPosicaoAtual = 0;

        for(int i = 0; i< Main.nuItens; i++){
            if(posicaoAtual[i] == 1) valorPosicaoAtual+=Main.valor[i];
        }

        if (valorPosicaoAtual > valorMelhorPosicao) {
            valorMelhorPosicao = valorPosicaoAtual;
            System.arraycopy(posicaoAtual, 0, melhorPosicao, 0, posicaoAtual.length);
        }

    }

    public void atualizarVelocidade() {

        for (int i = 0; i < velocidade.length; i++) {

            velocidade[i] = alfa * velocidade[i]
                    + beta * Math.random() * (melhorPosicao[i] - posicaoAtual[i])
                    + gama * Math.random() * (Nuvem.melhorPosicaoNuvem[i] - posicaoAtual[i]);

            if (velControl) {
                if (velocidade[i] > vmax) {
                    velocidade[i] = vmax;
                } else if (velocidade[i] < -vmax) {
                    velocidade[i] = -vmax;
                }
            }

        }

    }

    public void atualizarPosicao() {

        double s;

        do {
            for (int i = 0; i < posicaoAtual.length; i++) {

                s = (1 / (1 + Math.exp(-velocidade[i])));

                if (Math.random() < s) {
                    posicaoAtual[i] = 1;
                } else {
                    posicaoAtual[i] = 0;
                }
            }
        }while(!validar());

    }

    public ArrayList<Integer> getAtributos() {

        ArrayList<Integer> atributos = new ArrayList<>();
        for (int i = 0; i < posicaoAtual.length; i++) {
            if (posicaoAtual[i] == 0) {
                atributos.add(i + 1);
            }
        }
        return atributos;

    }

    public void setValorPosicaoAtual(double v) {
        valorPosicaoAtual = v;
    }

    public double getValorPosicaoAtual() {
        return valorPosicaoAtual;
    }

    public double getValorMelhorPosicao() {
        return valorMelhorPosicao;
    }

    public int[] getMelhorPosicao() {
        return melhorPosicao;
    }

    public double getPeso(){
        double p=0;
        for (int i = 0; i < Main.nuItens; i++) {
            if (melhorPosicao[i] == 1) p += Main.peso[i];
        }
        return p;
    }

    public double getValor(){
        double v=0;
        for(int i=0; i<Main.nuItens; i++){
            if(posicaoAtual[i] == 1) v+=Main.valor[i];
        }
        return v;
    }

    @Override
    public String toString() {
        return "Particula{ " +
                "posicaoAtual = " + Arrays.toString(posicaoAtual) +
                ", valorPosicaoAtual = " + valorPosicaoAtual +
                '}'+" Peso = "+getPeso();
    }

    @Override
    public int compareTo(Particula o) {

        //if (valorMelhorPosicao != o.getValorMelhorPosicao()) {
            return valorMelhorPosicao.compareTo(o.getValorMelhorPosicao());
        //} else {
        //    Integer thistam = this.getAtributos().size();
        //    Integer anothertam = o.getAtributos().size();
        //    return thistam.compareTo(anothertam);
        //}

    }


}
