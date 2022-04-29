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
    private static double tamanhoPacotes = 5000;//A esteira pode produzir pacotes de até 5000 cm³ por vez;
    private static double transicao = 0.5;
    private static double tempoProducaoPacote = 5.0;


    // Getters
    public int  getQuantidadePedidos(){
        return this.quantidadePedidos;
    }
    public List<Cliente>  getPedidos(){
        return this.Pedidos;
    }
    public static double  getTamanhoPacotes(){
        return tamanhoPacotes;
    }

    public static double  getTransicao(){
        return transicao;
    }
    public static double  getTempoProducaoPacote(){
        return tempoProducaoPacote;
    }


    // Method's
    
    public void setQuantidadePedidos(int quantidadePedidos){
        this.quantidadePedidos = quantidadePedidos;
    }

    public void adicionarPedido(String[] linhaA) {
        int clienteHasExist;
        Pedido pedido = new Pedido(Integer.parseInt(linhaA[1]), Integer.parseInt(linhaA[2]));
        clienteHasExist = -1;//Cliente.clientHasExist(this.Pedidos,linhaA[0]); // Caso queira sem a estrutura organizada, incluir o -1
        if (clienteHasExist != -1) {
            Cliente cliente = Pedidos.get(clienteHasExist);
            cliente.setPedido(pedido);
            Pedidos.set(clienteHasExist,cliente);
        }else{
            Cliente cliente = new Cliente(linhaA[0]);
            cliente.setPedido(pedido);
            Pedidos.add(cliente);
        }
        
    }

    // public void inserirPedido( Pedido pedido) {
    //     this.Pedidos.add(pedido);
    // }
}