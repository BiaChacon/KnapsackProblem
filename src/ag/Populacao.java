package ag;

import java.util.ArrayList;
import java.util.Collections;

public class Populacao {

    private final ArrayList<Individuo> individuos = new ArrayList<>();

    public void iniciarPopulacao(int tamPop, int qtdItens) {
        for (int i = 0; i < tamPop; i++) {
            individuos.add(new Individuo(qtdItens));
        }
    }

    public void ordenarPopulacao() {
        Collections.sort(individuos,Collections.reverseOrder());
    }

    public void avaliarPopulacao() {
        for (Individuo i : individuos) {
            i.setAptidao(i.avaliar());
        }

    }

    public Individuo getIndividuo(int pos) {
        return individuos.get(pos);
    }

    public ArrayList<Individuo> getIndividuos() {
        return individuos;
    }

    public void setIndividuo(Individuo individuo) {
        individuos.add(individuo);
    }

    public void setIndividuos(ArrayList<Individuo> filhos) {
        individuos.addAll(filhos);
    }

    public int getNumIndividuos() {
        return individuos.size();
    }

}