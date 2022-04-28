package Entidades;

import java.io.IOException;

public class Principal {

	public static void main(String args[]) throws IOException {
		String path = "C:/Users/Eduardo Atene/Desktop/Faculdade/SO/dados.txt";

		Empilhadeira empilhadeira = ManipuladorArquivo.leitor(path);
		Escalonador.Escalonador(empilhadeira.getPedidos());
		Escalonador.imprimirInformacoes();
		// Algoritmo Escalonamento


		// 
	}

}