package ag;

import java.util.ArrayList;
import java.util.Random;

public final class Genetico {

    static final double taxaMutacao = 0.5;
    static final double taxaCrossover = 0.9;
    static final boolean eletismo = true;
    static final boolean estagna = true;
    static final double valorEstagna = 100;
    static final int tamPopulacao = 100;
    static final int numMaxGeracoes = 500;

    private Populacao populacao;
    private Individuo melhor;
    private final Random r;
    private int contEstagnar;
    private double melhorAptidaoAnterior;

    public Genetico() {

        r = new Random();
        populacao = new Populacao();
        contEstagnar = 0;
        melhorAptidaoAnterior = -1;

    }

    public void executarAG() {

        System.out.println("------EXECUTANDO AG------");
        int geracao = 0;

        populacao.iniciarPopulacao(tamPopulacao, Principal.nuItens);
        populacao.avaliarPopulacao();
        populacao.ordenarPopulacao();
        melhor = populacao.getIndividuo(0);

        System.out.println("Geracao " + geracao + "| Melhor " + melhor.getAptidao());

        do {
            geracao++;

            populacao = gerarPopulacao();
            populacao.avaliarPopulacao();

            populacao.ordenarPopulacao();
            if (populacao.getIndividuo(0).getAptidao() > melhor.getAptidao()) {
                melhor = populacao.getIndividuo(0);
            }

            System.out.println("Geracao " + geracao + "| Melhor: Valor = " + melhor.getAptidao());

            if (estagna) {
                contaEstagnacao();
                if (contEstagnar >= valorEstagna) {
                    break;
                }
            }

        } while (geracao < numMaxGeracoes);

        System.out.println("Geracao " + geracao + "| Melhor: " + melhor);

    }

    public Populacao gerarPopulacao() {

        Populacao novaPopulacao = new Populacao();

        if (eletismo) novaPopulacao.setIndividuo(melhor);

        while (novaPopulacao.getNumIndividuos() <= tamPopulacao) {
            novaPopulacao.setIndividuos(cruzamento(selecaoTorneioBinario()));
        }

        return novaPopulacao;

    }

    public ArrayList<Individuo> selecaoTorneioBinario() {

        ArrayList<Individuo> pais = new ArrayList<>();
        int a, b;

        for (int i = 0; i < 2; i++) {

            a = r.nextInt(tamPopulacao);
            b = r.nextInt(tamPopulacao);

            if (a < b) {
                pais.add(populacao.getIndividuo(a));
            } else {
                pais.add(populacao.getIndividuo(b));
            }

        }

        return pais;

    }

    public ArrayList<Individuo> cruzamento(ArrayList<Individuo> pais) {

        int[] pai0 = pais.get(0).getGenes();
        int[] pai1 = pais.get(1).getGenes();
        int[] filho0, filho1;

        if (r.nextDouble() > taxaCrossover) {
            filho0 = pai0;
            filho1 = pai1;
        } else {
            int tam = pai0.length;

            filho0 = new int[tam];
            filho1 = new int[tam];

            System.arraycopy(pai0, 0, filho0, 0, tam / 2);
            System.arraycopy(pai0, tam / 2, filho1, tam / 2, tam - tam / 2);
            System.arraycopy(pai1, 0, filho1, 0, tam / 2);
            System.arraycopy(pai1, tam / 2, filho0, tam / 2, tam - tam / 2);
        }

        ArrayList<Individuo> filhos = new ArrayList<>();
        filhos.add(new Individuo(filho0));
        filhos.add(new Individuo(filho1));
        return filhos;

    }

    private void contaEstagnacao() {

        if (melhorAptidaoAnterior == -1 || populacao.getIndividuo(0).getAptidao() != melhorAptidaoAnterior) {
            melhorAptidaoAnterior = populacao.getIndividuo(0).getAptidao();
            contEstagnar = 1;
        } else {
            contEstagnar++;
        }

    }

    public Individuo getMelhor() {
        return melhor;
    }

}