package Entidades;

import java.time.Duration;
import java.util.ArrayList;
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
    private static int tempoTotalGastoSegundos = 0;
    private static int tempoTotalGastoMinutos = 0;
    private static int tempoTotalGastoHoras = 0;

    private static Double tempoDeExecuçãoAux = 0.0;

    // Simulação dupla esteira
    private static List<PedidosSaida> pedidosSaidaDuplo1 = new ArrayList<PedidosSaida>();
    private static List<PedidosSaida> pedidosSaidaDuplo2 = new ArrayList<PedidosSaida>();
    
    private static int[] quantidadeTotalPacoteProduzidosDupla = new int[2];
    private static int[] quantidadeTotalProdutosDupla = new int[2];

    private static int[] quantidadeTotalPacoteProduzidosDupla1 = new int[2];
    private static int[] quantidadeTotalProdutosDupla1 = new int[2];

    // Tempo em Segundos / Minutos / Horas
    private static int[] tempoTotalGastoSegundosDupla = new int[2];
    private static int[] tempoTotalGastoMinutosDupla = new int[2];
    private static int[] tempoTotalGastoHorasDupla = new int[2];

    public static void Escalonador(List<Cliente> pedidos){
        // Armazenar em uma lista, ordenado por Cliente e seus pedido
        // Observação: Não vai estar ordenado de acordo com o arquivo. Ordenado de acordo com o Cliente e seus pedido ( Pois é assim que está sendo armazenado as informações no programa)
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

        // Conveter o tempo total de segundos a horas
        Duration total = Duration.ofSeconds((int) Math.round(tempoTotalGastoSegundos));
        int horas = total.toHoursPart();
        int minutos = total.toMinutesPart();
        int segundos = total.toSecondsPart();

        tempoTotalGastoMinutos = minutos;
        tempoTotalGastoHoras = horas ;
        tempoTotalGastoSegundos = segundos;

        // InsertShort 
        for (int j = 1; j < Escalonador.pedidosSaida.size(); j++){
            for (int i = j - 1; (i >= 0) && 
                            (
                                /*
                                Aqui fica responsável por realizar a condição que irá escalonar os processos.
                                Tem três algoritmos.
                                1- FSCO
                                2- SJF Com prioridade  | Escolhido
                                3- SJF Sem prioridade
                                
                                Escolhido foi o SFJ Com Prioridade.
                                */

                                 /*Algoritmo FCFS |  Ordenado Por Clientes */             
                                // false

                                /*Algoritmo SJF Com Prioridade do Prazo. Os menores prazo primeiro */             
                                ( (Escalonador.pedidosSaida.get(i+1).getPrazoEnpacotament() > 0 &&  Escalonador.pedidosSaida.get(i).getPrazoEnpacotament() == 0) ||
                                  (Escalonador.pedidosSaida.get(i).getQuantidadeProdutos() > Escalonador.pedidosSaida.get(i+1).getQuantidadeProdutos()  && ( (Escalonador.pedidosSaida.get(i).getPrazoEnpacotament() == 0 && Escalonador.pedidosSaida.get(i+1).getPrazoEnpacotament() == 0 ) || (Escalonador.pedidosSaida.get(i).getPrazoEnpacotament() > 0 && Escalonador.pedidosSaida.get(i+1).getPrazoEnpacotament() > 0 )  ) ) )
                                
                                 /*Algoritmo SJF Sem Prioridade */             
                                //(Escalonador.pedidosSaida.get(i).getQuantidadeProdutos() > Escalonador.pedidosSaida.get(i+1).getQuantidadeProdutos()   ) 
                            )
            ; i--){
                // Realizar a troca 
                Escalonador.pedidosSaida.set(i, Escalonador.pedidosSaida.set(i+1, Escalonador.pedidosSaida.get(i)));

           }
        }

        // Gerenciar Tempo 
        Boolean auxDuploEmp = true;
        for (int j = 1; j < Escalonador.pedidosSaida.size(); j++){
            tempoDeExecuçãoAux += pedidosSaida.get(j).getTempoParaProducaoTotal();
            if(tempoDeExecuçãoAux < 14400){
                quantidadeTotalPacoteProduzidos1 += pedidosSaida.get(j).getQuantidadePacotesProduzidos();
                quantidadeTotalProdutos1 += pedidosSaida.get(j).getQuantidadeProdutos();
            }
            
            // Adicionar elemento no Simulador de Dupla Esteira
            if(auxDuploEmp){
                Escalonador.pedidosSaidaDuplo1.add(Escalonador.pedidosSaida.get(j));
                auxDuploEmp = false;
            }else{
                Escalonador.pedidosSaidaDuplo2.add(Escalonador.pedidosSaida.get(j));
                auxDuploEmp = true;
            }
        }

        // Pegar tempo e quantidade total de produtos e pacotes produzidos.

        int tempoDeExecuçãoAux1 = 0;
        int tempoDeExecuçãoAux2 = 0;
        for (int j = 1; j < Escalonador.pedidosSaidaDuplo1.size(); j++){
            quantidadeTotalPacoteProduzidosDupla[0] = pedidosSaidaDuplo1.get(j).getQuantidadePacotesProduzidos();
            quantidadeTotalProdutosDupla[0] = pedidosSaidaDuplo1.get(j).getQuantidadeProdutos();
            tempoTotalGastoSegundosDupla[0] += pedidosSaidaDuplo1.get(j).getTempoParaProducaoTotal();

            tempoDeExecuçãoAux1 += pedidosSaidaDuplo1.get(j).getTempoParaProducaoTotal();
            if(tempoDeExecuçãoAux1 < 14400){
                quantidadeTotalPacoteProduzidosDupla1[0] += pedidosSaidaDuplo1.get(j).getQuantidadePacotesProduzidos();
                quantidadeTotalProdutosDupla1[0] += pedidosSaidaDuplo1.get(j).getQuantidadeProdutos();
            }
        }
                        
        Duration totala1 = Duration.ofSeconds((int) Math.round(tempoTotalGastoSegundosDupla[0] ));
        int horasa1 = totala1.toHoursPart();
        int minutosa1 = totala1.toMinutesPart();
        int segundosa1 = totala1.toSecondsPart();

        tempoTotalGastoMinutosDupla[0] = minutosa1;
        tempoTotalGastoHorasDupla[0] = horasa1 ;
        tempoTotalGastoSegundosDupla[0] = segundosa1;

        // Pegar tempo e quantidade total de produtos e pacotes produzidos.

        for (int j = 1; j < Escalonador.pedidosSaidaDuplo2.size(); j++){
            quantidadeTotalPacoteProduzidosDupla[1] = pedidosSaidaDuplo2.get(j).getQuantidadePacotesProduzidos();
            quantidadeTotalProdutosDupla[1] = pedidosSaidaDuplo2.get(j).getQuantidadeProdutos();
            tempoTotalGastoSegundosDupla[1] += pedidosSaidaDuplo2.get(j).getTempoParaProducaoTotal();
            
            tempoDeExecuçãoAux2 += pedidosSaidaDuplo1.get(j).getTempoParaProducaoTotal();
            if(tempoDeExecuçãoAux2 < 14400){
                quantidadeTotalPacoteProduzidosDupla1[1] += pedidosSaidaDuplo1.get(j).getQuantidadePacotesProduzidos();
                quantidadeTotalProdutosDupla1[1] += pedidosSaidaDuplo1.get(j).getQuantidadeProdutos();
            }
            
        }
        Duration totala2 = Duration.ofSeconds((int) Math.round(tempoTotalGastoSegundosDupla[1]));
        int horasa2 = totala2.toHoursPart();
        int minutosa2 = totala2.toMinutesPart();
        int segundosa2 = totala2.toSecondsPart();

        tempoTotalGastoMinutosDupla[1] = minutosa2;
        tempoTotalGastoHorasDupla[1] = horasa2 ;
        tempoTotalGastoSegundosDupla[1] = segundosa2;
    }

    public static void Relatório() {
		System.out.println("|=======================================================|");
		System.out.println("|======= Algoritmo de SFT com Prioridade ===============|");
		System.out.println("|=======================================================|");
		System.out.println("|Tempo Total: " + tempoTotalGastoHoras + " : "+tempoTotalGastoMinutos+" : "+tempoTotalGastoSegundos);
		System.out.println("|Quantidade Total de Pacotes Produzidos: " + quantidadeTotalPacoteProduzidos);
		System.out.println("|Quantidade Total de Produtos: " + quantidadeTotalProdutos);
		System.out.println("|=======================================================|");
        System.out.println("|========= Relatório de Produtos antes das 12h =========|");
		System.out.println("|Quantidade Total de Pacotes Produzidos: " + quantidadeTotalPacoteProduzidos1);
		System.out.println("|Quantidade Total de Produtos: " + quantidadeTotalProdutos1);
		System.out.println("|=======================================================|");
		System.out.println("|======== Simulação 2 Empilhadeira Dados ===============|");
        System.out.println("|=======================================================|");
        System.out.println("|===================== Esteira 1 =======================|");
		System.out.println("|Tempo Total: " + tempoTotalGastoHorasDupla[0] + " : "+tempoTotalGastoMinutosDupla[0]+" : "+tempoTotalGastoSegundosDupla[0]);
		System.out.println("|Quantidade Total de Pacotes Produzidos: " + quantidadeTotalPacoteProduzidosDupla[0]);
		System.out.println("|Quantidade Total de Produtos: " + quantidadeTotalProdutosDupla[0]);
        System.out.println("|== Relatório de Produtos antes das 12h ==");
		System.out.println("|Quantidade Total de Pacotes Produzidos: " + quantidadeTotalPacoteProduzidosDupla1[0]);
		System.out.println("|Quantidade Total de Produtos: " + quantidadeTotalProdutosDupla1[0]);
        
        System.out.println("|===================== Esteira 2 =======================|");

		System.out.println("|Tempo Total: " + tempoTotalGastoHorasDupla[1] + " : "+tempoTotalGastoMinutosDupla[1]+" : "+tempoTotalGastoSegundosDupla[1]);
        System.out.println("|Quantidade Total de Pacotes Produzidos: " + quantidadeTotalPacoteProduzidosDupla[1]);
		System.out.println("|Quantidade Total de Produtos: " + quantidadeTotalProdutosDupla[1]);
        System.out.println("|== Relatório de Produtos antes das 12h ==");
		System.out.println("|Quantidade Total de Pacotes Produzidos: " + quantidadeTotalPacoteProduzidosDupla1[1]);
		System.out.println("|Quantidade Total de Produtos: " + quantidadeTotalProdutosDupla1[1]);
		System.out.println("|=======================================================|");
		System.out.println("|=======================================================|");


    }

    // public static vo
}