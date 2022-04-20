package Entidades;

public class Pedido {

    private static double tamanhoProduto = 250;
    private int quantidadeProdutos;
    private int prazoEnpacotamento;


    // Construtor 

    public Pedido(int quantidadeProdutos, int prazoEnpacotamento){
        this.quantidadeProdutos = quantidadeProdutos;
        this.prazoEnpacotamento = prazoEnpacotamento;
    }

    // Getters
    public static double  getTamanhoProduto(){
        return tamanhoProduto;
    }
    public int  getQuantidadeProdutos(){
        return this.quantidadeProdutos;
    }
    public int  getPrazoEnpacotament(){
        return this.prazoEnpacotamento;
    }
}
