package sample;

import ag.Genetico;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pso.Nuvem;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

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
    private Label labelPSO, labelAG;

    @FXML
    private void executarAG() {

        double knapsackMaxSize = Double.parseDouble(textKnapsackMaxSize.getText());
        Main.knapsackMaxSize = knapsackMaxSize;

        String[] arrayPeso = textPeso.getText().split(",");

        /*for ( String i: arrayPeso ) {
            System.out.println(i);
        }
        System.out.println(Main.knapsackMaxSize);
*/
        double[] peso;
        peso = new double[arrayPeso.length];

        for (int i = 0 ; i < arrayPeso.length; i++){
            peso[i] = Double.parseDouble(arrayPeso[i]);
        }
        Main.peso = peso;

        String[] arrayValor = textValor.getText().split(",");
        double[] valor = new double[arrayPeso.length];
        for (int i = 0 ; i < arrayValor.length; i++){
            valor[i] = Double.parseDouble(arrayValor[i]);
        }
        Main.valor = valor;

        Main.nuItens = peso.length;

        Genetico ag = new Genetico();
        long startTime = System.nanoTime();
        ag.executarAG();

        long endTime = System.nanoTime();
        long timaElapsed = endTime - startTime;
        System.out.println(timaElapsed/1000000);

        labelAG.setText("Tempo AG = "+ timaElapsed/1000000 + " milissegundos");

    }

    @FXML
    private void executarPSO() {

        double knapsackMaxSize = Double.parseDouble(textKnapsackMaxSize.getText());
        Main.knapsackMaxSize = knapsackMaxSize;

        String[] arrayPeso = textPeso.getText().split(",");

        double[] peso;
        peso = new double[arrayPeso.length];

        for (int i = 0 ; i < arrayPeso.length; i++){
            peso[i] = Double.parseDouble(arrayPeso[i]);
        }
        Main.peso = peso;

        String[] arrayValor = textValor.getText().split(",");
        double[] valor = new double[arrayPeso.length];
        for (int i = 0 ; i < arrayValor.length; i++){
            valor[i] = Double.parseDouble(arrayValor[i]);
        }

        Main.valor = valor;

        Main.nuItens = peso.length;

        long startTimeP = System.nanoTime();

        Nuvem nuvemParticulas = new Nuvem();
        nuvemParticulas.executarPSO();

        long endTimeP = System.nanoTime();
        long timaElapsedP = endTimeP - startTimeP;
        System.out.println(timaElapsedP/1000000);
        labelPSO.setText("Tempo PSO = "+ timaElapsedP/1000000 + " milissegundos");

    }

}

