package sample;

import ag.Genetico;
import ag.Principal;
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
        Principal.knapsackMaxSize = knapsackMaxSize;

        String[] arrayPeso = textPeso.getText().split(" ");

        System.out.println(arrayPeso.length);
        System.out.println(Principal.knapsackMaxSize);

        double[] peso = {};
        for (int i = 0 ; i < arrayPeso.length; i++){
            peso[i] = Double.parseDouble(arrayPeso[i]);
        }
        Principal.peso = peso;

        String[] arrayValor = textValor.getText().split(" ");
        double[] valor = {};
        for (int i = 0 ; i < arrayValor.length; i++){
            valor[i] = Double.parseDouble(arrayValor[i]);
        }
        Principal.valor = valor;

        Principal.nuItens = peso.length;

        Genetico ag = new Genetico();
        ag.executarAG();

    }

    @FXML
    private void executarPSO() {}

}
