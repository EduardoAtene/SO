package Entidades;

import java.io.IOException;

public class Principal {

	public static void main(String args[]) throws IOException {
		String path = "C:/Users/Eduardo Atene/Desktop/Faculdade/SO/Entidades/Entidades/Arquivo/dados.txt"; // Inclua o Diretório do Arquivo. 

		// Leitura do arquivo e no armazenamento 
		Empilhadeira empilhadeira = ManipuladorArquivo.leitor(path);
		
		// Escalonador 
		Escalonador.Escalonador(empilhadeira.getPedidos());
		
		// Relatório
		Escalonador.Relatório();
	}

}