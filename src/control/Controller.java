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
        long timeElapsed = endTime - startTime;
        System.out.println(timeElapsed/1000000);

        Runtime rt = Runtime.getRuntime();
        String memory = "\nUso memória = "+(rt.totalMemory()-rt.freeMemory())/(1000*1000)+"M";
        System.out.println(memory);
        labelAG.setText("Tempo = "+ timeElapsed/1000000 + " milissegundos\n"+resultado+memory);

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
        long timeElapsedP = endTimeP - startTimeP;
        System.out.println(timeElapsedP/1000000);

        Runtime rt = Runtime.getRuntime();
        String memory = "\nUso memória = "+(rt.totalMemory()-rt.freeMemory())/(1000*1000)+"M";
        System.out.println(memory);
        labelPSO.setText("Tempo = "+ timeElapsedP/1000000 + " milissegundos\n"+resultado+memory);

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

