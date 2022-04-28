package Entidades;

public class Pedido {

    private static double tamanhoProduto = 250;
    private double tamanhoTotalProduto;
    private int quantidadeProdutos;
    private int prazoEnpacotamento;


    // Construtor 

    public Pedido(int quantidadeProdutos, int prazoEnpacotamento){
        this.quantidadeProdutos = quantidadeProdutos;
        this.prazoEnpacotamento = prazoEnpacotamento;
        this.tamanhoTotalProduto = this.quantidadeProdutos * tamanhoProduto;
    }

    // Getters
    public static double  getTamanhoProduto(){
        return tamanhoProduto;
    }
    public double getTamanhoTotalProduto(){
        return this.tamanhoTotalProduto;
    }
    public int  getQuantidadeProdutos(){
        return this.quantidadeProdutos;
    }
    public int  getPrazoEnpacotament(){
        return this.prazoEnpacotamento;
    }
    
}
