package pso;

import java.util.ArrayList;
import java.util.Arrays;

public class Particula implements Comparable<Particula> {

    static final double ALFA = 0.5; //inercia
    static final double BETA = 2.05; //memoria
    static final double GAMA = 2.05; //cooperacao
    static final double VMAX = 6; //velocidade maxima
    static final boolean VELCONTROL = true; //se controla ou nao a velocidade

    private final int[] posicaoAtual;
    private double valorPosicaoAtual;

    private final int[] melhorPosicao;
    private Double valorMelhorPosicao;

    private final double[] velocidade;

    public Particula() {

        posicaoAtual = new int[Principal.nuItens];
        melhorPosicao = new int[Principal.nuItens];
        velocidade = new double[Principal.nuItens];

        valorMelhorPosicao = new Double(0);

        do {
            inicializarPosicao();
        }while(!validar());

        inicializarVelocidade();

    }

    public boolean validar(){

        double p=0;

        for (int i = 0; i < Principal.nuItens; i++) {
            if (posicaoAtual[i] == 1) p += Principal.peso[i];
        }

        if(p <= Principal.knapsackMaxSize){
            return true;
        }else{
            return false;
        }

    }

    //tirar depois
    public double getpeso(){
        double p=0;

        for (int i = 0; i < Principal.nuItens; i++) {
            if (posicaoAtual[i] == 1) p += Principal.peso[i];
        }
        return p;
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

    public ArrayList<Integer> getAtributos() {
        ArrayList<Integer> atributos = new ArrayList<>();
        for (int i = 0; i < posicaoAtual.length; i++) {
            if (posicaoAtual[i] == 0) {
                atributos.add(i + 1);
            }
        }
        return atributos;
    }


    public void avaliarSolucao() {

        double v=0;

        for(int i=0; i<Principal.nuItens; i++){
            if(posicaoAtual[i] == 1) v+=Principal.valor[i];
        }

        if (valorPosicaoAtual > valorMelhorPosicao) {
            valorMelhorPosicao = valorPosicaoAtual;
            System.arraycopy(posicaoAtual, 0, melhorPosicao, 0, posicaoAtual.length);
        }
    }


    public void setValorPosicaoAtual(double v) {
        valorPosicaoAtual = v;
    }

    public void atualizarVelocidade() {
        for (int i = 0; i < velocidade.length; i++) {
            velocidade[i] = ALFA * velocidade[i]
                    + BETA * Math.random() * (melhorPosicao[i] - posicaoAtual[i])
                    + GAMA * Math.random() * (Nuvem.melhorPosicaoNuvem[i] - posicaoAtual[i]);

            if (VELCONTROL) {
                if (velocidade[i] > VMAX) {
                    velocidade[i] = VMAX;
                } else if (velocidade[i] < - VMAX) {
                    velocidade[i] = - VMAX;
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

    public double getValorPosicaoAtual() {
        return valorPosicaoAtual;
    }

    public double getValorMelhorPosicao() {
        return valorMelhorPosicao;
    }

    public int[] getMelhorPosicao() {
        return melhorPosicao;
    }

    @Override
    public String toString() {
        return "Particula{" +
                "posicaoAtual=" + Arrays.toString(posicaoAtual) +
                ", valorPosicaoAtual=" + valorPosicaoAtual +
                '}'+"peso="+getpeso();
    }

    @Override
    public int compareTo(Particula o) {
        if (valorMelhorPosicao != o.getValorMelhorPosicao()) {
            return valorMelhorPosicao.compareTo(o.getValorMelhorPosicao());
        } else {
            Integer thistam = this.getAtributos().size();
            Integer anothertam = o.getAtributos().size();
            return thistam.compareTo(anothertam);
        }
    }

}
