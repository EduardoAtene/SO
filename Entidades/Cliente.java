package Entidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Cliente
 */
public class Cliente {

    private String Nome;
    private List<Pedido> Pedido = new ArrayList<Pedido>();
    
    public Cliente(String nome){
        this.Nome = nome;
    }

    // Getters
    public String  getNome(){
        return this.Nome;
    }
    public List<Pedido>  getPedidos(){
        return this.Pedido;
    }

    public void setPedido( Pedido pedido) {
        this.Pedido.add(pedido);
    }

    public static Cliente clientHasExist(List<Cliente> Clientes,String nome) {
        Cliente newCliente = new Cliente(nome);
        for(int i = 0; i < Clientes.size(); i++){
            Cliente clienteU = Clientes.get(i);
            if(clienteU.Nome == nome)
                return clienteU;
        }
        return newCliente;
    }
}