package ag;


public class Principal {

    // Knapsack related variables
    public static final int nuItens = 10;
    public static final double[] valor = {1,2,10,2,69,5,5,8,1,1};
    public static final double[] peso = {1,2,4,1,1,6,8,2,9,10};
    public static final double knapsackMaxSize = 1;

    public static void main(String[] args) {

        Genetico ag = new Genetico();
        ag.executarAG();
        System.out.println("------PROCESSO CONCLUIDO------");

    }



}