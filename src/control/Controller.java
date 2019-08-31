package control;

import ag.Genetico;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.Main;
import pso.Nuvem;

public class Controller {

    @FXML
    private Button btAG, btPSO, btLimpar;

    @FXML
    private TextField textPeso;

    @FXML
    private TextField textValor;

    @FXML
    private TextField textNuObj;
    @FXML
    private TextField textKnapsackMaxSize;

    @FXML
    private Label labelPSO, labelAG;

    @FXML
    private void executarAG() {

        double knapsackMaxSize = Double.parseDouble(textKnapsackMaxSize.getText());
        Main.knapsackMaxSize = knapsackMaxSize;

        int nuObj = Integer.parseInt(textNuObj.getText());
        Main.nuItens = nuObj;

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

        Genetico ag = new Genetico();
        long startTime = System.nanoTime();
        String resultado = ag.executarAG();

        long endTime = System.nanoTime();
        long timaElapsed = endTime - startTime;
        System.out.println(timaElapsed/1000000);

        labelAG.setText("Tempo = "+ timaElapsed/1000000 + " milissegundos\n"+resultado);

    }

    @FXML
    private void executarPSO() {

        double knapsackMaxSize = Double.parseDouble(textKnapsackMaxSize.getText());
        Main.knapsackMaxSize = knapsackMaxSize;

        int nuObj = Integer.parseInt(textNuObj.getText());
        Main.nuItens = nuObj;

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

        long startTimeP = System.nanoTime();

        Nuvem nuvemParticulas = new Nuvem();
        String resultado = nuvemParticulas.executarPSO();

        long endTimeP = System.nanoTime();
        long timaElapsedP = endTimeP - startTimeP;
        System.out.println(timaElapsedP/1000000);

        labelPSO.setText("Tempo = "+ timaElapsedP/1000000 + " milissegundos\n"+resultado);

    }

    @FXML
    private void limpar(){

        textValor.clear();
        textPeso.clear();
        textNuObj.clear();
        textKnapsackMaxSize.clear();
        labelAG.setText("");
        labelPSO.setText("");

    }

}

