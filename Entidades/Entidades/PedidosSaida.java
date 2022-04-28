package Entidades;

import java.time.Duration;

// import java.util.List;

/**
 * PedidosSaida
 */
public class PedidosSaida {

    private String Nome;
    private int quantidadeProdutos;
    private int prazoEnpacotamento;
    private double tamanhoTotalProduto;

    private int tempoParaProducaoTotal; // Tempo para produção, incluindo todos pacotes e as movimentações da empilhadeira (0.5)
    
    private int tempoParaProducaoTotalSegundos;
    private int tempoParaProducaoTotalMinutos;
    private int tempoParaProducaoTotalHoras;

    private int quantidadePacotesProduzidos;


    public PedidosSaida(String Nome,int prazoEnpacotamento, int quantidadeProdutos,double tamanhoTotalProduto){
        this.Nome = Nome;
        this.quantidadeProdutos = quantidadeProdutos;
        this.prazoEnpacotamento = prazoEnpacotamento;
        this.tamanhoTotalProduto = tamanhoTotalProduto;
        
        double tempoProducaoPacote = Empilhadeira.getTempoProducaoPacote(); // Segundos
        double tamanhoMaximoPacote = Empilhadeira.getTamanhoPacotes();
        double tempoTransicao = Empilhadeira.getTransicao(); // Segundos

        this.quantidadePacotesProduzidos = (int )Math.ceil( this.tamanhoTotalProduto  / tamanhoMaximoPacote);
        this.tempoParaProducaoTotal = (int)(this.quantidadePacotesProduzidos * (tempoProducaoPacote + tempoTransicao));
        
        Duration total = Duration.ofSeconds(this.tempoParaProducaoTotal);
        int horas = total.toHoursPart();
        int minutos = total.toMinutesPart();
        int segundos = total.toSecondsPart();
        
        // int segundos = (int) this.tempoParaProducaoTotal;
        // int horas = segundos / 3600;
        // segundos %= 3600;
        // int minutos = segundos / 60;
        // segundos %= 60;
        this.tempoParaProducaoTotalMinutos = horas;
        this.tempoParaProducaoTotalHoras = minutos;
        this.tempoParaProducaoTotalSegundos = segundos;

    }

    public String  getNome(){
        return this.Nome;
    }

    public int  getQuantidadeProdutos(){
        return this.quantidadeProdutos;
    }
    public int  getPrazoEnpacotament(){
        return this.prazoEnpacotamento;
    }

    public double  getTempoParaProducaoTotal(){
        return this.tempoParaProducaoTotal;
    }
    public int  getQuantidadePacotesProduzidos(){
        return this.quantidadePacotesProduzidos;
    }
    
}