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
    public void setPedido( Pedido pedido,int index) {
        this.Pedido.add(pedido);
    }

    public static int clientHasExist(List<Cliente> Clientes,String nome) {
        for(int i = 0; i < Clientes.size(); i++){
            Cliente clienteU = Clientes.get(i);
            if(nome.equals(clienteU.Nome))
                return i;
        }
        return -1;
    }
}