package sample;

import ag.Genetico;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pso.Nuvem;

public class Controller {

    @FXML
    private Button btAG, btPSO;

    @FXML
    private TextField textPeso;

    @FXML
    private TextField textValor;

    @FXML
    private TextField textKnapsackMaxSize;

    @FXML
    private void executarAG() {

        double knapsackMaxSize = Double.parseDouble(textKnapsackMaxSize.getText());
        Main.knapsackMaxSize = knapsackMaxSize;

        String[] arrayPeso = textPeso.getText().split(" ");

        for ( String i: arrayPeso ) {
            System.out.println(i);
        }
        System.out.println(Main.knapsackMaxSize);

        double[] peso;
        peso = new double[arrayPeso.length];

        for (int i = 0 ; i < arrayPeso.length; i++){
            peso[i] = Double.parseDouble(arrayPeso[i]);
        }
        Main.peso = peso;

        String[] arrayValor = textValor.getText().split(" ");
        double[] valor = new double[arrayPeso.length];
        for (int i = 0 ; i < arrayValor.length; i++){
            valor[i] = Double.parseDouble(arrayValor[i]);
        }
        Main.valor = valor;

        Main.nuItens = peso.length;

        Genetico ag = new Genetico();
        ag.executarAG();

    }

    @FXML
    private void executarPSO() {

        double knapsackMaxSize = Double.parseDouble(textKnapsackMaxSize.getText());
        Main.knapsackMaxSize = knapsackMaxSize;

        String[] arrayPeso = textPeso.getText().split(" ");

        for ( String i: arrayPeso ) {
            System.out.println(i);
        }
        System.out.println(Main.knapsackMaxSize);

        double[] peso;
        peso = new double[arrayPeso.length];

        for (int i = 0 ; i < arrayPeso.length; i++){
            peso[i] = Double.parseDouble(arrayPeso[i]);
        }
        Main.peso = peso;

        String[] arrayValor = textValor.getText().split(" ");
        double[] valor = new double[arrayPeso.length];
        for (int i = 0 ; i < arrayValor.length; i++){
            valor[i] = Double.parseDouble(arrayValor[i]);
        }

        Main.valor = valor;

        Main.nuItens = peso.length;

        Nuvem nuvemParticulas = new Nuvem();
        nuvemParticulas.executarPSO();
    }

}

