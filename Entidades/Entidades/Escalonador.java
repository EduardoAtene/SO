package Entidades;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Escalonador
 */
public class Escalonador {

    // Lista de Pedidos para Saida
    private static List<PedidosSaida> pedidosSaida = new ArrayList<PedidosSaida>();
    
    // Informações referente a quantidade total de pacote produzido e de produtos
    private static int quantidadeTotalPacoteProduzidos = 0;
    private static int quantidadeTotalProdutos = 0;

    // Informações referente a quantidade total de pacote produzido e de produtos || Antes Meio Dia
    private static int quantidadeTotalPacoteProduzidos1 = 0;
    private static int quantidadeTotalProdutos1 = 0;

    // Tempo em Segundos / Minutos / Horas
    private static Double tempoTotalGastoSegundos = 0.0;
    private static Double tempoTotalGastoMinutos = 0.0;
    private static Double tempoTotalGastoHoras = 0.0;

    private static Double auxVariable = 0.0;

    public static void Escalonador(List<Cliente> pedidos){
        for(int i = 0; i < pedidos.size(); i++){
            Cliente cliente = pedidos.get(i);
            List<Pedido> pedidoCliente = cliente.getPedidos();
            for(int x = 0; x < pedidoCliente.size(); x++){
                PedidosSaida pedidosAux = new PedidosSaida(cliente.getNome(), pedidoCliente.get(x).getPrazoEnpacotament(), 
                                                                            pedidoCliente.get(x).getQuantidadeProdutos(),
                                                                            pedidoCliente.get(x).getTamanhoTotalProduto());
                
                tempoTotalGastoSegundos += pedidosAux.getTempoParaProducaoTotal();
                quantidadeTotalPacoteProduzidos += pedidosAux.getQuantidadePacotesProduzidos();
                quantidadeTotalProdutos += pedidosAux.getQuantidadeProdutos();
                Escalonador.pedidosSaida.add(pedidosAux);
            }
        }

        Duration total = Duration.ofSeconds((int) Math.round(tempoTotalGastoSegundos));
        int horas = total.toHoursPart();
        int minutos = total.toMinutesPart();
        int segundos = total.toSecondsPart();

        tempoTotalGastoMinutos += tempoTotalGastoSegundos / 60;
        tempoTotalGastoHoras += tempoTotalGastoMinutos / 60;

        // InsertShort 
        for (int j = 1; j < Escalonador.pedidosSaida.size(); j++){
            for (int i = j - 1; (i >= 0) && 
                            (
                                 /*Algoritmo FIFO Ordenado Por Clientes */             
                                // false

                                 /*Algoritmo SJF Com Prioridade do Prazo. Os menores prazo primeiro */             
                                // ( (Escalonador.pedidosSaida.get(i+1).getPrazoEnpacotament() > 0 &&  Escalonador.pedidosSaida.get(i).getPrazoEnpacotament() == 0) ||
                                //   (Escalonador.pedidosSaida.get(i).getQuantidadeProdutos() > Escalonador.pedidosSaida.get(i+1).getQuantidadeProdutos()  && ( (Escalonador.pedidosSaida.get(i).getPrazoEnpacotament() == 0 && Escalonador.pedidosSaida.get(i+1).getPrazoEnpacotament() == 0 ) || (Escalonador.pedidosSaida.get(i).getPrazoEnpacotament() > 0 && Escalonador.pedidosSaida.get(i+1).getPrazoEnpacotament() > 0 )  ) ) )
                                
                                /*Algoritmo SJF Com Prioridade do Prazo. Os menores prazo primeiro */             
                                ( (Escalonador.pedidosSaida.get(i+1).getPrazoEnpacotament() > 0 &&  Escalonador.pedidosSaida.get(i).getPrazoEnpacotament() == 0) ||
                                  (Escalonador.pedidosSaida.get(i).getQuantidadeProdutos() > Escalonador.pedidosSaida.get(i+1).getQuantidadeProdutos()  && ( (Escalonador.pedidosSaida.get(i).getPrazoEnpacotament() == 0 && Escalonador.pedidosSaida.get(i+1).getPrazoEnpacotament() == 0 ) || (Escalonador.pedidosSaida.get(i).getPrazoEnpacotament() > 0 && Escalonador.pedidosSaida.get(i+1).getPrazoEnpacotament() > 0 )  ) ) )
                                
                                 /*Algoritmo SJF Sem Preempção */             
                                //(Escalonador.pedidosSaida.get(i).getQuantidadeProdutos() > Escalonador.pedidosSaida.get(i+1).getQuantidadeProdutos()   ) 

                            )
            ; i--){
                // Realizar a troca 
                Escalonador.pedidosSaida.set(i, Escalonador.pedidosSaida.set(i+1, Escalonador.pedidosSaida.get(i)));
           }
        }


        for (int j = 1; j < Escalonador.pedidosSaida.size(); j++){
            auxVariable += pedidosSaida.get(j).getTempoParaProducaoTotal();
            if(auxVariable < 14400){
                quantidadeTotalPacoteProduzidos1 += pedidosSaida.get(j).getQuantidadePacotesProduzidos();
                quantidadeTotalProdutos1 += pedidosSaida.get(j).getQuantidadeProdutos();
            }
        }
    }

    public static void imprimirInformacoes() {
		System.out.println("Tempo Total em Horas: " + tempoTotalGastoHoras);
		System.out.println("Quantidade Total de Pacotes Produzidos: " + quantidadeTotalPacoteProduzidos);
		System.out.println("Quantidade Total de Produtos: " + quantidadeTotalProdutos);
		System.out.println("=======================================================");
        System.out.println("Relatório de Produtos antes das 12h ");
		System.out.println("Quantidade Total de Pacotes Produzidos: " + quantidadeTotalPacoteProduzidos1);
		System.out.println("Quantidade Total de Produtos: " + quantidadeTotalProdutos1);
		System.out.println("=======================================================");


    }

    // public static vo
}