package Entidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Empilhadeira
 */
public class Empilhadeira {

    // String Nome;
    private int quantidadePedidos;
    private List<Cliente> Pedidos = new ArrayList<Cliente>();
    
    // Informações Equipamento
    private double tamanhoPacotes = 5000;//A esteira pode produzir pacotes de até 5000 cm³ por vez;
    private double transicao = 0.5;
    private double tempoProducaoPacote = 5.0;


    // Getters
    public int  getQuantidadePedidos(){
        return this.quantidadePedidos;
    }
    public List<Cliente>  getPedidos(){
        return this.Pedidos;
    }
    public double  getTamanhoPacotes(){
        return this.tamanhoPacotes;
    }
    public double  getTransicao(){
        return this.transicao;
    }
    public double  getTempoProducaoPacote(){
        return this.tempoProducaoPacote;
    }


    // Method's
    
    public void setQuantidadePedidos(int quantidadePedidos){
        this.quantidadePedidos = quantidadePedidos;
    }

    public void adicionarPedido(String[] linhaA) {
        Cliente cliente;
        Pedido pedido = new Pedido(Integer.parseInt(linhaA[1]), Integer.parseInt(linhaA[2]));
        cliente = Cliente.clientHasExist(this.Pedidos,linhaA[0]);
        cliente.setPedido(pedido);
        Pedidos.add(cliente);
    }

    // public void inserirPedido( Pedido pedido) {
    //     this.Pedidos.add(pedido);
    // }
}