package pso;

import sample.Main;

import java.util.ArrayList;
import java.util.Collections;

public class Nuvem {

    static final int QTDPARTICULAS = 100;
    static final int QTDITERACOES = 500;

    private final ArrayList<Particula> particulas;

    static int[] melhorPosicaoNuvem;

    private double valorMelhorPosicaoNuvem;

    public Nuvem() {

        particulas = new ArrayList<>();

        for (int i = 0; i < QTDPARTICULAS; i++) {
            particulas.add(new Particula());
        }

        melhorPosicaoNuvem = new int[Main.nuItens];

    }



    public void executarPSO() {

        System.out.println("------EXECUTANDO PSO------");

        for (int i = 0; i < QTDITERACOES; i++) {

            for (Particula p : particulas) {
                p.avaliarSolucao();
            }

            Collections.sort(particulas, Collections.reverseOrder());

            if (particulas.get(0).getValorMelhorPosicao() > valorMelhorPosicaoNuvem) {
                valorMelhorPosicaoNuvem = particulas.get(0).getValorMelhorPosicao();
                System.arraycopy(particulas.get(0).getMelhorPosicao(), 0, melhorPosicaoNuvem, 0, melhorPosicaoNuvem.length);
            }

            for (Particula p : particulas) {
                p.atualizarVelocidade();
                p.atualizarPosicao();
            }
            System.out.println("Iteracao " + i + " " + particulas.get(0).toString());

        }
        /*
        for (int i=0; i<particulas.size(); i++) {

            System.out.println(particulas.get(i).getValorMelhorPosicao() + particulas.get(i).getValor());
        }
         */

    }


}
