package pso;

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

        melhorPosicaoNuvem = new int[Principal.nuItens];
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
            resumoIteracao(i);
        }

    }

    private void resumoIteracao(int iteracao) {
        System.out.println("Iteracao " + iteracao + "| Melhor " + valorMelhorPosicaoNuvem);
    }

    public ArrayList<Integer> getMelhorSolucaoNuvem() {
        ArrayList<Integer> selecionados = new ArrayList<>();
        for (int i = 0; i < melhorPosicaoNuvem.length; i++) {
            if (melhorPosicaoNuvem[i] == 0) {
                selecionados.add(i + 1);
            }
        }
        return selecionados;
    }

}
