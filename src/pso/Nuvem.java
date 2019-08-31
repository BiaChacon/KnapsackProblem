package pso;

import main.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Nuvem {

    static final int QTDPARTICULAS = 100;
    static final int QTDITERACOES = 500;

    private final ArrayList<Particula> particulas;

    static int[] melhorPosicaoNuvem;

    private double valorMelhorPosicaoNuvem;

    private double melhorPeso;

    public Nuvem() {

        particulas = new ArrayList<>();

        for (int i = 0; i < QTDPARTICULAS; i++) {
            particulas.add(new Particula());
        }

        melhorPosicaoNuvem = new int[Main.nuItens];

    }



    public String executarPSO() {

        System.out.println("------EXECUTANDO PSO------");

        for (int i = 0; i < QTDITERACOES; i++) {

            for (Particula p : particulas) {
                p.avaliarSolucao();
            }

            Collections.sort(particulas, Collections.reverseOrder());

            if (particulas.get(0).getValorMelhorPosicao() > valorMelhorPosicaoNuvem) {
                valorMelhorPosicaoNuvem = particulas.get(0).getValorMelhorPosicao();
                melhorPeso = particulas.get(0).getPeso();
                System.arraycopy(particulas.get(0).getMelhorPosicao(), 0, melhorPosicaoNuvem, 0, melhorPosicaoNuvem.length);
            }

            for (Particula p : particulas) {
                p.atualizarVelocidade();
                p.atualizarPosicao();
            }

            System.out.println("Iteracao " + i + " valor melhor Partiicula " + valorMelhorPosicaoNuvem + "melhor particula"+ Arrays.toString(melhorPosicaoNuvem) + "peso"+ melhorPeso );

        }
        return "Melhor particula = "+Arrays.toString(melhorPosicaoNuvem)+"\n"+"Valor = "+valorMelhorPosicaoNuvem+"\n"+"Peso = "+melhorPeso;
    }


}
